package LeetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

// IMP: 多种做法的逐步扩展

public class Problem_727_MinWindow {

    // s1的最短子串中包含s2这个子序列
    // 每次来到s1的位置都找s2长度一遍, 复杂度O(N*M)
    public String minWindow(String s1, String s2) {
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) {
            return "";
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length;
        int M = str2.length;
        int minLen = Integer.MAX_VALUE;
        String ans = "";

        int[][] smap = new int[N][26];
        for (int i = 0; i < N; i++) {
            Arrays.fill(smap[i], -1);
        }
        // 方法1: 从右往左求smap表
        // s1从i位置出发, 最近的a...z位置, 没有就是-1;
        // smap[N - 1][str1[N - 1] - 'a'] = N - 1;
        // for (int i = N - 2; i >= 0; i--) {
        //     for (int j = 0; j < 26; j++) {
        //         if (str1[i] - 'a' == j) {
        //             smap[i][j] = i;
        //         } else {
        //             smap[i][j] = smap[i + 1][j];
        //         }
        //     }
        // }
        // 方法2: 从左往右求smap表
        int[] last = new int[26]; // cha字符上一次出现的位置的下一个, 默认用0
        for (int i = 0; i < N; i++) {
            int cha = str1[i] - 'a';
            for (int j = last[cha]; j <= i; j++) {
                // 从上次 cha 字符出现的位置到i位置全部更新
                smap[j][cha] = i;
            }
            last[cha] = i + 1; // cha字符开始找寻的下一个位置
        }

        // for (int i = 0; i < N; i++) {
        //     if (str1[i] == str2[0]) { // 从当前i位置开始找一个包含s2子序列的子串
        //         int end = getEnd2(str1, i, str2, smap);
        //         if (end != -1) {
        //             int len = end - i + 1;
        //             if (len < minLen) {
        //                 minLen = len;
        //                 ans = String.valueOf(str1, i, len);
        //             }
        //         }
        //     }
        // }
        // return ans;

        // NOTE: 每次使用String.valueOf 更新ans, 速度非常慢, 改用记录截取位置点会更快
        int l = -1;
        int r = -1;
        for (int i = 0; i < N; i++) {
            if (str1[i] == str2[0]) { // 从当前i位置开始找一个包含s2子序列的子串
                int end = getEnd2(str1, i, str2, smap);
                if (end != -1) {
                    int len = end - i + 1;
                    if (len < minLen) {
                        minLen = len;
                        l = i;
                        r = end;
                    }
                }
            }
        }
        ans = l == -1 ? "" : s1.substring(l, r + 1);
        return ans;
    }

    // str1 从i位置出发找到str2这个子序列整体, 返回最后位置
    // 如果没找到返回-1
    private int getEnd(char[] str1, int i, char[] str2, int[][] smap) {
        int N = str1.length;
        int M = str2.length;
        int j = 0;
        while (i < N && j < M) {
            int pos = smap[i][str2[j] - 'a'];
            if (pos == -1) return -1;
            j++;//找下一个s2里的字符
            i = pos + 1; // 从pos下一个位置开始找s2下一个字母
        }
        // i== N|| j == M
        if (j != M) {
            return -1;
        }
        return --i;
    }

    private int getEnd2(char[] str1, int i, char[] str2, int[][] smap) {
        int N = str1.length;
        int M = str2.length;
        int j = 0;
        while (j < M) {
            if (i >= N) {
                return -1;
            }
            if (str1[i] == str2[j]) {
                i++;
                j++;
            } else {
                i = smap[i][str2[j] - 'a'];
            }
            if (i == -1) {
                return -1;
            }
        }
        return --i;
    }

