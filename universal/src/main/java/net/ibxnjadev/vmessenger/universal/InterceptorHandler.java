package net.ibxnjadev.vmessenger.universal;

/**
 * Manage all interceptors
 */

public interface InterceptorHandler {

    /**
     * Register a Data Interceptor that content interceptor information
     * @param dataInterceptor the dataInterceptor
     */

    <T> void register(DataInterceptor<T> dataInterceptor);

    /**
     * Call all interceptors
     * @param className the class name
     * @param content the content
     */

    <T> void call(String className, String content);

    /**
     * unregister all
     */

    void unregisterAll();

}