package com.cuit.web.limit;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
import com.cuit.web.util.ReflectUtil;

/**
 * Dao层的所有类共有的接口，提供ibatis的映射访问接口
 * @author Mx
 */
public class SqlMapClientSupport extends SqlMapClientDaoSupport {

    @Autowired
    private SqlMapClient sqlMapClient;

    @Autowired
    private SqlExecutor sqlExecutor;

    @PostConstruct
    public void initSqlMapClient() {
        super.setSqlMapClient(sqlMapClient);
    }

    public SqlExecutor getSqlExecutor() {
        return sqlExecutor;
    }

    public void setSqlExecutor(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    public void setEnableLimit(boolean enableLimit) {
        if (sqlExecutor instanceof LimitSqlExecutor) {
            ((LimitSqlExecutor) sqlExecutor).setEnableLimit(enableLimit);
        }
    }

    /**
     * 初始化重载带数据库方言的sqlExecutor类
     **/
    public void initialize() throws Exception {
        if (sqlExecutor != null) {
            if (sqlMapClient instanceof SqlMapClientImpl) {
                ReflectUtil.setFieldValue(((SqlMapClientImpl) sqlMapClient).getDelegate(), "sqlExecutor", SqlExecutor.class, sqlExecutor);
            }
        }
    }

    /**
     * 无条件的查询,返回单个Object对象
     * @param statementName 查询语句xml引用
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T queryForObject(String statementName) throws Exception {
        return (T) this.queryForObject(statementName, null);
    }

    /**
     * 带条件的查询,返回单个Object对象
     * @param statementName 查询语句xml引用
     * @param parameterObject 查询条件参宿
     * @return
     * @throws Exception
     */
    public <T> T queryForObject(String statementName, Object parameterObject) throws Exception {
        T result = null;

        if (parameterObject != null) {
            result = (T) this.getSqlMapClientTemplate().queryForObject(statementName, parameterObject);
        } else {
            result = (T) this.getSqlMapClientTemplate().queryForObject(statementName);
        }

        return result;
    }

    /**
     * 无条件的查询,返回List对象
     * @param statementName 查询语句xml引用
     * @return
     * @throws Exception
     */
    public <T extends List> T queryForList(String statementName) throws Exception {
        return (T) this.queryForList(statementName, null);
    }

    /**
     * 带条件的查询,返回List对象
     * @param statementName 查询语句xml引用
     * @param parameterObject 查询条件参宿
     * @return
     * @throws Exception
     */
    public <T extends List> T queryForList(String statementName, Object parameterObject) throws Exception {
        T result = null;
        if (parameterObject != null) {
            result = (T) this.getSqlMapClientTemplate().queryForList(statementName, parameterObject);
        } else {
            result = (T) this.getSqlMapClientTemplate().queryForList(statementName);
        }
        return result;
    }

    /**
     * 无条件,带分页条件的查询
     * @param statementName 查询语句xml引用
     * @param currentPage 当前页
     * @param pageSize 每页容量
     * @return
     * @throws Exception
     */
    public <T extends List> T queryForList(String statementName, int currentPage, int pageSize) throws Exception {
        return (T) this.queryForList(statementName, null, currentPage, pageSize);
    }

    /**
     * 带条件,带分页条件的查询
     * @param statementName 查询语句xml引用
     * @param parameterObject 查询条件参数
     * @param currentPage 当前页
     * @param pageSize 每页容量
     * @return
     * @throws Exception
     */
    public <T extends List> T queryForList(String statementName, Object parameterObject, int currentPage, int pageSize) throws Exception {
        T result = null;

        if (parameterObject != null) {
            result = (T) this.getSqlMapClientTemplate().queryForList(statementName, parameterObject, currentPage, pageSize);
        } else {
            result = (T) this.getSqlMapClientTemplate().queryForList(statementName, currentPage, pageSize);
        }

        return result;
    }

    /**
     * 无参数的插入
     * @param statementName 查询语句xml引用
     * @return
     * @throws Exception
     */
    public Object insert(String statementName) throws Exception {
        return this.insert(statementName, null);
    }

    /**
     * 有参数的插入
     * @param statementName 查询语句xml引用
     * @param parameterObject 查询条件参数
     * @return
     * @throws Exception
     */
    public Object insert(String statementName, Object parameterObject) throws Exception {
        Object result = null;

        if (parameterObject != null) {
            result = this.sqlMapClient.insert(statementName, parameterObject);
        } else {
            result = this.sqlMapClient.insert(statementName);
        }

        return result;
    }

    /**
     * 无参数的更新
     * @param statementName 查询语句xml引用
     * @return
     * @throws Exception
     */
    public Object update(String statementName) throws Exception {
        return this.update(statementName, null);
    }

    /**
     * 有参数的更新,由外部程序控制事务
     * @param statementName 查询语句xml引用
     * @param parameterObject 查询条件参数
     * @return
     * @throws Exception
     */
    public Object update(String statementName, Object parameterObject) throws Exception {
        Object result = null;

        if (parameterObject != null) {
            result = this.sqlMapClient.update(statementName, parameterObject);
        } else {
            result = this.sqlMapClient.update(statementName);
        }

        return result;
    }

    /**
     * 无参数的删除
     * @param statementName 查询语句xml引用
     * @return
     * @throws Exception
     */
    public Object delete(String statementName) throws Exception {
        return this.delete(statementName, null);
    }

    /**
     * 有参数的删除,由外部程序控制事务
     * @param statementName 查询语句xml引用
     * @param parameterObject 查询条件参数
     * @return
     * @throws Exception
     */
    public Object delete(String statementName, Object parameterObject) throws Exception {
        Object result = null;

        if (parameterObject != null) {
            result = this.sqlMapClient.delete(statementName, parameterObject);
        } else {
            result = this.sqlMapClient.delete(statementName);
        }

        return result;
    }

    /**
     * 批量插入,外部控制数据
     * @param statementName 查询语句xml引用
     * @param parameterObject 查询条件参数
     * @return
     * @throws Exception
     */
    public void insertForList(String statementName, List list) throws Exception {
        this.sqlMapClient.startBatch();

        int batch = 0;
        for (Object object : list) {
            sqlMapClient.insert(statementName, object);
            batch++;
            // 每1000条批量提交一次。
            if (batch == 1000) {
                sqlMapClient.executeBatch();
                sqlMapClient.startBatch();
                batch = 0;
            }
        }

        this.sqlMapClient.executeBatch();
    }

    /**
     * 批量更新,外部控制数据
     * @param statementName 查询语句xml引用
     * @param parameterObject 查询条件参数
     * @param sqlMapClient 数据库客户端
     * @return
     * @throws Exception
     */
    public void updateForList(String statementName, List list) throws Exception {
        this.sqlMapClient.startBatch();

        int batch = 0;
        for (Object object : list) {
            sqlMapClient.update(statementName, object);
            batch++;
            if (batch == 1000) {
                sqlMapClient.executeBatch();
                sqlMapClient.startBatch();
                batch = 0;
            }
        }

        this.sqlMapClient.executeBatch();
    }

    /**
     * 批量删除,外部控制数据
     * @param statementName 查询语句xml引用
     * @param parameterObject 查询条件参数
     * @param sqlMapClient 数据库客户端
     * @return
     * @throws Exception
     */
    public void deleteForList(String statementName, List list) throws Exception {
        this.sqlMapClient.startBatch();

        int batch = 0;
        for (Object object : list) {
            sqlMapClient.delete(statementName, object);
            batch++;
            // 每1000条批量提交一次。
            if (batch == 1000) {
                sqlMapClient.executeBatch();
                sqlMapClient.startBatch();
                batch = 0;
            }
        }

        this.sqlMapClient.executeBatch();
    }
}
