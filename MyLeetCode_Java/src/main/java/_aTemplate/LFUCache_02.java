package _aTemplate;


import java.util.HashMap;
import java.util.Map;

public class LFUCache_02 {

    class Item {
        Item l, r;
        int k, v;

        public Item(int _k, int _v) {
            k = _k;
            v = _v;
        }
    }

    class Bucket {
        Bucket l, r;
        int idx;
        Item head, tail;
        Map<Integer, Item> map = new HashMap<>();

        public Bucket(int _idx) {
            idx = _idx;
            head = new Item(-1, -1);
            tail = new Item(-1, -1);
            head.r = tail;
            tail.l = head;
        }

        void put(int key, int value) {
            Item item = null;
            if (map.containsKey(key)) {
                item = map.get(key);
                // 更新值
                item.v = value;
                // 在原来的双向链表位置中移除
                item.l.r = item.r;
                item.r.l = item.l;
            } else {
                item = new Item(key, value);
                // 添加到哈希表中
                map.put(key, item);
            }
            // 增加到双向链表头部
            item.r = head.r;
            item.l = head;
            head.r.l = item;
            head.r = item;
        }

        Item remove(int key) {
            if (map.containsKey(key)) {
                Item item = map.get(key);
                // 从双向链表中移除
                item.l.r = item.r;
                item.r.l = item.l;
                // 从哈希表中移除
                map.remove(key);
                return item;
            }
            return null; // never
        }

        Item clear() {
            // 从双向链表尾部找到待删除的节点
            Item item = tail.l;
            item.l.r = item.r;
            item.r.l = item.l;
            // 从哈希表中移除
            map.remove(item.k);
            return item;
        }

        boolean isEmpty() {
            return map.size() == 0;
        }
    }

    Map<Integer, Bucket> map = new HashMap<>();
    Bucket head, tail;
    int n;
    int cnt;

    public LFUCache_02(int capacity) {
        n = capacity;
        cnt = 0;
        head = new Bucket(-1);
        tail = new Bucket(-1);
        head.r = tail;
        tail.l = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Bucket cur = map.get(key);

            Bucket target = null;
            if (cur.r.idx != cur.idx + 1) {
                // 目标桶空缺
                target = new Bucket(cur.idx + 1);
                target.r = cur.r;
                target.l = cur;
                cur.r.l = target;
                cur.r = target;
            } else {
                target = cur.r;
            }

            // 将当前键值对从当前桶移除，并加入新的桶
            Item remove = cur.remove(key);
            target.put(remove.k, remove.v);
            // 更新当前键值对所在桶信息
            map.put(key, target);

            // 如果在移除掉当前键值对后，当前桶为空，则将当前桶删除（确保空间是 O(n) 的）
            // 也确保调用编号最小的桶的 clear 方法，能够有效移除掉一个元素
            deleteIfEmpty(cur);

            return remove.v;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (n == 0) return;
        if (map.containsKey(key)) {
            // 元素已存在，修改一下值
            Bucket cur = map.get(key);
            cur.put(key, value);
            // 调用一下 get 实现「使用次数」+ 1
            get(key);
        } else {
            // 容器已满，需要先删除元素
            if (cnt == n) {
                // 从第一个桶（编号最小、使用次数最小）中进行清除
                Bucket cur = head.r;
                Item clear = cur.clear();
                map.remove(clear.k);
                cnt--;

                // 如果在移除掉键值对后，当前桶为空，则将当前桶删除（确保空间是 O(n) 的）
                // 也确保调用编号最小的桶的 clear 方法，能够有效移除掉一个元素
                deleteIfEmpty(cur);
            }

            // 需要将当前键值对增加到 1 号桶
            Bucket first = null;

            // 如果 1 号桶不存在则创建
            if (head.r.idx != 1) {
                first = new Bucket(1);
                first.r = head.r;
                first.l = head;
                head.r.l = first;
                head.r = first;
            } else {
                first = head.r;
            }

            // 将键值对添加到 1 号桶
            first.put(key, value);
            // 更新键值对所在桶信息
            map.put(key, first);
            // 计数器加一
            cnt++;
        }
    }

    void deleteIfEmpty(Bucket cur) {
        if (cur.isEmpty()) {
            cur.l.r = cur.r;
            cur.r.l = cur.l;
            cur = null; // help GC
        }
    }
}


