package _DailyTarget;

public class Problem_307_NumArray {

    class NumArray {

        private int[] tree;
        private int[] nums;
        private int N;

        // 返回1~index的数的累加和
        private int sum(int index) {
            int res = 0;
            while (index != 0) {
                res += tree[index];
                index -= index & (-index);
            }
            return res;
        }

        // index位置的数+val
        private void add(int index, int val) {
            while (index < N) {
                tree[index] += val;
                index += index & (-index);
            }
        }

        public NumArray(int[] nums) {
            this.nums = nums;
            N = nums.length + 1; // 1废弃不用
            tree = new int[N]; // 1~size
            for (int i = 0; i < nums.length; i++) {
                add(i + 1, nums[i]);
            }
        }

        public void update(int index, int val) {
            int gap = val - nums[index];
            add(index + 1, gap);
            nums[index] = val;
        }

        public int sumRange(int left, int right) {
            return sum(right + 1) - sum(left);
        }
    }
}
