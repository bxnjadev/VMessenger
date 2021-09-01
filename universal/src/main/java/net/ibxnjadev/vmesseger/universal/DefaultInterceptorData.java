package net.ibxnjadev.vmesseger.universal;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class DefaultInterceptorData<T> implements DataInterceptor<T> {

    private final Class<T> clazz;
    private final Consumer<T> interceptor;
    private final Predicate<T> predicate;

    public DefaultInterceptorData(Class<T> clazz,
                                  Consumer<T> interceptor,
                                  Predicate<T> predicate) {
        this.clazz = clazz;
        this.interceptor = interceptor;
        this.predicate = predicate;
    }

    @Override
    public Class<T> getClazz() {
        return clazz;
    }

    @Override
    public Consumer<T> getInterceptor() {
        return interceptor;
    }

    @Override
    public Optional<Predicate<T>> getPredicate() {
        return Optional.ofNullable(predicate);
    }
}
