package LeetCode;

import java.util.ArrayDeque;
import java.util.Arrays;

public class Problem_331_ValidBinarySerialization {

    public boolean isValidSerialization(String preorder) {
        if (preorder == null || preorder.length() == 0) {
            return false;
        }
        String[] ss = preorder.split(",");
        ArrayDeque<String> queue = new ArrayDeque<>(Arrays.asList(ss));
        return check(queue, queue.size());
    }

    // 弹出#, 不消化队列, 弹出数字消耗两个, 最后queue应该空了
    private boolean check(ArrayDeque<String> queue, int rest) {
        if (queue.isEmpty()) {
            return rest == 0;
        }
        ArrayDeque<String> newQueue = new ArrayDeque<>();
        String head = queue.pollFirst();
        newQueue.add(head);
        while (!newQueue.isEmpty()) {
            String cur = newQueue.pollFirst();
            if (cur == null) {
                return false;
            }
            if ("#".equals(cur)) {
                continue;
            } else {
                if (queue.size() < 2) {
                    return false;
                }
                newQueue.add(queue.pollFirst());
                newQueue.add(queue.pollFirst());
            }
        }
        return queue.isEmpty();
    }

    public static void main(String[] args) {
        String preorder = "9,#,#,1";
        var ans = new Problem_331_ValidBinarySerialization().isValidSerialization(preorder);
        System.out.println(ans);
    }
}
