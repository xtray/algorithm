package LeetCode.Jianzhi;

import java.util.Arrays;

public class Problem_JZ045_MinNumber {

    public String minNumber(int[] nums) {
        String[] strArr = new String[nums.length];
        int idx = 0;
        for(int num : nums) {
            strArr[idx++] = String.valueOf(num);
        }
        Arrays.sort(strArr, (o1, o2) -> (o1+o2).compareTo(o2+o1));
        StringBuilder sb = new StringBuilder();
        for(String str : strArr) {
            sb.append(str);
        }
        return sb.toString();
    }
}
