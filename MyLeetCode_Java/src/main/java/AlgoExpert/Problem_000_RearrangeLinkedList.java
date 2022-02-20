package AlgoExpert;

public class Problem_000_RearrangeLinkedList {

    // 将单向链表按某值划分成左边小、中间相等、右边大的形式
    public static LinkedList rearrangeLinkedList(LinkedList head, int k) {

        LinkedList smallHead = null, smallTail = null;
        LinkedList bigHead = null, bigTail = null;
        LinkedList kNodeHead = null, kNodeTail = null;
        LinkedList next = null;

        while (head != null) {
            next = head.next;
            head.next = null;
            if(head.value == k) {
                if(kNodeHead == null) {
                    kNodeHead = head;
                    kNodeTail = head;
                } else {
                    kNodeTail.next = head;
                    kNodeTail = head;
                }
            } else if(head.value < k) {
                if(smallHead == null) {
                    smallHead = head;
                    smallTail = head;
                } else {
                    smallTail.next = head;
                    smallTail = head;
                }
            } else { // > k
                if(bigHead == null) {
                    bigHead = head;
                    bigTail = head;
                } else {
                    bigTail.next = head;
                    bigTail = head;
                }
            }
            head = next;
        }

        // 小于区域的尾巴，连等于区域的头，等于区域的尾巴连大于区域的头
        if(smallTail != null) { // 如果有小于区域
            smallTail.next = kNodeHead;
            kNodeTail = kNodeTail == null? smallTail:kNodeTail; // 下一步，谁去连大于区域的头，谁就变成eT
        }
        // 下一步，一定是需要用kNodeTail 去接 大于区域的头
        // 有等于区域，kNodeTail -> 等于区域的尾结点
        // 无等于区域，kNodeTail -> 小于区域的尾结点
        // kNodeTail 尽量不为空的尾巴节点
        if(kNodeTail != null) { // 如果小于区域和等于区域，不是都没有
            kNodeTail.next = bigHead;
        }
        return smallHead != null? smallHead:(kNodeHead != null?kNodeHead:bigHead);
    }

    static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            next = null;
        }
    }

    public static void main(String[] args) {
        var head = new LinkedList(0);
        head.next = new LinkedList(3);
        head.next.next = new LinkedList(2);
        head.next.next.next = new LinkedList(1);
        head.next.next.next.next = new LinkedList(4);
        head.next.next.next.next.next = new LinkedList(5);
        int k = 0;
        var ans = rearrangeLinkedList(head, k);
        while (ans != null) {
            System.out.print(ans.value + "->");
            ans = ans.next;
        }
        System.out.println("null");
    }
}
