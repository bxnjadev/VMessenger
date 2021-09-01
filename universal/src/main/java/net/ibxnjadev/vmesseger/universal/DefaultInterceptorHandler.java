package net.ibxnjadev.vmesseger.universal;

import net.ibxnjadev.vmesseger.universal.executor.DefaultInterceptorExecutor;
import net.ibxnjadev.vmesseger.universal.executor.InterceptorExecutor;
import net.ibxnjadev.vmesseger.universal.serialize.ObjectSerialize;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DefaultInterceptorHandler implements InterceptorHandler {

    private final Map<String, CompoundInterceptor<?>> interceptors = new HashMap<>();
    private final InterceptorExecutor interceptorExecutor = new DefaultInterceptorExecutor();
    private final ObjectSerialize objectSerialize;

    public DefaultInterceptorHandler(ObjectSerialize objectSerialize) {
        this.objectSerialize = objectSerialize;
    }

    @Override
    public <T> void register(String subChannel, DataInterceptor<T> dataInterceptor) {

        CompoundInterceptor<T> compoundInterceptor = (CompoundInterceptor<T>) interceptors.get(subChannel);

        if (compoundInterceptor == null) {
            compoundInterceptor = new CompoundInterceptor<>();
            interceptors.put(subChannel, compoundInterceptor);
        }

        compoundInterceptor.register(dataInterceptor);
    }

    @Override
    public <T> void call(String subChannel, String content) {

        CompoundInterceptor<T> compoundInterceptor = (CompoundInterceptor<T>) interceptors.get(subChannel);

        if (compoundInterceptor == null) {
            return;
        }

        for (DataInterceptor<T> interceptor : compoundInterceptor.getInterceptors()) {
            interceptorExecutor.execute(
                    objectSerialize.deserialize(interceptor.getClazz(), content), interceptor);
        }

    }

    @Override
    public void unregisterAll() {
        interceptors.keySet().forEach(interceptors::remove);
    }
}
