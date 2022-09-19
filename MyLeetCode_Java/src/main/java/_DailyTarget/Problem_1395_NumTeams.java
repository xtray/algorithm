package _DailyTarget;

// IMP: 三元组问题, 枚举中间的值

public class Problem_1395_NumTeams {

    // https://leetcode.cn/problems/count-number-of-teams/solution/by-ac_oier-qm3a/

    // 计算每一个数右侧有几个数比它大
    // 计算每一个数左侧有几个数比它小
    public int numTeams(int[] rating) {
        int N = rating.length;
        int limit = (int) 1e5;
        int ans = 0;
        // NOTE: limit需要+1, 因为数值最大为limit,而在indexTree中位置需要+1 同Problem_315_CountSmaller
        IndexTree t1 = new IndexTree(limit + 1); // 右边的
        IndexTree t2 = new IndexTree(limit + 1); // 左边的
        for (int i = 0; i < N; i++) {
            t2.add(rating[i] + 1, 1);
        }
        for (int i = 0; i < N; i++) {
            int idx = rating[i] + 1;
            t2.add(idx, -1); // 消除右侧影响, 只保留当前i位置往右的
            // 上升组
            ans += t1.rangeSum(1, idx - 1) * t2.rangeSum(idx + 1, limit + 1);
            // 下降组
            ans += t1.rangeSum(idx + 1, limit + 1) * t2.rangeSum(1, idx - 1);
            t1.add(idx, 1);
        }

        return ans;
    }

    public static class IndexTree {

        private int[] tree;
        private int N;

        public IndexTree(int size) {
            N = size;
            tree = new int[N + 1]; // 0位置弃而不用！
        }

        public void add(int index, int d) {
            while (index <= N) {
                tree[index] += d;
                index += index & -index;
            }
        }

        // 1~index 累加和是多少？
        public int sum(int index) {
            int ret = 0;
            while (index > 0) {
                ret += tree[index];
                index -= index & -index;
            }
            return ret;
        }

        public int rangeSum(int L, int R) {
            return sum(R) - sum(L - 1);
        }

    }

    public static void main(String[] args) {
        int[] rating = {2, 5, 3, 4, 1};
        var ans = new Problem_1395_NumTeams().numTeams(rating);
        System.out.println(ans);
    }
}
