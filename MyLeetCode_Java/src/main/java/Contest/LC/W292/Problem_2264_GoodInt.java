package Contest.LC.W292;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.cn/problems/largest-3-same-digit-number-in-string/

public class Problem_2264_GoodInt {

    public String largestGoodInteger(String num) {
        if (num == null || num.length() == 0) {
            return "";
        }
        int N = num.length();
        List<List<Integer>> numList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numList.add(new ArrayList<>());
        }
        char[] str = num.toCharArray();
        for (int i = 0; i < N; i++) {
            int idx = str[i] - '0';
            numList.get(idx).add(i);
        }
        String maxRes = "";
        for (List<Integer> list : numList) {
            if (list.size() < 3) continue;
            String cur = getCurNum(num, list);
            if (cur.compareTo(maxRes) > 0) {
                maxRes = cur;
            }
        }
        return maxRes;
    }

    private String getCurNum(String num, List<Integer> list) {
        String res = "";
        for (int i = 0; i + 2 < list.size(); i++) {
            if (list.get(i + 2) - list.get(i) + 1 == 3) {
                res = num.substring(list.get(i), list.get(i + 2) + 1);
                return res;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        // String num = "6777133339";
        // String num = "42352338";
        // String num = "7447444";
        String num = "74474444";
        var ans = new Problem_2264_GoodInt().largestGoodInteger(num);
        System.out.println(ans);
    }
}
