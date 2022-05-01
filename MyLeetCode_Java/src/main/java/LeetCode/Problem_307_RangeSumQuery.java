package LeetCode;

// IMP: 重要indextree模板题!!

public class Problem_307_RangeSumQuery {

    // logN
    static class NumArray {

        private int[] tree;
        private int[] nums;
        private int N;

        public NumArray(int[] nums) {
            N = nums.length;
            tree = new int[N + 1];
            this.nums = nums;
            for (int i = 0; i < nums.length; i++) {
                add(i + 1, nums[i]);
            }
        }

        // 1...index累加和
        private int sum(int index) {
            int res = 0;
            while (index > 0) {
                res += tree[index];
                index -= index & -index;
            }
            return res;
        }

        private void add(int index, int val) {
            while (index <= N) {
                tree[index] += val;
                index += index & -index;
            }
        }

        public void update(int index, int val) {
            int delta = val - nums[index];
            add(index + 1, delta);
            nums[index] = val; // IMP: 不要忘了这一句, 用来计算增量
        }

        public int sumRange(int left, int right) {
            // left +1 ~ right +1
            return sum(right + 1) - sum(left);
        }
    }

    public void test() {
        int[] nums = {7, 2, 7, 2, 0};
        var sl = new NumArray(nums);
        sl.update(4, 6);
        sl.update(0, 2);
        sl.update(0, 9);
        var ans1 = sl.sumRange(4, 4);
        System.out.println(ans1);

        sl.update(3, 8);
        var ans2 = sl.sumRange(0, 4);
        System.out.println(ans2);
    }

    public static void main(String[] args) {
        new Problem_307_RangeSumQuery().test();
    }
}
