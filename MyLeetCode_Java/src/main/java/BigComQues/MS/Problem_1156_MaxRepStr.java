package BigComQues.MS;

/**
 * https://leetcode-cn.com/circle/article/qA7LcR/
 * https://leetcode-cn.com/problems/swap-for-longest-repeated-character-substring/
 */
public class Problem_1156_MaxRepStr {

    // 时间复杂度: O(N)
    public int maxRepOpt1(String text) {
        if (text == null || text.length() == 0) {
            return 0;
        }
        if (text.length() == 1) {
            return 1;
        }
        char[] str = text.toCharArray();
        int N = str.length;
        // 1. 预处理, 每个字符两侧的是否相等
        boolean[] checkSame = new boolean[N];
        checkSame[0] = true;
        checkSame[N - 1] = true;
        int[] left = new int[N]; // 字符左侧连续的字符个数
        int[] right = new int[N]; // 字符右侧连续的字符个数
        int[] count = new int[26]; // 每个字符词频统计
        left[0] = 1;
        right[N - 1] = 1;
        count[str[0] - 'a']++;
        count[str[N - 1] - 'a']++;
        for (int i = 1; i < N - 1; i++) {
            if (str[i - 1] == str[i + 1]) {
                checkSame[i] = true;
            }
            left[i] = str[i] == str[i - 1] ? left[i - 1] + 1 : 1;
            int j = N - 1 - i;
            right[j] = str[j] == str[j + 1] ? right[j + 1] + 1 : 1;
            count[str[i] - 'a']++;
        }
        left[N - 1] = str[N - 1] == str[N - 2] ? left[N - 2] + 1 : 1;
        right[0] = str[0] == str[1] ? right[1] + 1 : 1;
        int preCnt = 0;
        int postCnt = 0;
        int maxCnt = 1;
        for (int i = 0; i < N; i++) {
            if (checkSame[i]) { // 两侧相等, 包括边界判断
                preCnt = i != 0 ? left[i - 1] : 0;
                postCnt = i != N - 1 ? right[i + 1] : 0;
                int cur = preCnt + postCnt;
                // 中间字符跟两边相等的情况, +1
                if ((i == 0 && str[1] == str[0]) ||
                        (i == N - 1 && str[N - 1] == str[N - 2]) ||
                        (i > 0 && i < N - 1 && str[i - 1] == str[i])) {
                    cur++;
                }
                // 远处还有一个多余的1的情况,可以挪过来 + 1
                if ((i != 0 && count[str[i - 1] - 'a'] > cur) ||
                        (i != N - 1 && count[str[i + 1] - 'a'] > cur)) {
                    cur++;
                }
                // true的两个边界特列,
                // 当前字符跟边界靠里的不一样, 但是还有其他地方存在, 此时为2
                if (((i == 0 && str[0] != str[1]) ||
                        (i == N - 1 && str[N - 1] != str[N - 2])) &&
                        count[str[i] - 'a'] > 1) {
                    cur = 2;
                }
                maxCnt = Math.max(maxCnt, cur);
            } else { // 不包括左右两个边界
                // 两侧不相等时候:
                // 情况1: 左侧等于当前字符
                // 情况2: 右侧等于当前字符
                // 情况3: 两侧不相等, 且都不等于当前字符
                preCnt = left[i - 1];
                postCnt = right[i + 1];
                int cur = 0;
                if( (left[i] != left[i-1] + 1) && (right[i] != right[i+1] + 1)) {
                    if (count[str[i] - 'a'] > 1) {
                        cur = 2;
                    }
                } else {
                    if(left[i] == left[i-1] + 1) {
                        preCnt++;
                    } else if (right[i] == right[i+1] + 1) {
                        postCnt++;
                    }
                    int leftCnt = preCnt +  (count[str[i - 1] - 'a'] > preCnt ? 1:0);
                    int rightCnt = postCnt +  (count[str[i + 1] - 'a'] > postCnt ? 1:0);
                    cur = Math.max(leftCnt, rightCnt);
                }
                maxCnt = Math.max(maxCnt, cur);
            }
        }
        return maxCnt;
    }

