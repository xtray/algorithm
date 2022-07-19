package LeetCode;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_2343_KthSmallerNum {

    public static class Info {
        BigInteger val;
        int idx;

        public Info(BigInteger v, int i) {
            val = v;
            idx = i;
        }
    }

    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        List<Info> list = new ArrayList<>();
        int N = queries.length;
        int M = nums[0].length();
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            int k = queries[i][0];
            int trim = queries[i][1];
            int idx = 0;
            for (String num : nums) {
                num = num.substring(M - trim);
                list.add(new Info(new BigInteger(num), idx++));
            }
            list.sort((o1, o2) -> o1.val.compareTo(o2.val));
            ans[i] = list.get(k - 1).idx;
            list.clear();
        }
        return ans;
    }

    public static class Info1 {
        String val;
        int idx;

        public Info1(String v, int i) {
            val = v;
            idx = i;
        }
    }


    // NOTE: 同长度数字字符串做字符串比较同数值比较
    public int[] smallestTrimmedNumbers1(String[] nums, int[][] queries) {
        List<Info1> list = new ArrayList<>();
        int N = queries.length;
        int M = nums[0].length();
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            int k = queries[i][0];
            int trim = queries[i][1];
            int idx = 0;
            for (String num : nums) {
                num = num.substring(M - trim);
                list.add(new Info1(num, idx++));
            }
            list.sort((o1, o2) -> o1.val.compareTo(o2.val));
            ans[i] = list.get(k - 1).idx;
            list.clear();
        }
        return ans;
    }

    public static void main(String[] args) {

        String[] nums = {"102", "473", "251", "814"};
        int[][] queries = {{1, 1}, {2, 3}, {4, 2}, {1, 2}};


        // String[] nums = {"24","37","96","04"};
        // int[][] queries = {{2, 1}, {2, 2}};
        var ans = new Problem_2343_KthSmallerNum().smallestTrimmedNumbers1(nums, queries);
        System.out.println(Arrays.toString(ans));

        String a = "001";
        String b = "011";
        System.out.println(a.compareTo(b) > 0 ? "a大于b" : "a小于b");
        a = "1";
        b = "011";
        System.out.println(a.compareTo(b) > 0 ? "a大于b" : "a小于b");
    }
}
