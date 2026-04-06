import java.util.ArrayList;
import java.util.List;

public final class DistributedCacheFactory {
    public <K, V> DistributedCache<K, V> create(DistributedCacheConfig config,
                                                 DistributionStrategy<K> distributionStrategy,
                                                 Database<K, V> database,
                                                 EvictionPolicyFactory<K> evictionPolicyFactory) {
        List<CacheNode<K, V>> nodes = new ArrayList<>();
        for (int i = 0; i < config.getNodeCount(); i++) {
            nodes.add(new CacheNode<>(
                    "node-" + i,
                    config.getNodeCapacity(),
                    evictionPolicyFactory.createPolicy()
            ));
        }
        return new DistributedCache<>(nodes, distributionStrategy, database);
    }
}
