import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        DistributedCacheConfig config = DistributedCacheConfig.builder()
                .nodeCount(3)
                .nodeCapacity(2)
                .build();

        InMemoryDatabase<String, String> database = new InMemoryDatabase<>();
        database.write("user:1", "Alice");
        database.write("user:2", "Bob");
        database.write("user:3", "Charlie");

        DistributedCacheFactory factory = new DistributedCacheFactory();
        DistributedCache<String, String> cache = factory.create(
                config,
                new ModuloDistributionStrategy<>(),
                database,
                new LRUEvictionPolicyFactory<>()
        );

        System.out.println("=== Cache Miss Flow ===");
        System.out.println("get(user:1) => " + cache.get("user:1")); // miss -> DB -> cache
        System.out.println("get(user:1) => " + cache.get("user:1")); // hit

        System.out.println("\n=== Put and Distribution ===");
        cache.put("user:100", "Daisy");
        cache.put("user:101", "Eve");
        cache.put("user:102", "Frank");
        printNodeSizes(cache);

        System.out.println("\n=== Switch Distribution Strategy (without cache core changes) ===");
        Map<String, Integer> routeMap = new HashMap<>();
        routeMap.put("vip:1", 0);
        routeMap.put("vip:2", 1);

        DistributedCache<String, String> mapRoutedCache = factory.create(
                config,
                new MapBasedDistributionStrategy<>(routeMap),
                database,
                new LRUEvictionPolicyFactory<>()
        );
        mapRoutedCache.put("vip:1", "Premium-A");
        mapRoutedCache.put("vip:2", "Premium-B");
        System.out.println("get(vip:1) => " + mapRoutedCache.get("vip:1"));
        System.out.println("get(vip:2) => " + mapRoutedCache.get("vip:2"));
    }

    private static void printNodeSizes(DistributedCache<String, String> cache) {
        for (CacheNode<String, String> node : cache.getNodes()) {
            System.out.println(node.getNodeId() + " size=" + node.size());
        }
    }
}
