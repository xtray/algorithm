package _BasicProblems;

// IMP: Morris遍历, T: O(N), S: O(1)
public class Morris {

    public static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node(int v) {
            val = v;
        }
    }

    // 0523
    public static void morris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null; // 每到一个节点都会去找它左树的最右节点
        while (cur != null) {
            mostRight = cur.left; // 先来到左孩子
            if (mostRight != null) { // 有左树
                // 左孩子不是空, 找左树最右节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // mostRight : 一定是当前节点左树上的最右节点
                // 情况1: 第一次来到当前节点
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }
                // 情况2: 第二次来到, mostRight.right == cur
                mostRight.right = null;
            }
            // 第二次来到当前节点
            // 1. 没有左树向右移动
            // 2. mostRight.right == cur, 恢复, 往右跳
            cur = cur.right;
        }
    }

    // Morris先序遍历, 一个节点在第一次到达自己就打印
    // 1. 节点无左树, 只能到自己一次, 刚到他就打印
    // 2. 节点有左树, 第一次到达时候打印
    public static void morrisPre(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    // 情况2. 节点有左树, 第一次到达时候打印
                    System.out.println(cur.val + " ");
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }
                // mostRight.right == cur
                mostRight.right = null;
            } else {
                // 情况1. 没左树, 第一次到达时候就打印
                System.out.println(cur.val + " ");
            }
            // 1. 没有左树向右移动
            // 2. mostRight.right == cur, 恢复, 往右跳
            cur = cur.right;
        }
    }

    // Morris中序遍历, 一个节点在第二次来到自己时候打印
    // 1.没有左树, 直接打印
    // 2.有左树, 第二次来到自己打印
    public static void morrisIn(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) { // 左孩子不是空, 找左树最右节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }
                // mostRight.right == cur
                mostRight.right = null;
            }
            // 1. 没有左树向右移动
            // 2. 有左树, 到自己两次
            System.out.println(cur.val + " ");
            cur = cur.right;
        }
    }

    // Morris实现后序遍历
    // NOTE: 把处理时机放在能回到自己两次的节点且第二次回到自己的时候
    // 第二次回到自己的时候, 逆序打印他左树右边界(使用链表翻转)
    // 在Morris遍历跑完之后, 单独打印整棵树的右边界
    public static void morrisPost(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null; // 左树上的最右节点
        while (cur != null) {
            mostRight = cur.left; // 先来到左孩子
            if (mostRight != null) { // 左孩子不是空, 找左树最右节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right; // 不断往右
                }
                // mostRight 一定是左树最右节点, 但是有两种情况
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }
                // mostRight.right == cur
                mostRight.right = null; // 恢复, 指向空
                // 第二次回到自己的时候, 逆序打印自己的左树右边界
                printEdge(cur.left);
            }
            cur = cur.right;
        }
        // 最后不要忘了逆序打印整棵树的右边界
        printEdge(head);
    }

    // 打印node节点的左树右边界
    private static void printEdge(Node head) {
        Node tail = reverseEdge(head); // 再次翻转回来用
        Node cur = tail;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
        // 打完之后再翻转回来
        reverseEdge(tail);
    }

    // 链表翻转
    private static Node reverseEdge(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.right;
            head.right = pre;
            pre = head;
            head = next;
        }
        return pre;
    }


}
