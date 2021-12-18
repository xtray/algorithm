package Jianzhi;

import java.util.ArrayList;
import java.util.List;

public class Problem_JZ38_Permutation {

    public String[] permutation(String word) {
        if (word == null || word.length() == 0) {
            return new String[]{};
        }
        char[] str = word.toCharArray();
        List<String> res = new ArrayList<>();
        process(str, 0, res);
        String[] ans = new String[res.size()];
        int index = 0;
        for(String item : res) {
            ans[index++] = item;
        }
        return ans;
    }

    // str[0...i-1]已经做好决定的, 当前来到 i 位置处理
    // i...len-1位置的字符都可以来到 i 位置
    private void process(char[] str, int i, List<String> res) {
        if (i == str.length) {
            res.add(String.valueOf(str));
            return;
        }
        boolean[] visit = new boolean[26];
        for (int j = i; j < str.length; j++) {
            if (!visit[str[j] - 'a']) {
                visit[str[j] - 'a'] = true;
                swap(str, i, j);
                process(str, i + 1, res);
                swap(str, i, j); // 恢复现场
            } // else: 当前位置已经来过这个字符了
        }
    }

    private void swap(char[] str, int i, int j) {
        char tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
    }

    public static void main(String[] args) {
        Problem_JZ38_Permutation sl = new Problem_JZ38_Permutation();
        String s = "aac";
        String[] ans2 = sl.permutation(s);
        for (String str : ans2) {
            System.out.println(str);
        }
    }
}
