package mx.zetta.adf.commons;

import java.util.List;
import java.util.Objects;

import javax.persistence.TypedQuery;

public class JPAUtils {
    /** Atributo para establecer el modo de recuperacion del Cache */
    public static final String CACHE_RETRIEVE_MODE = "javax.persistence.cache.retrieveMode";
    /** Modo de recuperacion del Cache ByPass, NO utiliza los datos del Cache */
    public static final String CACHE_RETRIEVE_MODE_BYPASS = "BYPASS";
    /** Modo de recuperacion del Cache Use, SI utiliza los datos del Cache */
    public static final String CACHE_RETRIEVE_MODE_USE = "USE";
    /**
     * Atributo para establecer el modo en como se almacenara el dato recuperado
     * en el Cache
     */
    public static final String CACHE_STORE_MODE = "javax.persistence.cache.storeMode";
    /**
     * Modo de Almacenamiento del Cache ByPass, NO actualizada los datos del
     * Cache
     */
    public static final String CACHE_STORE_MODE_BYPASS = "BYPASS";
    /**
     * Modo de Almacenamiento del Cache Refresh, SI actualizada los datos del
     * Cache posterir a recuperar los datos del almacen
     */
    public static final String CACHE_STORE_MODE_REFRESH = "REFRESH";
    /**
     * Modo de Almacenamiento del Cache Use, utiliza los datos del Cache si el
     * datos se encuentra alli
     */
    public static final String CACHE_STORE_MODE_USE = "USE";

    private JPAUtils() {

    }

    /**
     * Retorna el primer objeto de la lista de parametros, si la lista es nula o
     * esta vacia retornara <code>null</code>
     * 
     * @return
     * 
     * @return El primer objeto de la lista
     */
    public static <T> T getSingleResult(TypedQuery<T> query) {
        if (Objects.isNull(query)) {
            return null;
        }
        query.setMaxResults(1);
        final List<T> result = query.getResultList();
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);

    }

    public static StringBuilder whereAnd(StringBuilder jpql) {
        if (jpql.toString().toUpperCase().indexOf("WHERE") == -1) {
            jpql.append(" WHERE ");
        } else {
            jpql.append(" AND ");
        }
        return jpql;
    }

    public static <T> void setNOCache(TypedQuery<T> query) {
        /*- NO usa cache */
        query.setHint(JPAUtils.CACHE_RETRIEVE_MODE, JPAUtils.CACHE_RETRIEVE_MODE_BYPASS);
        /*- NO almacena cache */
        query.setHint(JPAUtils.CACHE_STORE_MODE, JPAUtils.CACHE_STORE_MODE_BYPASS);
    }
}
