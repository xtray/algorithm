package _Exercise;

public class Problem_459_RepeatedSubstr {


    // 从小到大枚举长度, 长度最长到n的一半, O(N^2)
    public boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        // 移位操作优先级大于大小比较
        // 枚举前缀串的长度
        for (int len = 1; len <= N >> 1; len++) {
            if (N % len != 0) continue; // 不能整除, 跳过该长度
            boolean match = true;
            for (int j = len; j < N; j++) {
                // NOTE: 对任何 j ∈ [len, N), 存在: s[j] == s[j - len]
                if (str[j] != str[j - len]) {
                    match = false;
                    break; // 换下一个长度
                }
            }
            if (match) { // 有任意一个长度匹配, 返回true
                return true;
            }
        }
        return false;
    }

    // 重复字符串至少重复n（n>=2）次才满足，用s+s则至少2n次重复，破环第一个和最后一个，
    // 在n>=2的前提下2n-2>=n 恒成立,所以中间至少有一个重复n次的字符串。
    // 源字符串A 由 S + S组成, 即重复两次是最极端情况, 那么 A + A = S + S + S + S
    // 破坏首尾以后, 中间要有一个完整的A,
    // 其他情况, 如果A = S + S + S, A+A 破坏首尾, 中间也满足
    // 极端情况, S = 1, 那么破坏首尾, 也满足
    public boolean repeatedSubstringPattern1(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        return (s + s).substring(1, s.length() * 2 - 1).contains(s);
    }


    public boolean repeatedSubstringPattern2(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        // index == s.len 说明找到了第二个复制的s上
        // return (s + s).indexOf(s, 1) != s.length();
        return (s + s).indexOf(s, 1) < s.length();
    }

    public boolean repeatedSubstringPattern3(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        return getIndexOf((s + s).substring(1, s.length() * 2 - 1), s) != -1;
    }

    public static int getIndexOf(String s1, String s2) {
        if (s1 == null || s2 == null || s2.length() < 1 || s1.length() < s2.length()) {
            return -1;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int x = 0; // str1里比对到的位置
        int y = 0; // str2里比对到的位置
        // O(M), M<=N
        int[] next = getNextArray(str2);
        // O(N)
        while (x < str1.length && y < str2.length) {
            if (str1[x] == str2[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }
        return y == str2.length ? x - y : -1;
    }

    // next数组
    private static int[] getNextArray(char[] str2) {
        if (str2.length == 1) {
            return new int[]{-1}; // 任何字符串0位置都是-1
        }
        int[] next = new int[str2.length];
        next[0] = -1; // 人为规定
        next[1] = 0;
        int i = 2; // 目前开始求next数组值的位置
        int cn = 0; // 跟i位置比对的位置, i开始2位置, 前一个是1位置, 上面的next数组是0, cn: count?
        // 三个分支是互斥的, 只会进一个, while循环发生的次数和三种分支的总次数一回事
        // 所以可以用三个分支总共的发生次数的极限来估计while发生的次数
        while (i < str2.length) {
            if (str2[i - 1] == str2[cn]) { // 匹配成功的时候
                next[i++] = ++cn;
            } else if (cn > 0) { // 没配成功, cn还能继续往左跳
                cn = next[cn];
            } else { // 没配成功, cn不能往左跳
                next[i++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String s = "aba";
        var ans = new Problem_459_RepeatedSubstr().repeatedSubstringPattern3(s);
        System.out.println(ans);
    }
}
