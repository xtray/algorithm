package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_89_GrayCode {

    // https://leetcode-cn.com/problems/gray-code/solution/gray-code-jing-xiang-fan-she-fa-by-jyd/
    // 由 N 阶 G(N) 构造 G(N+1)阶 的方法
    // N+1阶前一半在 G(N)的基础上前面加 0, 即不需要变化
    // N+1 阶的后一半
    //   为了保证相差只有一位, 让 G(N)集合的排序逆序排列为 R(N), 在每一个前面+1 为 R'(N)
    // 合并G(N) + R'(N)
    public List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();
        ans.add(0); // 0阶格雷码
        for (int i = 1; i <= n; i++) {
            for (int j = ans.size() - 1; j >= 0; j--) {
                ans.add(1 << (i-1) | ans.get(j));
            }
        }
        return ans;
    }
}
