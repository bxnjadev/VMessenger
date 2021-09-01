package net.ibxnjadev.vmesseger.universal;

/**
 * Manage all interceptors
 */

public interface InterceptorHandler {

    /**
     * Register a Data Interceptor that content interceptor information
     * @param subChannel the subChannel
     * @param dataInterceptor the dataInterceptor
     */

    <T> void register(String subChannel, DataInterceptor<T> dataInterceptor);

    /**
     * Call all interceptors
     * @param subChannel the subChannel
     * @param content the content
     */

    <T> void call(String subChannel, String content);

    /**
     * unregister all
     */

    void unregisterAll();

}