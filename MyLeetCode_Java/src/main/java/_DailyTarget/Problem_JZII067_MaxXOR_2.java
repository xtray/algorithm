package _DailyTarget;

public class Problem_JZII067_MaxXOR_2 {

    public static class TrieNode {
        public TrieNode[] nexts;

        public TrieNode() {
            nexts = new TrieNode[2]; // 0,1两个分支
        }
    }

    public static class TrieTree {
        public TrieNode root;

        public TrieTree() {
            root = new TrieNode();
        }

        public void addNum(int num) {
            TrieNode cur = root;
            for (int i = 31; i >= 0; i--) {
                int index = ((num >> i) & 1);
                if (cur.nexts[index] == null) {
                    cur.nexts[index] = new TrieNode();
                }
                cur = cur.nexts[index];
            }
        }

        // 如果是负数, 除开符号位, 也是找最大的
        // 贪心: 紧着高位去变1
        public int getMaxXorNum(int num) {
            TrieNode cur = root;
            int maxXor = 0;
            for (int i = 31; i >= 0; i--) {
                // NOTE: 符号位的贪心策略: 第31位希望遇到跟我一样的
                // 符号位1, 遇到1, 异或变成0 会更大
                int org = ((num >> i) & 1);
                int best = i == 31 ? org : (1 - org); // 最好的预期
                if (cur.nexts[best] == null) {
                    best = org;
                }
                maxXor |= (org ^ best) << i; // NOTE: 第i位最终变成的是: (org ^ best)
                cur = cur.nexts[best];
            }
            return maxXor;
        }
    }


    // NOTE: 利用Trie 从高位到低位添加, 查找每个数最大的XOR,
    public int findMaximumXOR(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        TrieTree trie = new TrieTree();
        int N = nums.length;
        trie.addNum(nums[0]);
        int maxXor = Integer.MIN_VALUE; // 可能有负数
        for (int i = 0; i < N; i++) {
            int curMax = trie.getMaxXorNum(nums[i]);
            maxXor = Math.max(maxXor, curMax);
            trie.addNum(nums[i]);
        }
        return maxXor;
    }

    public static void main(String[] args) {
        int[] nums = {3, 10, 5, 25, 2, 8};
        var ans = new Problem_JZII067_MaxXOR_2().findMaximumXOR(nums);
        System.out.println(ans);
    }

}
