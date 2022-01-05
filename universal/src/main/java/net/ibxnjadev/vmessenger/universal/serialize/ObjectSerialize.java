package net.ibxnjadev.vmessenger.universal.serialize;

/**
 * Serialize and deserialize a object
 */

public interface ObjectSerialize {

    /**
     * Serialize a object in String
     * @param object the object
     * @return the serialized object at String
     */

    <T> String serialize(T object);

    /**
     * Deserialize a String to object
     * @param clazz the clazz
     * @param content the content
     * @return the object deserialize
     */

    <T> T deserialize(Class<T> clazz, String content);

}
