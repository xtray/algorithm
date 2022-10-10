package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class Problem_1640_CanFormArray {

    private static class TrieNode {
        public Map<Integer, TrieNode> nexts;
        public boolean end;

        public TrieNode() {
            nexts = new HashMap<>();
        }
    }

    public void add(int[] arr, TrieNode root) {
        TrieNode node = root;
        for (int num : arr) {
            if (!node.nexts.containsKey(num)) {
                node.nexts.put(num, new TrieNode());
            }
            node = node.nexts.get(num);
        }
        node.end = true;
    }

    public boolean canFormArray(int[] arr, int[][] pieces) {
        TrieNode root = new TrieNode();
        for (int[] nums : pieces) {
            add(nums, root);
        }
        int N = arr.length;
        boolean[] dp = new boolean[N + 1];
        dp[N] = true;
        // dp[i]: i...结束的数字能不能被分解
        for (int i = N - 1; i >= 0; i--) {
            TrieNode cur = root;
            for (int end = i; end < N; end++) {
                if (!cur.nexts.containsKey(arr[end])) {
                    break;
                }
                cur = cur.nexts.get(arr[end]);
                if (cur.end) {
                    dp[i] |= dp[end + 1];
                }
                if (dp[i]) {
                    break;
                }
            }
        }
        return dp[0];
    }
}
