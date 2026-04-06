import java.util.HashMap;
import java.util.Map;

public final class MapBasedDistributionStrategy<K> implements DistributionStrategy<K> {
    private final Map<K, Integer> routeMap;

    public MapBasedDistributionStrategy(Map<K, Integer> routeMap) {
        if (routeMap == null) {
            throw new IllegalArgumentException("routeMap must not be null");
        }
        this.routeMap = new HashMap<>(routeMap);
    }

    @Override
    public int resolveNodeIndex(K key, int nodeCount) {
        Integer mappedNode = routeMap.get(key);
        if (mappedNode == null) {
            return Math.floorMod(key.hashCode(), nodeCount);
        }
        return Math.floorMod(mappedNode, nodeCount);
    }
}
