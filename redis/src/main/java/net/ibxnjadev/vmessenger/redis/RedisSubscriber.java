package net.ibxnjadev.vmessenger.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.ibxnjadev.vmesseger.universal.Messenger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Subscribe at redis
 */

public class RedisSubscriber {

    private final String channelName;
    private final JedisPool jedisPool;
    private final ObjectMapper mapper;
    private final Messenger messenger;

    public RedisSubscriber(
            String channelName,
            JedisPool jedisPool,
            ObjectMapper mapper,
            Messenger messenger
    ) {
        this.channelName = channelName;
        this.jedisPool = jedisPool;
        this.mapper = mapper;
        this.messenger = messenger;

        subscribe();
    }

    public void subscribe() {
        new Thread(() -> {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.subscribe(new RedisMessageListener(messenger, mapper), channelName);
            }
        }).start();
    }

}
