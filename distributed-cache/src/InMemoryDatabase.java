import java.util.concurrent.ConcurrentHashMap;

public final class InMemoryDatabase<K, V> implements Database<K, V> {
    private final ConcurrentHashMap<K, V> storage = new ConcurrentHashMap<>();

    @Override
    public V read(K key) {
        return storage.get(key);
    }

    @Override
    public void write(K key, V value) {
        storage.put(key, value);
    }
}
