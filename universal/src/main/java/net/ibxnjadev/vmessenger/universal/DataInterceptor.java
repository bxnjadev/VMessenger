package net.ibxnjadev.vmessenger.universal;

/**
 * Contain the data for a interceptor of a object
 */

public interface DataInterceptor<T> {

    /**
     * @return The class object interceptor
     */

    Class<T> getClazz();

    /**
     * @return The interceptor
     */

    Interceptor<T> getInterceptor();

}
