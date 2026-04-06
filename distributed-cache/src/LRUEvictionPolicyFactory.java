public final class LRUEvictionPolicyFactory<K> implements EvictionPolicyFactory<K> {
    @Override
    public EvictionPolicy<K> createPolicy() {
        return new LRUEvictionPolicy<>();
    }
}
