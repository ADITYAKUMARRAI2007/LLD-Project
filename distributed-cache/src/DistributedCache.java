import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DistributedCache<K, V> {
    private final List<CacheNode<K, V>> nodes;
    private final DistributionStrategy<K> distributionStrategy;
    private final Database<K, V> database;

    public DistributedCache(List<CacheNode<K, V>> nodes,
                            DistributionStrategy<K> distributionStrategy,
                            Database<K, V> database) {
        if (nodes == null || nodes.isEmpty()) {
            throw new IllegalArgumentException("nodes must not be empty");
        }
        if (distributionStrategy == null) {
            throw new IllegalArgumentException("distributionStrategy must not be null");
        }
        if (database == null) {
            throw new IllegalArgumentException("database must not be null");
        }

        this.nodes = Collections.unmodifiableList(new ArrayList<>(nodes));
        this.distributionStrategy = distributionStrategy;
        this.database = database;
    }

    public V get(K key) {
        CacheNode<K, V> node = distributionStrategy.resolveNode(key, nodes);
        V cachedValue = node.get(key);

        if (cachedValue != null) {
            return cachedValue;
        }

        V dbValue = database.read(key);
        if (dbValue != null) {
            node.put(key, dbValue);
        }
        return dbValue;
    }

    // Assumption: write-through strategy. put updates cache and database.
    public void put(K key, V value) {
        CacheNode<K, V> node = distributionStrategy.resolveNode(key, nodes);
        node.put(key, value);
        database.write(key, value);
    }

    public List<CacheNode<K, V>> getNodes() {
        return nodes;
    }
}
