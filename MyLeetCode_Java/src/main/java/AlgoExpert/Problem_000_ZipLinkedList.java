package AlgoExpert;

public class Problem_000_ZipLinkedList {

    // This is an input class. Do not edit.
    public static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            this.next = null;
        }
    }

    // 链表改序问题
    // 1st node -> kth node -> 2nd node -> (k - 1)th node -> 3rd node -> (k - 2)th node -> ...
    public LinkedList zipLinkedList(LinkedList head) {
        if (head == null || head.next == null) {
            return head;
        }
        LinkedList n1 = head; // 慢指针
        LinkedList n2 = head; // 快指针
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
        }
        n2 = n1.next; // 指向中点
        n1.next = null;
        LinkedList n3 = null; // next指针
        //后半部分逆序
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2; // pre
            n2 = n3;
        }
        n2 = head;
        LinkedList n4 = null; // next指针
        while (n1 != null && n2 != null) {
            n3 = n2.next; // 先保存 next
            n4 = n1.next;

            n2.next = n1;
            n1.next = n3;
            n2 = n3;
            n1 = n4;
        }
        return head;
    }


}
