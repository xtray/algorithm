package _aTemplate;

public class KMP {

    // O(N)
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
        while(i< str2.length) {
            if(str2[i-1] == str2[cn]) { // 匹配成功的时候
                next[i++] = ++cn;
            } else if(cn > 0) { // 没配成功, cn还能继续往左跳
                cn = next[cn];
            } else { // 没配成功, cn不能往左跳
                next[i++] = 0;
            }
        }
        return next;
    }
}
