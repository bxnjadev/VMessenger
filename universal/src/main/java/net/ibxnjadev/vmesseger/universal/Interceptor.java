package net.ibxnjadev.vmesseger.universal;

public interface Interceptor<T> {

    void subscribe(T object);

    default boolean check(T object) {
        return true;
    }

}
