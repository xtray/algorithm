package _Contest.LC.DW83;

import java.util.ArrayList;
import java.util.List;

public class Problem_2348_NumOfAllZeroArrays {

    public long zeroFilledSubarray(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int cnt = 0;
        for (int num : nums) {
            if (num == 0) {
                cnt++;
            } else {
                list.add(cnt);
                cnt = 0;
            }
        }
        if(cnt != 0) list.add(cnt);
        long ans = 0;
        for (int num : list) {
            ans += (long) num * (num + 1) / 2L;
        }
        return ans;
    }

    // 以右端点结束的子数组个数就是0的长度


    public static void main(String[] args) {
        // nums = [0,0,0,2,0,0]
        // N*(1+N)/2
        int[] nums = {0,0,0,2,0,0};
        var ans = new Problem_2348_NumOfAllZeroArrays().zeroFilledSubarray(nums);
        System.out.println(ans);
    }
}
