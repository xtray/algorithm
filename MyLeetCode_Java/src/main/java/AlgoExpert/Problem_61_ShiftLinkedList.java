package AlgoExpert;

public class Problem_61_ShiftLinkedList {
    static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            next = null;
        }
    }

    public static LinkedList shiftLinkedList(LinkedList head, int k) {
        LinkedList tail = head;
        int len = 1;
        while (tail.next != null) {
            tail = tail.next;
            len++;
        }

        int offset = Math.abs(k) % len;
        if (offset == 0) return head;
        // 需要移动的步数
        int tailPos = k > 0 ? len - offset : offset;
        LinkedList newTail = head;
        for (int i = 1; i < tailPos; i++) {
            newTail = newTail.next;
        }

        LinkedList newHead = newTail.next;
        newTail.next = null;
        tail.next = head;
        return newHead;
    }

}
