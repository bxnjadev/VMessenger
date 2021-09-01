package net.ibxnjadev.vmessenger.serialize.gson;

import com.google.gson.Gson;
import net.ibxnjadev.vmesseger.universal.serialize.ObjectSerialize;

/**
 * Adapter for Gson
 */

public class GsonAdapter implements ObjectSerialize {

    private final Gson gson;

    public GsonAdapter(
            Gson gson
    ) {
        this.gson = gson;
    }

    public GsonAdapter() {
        this.gson = new Gson();
    }

    @Override
    public <T> String serialize(T object) {
        return gson.toJson(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, String content) {
        return gson.fromJson(content, clazz);
    }

}
