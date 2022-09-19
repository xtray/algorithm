package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_1206_SkipList {

    // 跳表的节点定义
    static class SkipListNode {
        public int key;
        public List<SkipListNode> nextNodes; // 每一个节点可能有多层链表

        public SkipListNode() {
            nextNodes = new ArrayList<>();
        }

        public SkipListNode(int k) {
            key = k;
            nextNodes = new ArrayList<>();
        }
    }

    public static class Skiplist {
        // 跳表的定义
        private static final double PROBABILITY = 0.5; // < 0.5 继续做，>=0.5 停
        private SkipListNode head;
        private int size;
        private int maxLevel;

        public Skiplist() {
            head = new SkipListNode();
            head.nextNodes.add(null); // 0
            size = 0;
            maxLevel = 0;
        }

        // 从最高层开始，一路找下去，
        // 最终，找到第0层的<key的最右的节点
        private SkipListNode mostRightLessNodeInTree(int key) {
            int level = maxLevel;
            SkipListNode cur = head;
            while (level >= 0) { // 从上层跳下层
                //  cur  level  -> level-1
                cur = mostRightLessNodeInLevel(key, cur, level--);
            }
            return cur;
        }

        // 在level层里，不停的往右, 找到<key最后一个节点并返回
        private SkipListNode mostRightLessNodeInLevel(
                int key,
                SkipListNode cur,
                int level) {
            SkipListNode next = cur.nextNodes.get(level);
            while (next != null && next.key < key) {
                cur = next;
                next = cur.nextNodes.get(level);
            }
            return cur;
        }

        public boolean containsKey(int key) {
            SkipListNode less = mostRightLessNodeInTree(key);
            SkipListNode next = less.nextNodes.get(0);
            return next != null && next.key == key;
        }

        // 新增
        public void put(int key) {
            // 0层上，最右一个，< key 的Node -> >key
            SkipListNode less = mostRightLessNodeInTree(key);
            size++;
            int newNodeLevel = 0;
            while (Math.random() < PROBABILITY) {
                newNodeLevel++;
            }
            // newNodeLevel
            while (newNodeLevel > maxLevel) {
                head.nextNodes.add(null);
                maxLevel++;
            }
            SkipListNode newNode = new SkipListNode(key);
            for (int i = 0; i <= newNodeLevel; i++) {
                newNode.nextNodes.add(null);
            }
            int level = maxLevel;
            SkipListNode pre = head;
            while (level >= 0) {
                // level 层中，找到最右的 < key 的节点
                pre = mostRightLessNodeInLevel(key, pre, level);
                if (level <= newNodeLevel) {
                    newNode.nextNodes.set(level, pre.nextNodes.get(level));
                    pre.nextNodes.set(level, newNode);
                }
                level--;
            }
        }

        public void remove(int key) {
            if (containsKey(key)) {
                size--;
                int level = maxLevel;
                SkipListNode pre = head;
                while (level >= 0) {
                    pre = mostRightLessNodeInLevel(key, pre, level);
                    SkipListNode next = pre.nextNodes.get(level);
                    // 1）在这一层中，pre下一个就是key
                    // 2）在这一层中，pre的下一个key是>要删除key
                    if (next != null && next.key == key) {
                        // free delete node memory -> C++
                        // level : pre -> next(key) -> ...
                        pre.nextNodes.set(level, next.nextNodes.get(level));
                    }
                    // 在level层只有一个节点了，就是默认节点head
                    if (level != 0 && pre == head && pre.nextNodes.get(level) == null) {
                        head.nextNodes.remove(level);
                        maxLevel--;
                    }
                    level--;
                }
            }
        }

        public int size() {
            return size;
        }

        public boolean search(int target) {
            return containsKey(target);
        }

        public void add(int num) {
            put(num);
        }

        public boolean erase(int num) {
            if (!containsKey(num)) return false;
            remove(num);
            return true;
        }
    }


    public static void main(String[] args) {
        Skiplist sl = new Skiplist();
        sl.add(1);
        sl.add(2);
        sl.add(3);

        System.out.println("========END===========");

    }

}
