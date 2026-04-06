import java.util.Iterator;
import java.util.LinkedHashSet;

public final class LRUEvictionPolicy<K> implements EvictionPolicy<K> {
    private final LinkedHashSet<K> accessOrder = new LinkedHashSet<>();

    @Override
    public synchronized void onKeyAccess(K key) {
        accessOrder.remove(key);
        accessOrder.add(key);
    }

    @Override
    public synchronized void onKeyInsert(K key) {
        accessOrder.remove(key);
        accessOrder.add(key);
    }

    @Override
    public synchronized void onKeyRemove(K key) {
        accessOrder.remove(key);
    }

    @Override
    public synchronized K evictCandidate() {
        Iterator<K> iterator = accessOrder.iterator();
        return iterator.hasNext() ? iterator.next() : null;
    }
}
