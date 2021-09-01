package net.ibxnjadev.vmesseger.universal;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * The messenger for can send message and intercept
 */

public interface Messenger {

    /**
     * Send the message
     * @param subChannel the subChannel
     * @param object the object that send
     */

    <T> void sendMessage(String subChannel, T object);

    /**
     * intercept when is received the message
     * @param subChannel the subChannel
     * @param clazz the object clazz
     * @param interceptor the interceptor
     */

    default <T> void intercept(String subChannel, Class<T> clazz, Consumer<T> interceptor) {
        intercept(subChannel, clazz, null, interceptor);
    }

    /**
     * intercept when is received the message
     * @param subChannel the subChannel
     * @param predicate this check if condition and execute the interceptor
     * @param interceptor the interceptor
     */

    <T> void intercept(String subChannel, Class<T> clazz, Predicate<T> predicate, Consumer<T> interceptor);

    /**
     * This is executed when message is received
     * @param subChannel the subChannel
     * @param content the content message that be will serialized
     */

    default void call(String subChannel, String content) {
        getInterceptorHandler()
                .call(subChannel, content);
    }

    /**
     * unregister all interceptors
     */

    void unregisterAll();

    /**
     * @return the channel name
     */

    String getChannelName();

    /**
     * @return the interceptor handler
     */

    InterceptorHandler getInterceptorHandler();

}
