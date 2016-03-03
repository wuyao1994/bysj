package com.cuit.web.limit.dialect;

import org.springframework.stereotype.Component;

/**
 * mysql的方言实现
 * @author Mx
 *
 */
@Component
public class MysqlDialect extends Dialect {

    public String getLimitString(String sql, int offset, int limit) {
        sql = sql.trim();
        StringBuffer pagingSelect = new StringBuffer(sql.length() + 20);

        pagingSelect.append(sql);
        if (offset > 0) {
            pagingSelect.append(" limit ").append((offset - 1) * limit);
            pagingSelect.append(" , ").append(limit);
        }

        return pagingSelect.toString();
    }

    public boolean supportsLimit() {
        return true;
    }

}
