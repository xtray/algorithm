package AlgoExpert;

public class Problem_000_NodeSwap {

    // This is an input class. Do not edit.
    public static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            this.next = null;
        }
    }

    // O(n) time | O(n) space - where n is the number of nodes in the Linked List
    public LinkedList nodeSwap0(LinkedList head) {
        if (head == null || head.next == null) {
            return head;
        }
        LinkedList nextNode = head.next;
        head.next = nodeSwap(head.next.next);
        nextNode.next = head;
        return nextNode;
    }

    // 两两翻转链表结点
    public static LinkedList nodeSwap(LinkedList head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 至少有两个结点
        LinkedList first = head;
        LinkedList second = head.next;
        head = second;
        // 先连接第一组
        LinkedList next = second.next;
        second.next = first;
        first.next = next;
        // 上一组的结尾结点
        second = first;
        while (second.next != null) {
            // 1-->2-->3
            // 2-->1-->3
            LinkedList pre = second;
            first = second.next;
            second = first.next;
            if(second == null) {
                return head;
            }
            next = second.next;
            pre.next = second;
            second.next = first;
            first.next = next;
            second = first;
        }
        return head;
    }

    public static void main(String[] args) {
        LinkedList head = new LinkedList(0);
        head.next = new LinkedList(1);
        head.next.next = new LinkedList(2);
        head.next.next.next = new LinkedList(3);
        // head.next.next.next.next = new LinkedList(4);
        // head.next.next.next.next.next = new LinkedList(5);
        var ans = nodeSwap(head);
        while (ans != null) {
            System.out.print(ans.value + "->");
            ans = ans.next;
        }
        System.out.println("null");
    }
}
