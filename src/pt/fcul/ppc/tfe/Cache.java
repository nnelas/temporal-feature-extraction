package pt.fcul.ppc.tfe;

import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final ConcurrentHashMap<String, Object> concurrentCache;

    public Cache() {
        this.concurrentCache = new ConcurrentHashMap<>();
    }

    public boolean contains(String key) {
        return concurrentCache.containsKey(key);
    }

    public void put(String key, Object value) {
        if (!contains(key)) {
            concurrentCache.put(key, value);
        } else {
            concurrentCache.replace(key, value);
        }
    }

    public Object get(String key) {
        return concurrentCache.get(key);
    }
}
