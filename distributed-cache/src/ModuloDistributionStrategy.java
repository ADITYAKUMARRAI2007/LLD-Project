public final class ModuloDistributionStrategy<K> implements DistributionStrategy<K> {
    @Override
    public int resolveNodeIndex(K key, int nodeCount) {
        if (nodeCount <= 0) {
            throw new IllegalArgumentException("nodeCount must be > 0");
        }
        return Math.floorMod(key.hashCode(), nodeCount);
    }
}
