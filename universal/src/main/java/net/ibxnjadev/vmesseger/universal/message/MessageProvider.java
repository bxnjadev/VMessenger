package net.ibxnjadev.vmesseger.universal.message;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class MessageProvider {

    private final ObjectMapper mapper;

    public MessageProvider(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public MessageProvider() {
        mapper = new ObjectMapper();
    }

    public Message get(String content) {
        try {
            return mapper.readValue(content, Message.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
