package net.ibxnjadev.vmessenger.universal;

import java.util.HashSet;
import java.util.Set;

/**
 * Store the interceptors based in class
 */

public class CompoundInterceptor<T> {

    /**
     * The interceptors
     */

    private final Set<DataInterceptor<T>> interceptors;

    public CompoundInterceptor() {
        interceptors = new HashSet<>();
    }

    /**
     * Register a data interceptor
     * @param interceptor the interceptor
     */

    public void register(DataInterceptor<T> interceptor) {
        interceptors.add(interceptor);
    }

    /**
     * @return the all interceptors
     */

    public Set<DataInterceptor<T>> getInterceptors() {
        return interceptors;
    }

}
