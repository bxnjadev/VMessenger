package net.ibxnjadev.vmesseger.universal.executor;

import net.ibxnjadev.vmesseger.universal.DataInterceptor;

public interface InterceptorExecutor {

    /**
     * This is executed when the message is received
     * @param object the object already serialized
     * @param interceptorData the data interceptor
     */

    <T> void execute(T object, DataInterceptor<T> interceptorData);

}
