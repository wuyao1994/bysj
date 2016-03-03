package com.cuit.web.limit.dialect;


/**
 * 方言接口，主要控制分页访问的
 * @author Mx
 *
 */
public abstract class Dialect {
    private static final String MYSQL = "mysql";
    private static final String ORACLE = "oracle";
    public static Dialect create(String productName) throws Exception {
        if (productName.indexOf(MYSQL) != -1) {
            return new MysqlDialect();
        } else if (productName.indexOf(ORACLE) != -1) {
            return new OracleDialect();
        } else {
            throw new Exception("DatabaseType was not supported.");
        }
    }
    /**
     * 是否支持分页
     * @return
     */
    public abstract boolean supportsLimit();

    /**
     * 根据原sql语句，页码和每页行数信息返回分页sql语句
     * @param sql
     * @param offset
     * @param limit
     * @return
     */
    public abstract String getLimitString(String sql, int offset, int limit);
}
