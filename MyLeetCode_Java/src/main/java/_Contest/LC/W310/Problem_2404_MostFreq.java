package _Contest.LC.W310;

import java.util.*;

public class Problem_2404_MostFreq {

    public int mostFrequentEven(int[] nums) {
        Map<Integer, Integer> cntMap = new HashMap<>();
        int maxCnt = 1;
        for (int num : nums) {
            if ((num & 1) != 0) continue;
            cntMap.put(num, cntMap.getOrDefault(num, 0) + 1);
            maxCnt = Math.max(maxCnt, cntMap.get(num));
        }
        int ans = Integer.MAX_VALUE;
        for (int num : cntMap.keySet()) {
            if (cntMap.get(num) == maxCnt && num < ans) {
                ans = num;
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
        int[] nums = {
                4287,799,8103,3526,8396,7060,8287,4214,4093,6763,651,4907,8350,4866,5114,5245,3198,6644,3620,1586,3068,2769,9506,2319,588,5055,730,6968,4814,144,5180,8798,8783,3319,7765,3254,7164,5462,8085,5481,8363,3135,2910,583,3751,3044,8734,4075,5489,7866,3404,9532,3751,571,7460,4934,9346,4076,1505,9576,9406,3342,8090,5979,3140,4453,4779,9324,2945,7279,6809,9619,6958,9881,8308,9246,2529,3727,1241,7230,4536,9852,1667,1688,1320,6400,6359,6140,904,6287,6499,9256,7666,4083,2927,3165,9239,9433,1620,6961,3115,8766,2416,6653,9030,2690,9165,6150,1644,5925
        };
        var ans = new Problem_2404_MostFreq().mostFrequentEven(nums);
        System.out.println(ans);
    }
}
