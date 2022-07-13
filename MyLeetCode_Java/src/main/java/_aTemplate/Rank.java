package _aTemplate;

public class Rank {

    // 有序数组中 num 的排序顺位, 相等的时候返回最大的排名
    // >= num 最左的位置
    public static int rank(int[] sorted, int num) {
        int l = 0;
        int r = sorted.length - 1;
        int m = 0;
        int ans = -1;
        while (l <= r) {
            m = (l + r) / 2;
            if (sorted[m] >= num) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans + 1; // rank 从1开始, 所以要+1
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 3, 7, 9, 11};
        int num = 4;
        var ans = rank(nums, num);
        System.out.println(ans);
    }
}
