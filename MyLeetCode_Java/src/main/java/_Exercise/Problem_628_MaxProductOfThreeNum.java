package _Exercise;

public class Problem_628_MaxProductOfThreeNum {

    // 分情况
    // 都是负数: 取最大的三个
    // 有一个非负: 取最大的一个, 最小的两个
    // 有两个及两个以上非负: 取最大的三个 跟 取最大的一个, 最小的两个 取大的
    public int maximumProduct(int[] nums) {
        // int max1 = Integer.MIN_VALUE;
        // int max2 = Integer.MIN_VALUE;
        // int max3 = Integer.MIN_VALUE;
        // int min1 = Integer.MAX_VALUE;
        // int min2 = Integer.MAX_VALUE;
        int max1, max2, max3;
        int min1, min2;
        max1 = max2 = max3 = Integer.MIN_VALUE;
        min1 = min2 = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }
            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
        }
        // if (max1 < 0) {
        //     return max1 * max2 * max3;
        // }
        // // max1 >= 0
        // if (max2 < 0) {
        //     return max1 * min2 * min1;
        // }
        // // max2 >= 0
        return Math.max(max1 * max2 * max3, max1 * min2 * min1);
    }

    public static void main(String[] args) {
        int[] nums = {-100, -98, -1, 2, 3, 4}; // 39200
        var ans = new Problem_628_MaxProductOfThreeNum().maximumProduct(nums);
        System.out.println(ans);
    }
}
