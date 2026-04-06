import java.util.HashMap;
import java.util.Map;

public final class CacheNode<K, V> {
    private final String nodeId;
    private final int capacity;
    private final EvictionPolicy<K> evictionPolicy;
    private final Map<K, V> storage;

    public CacheNode(String nodeId, int capacity, EvictionPolicy<K> evictionPolicy) {
        if (nodeId == null || nodeId.isEmpty()) {
            throw new IllegalArgumentException("nodeId must not be empty");
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be > 0");
        }
        if (evictionPolicy == null) {
            throw new IllegalArgumentException("evictionPolicy must not be null");
        }
        this.nodeId = nodeId;
        this.capacity = caPpacity;
        this.evictionPolicy = evictionPolicy;
        this.storage = new HashMap<>();
    }

    public synchronized V get(K key) {
        V value = storage.get(key);
        if (value != null) {
            evictionPolicy.onKeyAccess(key);
        }
        return value;
    }

    public synchronized void put(K key, V value) {
        if (storage.containsKey(key)) {
            storage.put(key, value);
            evictionPolicy.onKeyAccess(key);
            return;
        }

        if (storage.size() >= capacity) {
            K evictKey = evictionPolicy.evictCandidate();
            if (evictKey != null) {
                storage.remove(evictKey);
                evictionPolicy.onKeyRemove(evictKey);
            }
        }

        storage.put(key, value);
        evictionPolicy.onKeyInsert(key);
    }

    public synchronized int size() {
        return storage.size();
    }

    public String getNodeId() {
        return nodeId;
    }
}
