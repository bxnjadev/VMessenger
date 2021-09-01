package net.ibxnjadev.vmessenger.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.ibxnjadev.vmesseger.universal.DefaultInterceptorHandler;
import net.ibxnjadev.vmesseger.universal.InterceptorHandler;
import net.ibxnjadev.vmesseger.universal.Messenger;
import net.ibxnjadev.vmesseger.universal.message.Message;
import net.ibxnjadev.vmesseger.universal.message.MessageProvider;
import net.ibxnjadev.vmesseger.universal.serialize.ObjectSerialize;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class RedisMessenger implements Messenger {

    private final InterceptorHandler interceptorHandler;
    private final ObjectSerialize objectSerialize;
    private final JedisPool jedisPool;

    private final MessageProvider messageProvider = new MessageProvider();
    private final ObjectMapper mapper = new ObjectMapper();

    private final String channelName;

    public RedisMessenger(String channelName,
                          JedisPool jedisPool,
                          ObjectSerialize objectSerialize) {
        this(channelName, new DefaultInterceptorHandler(objectSerialize), jedisPool, objectSerialize);
    }

    public RedisMessenger(String channelName,
                          InterceptorHandler interceptorHandler,
                          JedisPool jedisPool,
                          ObjectSerialize objectSerialize) {
        this.interceptorHandler = interceptorHandler;
        this.objectSerialize = objectSerialize;
        this.jedisPool = jedisPool;
        this.channelName = channelName;

        RedisSubscriber subscriber = new RedisSubscriber(channelName, jedisPool, mapper, this);
    }

    @Override
    public <T> void sendMessage(String subChannel, T object) {
        Message message = new Message(subChannel, objectSerialize.serialize(object));

        try (Jedis jedis = jedisPool.getResource()) {
            try {
                jedis.publish(channelName, mapper.writeValueAsString(message));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public String getChannelName() {
        return channelName;
    }

    @Override
    public InterceptorHandler getInterceptorHandler() {
        return interceptorHandler;
    }
}
