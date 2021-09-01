package net.ibxnjadev.vmesseger.universal.executor;

import net.ibxnjadev.vmesseger.universal.DataInterceptor;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class DefaultInterceptorExecutor implements InterceptorExecutor {

    @Override
    public <T> void execute(T object, DataInterceptor<T> interceptorData) {
        Optional<Predicate<T>> optionalPredicate = interceptorData.getPredicate();

        if (optionalPredicate.isPresent()) {
            Predicate<T> predicate = optionalPredicate.get();

            if (!predicate.test(object)) {
                return;
            }
        }

        Consumer<T> interceptor = interceptorData.getInterceptor();
        interceptor.accept(object);
    }

}
