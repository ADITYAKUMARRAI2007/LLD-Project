public interface EvictionPolicyFactory<K> {
    EvictionPolicy<K> createPolicy();
}
