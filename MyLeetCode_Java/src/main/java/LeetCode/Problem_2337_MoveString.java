package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_2337_MoveString {

    // dp[i][j]
    // str1 从i位置开始能不能经过移动跟str2从j位置开始移动匹配
    public boolean canChange(String s1, String s2) {
        if (!s1.replace("_", "").equals(s2.replace("_", ""))) {
            return false;
        }
        int N = s1.length();
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (str1[i] != '_') {
                list.add(new int[]{str1[i] - 'L', i});
            }
        }
        int k = 0;
        int M = list.size();
        for (int j = 0; j < N; j++) {
            if (str2[j] == '_') continue;
            // target里的L,R来消化list中的LR, 应该是一一对应的
            if (k < M && (str2[j] == 'L' && j > list.get(k)[1] || str2[j] == 'R' && j < list.get(k)[1])) {
                return false;
            }
            k++;
        }
        return true;
    }

    public boolean canChange1(String s1, String s2) {
        // replaceAll基于正则替换,速度慢
        // if (!s1.replaceAll("_", "").equals(s2.replaceAll("_", ""))) {
        //     return false;
        // }
        if (!s1.replace("_", "").equals(s2.replace("_", ""))) {
            return false;
        }
        int N = s1.length();
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int i = 0;
        int j = 0;
        // NOTE: target里的L,R来消化start中的L,R, 他们是一一对应的
        while (i < N && j < N) {
            while (i < N && str1[i] == '_') i++;
            while (j < N && str2[j] == '_') j++;
            // str1[i] == 'L' && i >= j
            // str1[i] == 'R' && i <= j
            if (i < N && (str1[i] == 'L' && i < j || str1[i] == 'R' && i > j)) {
                return false;
            }
            i++;
            j++;
        }
        return true;
    }


    public static void main(String[] args) {
        String start = "_L__R__R_";
        String target = "L___R__R_";
        var ans = new Problem_2337_MoveString().canChange(start, target);
        System.out.println(ans);
    }
}