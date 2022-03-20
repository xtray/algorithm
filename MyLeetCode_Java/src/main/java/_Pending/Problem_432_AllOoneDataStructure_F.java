//
// 2022-03-16 11:29:52
// https://leetcode-cn.com/problems/all-oone-data-structure/

package _Pending;

import java.util.HashMap;
import java.util.Map;

/**
 * Not Pass!
 */

public class Problem_432_AllOoneDataStructure_F {

    // 节点的数据结构
    public class ListNode{
        public String value; // 字符串
        public Integer times; // 字符串出现的次数
        public ListNode pre;
        public ListNode next;
        public ListNode(String v, Integer t) {
            value = v;
            times = t;
            pre = null;
            next = null;
        }
    }

    // 桶的数据结构
    public class Bucket{
        Integer times; // 桶的词频
        ListNode head; // 桶内的头节点
        ListNode tail; // 桶内的尾节点
        Bucket last; // 桶之间是双向链表所以有前一个桶
        Bucket next; // 桶之间是双向链表所以有后一个桶
        public Bucket(ListNode node) {
            head = node;
            tail = node;
            times = node.times;
        }

        // 把一个新的节点加入这个桶，新的节点都放在顶端变成新的头部
        public void addNodeFromHead(ListNode newHeadNode) {
            newHeadNode.next = head;
            head.pre = newHeadNode;
            head = newHeadNode;
        }

        // 判断桶是不是空的
        public boolean isEmpty() {
            return head == null;
        }

        // 删除node节点并保证node的上下环境重新连接
        public void removeNode(ListNode node) {
            if(head == tail) {
                head = null;
                tail = null;
            } else {
                if (node == head) {
                    head = head.next;
                    head.pre = null;
                } else if(node == tail) {
                    tail = tail.pre;
                    tail.next = null;
                } else {
                    node.pre.next = node.next;
                    node.next.pre = node.pre;
                }
            }
            node.pre = null;
            node.next = null;
        }
    }

    public class AllOne {

        private Map<String, ListNode> records; // 表示key(String)由哪个节点(Node)代表
        private Map<ListNode, Bucket> heads; // 表示节点(Node)在哪个桶(Bucket)里
        private Bucket headList; // 整个结构中位于最左的桶
        private Bucket tailList; // 整个结构中位于最左的桶

        public AllOne() {
            records = new HashMap<>();
            heads = new HashMap<>();
            headList = null;
            tailList = null;
        }

        // removeBucket：刚刚减少了一个节点的桶
        // 这个函数的功能是，判断刚刚减少了一个节点的桶是不是已经空了。
        // 1）如果不空，什么也不做
        //
        // 2)如果空了，removeBucket还是整个缓存结构最左的桶(headList)。
        // 删掉这个桶的同时也要让最左的桶变成removeBucket的下一个。
        //
        // 3)如果空了，removeBucket不是整个缓存结构最左的桶(headList)。
        // 把这个桶删除，并保证上一个的桶和下一个桶之间还是双向链表的连接方式
        //
        // 函数的返回值表示刚刚减少了一个节点的桶是不是已经空了，空了返回true；不空返回false
        private boolean modifyHeadList(Bucket removeBucket) {
            if (removeBucket.isEmpty()) {
                if (headList == removeBucket) {
                    headList = removeBucket.next;
                    if (headList != null) {
                        headList.last = null;
                    }
                } else {
                    removeBucket.last.next = removeBucket.next;
                    if (removeBucket.next != null) {
                        removeBucket.next.last = removeBucket.last;
                    }
                }
                return true;
            }
            return false;
        }

