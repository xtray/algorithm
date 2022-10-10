package _DailyTarget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_1707_MaxXor {

    public static class TrieNode {
        public TrieNode[] nexts;

        public TrieNode() {
            nexts = new TrieNode[2]; // 0, 1
        }
    }

    // num 按32位, 从高到底位塞入前缀树
    public void add(int num) {
        TrieNode cur = root;
        int bit = 31;
        while (bit >= 0) {
            // int path = (num & (1 << bit)) == 0 ? 0 : 1;
            int path = (num >> bit) & 1;
            if (cur.nexts[path] == null) {
                cur.nexts[path] = new TrieNode();
            }
            cur = cur.nexts[path];
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
            if (cur.nexts[wish] == null) {
                wish = 1 - wish;
            }
            // 当前位位异或完的结果扔到i位置上
            ans |= (path ^ wish) << bit;
            cur = cur.nexts[wish];
            bit--;
        }
        return ans;
    }

    private TrieNode root;

    public int[] maximizeXor(int[] nums, int[][] queries) {
        root = new TrieNode();
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            list.add(new int[]{queries[i][0], queries[i][1], i});
        }
        list.sort((o1, o2) -> o1[1] - o2[1]);
        int M = list.size();
        int[] ans = new int[M];
        Arrays.sort(nums);
        for (int j = 0, i = 0; j < M; ) {
            if (i < nums.length && nums[i] <= list.get(j)[1]) {
                add(nums[i]);
                i++;
            } else {
                // 结算 j
                int index = list.get(j)[2];
                int val = i == 0 ? -1 : maxXor(list.get(j)[0]);
                ans[index] = val;
                j++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // int[] nums = {0, 1, 2, 3, 4};
        // int[][] queries = {{3, 1}, {1, 3}, {5, 6}};

        int[] nums = {5, 2, 4, 6, 6, 3};
        int[][] queries = {{12, 4}, {8, 1}, {6, 3}};


        var ans = new Problem_1707_MaxXor().maximizeXor(nums, queries);


        System.out.println(Arrays.toString(ans));
    }


}
