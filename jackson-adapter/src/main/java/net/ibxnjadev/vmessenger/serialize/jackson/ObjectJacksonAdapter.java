package net.ibxnjadev.vmessenger.serialize.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.ibxnjadev.vmesseger.universal.serialize.ObjectSerialize;

import java.io.IOException;

/**
 * Serialize and serialize with Jackson
 */

public class ObjectJacksonAdapter implements ObjectSerialize {

    private final ObjectMapper mapper;

    public ObjectJacksonAdapter(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public ObjectJacksonAdapter() {
        this.mapper = new ObjectMapper();
    }

    @Override
    public <T> String serialize(T object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> T deserialize(Class<T> clazz, String content) {
        try {
            return mapper.readValue(content, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
