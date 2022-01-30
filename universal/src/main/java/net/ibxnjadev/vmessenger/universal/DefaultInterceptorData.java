package net.ibxnjadev.vmessenger.universal;

public class DefaultInterceptorData<T> implements DataInterceptor<T> {

    private final Class<T> clazz;
    private final Interceptor<T> interceptor;

    public DefaultInterceptorData(Class<T> clazz, Interceptor<T> interceptor) {
        this.clazz = clazz;
        this.interceptor = interceptor;
    }

    @Override
    public Class<T> getClazz() {
        return clazz;
    }

    @Override
    public Interceptor<T> getInterceptor() {
        return interceptor;
    }

}
