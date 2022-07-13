package LeetCode;

// https://leetcode.cn/problems/substring-with-largest-variance/

// ref: LC53: https://leetcode.cn/problems/maximum-subarray/
// ref: LC1781 https://leetcode.cn/problems/sum-of-beauty-of-all-substrings/

// IMP: 多看, 比较难理解
// PENDING: 还是不大理解

public class Problem_2272_MaxVarianceSubStr {

    // https://leetcode.cn/problems/substring-with-largest-variance/solution/by-endlesscheng-5775/
    public int largestVariance(String s) {
        char[] str = s.toCharArray();
        int ans = 0;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (j == i) continue;
                int diff1 = 0; // 维护字符a, b的出现次数之差(可能只有a)
                int diff2 = Integer.MIN_VALUE; // a,b均出现时候的次数差(即最大波动), b不出现时候, ans==0
                for (char ch : str) {
                    if (ch == (char) ('a' + i)) { // 遇到a字符
                        diff1++;
                        diff2++; // aaba的情况, b后面的a
                    } else if (ch == (char) ('a' + j)) { // 遇到b字符
                        diff1--;
                        diff2 = diff1;
                        if(diff1 < 0) diff1 = 0;
                    }
                    // 情况1: 只有a, 那么diff1 >0 , diff2: 最小值 --> ans == 0
                    //       diff2++ --> 不加答案就是错的
                    // 情况2: 只有b, 那么diff1 <0 , diff2: -1 --> ans == 0
                    // 情况3: 没a,没b, 那么diff1 == 0 , diff2: 最小值 --> ans == 0
                    // 情况4: 有a, 有b --> ans = diff2
                    ans = Math.max(ans, diff2);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "aababbb";
        // String s = "ab";
        var ans = new Problem_2272_MaxVarianceSubStr().largestVariance(s);
        System.out.println(ans);
    }
}
