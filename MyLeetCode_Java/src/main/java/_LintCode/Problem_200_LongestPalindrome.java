package _LintCode;

public class Problem_200_LongestPalindrome {

    // 暴力解: O(N^2)
    // 从每一个i位置向左右两侧扩散
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] str = getManacherStr(s);
        int N = str.length;
        int max = 0; // 最大回文半径
        int maxPos = -1; // 最大回文右边界, 到不了的位置
        for (int i = 0; i < N; i++) {
            int x = i - 1;
            int y = i + 1;
            int cnt = 1; // 回文半径
            while (x >= 0 && y < N && str[x] == str[y]) {
                cnt++;
                x--;
                y++;
            }
            if (cnt > max) {
                max = cnt;
                maxPos = i + cnt;
            }
        }
        // 真实回文长度: 回文半径 - 1
        int len = max - 1;
        // NOTE: 真实结尾位置=(处理串结尾位置-1)/2
        int end = (maxPos - 1) >> 1;
        return s.substring(end - len, end);
    }

    private char[] getManacherStr(String s) {
        int N = s.length();
        char[] ans = new char[N << 1 | 1];
        for (int i = 0; i < N; i++) {
            ans[i << 1] = '#';
            ans[i << 1 | 1] = s.charAt(i);
        }
        ans[ans.length - 1] = '#';
        return ans;
    }

    // Manacher解法
    public String longestPalindrome2(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] str = manacherString(s);
        int N = str.length;
        // 回文半径的大小
        int[] pArr = new int[str.length];
        int C = -1;
        // 讲述中：R代表最右的扩成功的位置
        // coding：最右的扩成功位置的，再下一个位置
        int R = -1;
        int max = 0; // 最大回文半径
        int maxPos = -1; // 最大回文右边界, 到不了的位置

        for (int i = 0; i < str.length; i++) { // 0 1 2
            // R第一个违规的位置，i>= R
            // i位置扩出来的答案，i位置扩的区域，至少是多大。
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]])
                    pArr[i]++;
                else {
                    break;
                }
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            if (pArr[i] > max) {
                max = pArr[i];
                maxPos = i + pArr[i];
            }
        }
        // 真实长度
        int len = max - 1; // 真实回文长度
        // NOTE: 真实结尾位置=(处理串结尾位置-1)/2
        int end = (maxPos - 1) >> 1;
        return s.substring(end - len, end);
    }

    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "abcdzdcab";
        var ans = new Problem_200_LongestPalindrome().longestPalindrome(s);
        System.out.println(ans);
    }
}
