// package LeetCode;
//
// public class Problem_148_SortList {
//     public static class ListNode {
//         int val;
//         ListNode next;
//
//         ListNode() {
//         }
//
//         ListNode(int val) {
//             this.val = val;
//         }
//
//         ListNode(int val, ListNode next) {
//             this.val = val;
//             this.next = next;
//         }
//     }
//
//     public ListNode sortList(ListNode head) {
//         if (head == null) {
//             return head;
//         }
//         return process(head);
//     }
//
//     private ListNode process(ListNode head) {
//         if (head.next == null) {
//             return head;
//         }
//         ListNode slow = head;
//         ListNode fast = head;
//         while (fast != null && fast.next != null) {
//             slow = slow.next;
//             fast = fast.next.next;
//             if(slow == fast) break;
//         }
//         ListNode head1 = process(head, slow);
//         ListNode head2 = process(slow, null);
//
//
//
//     }
// }
