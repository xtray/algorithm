package LeetCode;


// ref: https://leetcode.cn/problems/di-string-match/solution/by-ac_oier-pvjk/
// 更好理解一点的:
// ref: https://leetcode.cn/problems/di-string-match/solution/by-nehzil-lc77/
// NOTE: 不是很理解, 回看!!

public class Problem_942_DecreaseStringMatch {

    // I: 后一个大, 使用当前最小值进行构造
    // D: 后一个小, 使用当前最大值进行构造
    // 起始最小值为 0，最大值为 n
    public static int[] diStringMatch(String s) {
        if (s == null || s.length() == 0) {
            return new int[0];
        }
        int N = s.length();
        int[] ans = new int[N + 1]; // 0 ~ N
        char[] str = s.toCharArray();
        int L = 0;
        int R = N;
        int idx = 0;
        for (int i = 0; i < N; i++) {
            ans[idx++] = str[i] == 'I' ? L++ : R--;
        }
        // 最后N-1位置的数, 没有大小要求, 剩下的就可以
        // 因为一定有解, 最后剩余的数就是L,或者R
        // ans[idx] = L;
        ans[idx] = R;
        return ans;
    }

    public static void main(String[] args) {
        String s = "IDID";
        var ans = diStringMatch(s);
        for (int num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}


