package net.ibxnjadev.vmessenger.universal.executor;

import net.ibxnjadev.vmessenger.universal.DataInterceptor;
import net.ibxnjadev.vmessenger.universal.Interceptor;

public class DefaultInterceptorExecutor implements InterceptorExecutor {

    @Override
    public <T> void execute(T object, DataInterceptor<T> interceptorData) {
        Interceptor<T> interceptor = interceptorData.getInterceptor();

        if (interceptor.check(object)) {
            interceptor.subscribe(object);
        }

    }

}
