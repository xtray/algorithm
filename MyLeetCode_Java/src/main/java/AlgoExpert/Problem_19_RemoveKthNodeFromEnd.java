package AlgoExpert;

public class Problem_19_RemoveKthNodeFromEnd {

    public static void removeKthNodeFromEnd(LinkedList head, int k) {
        if (head == null) return;
        if (k == 0) return;
        int len = 1;
        LinkedList tail = head;
        while (tail.next != null) {
            len++;
            tail = tail.next;
        }
        if (k > len) return;
        LinkedList preNode = head;
        int jumps = len - k;

        if (jumps == 0) {
            // 删除头部
            LinkedList node = head.next;
            head.value = node.value;
            head.next = node.next;
            return;
        }

        for (int i = 1; i < jumps; i++) {
            preNode = preNode.next;
        }
        LinkedList next = preNode.next.next;
        preNode.next = next;
    }

    public static void removeKthNodeFromEnd2(LinkedList head, int k) {
        if (head == null) return;
        if (k == 0) return;

        int count = 1;
        LinkedList first = head;
        LinkedList second = head;
        while (count <= k && second != null) { // 有可能不足 k 个
            second = second.next;
            count++;
        }
        if (count < k) return; //总共不足 k 个

        if (second == null) { // 删除头部
            head.value = head.next.value;
            head.next = head.next.next;
            return;
        }
        // first 跟 second 一起跳, 当 second 到达 末尾, 他俩间隔为k
        // 此时 first 位于倒数第 k 的前一个
        while (second.next != null) {
            second = second.next;
            first = first.next;
        }
        first.next = first.next.next;
    }

    static class LinkedList {
        int value;
        LinkedList next = null;

        public LinkedList(int value) {
            this.value = value;
        }
    }
}
