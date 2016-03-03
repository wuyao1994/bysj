package com.cuit.web.limit.dialect;
/**
 * oracle的方言实现
 * @author Mx
 *
 */
public class OracleDialect extends Dialect {

    public String getLimitString(String sql, int offset, int limit) {
        sql = sql.trim();
        String forUpdateClause = null;
        boolean isForUpdate = false;
        int forUpdateIndex = sql.toLowerCase().lastIndexOf("for update");
        if (forUpdateIndex > -1) {
            forUpdateClause = sql.substring(forUpdateIndex);
            sql = sql.substring(0, forUpdateIndex - 1);
            isForUpdate = true;
        }
        StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
        if (offset > 0)
            pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");

        pagingSelect.append(sql);
        if (offset > 0) {
            pagingSelect.append(" ) row_ where rownum <= ").append(offset * limit);
            pagingSelect.append(") where rownum_ > ").append((offset - 1) * limit);
        }

        if (isForUpdate) {
            pagingSelect.append(" ");
            pagingSelect.append(forUpdateClause);
        }
        return pagingSelect.toString();
    }

    public boolean supportsLimit() {
        return true;
    }

}
