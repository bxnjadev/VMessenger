package net.ibxnjadev.vmessenger.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.ibxnjadev.vmessenger.universal.Messenger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Subscribe at redis
 */

public class RedisSubscriber {

    private final String channelName;
    private final Jedis jedis;
    private final ObjectMapper mapper;
    private final Messenger messenger;

    public RedisSubscriber(
            String channelName,
            Jedis jedis,
            ObjectMapper mapper,
            Messenger messenger
    ) {
        this.channelName = channelName;
        this.jedis = jedis;
        this.mapper = mapper;
        this.messenger = messenger;

        subscribe();
    }

    public void subscribe() {
        new Thread(() -> {
            jedis.subscribe(new RedisMessageListener(messenger, mapper), channelName);
        }).start();
    }

}
