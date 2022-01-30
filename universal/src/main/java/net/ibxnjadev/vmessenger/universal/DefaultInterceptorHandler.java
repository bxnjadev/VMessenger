package net.ibxnjadev.vmessenger.universal;

import net.ibxnjadev.vmessenger.universal.executor.DefaultInterceptorExecutor;
import net.ibxnjadev.vmessenger.universal.executor.InterceptorExecutor;
import net.ibxnjadev.vmessenger.universal.serialize.ObjectSerialize;

import java.util.HashMap;
import java.util.Map;

public class DefaultInterceptorHandler implements InterceptorHandler {

    private final Map<String, CompoundInterceptor<?>> interceptors = new HashMap<>();
    private final InterceptorExecutor interceptorExecutor = new DefaultInterceptorExecutor();
    private final ObjectSerialize objectSerialize;

    public DefaultInterceptorHandler(ObjectSerialize objectSerialize) {
        this.objectSerialize = objectSerialize;
    }

    @Override
    public <T> void register(DataInterceptor<T> dataInterceptor) {
        Class<T> clazz = dataInterceptor.getClazz();
        CompoundInterceptor<T> compoundInterceptor = (CompoundInterceptor<T>) interceptors.get(clazz.getSimpleName());

        if (compoundInterceptor == null) {
            compoundInterceptor = new CompoundInterceptor<>();
            interceptors.put(clazz.getSimpleName(), compoundInterceptor);
        }

        compoundInterceptor.register(dataInterceptor);
    }

    @Override
    public <T> void call(String className, String content) {

        CompoundInterceptor<T> compoundInterceptor = (CompoundInterceptor<T>) interceptors.get(className);

        if (compoundInterceptor != null) {
            for (DataInterceptor<T> interceptor : compoundInterceptor.getInterceptors()) {
                interceptorExecutor.execute(
                        objectSerialize.deserialize(interceptor.getClazz(), content), interceptor);
            }
        }

    }

    @Override
    public void unregisterAll() {
        interceptors.keySet().forEach(interceptors::remove);
    }
}
