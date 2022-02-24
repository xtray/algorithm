package AlgoExpert;

import java.util.ArrayList;
import java.util.List;

public class Problem_000_ZigzagTraverse {

    // zigzag打印矩阵
    public static List<Integer> zigzagTraverse(List<List<Integer>> arr) {
        List<Integer> ans = new ArrayList<>();
        if (arr == null || arr.size() == 0) {
            return ans;
        }
        // (a,b) A 先往右, 然后再往下
        int a = 0;
        int b = 0;
        // (c,d) B 先往下, 再往右
        int c = 0;
        int d = 0;
        int row = arr.size() - 1;
        int col = arr.get(0).size() - 1;
        boolean fromUp = true;
        while (a != row + 1) {
            traverseLine(arr,ans,a,b,c,d,fromUp);
            // A  先往右，再往下
            a = b == col ? a + 1 : a;
            b = b == col ? b : b + 1;
            // B  先往下，在往右
            d = c == row ? d + 1 : d;
            c = c == row ? c : c + 1;
            fromUp = !fromUp;
        }
        return ans;
    }

    private static void traverseLine(
            List<List<Integer>> arr,
            List<Integer> ans,
            int a, int b, int c, int d,
            boolean fromUp) {
        if(fromUp) {
            while (a!=c+1) {
                ans.add(arr.get(a++).get(b--));
            }
        } else {
            while (c != a-1) {
                ans.add(arr.get(c--).get(d++));
            }
        }
    }
}
