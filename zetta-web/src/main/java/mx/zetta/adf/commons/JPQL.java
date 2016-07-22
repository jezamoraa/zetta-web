package mx.zetta.adf.commons;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Query;

public class JPQL implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String SPACE = " ";
    private static final String AND = " AND ";
    private final StringBuilder jpql;
    private final Map<String, Object> params;

    public JPQL() {
        jpql = new StringBuilder();
        params = new HashMap<String, Object>();
    }

    public JPQL(String jpql) {
        this.jpql = new StringBuilder(jpql);
        this.jpql.append("WHERE 1 = 1 ");
        params = new HashMap<String, Object>();
    }

    public JPQL append(String jpqlFragment, String paramName, Object paramValue) {
        jpql.append(SPACE + AND + jpqlFragment + SPACE);
        params.put(paramName, paramValue);
        return this;
    }

    public JPQL append(boolean test, String jpqlFragment, String paramName, Object paramValue) {
        if (test) {
            jpql.append(SPACE + AND + jpqlFragment + SPACE);
            params.put(paramName, paramValue);
        }
        return this;
    }

    public JPQL append(String string) {
        jpql.append(string);
        return this;
    }

    public void setParameters(Query query) {
        params.forEach((key, value) -> query.setParameter(key, value));
    }

    @Override
    public String toString() {
        return jpql.toString();
    }
}