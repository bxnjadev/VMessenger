package net.ibxnjadev.vmessenger.universal.executor;

import net.ibxnjadev.vmessenger.universal.DataInterceptor;

public interface InterceptorExecutor {

    /**
     * This is executed when the message is received
     * @param object the object already serialized
     * @param interceptorData the data interceptor
     */

    <T> void execute(T object, DataInterceptor<T> interceptorData);

}
