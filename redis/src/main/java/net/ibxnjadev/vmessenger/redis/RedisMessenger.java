package net.ibxnjadev.vmessenger.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.ibxnjadev.vmessenger.universal.DefaultInterceptorHandler;
import net.ibxnjadev.vmessenger.universal.InterceptorHandler;
import net.ibxnjadev.vmessenger.universal.Messenger;
import net.ibxnjadev.vmessenger.universal.message.Message;
import net.ibxnjadev.vmessenger.universal.message.MessageProvider;
import net.ibxnjadev.vmessenger.universal.serialize.ObjectSerialize;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;

public class RedisMessenger implements Messenger {

    private final InterceptorHandler interceptorHandler;
    private final ObjectSerialize objectSerialize;
    private final JedisPool jedisPool;

    private final MessageProvider messageProvider = new MessageProvider();
    private final ObjectMapper mapper;

    private final String channelName;

    public RedisMessenger(String channelName,
                          JedisPool jedisPool,
                          Jedis jedis,
                          ObjectSerialize objectSerialize,
                          ObjectMapper mapper) {
        this(channelName, new DefaultInterceptorHandler(objectSerialize), jedisPool, jedis, objectSerialize, mapper);
    }

    public RedisMessenger(String channelName,
                          InterceptorHandler interceptorHandler,
                          JedisPool jedisPool,
                          Jedis jedis,
                          ObjectSerialize objectSerialize,
                          ObjectMapper mapper) {
        this.interceptorHandler = interceptorHandler;
        this.objectSerialize = objectSerialize;
        this.jedisPool = jedisPool;
        this.channelName = channelName;
        this.mapper = mapper;

        RedisSubscriber subscriber = new RedisSubscriber(channelName, jedis, mapper, this);
    }

    @Override
    public <T> void sendMessage(T object) {
        Message message = new Message(object.getClass().getSimpleName()
                , objectSerialize.serialize(object));

        try (Jedis jedis = jedisPool.getResource()) {
            try {
                String messageString = mapper.writeValueAsString(message);
                jedis.publish(channelName, messageString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public String getChannel() {
        return channelName;
    }

    @Override
    public InterceptorHandler getInterceptorHandler() {
        return interceptorHandler;
    }
}
