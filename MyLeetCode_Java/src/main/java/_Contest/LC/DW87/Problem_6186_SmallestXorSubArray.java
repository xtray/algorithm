package _Contest.LC.DW87;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem_6186_SmallestXorSubArray {

    public static class TrieNode {
        public TrieNode[] next; // 0, 1
        public int pass;

        public TrieNode() {
            next = new TrieNode[2];
        }
    }

    public static class TrieTree {
        public TrieNode root;

        public TrieTree() {
            root = new TrieNode();
        }

        // num 按32位, 从高到底位塞入前缀树
        public void add(int num) {
            TrieNode cur = root;
            int bit = 31;
            while (bit >= 0) {
                // int path = (num & (1 << bit)) == 0 ? 0 : 1;
                int path = (num >> bit) & 1;
                if (cur.next[path] == null) {
                    cur.next[path] = new TrieNode();
                }
                cur = cur.next[path];
                bit--;
            }
        }

        // 返回num跟谁结合最大
        public int maxXor(int num) {
            TrieNode cur = root;
            int bit = 31;
            int ans = 0;
            while (bit >= 0) {
                // num中的状态
                int path = (num >> bit) & 1;
                // 符号位(31位)的贪心策略是希望遇到跟自己一样的
                // 如果符号位是1, 后面还是紧着高位边1
                int wish = bit == 31 ? path : (1 - path);
                if (cur.next[wish] == null) {
                    wish = 1 - wish;
                }
                if (cur.next[wish] == null) break;
                // 当前位位异或完的结果扔到i位置上
                ans |= (path ^ wish) << bit;
                cur = cur.next[wish];
                bit--;
            }
            return ans;
        }
    }

    // 从i位置出发的最大异或值
    public int[] smallestSubarrays(int[] nums) {
        int N = nums.length;
        int[] ans = new int[N];
        TrieTree tree = new TrieTree();
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, N);
        int curXor = 0;

        for (int i = N - 1; i >= 0; i--) {
            curXor ^= nums[i];
            int curMax = tree.maxXor(nums[i]);
            int pos = map.getOrDefault(curMax ^ curXor, N);
            ans[i] = pos - i;
            map.put(curXor, i);
            tree.add(nums[i]);
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] nums = {1, 0, 2, 1, 3};
        var ans = new Problem_6186_SmallestXorSubArray().smallestSubarrays(nums);
        System.out.println(Arrays.toString(ans));
    }
}
