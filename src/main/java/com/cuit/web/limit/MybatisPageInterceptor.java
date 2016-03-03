package com.cuit.web.limit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cuit.web.bean.PagerInfo;
import com.cuit.web.limit.dialect.Dialect;
import com.cuit.web.util.ReflectUtil;

@Intercepts({ @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class }),
@Signature(method = "query", type = Executor.class, args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }) })
public class MybatisPageInterceptor implements Interceptor {
    private static final Logger logger = LoggerFactory.getLogger(MybatisPageInterceptor.class);
    public static Dialect dialect;
    public static final String PAGER_KEY = "_pager_";

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (invocation.getTarget() instanceof StatementHandler) {
            RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
            StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler, "delegate");
            BoundSql boundSql = delegate.getBoundSql();

            if (boundSql.getSql().toLowerCase().indexOf("select") == -1) {
                return invocation.proceed();
            }

            Object parameterObj = boundSql.getParameterObject();
            PagerInfo page = findPageObject(parameterObj);
            if (page == null || !page.supportPage()) {
                return invocation.proceed();
            }

            Connection connection = (Connection) invocation.getArgs()[0];
            if (dialect == null) {
                setDatabaseDialect(connection);
            }
            if (!dialect.supportsLimit()) {
                throw new Exception("This database does not support pager!");
            }

            String sql = boundSql.getSql();
            String pageSql = dialect.getLimitString(sql, page.getPage(), page.getSize());
            ReflectUtil.setFieldValue(boundSql, "sql", String.class, pageSql);

            MappedStatement mappedStatement = (MappedStatement) ReflectUtil.getFieldValue(delegate, "mappedStatement");
            page.setTotal(queryTotalRecord(connection, parameterObj, mappedStatement));

            return invocation.proceed();
        } else {
            Object oriParameterObj = invocation.getArgs()[1];
            PagerInfo page = findPageObject(oriParameterObj);
            invocation.getArgs()[1] = rebindPagerParameter(oriParameterObj, page);
            Object resultObj = invocation.proceed();
            return resultObj;
        }
    }

    /**
     * 根据connection信息设置dialect
     * @param connection
     * @throws Exception
     */
    private void setDatabaseDialect(Connection connection) throws Exception {
        String productName = connection.getMetaData().getDatabaseProductName();
        productName = productName.toLowerCase();
        dialect = Dialect.create(productName);
    }

    /**
     * 从参数中获取分页参数，如果没有，则返回null
     * @param parameterObj
     * @return
     */
    protected PagerInfo findPageObject(Object parameterObj) {
        if (parameterObj instanceof PagerInfo) {
            return (PagerInfo) parameterObj;
        }
        if (parameterObj instanceof Map) {
            Map<?,?> paramsMap = (Map<?, ?>) parameterObj;
            if(paramsMap.containsKey(PAGER_KEY)){
                return (PagerInfo) paramsMap.get(PAGER_KEY);
            }
            if(paramsMap.containsKey("param1") && paramsMap.containsKey("1")){
                for (Object val : paramsMap.values()) {
                    if (val instanceof PagerInfo) {
                        return (PagerInfo) val;
                    }
                }
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * 将分页参数和原来的参数重合混合
     * @param oriParameterObj
     * @param page
     * @return
     * @throws Exception
     */
    protected Object rebindPagerParameter(Object oriParameterObj, PagerInfo page) throws Exception {
        if (oriParameterObj instanceof Map) {
            Map paramsMap = (Map<?, ?>) oriParameterObj;
            if(paramsMap.containsKey("param1")){
                Object param1 = paramsMap.get("param1");
                if(param1 instanceof Map){
                    ((Map) param1).put(PAGER_KEY, page);
                    return param1;
                }else{
                    throw new Exception("pager info cannot be rebind to Java class except map.");
                }
            } else {
                paramsMap.put(PAGER_KEY, page);
                return paramsMap;
            }
        }
        if(page == null){
            return oriParameterObj;
        }
        if (oriParameterObj instanceof PagerInfo) {
            return oriParameterObj;
        }
        throw new Exception("pager info cannot be rebind to Java class except map.");
    }

    protected Long queryTotalRecord(Connection connection, Object parameterObject, MappedStatement mappedStatement) throws Exception {
        BoundSql boundSql = mappedStatement.getBoundSql(parameterObject);
        String sql = boundSql.getSql();
        String countSql = this.buildCountSql(sql);

        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings, parameterObject);
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, countBoundSql);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(countSql);
            parameterHandler.setParameters(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                long totalRecord = rs.getLong(1);
                return totalRecord;
            }
        }catch(Exception e){
            throw e;
        }finally {
            if (rs != null){
                try {
                    rs.close();
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
            if (pstmt != null){
                try {
                    pstmt.close();
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
        }
        logger.error("Failt to get pager total count.");
        return 0L;
    }

    /**
     * 根据原Sql语句获取对应的查询总记录数的Sql语句
     * @param sql
     * @return
     */
    private String buildCountSql(String sql) {
        return "select count(*) from (" + sql +") a";
    }
}
