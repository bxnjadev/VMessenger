package net.ibxnjadev.vmesseger.universal.executor;

import net.ibxnjadev.vmesseger.universal.DataInterceptor;
import net.ibxnjadev.vmesseger.universal.Interceptor;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class DefaultInterceptorExecutor implements InterceptorExecutor {

    @Override
    public <T> void execute(T object, DataInterceptor<T> interceptorData) {
        Interceptor<T> interceptor = interceptorData.getInterceptor();

        if (interceptor.check(object)) {
            interceptor.subscribe(object);
        }

    }

}
