package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_423_OriginalDigits {

    public String originalDigits(String target) {
        if(target == null || target.length()==0) {
            return target;
        }
       // "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
        int[] tCounts = new int[26];
        for (char t : target.toCharArray()) {
            tCounts[t - 'a']++;
        }
        //z, w, u, x, g 都只在一个数字中，即 0,2,4,6,8 中出现
        // 即 z 出现等同于 zero 出现, z 的次数就是 zero 的次数
        int[] cnt = new int[10];
        cnt[0] = tCounts['z' - 'a'];
        cnt[2] = tCounts['w'- 'a'];
        cnt[4] = tCounts['u'- 'a'];
        cnt[6] = tCounts['x'- 'a'];
        cnt[8] = tCounts['g'- 'a'];

        cnt[3] = tCounts['h'- 'a'] - cnt[8]; // h 只出现在 3,8
        cnt[5] = tCounts['f'- 'a'] - cnt[4]; // f 只出现在 4,5
        cnt[7] = tCounts['s'- 'a'] - cnt[6]; // s 只出现在 6,7

        cnt[1] = tCounts['o'- 'a'] - cnt[0] - cnt[2] - cnt[4]; // o 出现在 0,1,2,4

        cnt[9] = tCounts['i'- 'a'] - cnt[5] - cnt[6] - cnt[8];

        StringBuffer ans = new StringBuffer();
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < cnt[i]; ++j) {
                ans.append((char) (i + '0'));
            }
        }
        return ans.toString();
    }


    public static void main(String[] args) {
        Problem_423_OriginalDigits sl = new Problem_423_OriginalDigits();
        String input = "zerozero";
        var ans = sl.originalDigits(input);
        System.out.println(ans);
    }

}
