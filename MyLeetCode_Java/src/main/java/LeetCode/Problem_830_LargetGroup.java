package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_830_LargetGroup {

    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int L = 0;
        int R = 1;
        while (R < N) {
            if (str[R] == str[R - 1]) {
                R++;
            } else {
                if (R - L >= 3) {
                    List<Integer> list = new ArrayList<>();
                    list.add(L);
                    list.add(R - 1); // R是不等的, 下一个位置
                    ans.add(list);
                }
                L = R;
                R++;
            }
        }
        // NOTE: 不要忘了最后一段!!
        if (R - L >= 3) {
            List<Integer> list = new ArrayList<>();
            list.add(L);
            list.add(R - 1); // R是不等的, 下一个位置
            ans.add(list);
        }
        return ans;
    }
}