        // 从当前桶里拿出node, 放到新的桶里
        private void addMove(ListNode node, Bucket oldBucket) {
            // 从旧桶里删掉, 挪到新桶里
            oldBucket.removeNode(node);
            // preList表示次数+1的桶的前一个桶是谁
            // 如果oldBucket删掉node之后还有节点，oldBucket就是次数+1的桶的前一个桶
            // 如果oldBucket删掉node之后空了，oldBucket是需要删除的，所以次数+1的桶的前一个桶，是oldBucket的前一个
            Bucket preBucket = modifyHeadList(oldBucket) ? oldBucket.last : oldBucket;
            // nextBu表示次数+1的桶的后一个桶是谁
            Bucket nextBucket = oldBucket.next;
            if (nextBucket == null) {
                Bucket newBucket = new Bucket(node);
                if (preBucket != null) {
                    preBucket.next = newBucket;
                }
                newBucket.last = preBucket;
                if (headList == null) {
                    headList = newBucket;
                }
                heads.put(node, newBucket);
            } else {
                if (nextBucket.head.times.equals(node.times)) {
                    nextBucket.addNodeFromHead(node);
                    heads.put(node, nextBucket);
                } else {
                    Bucket newBucket = new Bucket(node);
                    if (preBucket != null) {
                        preBucket.next = newBucket;
                    }
                    newBucket.last = preBucket;
                    newBucket.next = nextBucket;
                    nextBucket.last = newBucket;
                    if (headList == nextBucket) {
                        headList = newBucket;
                    }
                    heads.put(node, newBucket);
                }
            }
        }

        // 从当前桶里拿出node, 放到新的桶里
        private void delMove(ListNode node, Bucket oldBucket) {
            // 从旧桶里删掉, 挪到新桶里
            oldBucket.removeNode(node);
            // preList表示次数+1的桶的前一个桶是谁
            // 如果oldBucket删掉node之后还有节点，oldBucket就是次数+1的桶的前一个桶
            // 如果oldBucket删掉node之后空了，oldBucket是需要删除的，所以次数+1的桶的前一个桶，是oldBucket的前一个
            Bucket preBucket = modifyHeadList(oldBucket) ? oldBucket.last : oldBucket;
            // nextBu表示次数+1的桶的后一个桶是谁
            Bucket nextBucket = oldBucket.next;
            if (nextBucket == null) {
                Bucket newBucket = new Bucket(node);
                if (preBucket != null) {
                    preBucket.next = newBucket;
                }
                newBucket.last = preBucket;
                if (headList == null) {
                    headList = newBucket;
                }
                heads.put(node, newBucket);
            } else {
                if (nextBucket.head.times.equals(node.times)) {
                    nextBucket.addNodeFromHead(node);
                    heads.put(node, nextBucket);
                } else {
                    Bucket newBucket = new Bucket(node);
                    if (preBucket != null) {
                        preBucket.next = newBucket;
                    }
                    newBucket.last = preBucket;
                    newBucket.next = nextBucket;
                    nextBucket.last = newBucket;
                    if (headList == nextBucket) {
                        headList = newBucket;
                    }
                    heads.put(node, newBucket);
                }
            }
        }
        public void inc(String key) {
            if(records.containsKey(key)) { // 之前加过, 增加词频
                ListNode node = records.get(key);
                node.times++;
                Bucket bu = heads.get(node);
                addMove(node, bu);
            } else {
                ListNode node = new ListNode(key, 1);
                if(headList == null) {
                    headList = new Bucket(node);
                } else {
                    if (headList.head.times.equals(node.times)) {
                        headList.addNodeFromHead(node);
                    } else { // 新建的必然是最小的桶
                        Bucket newBucket = new Bucket(node);
                        newBucket.next = headList;
                        headList.last = newBucket;
                        headList = newBucket;
                    }
                }
                records.put(key, node);
                heads.put(node, headList);
            }
        }

        public void dec(String key) {
            if(records.containsKey(key)) { // 之前加过, 减少词频
                ListNode node = records.get(key);
                node.times--;
                if(node.times == 0) {
                    records.remove(key);
                    heads.remove(node);
                }
                Bucket bu = heads.get(node);
                modifyHeadList(bu);
                delMove(node, bu);
            }
        }

        public String getMaxKey() {
            return tailList.head.value;
        }

        public String getMinKey() {
            return headList.head.value;
        }
    }



    public static void main(String[] args) {
        Problem_432_AllOoneDataStructure_F sl = new Problem_432_AllOoneDataStructure_F();

    }

}