    // 如果字符种类大于26中, 利用有序表快速找到>= 某一个位置最近的某个字符位置, logN
    // s1中每一个字符开始匹配s2, 每匹配s2中一个字符去有序表查数据 复杂度 O(N*M*logN)
    public String minWindow1(String s1, String s2) {
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) {
            return "";
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length;
        int M = str2.length;
        int minLen = Integer.MAX_VALUE;
        String ans = "";
        Map<Character, TreeSet<Integer>> map = new HashMap<>();
        for (char ch : str2) {
            map.put(ch, new TreeSet<>());
        }
        for (int i = 0; i < N; i++) {
            if (map.containsKey(str1[i])) {
                map.get(str1[i]).add(i);
            }
        }
        int l = -1;
        int r = -1;
        for (int i = 0; i < N; i++) {
            if (str1[i] == str2[0]) { // 从当前i位置开始找一个包含s2子序列的子串
                int end = getEnd1(str1, i, str2, map);
                if (end != -1) {
                    int len = end - i + 1;
                    if (len < minLen) {
                        minLen = len;
                        l = i;
                        r = end;
                    }
                }
            }
        }
        ans = l == -1 ? "" : s1.substring(l, r + 1);
        return ans;

    }

    private int getEnd1(char[] str1, int i, char[] str2, Map<Character, TreeSet<Integer>> map) {
        int N = str1.length;
        int M = str2.length;
        int j = 0;
        while (i < N && j < M) {
            Integer pos = map.get(str2[j]).ceiling(i);
            if (pos == null) return -1;
            j++;//找下一个s2里的字符
            i = pos + 1; // 从pos下一个位置开始找s2下一个字母
        }
        // i== N|| j == M
        if (j != M) {
            return -1;
        }
        return --i;
    }

    // 暴力递归
    public String minWindow3(String s, String t) {
        char[] str = s.toCharArray();
        char[] aim = t.toCharArray();
        int N = str.length;
        int M = aim.length;
        int len = Integer.MAX_VALUE;
        int l = -1;
        int r = -1;
        // 从每一个i位置出发可以整出t的最短长度
        for (int i = 0; i < N; i++) {
            int end = process(str, aim, i, 0);
            if (end != Integer.MAX_VALUE) {
                if (end - i < len) {
                    len = end - i;
                    l = i;
                    r = end;
                }
            }
        }
        return l == -1 ? "" : s.substring(l, r);
    }

    // s[si.....]
    // t[ti....]
    // 把t的整体，都配出来，s在哪能尽早结束！的下个位置
    private int process(char[] s, char[] t, int si, int ti) {
        if (ti == t.length) { // t匹配完了
            return si;
        }
        // t没有匹配万, s到结尾了
        if (si == s.length) {
            return Integer.MAX_VALUE;
        }
        // s,t都没到末尾
        // 可能性1: 不使用si位置去匹配ti位置
        int p1 = process(s, t, si + 1, ti);
        // 可能性1: 使用si位置去匹配ti位置
        int p2 = Integer.MAX_VALUE;
        if (s[si] == t[ti]) {
            p2 = process(s, t, si + 1, ti + 1);
        }
        return Math.min(p1, p2);
    }


    // 动态规划
    public String minWindow4(String s, String t) {
        char[] str = s.toCharArray();
        char[] aim = t.toCharArray();
        int N = str.length;
        int M = aim.length;
        int[][] dp = new int[N + 1][M + 1];
        for (int si = 0; si <= N; si++) {
            dp[si][M] = si;
        }
        for (int ti = 0; ti < M; ti++) {
            dp[N][ti] = Integer.MAX_VALUE;
        }
        for (int si = N - 1; si >= 0; si--) {
            for (int ti = M - 1; ti >= 0; ti--) {
                int r1 = dp[si + 1][ti];
                int r2 = str[si] == aim[ti] ? dp[si + 1][ti + 1] : Integer.MAX_VALUE;
                dp[si][ti] = Math.min(r1, r2);
            }
        }
        int len = Integer.MAX_VALUE;
        int l = -1;
        int r = -1;
        // 遍历整个dp表, 从每一个si出发能整出t的所有答案里求最小
        for (int si = 0; si < str.length; si++) {
            int pos = dp[si][0];
            if (pos != Integer.MAX_VALUE && pos - si < len) {
                len = dp[si][0] - si;
                l = si;
                r = pos;
            }
        }
        return l == -1 ? "" : s.substring(l, r);
    }

    public static void main(String[] args) {
        // String S = "abcdebdde", T = "bde";
        // String S = "jmeqksfr", T = "k";
        String S = "jmeqmr", T = "mm";
        var ans = new Problem_727_MinWindow().minWindow3(S, T);
        System.out.println(ans);
    }
}
