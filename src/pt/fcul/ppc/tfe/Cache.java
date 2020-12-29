package pt.fcul.ppc.tfe;

import java.util.HashMap;

public class Cache {
    private final HashMap<String, Object> cache;

    public Cache() {
        this.cache = new HashMap<>();
    }

    public boolean contains(String key) {
        return cache.containsKey(key);
    }

    public void put(String key, Object value) {
        if (!contains(key)) {
            cache.put(key, value);
        } else {
            cache.replace(key, value);
        }
    }

    public Object get(String key) {
        return cache.get(key);
    }
}
