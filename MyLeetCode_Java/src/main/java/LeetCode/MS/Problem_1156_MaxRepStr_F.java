package LeetCode.MS;

/**
 * Not Finished!!
 */
public class Problem_1156_MaxRepStr_F {

    public int maxRepOpt1(String text) {
        if (text == null || text.length() == 0) {
            return 0;
        }
        char[] str = text.toCharArray();
        int N = str.length;
        int L = 0; // 正在盯着的字符串
        int R = 0; //
        int max = 0;
        int diffCnt = 0;
        while (L < N) {
            while (R < N && (str[R] == str[L] || diffCnt < 1)) {
                if(str[R] != str[L]) {
                    diffCnt++;
                }
                R++;
            }
            int cnt1 = R - L;
            if (R == N || R == N - 1) {
                return Math.max(max, cnt1 - diffCnt);
            }
            // R来到一个不等于L的位置, 而且至少还有俩(包括R)
            int K = R + 1;
            while (K < N && str[K] == str[L]) {
                K++;
            }
            int cnt2 = K - R - 1;
            max = Math.max(max, cnt1 + cnt2);
            L = R;
        }

        return max;
    }

    public static void main(String[] args) {
        String text = "ababa";
        var ans = new Problem_1156_MaxRepStr_F().maxRepOpt1(text);
        System.out.println(ans);
    }
}
