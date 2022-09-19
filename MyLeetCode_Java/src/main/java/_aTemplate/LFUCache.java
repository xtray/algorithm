package _aTemplate;


import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCache<K, V> {

    class CacheNode {
        K key;
        V value;
        int frequency;

        CacheNode(K key, V value, int frequency) {
            this.key = key;
            this.value = value;
            this.frequency = frequency;
        }
    }

    private Map<Integer, LinkedHashSet<CacheNode>> frequencyTable = new HashMap<>();
    private Map<K, CacheNode> cacheTable = new HashMap<>();
    private int minFrequency;
    private int capacity;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFrequency = 0;
    }

    public void put(K key, V value) {
        if (capacity <= 0) {
            return;
        }
        CacheNode node = cacheTable.get(key);
        if (node != null) {
            node.value = value;
            increaseFrequency(node);
        } else {
            if (cacheTable.size() == capacity) {
                removeMinFrequencyNode();
            }
            addNewCacheNode(key, value);
        }
    }

    public V get(K key) {
        if (capacity <= 0) {
            return null;
        }
        CacheNode node = cacheTable.get(key);
        if (node != null) {
            increaseFrequency(node);
            return node.value;
        }
        return null;
    }

    private void addNewCacheNode(K key, V value) {
        CacheNode newCacheNode = new CacheNode(key, value, 1);
        LinkedHashSet<CacheNode> set = frequencyTable.computeIfAbsent(1, k -> new LinkedHashSet<>());
        set.add(newCacheNode);
        cacheTable.put(key, newCacheNode);
        minFrequency = 1;
    }

    private void removeMinFrequencyNode() {
        LinkedHashSet<CacheNode> minFrequencySet = frequencyTable.get(minFrequency);
        CacheNode minFrequencyNode = minFrequencySet.iterator().next();
        cacheTable.remove(minFrequencyNode.key);
        minFrequencySet.remove(minFrequencyNode);
        if (minFrequencySet.size() == 0) {
            frequencyTable.remove(minFrequency);
        }
    }

    private void increaseFrequency(CacheNode node) {
        int of = node.frequency;
        LinkedHashSet<CacheNode> set = frequencyTable.get(node.frequency);
        set.remove(node);
        if (set.isEmpty()) {
            frequencyTable.remove(of);
            if (of == minFrequency) {
                minFrequency++;
            }
        }
        int nf = node.frequency + 1;
        node.frequency++;
        LinkedHashSet<CacheNode> newSet = frequencyTable.computeIfAbsent(nf, k -> new LinkedHashSet<>());
        newSet.add(node);
    }

}


