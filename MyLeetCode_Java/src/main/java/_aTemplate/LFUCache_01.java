package _aTemplate;


import java.util.*;


public class LFUCache_01 {

    public class LFUCache {
        class CacheNode {
            int key;
            int value;
            int freq;

            CacheNode(int key, int value, int frequency) {
                this.key = key;
                this.value = value;
                this.freq = frequency;
            }
        }

        // 节点-->桶
        private Map<Integer, LinkedHashSet<CacheNode>> buckets = new HashMap<>();
        // 节点-->Node
        private Map<Integer, CacheNode> cacheMap = new HashMap<>();
        private int minFreq; // 最小出现频率
        private int cap; // 容量

        public LFUCache(int capacity) {
            this.cap = capacity;
            this.minFreq = 0;
        }

        public void put(int key, int value) {
            if (cap <= 0) {
                return;
            }
            CacheNode node = cacheMap.get(key);
            if (node != null) {
                node.value = value;
                increaseFreq(node);
            } else {
                if (cacheMap.size() == cap) {
                    removeMinFreqNode();
                }
                addNewCacheNode(key, value);
            }
        }

        public int get(int key) {
            if (cap <= 0) {
                return -1;
            }
            CacheNode node = cacheMap.get(key);
            if (node != null) {
                increaseFreq(node);
                return node.value;
            }
            return -1;
        }

        private void addNewCacheNode(int key, int value) {
            CacheNode newCacheNode = new CacheNode(key, value, 1);
            LinkedHashSet<CacheNode> set = buckets.computeIfAbsent(1, k -> new LinkedHashSet<>());
            set.add(newCacheNode);
            cacheMap.put(key, newCacheNode);
            minFreq = 1;
        }

        private void removeMinFreqNode() {
            LinkedHashSet<CacheNode> minFreqBu = buckets.get(minFreq);
            CacheNode minFreqNode = minFreqBu.iterator().next();
            cacheMap.remove(minFreqNode.key);
            minFreqBu.remove(minFreqNode);
            if (minFreqBu.size() == 0) {
                buckets.remove(minFreq);
            }
        }

        private void increaseFreq(CacheNode node) {
            int nodeFreq = node.freq;
            LinkedHashSet<CacheNode> bu = buckets.get(node.freq);
            bu.remove(node);
            if (bu.isEmpty()) {
                buckets.remove(nodeFreq);
                if (nodeFreq == minFreq) {
                    minFreq++;
                }
            }
            node.freq++;
            LinkedHashSet<CacheNode> newBu = buckets.computeIfAbsent(node.freq, k -> new LinkedHashSet<>());
            newBu.add(node);
        }
    }


}


