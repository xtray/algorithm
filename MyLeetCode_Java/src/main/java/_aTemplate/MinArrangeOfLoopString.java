package _aTemplate;


// https://www.cnblogs.com/StungYep/p/12251951.html


public class MinArrangeOfLoopString {


    // 字符串的最小表示法
    public static int getMinPos(String s) {
        char[] str = s.toCharArray();
        int N = str.length;
        int i = 0; // 字符a
        int j = 1; // 字符b
        int k = 0; // offset, 0~N-1
        while (i < N && j < N && k < N) {  // k<N, 最多比较一轮
            // 比较两个字符的字典序大小
            char a = str[(i + k) % N]; // 轮转取
            char b = str[(j + k) % N];
            if (a == b) {
                k++;
            } else {
                // 找到第一个不同的位置
                if (a > b) {
                    i = i + k + 1;
                } else {
                    j = j + k + 1;
                }
                if (i == j) {
                    j++; // 跳过相等的位置
                }
                k = 0;
            }
        }
        return Math.min(i, j);
    }

    public static void main(String[] args) {
        // String s = "1001";
        String s = "abda";
        int minPos = getMinPos(s);
        System.out.println(s.substring(minPos) + s.substring(0, minPos));
    }
}
