package LeetCode.Jianzhi;

import java.util.Arrays;

public class Problem_JZ045_MinNumber {

    // 拼接最小数
    public String minNumber(int[] nums) {
        String[] strArr = new String[nums.length];
        int idx = 0;
        for (int num : nums) {
            strArr[idx++] = String.valueOf(num);
        }
        Arrays.sort(strArr, (o1, o2) -> (o1 + o2).compareTo(o2 + o1)); // 字典序小的在前
        // Arrays.sort(strArr, (o1, o2) -> (o2 + o1).compareTo(o1 + o2)); // 字典序大的在前
        StringBuilder sb = new StringBuilder();
        for (String str : strArr) {
            sb.append(str);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] nums = {3, 30, 34, 5, 9};
        var ans = new Problem_JZ045_MinNumber().minNumber(nums);
        System.out.println(ans);
    }
}
