package LeetCode;

import java.util.ArrayDeque;

public class Problem_1265_PrintLinkedListReverse {

    interface ImmutableListNode {
        public void printValue(); // print the value of this node.

        public ImmutableListNode getNext(); // return the next node.
    }

    // 解法1:
    private ArrayDeque<ImmutableListNode> stack = new ArrayDeque();

    public void printLinkedListInReverse(ImmutableListNode head) {

        while (head != null) {
            stack.addFirst(head);
            head = head.getNext();
        }

        while (!stack.isEmpty()) {
            ImmutableListNode cur = stack.pollFirst();
            cur.printValue();
        }
    }


    // 解法2:
    public void printLinkedListInReverse1(ImmutableListNode head) {
        if (head == null) return;  //终止条件
        printLinkedListInReverse(head.getNext());  //递归到下一个
        head.printValue();//打印节点
    }

    // 解法3:
    // 在使用递归或者栈方式时，由于需要一次性将链表全部压栈，所以需要n的栈空间。
    // 将链表划分为前后两部分分别进行递归，这样栈空间就缩小了一半。
    // 时间O(nlogn), 空间O(logn)
    public void printLinkedListInReverse2(ImmutableListNode head) {
        process(head, null);
    }

    // 这里区间是左闭右开[ start, end)
    private void process(ImmutableListNode start, ImmutableListNode end) {
        if (start.getNext() == end) {
            // 由于左闭右开，此时区间中只剩一个节点了，打印并结束递归
            start.printValue();
            return;
        }
        // 使用快慢指针找到上中点
        ImmutableListNode slow = start;
        ImmutableListNode fast = start;
        while (fast != end && fast.getNext() != end) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        // NOTE: 因为要逆序，所以先递归后半部分
        process(slow, end);
        process(start, slow);
    }


}
