package net.ibxnjadev.vmessenger.universal;

/**
 * The messenger for can send message and intercept
 */

public interface Messenger {

    /**
     * Send the message
     * @param object the object that send
     */

    <T> void sendMessage(T object);

    /**
     * intercept when is received the message
     * @param clazz the object clazz
     * @param interceptor the interceptor
     */

    default <T> void intercept(Class<T> clazz, Interceptor<T> interceptor) {
        getInterceptorHandler().register(new DefaultInterceptorData<>(clazz, interceptor));
    }

    /**
     * This is executed when message is received
     * @param subChannel the subChannel
     * @param content the content message that be will serialized
     */

    default void call(String subChannel, String content) {
        getInterceptorHandler().call(subChannel, content);
    }

    /**
     * unregister all interceptors
     */

    default void unregisterAll() {
        getInterceptorHandler().unregisterAll();
    }

    /**
     * @return the channel name
     */

    String getChannel();

    /**
     * @return the interceptor handler
     */

    InterceptorHandler getInterceptorHandler();

}
