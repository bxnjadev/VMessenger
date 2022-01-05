package net.ibxnjadev.vmessenger.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.ibxnjadev.vmessenger.universal.Messenger;
import net.ibxnjadev.vmessenger.universal.message.Message;
import redis.clients.jedis.JedisPubSub;

import java.io.IOException;

public class RedisMessageListener extends JedisPubSub {

    private final Messenger messenger;
    private final ObjectMapper mapper;

    public RedisMessageListener(
            Messenger messenger,
            ObjectMapper mapper
    ) {
        this.messenger = messenger;
        this.mapper = mapper;
    }

    @Override
    public void onMessage(String channel, String content) {
        try {
            System.out.println(content);
            Message message = mapper.readValue(content, Message.class);
            messenger.call(message.getSubChannel(), message.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
