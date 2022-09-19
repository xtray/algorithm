package _DailyTarget;

public class Problem_JZII067_MaxXOR {

    public static class TrieNode {
        public TrieNode[] next; // 0, 1
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
                // 当前位位异或完的结果扔到i位置上
                ans |= (path ^ wish) << bit;
                cur = cur.next[wish];
                bit--;
            }
            return ans;
        }
    }

    // 利用前缀树, 紧着高位去变1
    public int findMaximumXOR(int[] nums) {
        TrieTree trie = new TrieTree();
        for (int num : nums) {
            trie.add(num);
        }
        int maxXor = Integer.MIN_VALUE;
        for (int num : nums) {
            maxXor = Math.max(maxXor, trie.maxXor(num));
        }
        return maxXor;
    }

    public int findMaximumXOR1(int[] nums) {
        TrieTree trie = new TrieTree();
        int maxXor = Integer.MIN_VALUE; // 可能有负数
        trie.add(nums[0]);
        for (int i = 1; i<nums.length; i++) {
            maxXor = Math.max(maxXor, trie.maxXor(nums[i]));
            trie.add(nums[i]);
        }
        return maxXor;
    }

    public static void main(String[] args) {
        int[] nums = {3,10,5,25,2,8};
        var ans = new Problem_JZII067_MaxXOR().findMaximumXOR(nums);
        System.out.println(ans);
    }

}