    public static String generateRandomStr(int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            char ch = (char) (26 * Math.random() + 'a');
            sb.append(ch);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // String text = "ababa";
        // String text = "aaabbaaa"; // 4
        // String text = "bbababaaaa"; // 6
        // String text = "abaab"; // 6
        // String text = "uqnu"; // 2
        // String text = "uwxxhqwx"; // 3
        String text = "qqxcctyr"; // 2
        var ans = new Problem_1156_MaxRepStr().maxRepOpt1(text);
        System.out.println(ans);
        var res = new Problem_1156_MaxRepStr().maxRepOpt2(text);
        System.out.println(res);

        int times = 1000000;
        int maxLen = 20000;
        // int maxLen = 100;
        System.out.println("Test Start");
        for (int i = 0; i < times; i++) {
            int size = (int) (Math.random() * maxLen) + 1;
            String str = generateRandomStr(size);
            var ans1 = new Problem_1156_MaxRepStr().maxRepOpt1(str);
            var ans2 = new Problem_1156_MaxRepStr().maxRepOpt2(str);
            if (ans1 != ans2) {
                System.out.println("Ooops!!");
                System.out.println("str: " + str);
                System.out.println("ans1: " + ans1);
                System.out.println("ans2: " + ans2);
                break;
            }
        }
        System.out.println("Test End.");

    }

    public int maxRepOpt2(String text) {
        char[] cs = text.toCharArray();
        // 每个字符计数
        int[] dics = new int[26];
        for (char c : cs) {
            dics[c - 'a'] += 1;
        }
        int len = cs.length;
        // 窗口内，第一个字符和数量
        char A = cs[0], last = A;
        int a = 1;
        int l = 0, r = 1;
        while (r < len && cs[r] == last) {
            r++;
            a++;
        }
        // 有可能就一个啊
        if (r == len || r == len - 1) {
            return r;
        }
        // 窗口里第二个字符
        char B = cs[r];
        last = B;
        int b = 1;
        r++;
        int ans = 0;
        while (r <= len) {
            char cur = r < len ? cs[r] : ' ';
            if (cur == A && a >= 1 && b <= 1) {
                // 窗口中不唯一的字符是A字符，A字符+1；
                a++;
                r++;
            } else if (cur == B && b >= 1 && a <= 1) {
                // 窗口中不唯一的字符是B字符，B字符+1；
                b++;
                r++;
            } else {
                // 窗口结构破坏，先有窗口内数字结算
                if (a == 1) {
                    // A是独苗，结算B
                    if (dics[B - 'a'] > b) {
                        ans = Math.max(ans, b + 1);
                    } else {
                        ans = Math.max(ans, b);
                    }
                } else {
                    // B是独苗，结算A
                    if (dics[A - 'a'] > a) {
                        ans = Math.max(ans, a + 1);
                    } else {
                        ans = Math.max(ans, a);
                    }
                }
                if (cur != A && cur != B) {
                    // cur不是A，B中一个。将a，b其中一个清空
                    while (a > 0 && b > 0) {
                        char c = cs[l++];
                        if (c == A) {
                            a--;
                        } else {
                            b--;
                        }
                    }
                    // 让A拿着不为空的字符信息
                    if (a == 0) {
                        A = B;
                        a = b;
                    }
                    // 找到B的字符信息。
                    while (r < len && cs[r] == A) {
                        a++;
                        r++;
                    }
                    if (r < len) {
                        B = cs[r];
                        b = 1;
                    }
                } else {
                    // 如果cur是A,B中一个，先加入进来
                    if (cur == A) {
                        a++;
                    } else {
                        b++;
                    }
                    // 将两个字符，变成最多都剩下1个
                    while (a > 1 && b > 1) {
                        char c = cs[l++];
                        if (c == A) {
                            a--;
                        } else {
                            b--;
                        }
                    }
                }
                r++;
            }
        }
        return ans;
    }
}
