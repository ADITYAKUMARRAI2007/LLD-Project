import java.util.List;

public interface DistributionStrategy<K> {
    int resolveNodeIndex(K key, int nodeCount);

    default <V> CacheNode<K, V> resolveNode(K key, List<CacheNode<K, V>> nodes) {
        if (nodes == null || nodes.isEmpty()) {
            throw new IllegalArgumentException("nodes must not be empty");
        }
        int index = resolveNodeIndex(key, nodes.size());
        return nodes.get(index);
    }
}
