package LeetCode;

import java.util.ArrayList;
import java.util.List;

// NOTE: 回看, 使用 flag = -flag 比较巧妙的流程控制

public class Problem_6_ZigZagStr {

    public String convert(String s, int numRows) {
        if (numRows < 2) {
            return s;
        }
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }
        char[] str = s.toCharArray();
        int i = 0;
        int flag = -1;
        for (char c : str) {
            rows.get(i).append(c);
            if (i == 0 || i == numRows - 1) { // 到达两个边界时翻转
                flag = -flag;
            }
            i += flag;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder row : rows) {
            res.append(row);
        }
        return res.toString();
    }

}
