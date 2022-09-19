package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_89_GrayCode {

    // https://leetcode-cn.com/problems/gray-code/solution/gray-code-jing-xiang-fan-she-fa-by-jyd/
    // 由 N 阶 G(N) 构造 G(N+1)阶 的方法
    // N+1阶前一半在G(N)的基础上前面加0, 即不需要变化
    // N+1 阶的后一半在G(N)的逆序排列R(N)的每一个前面+1
    // 合并G(N) + R'(N)
    public List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();
        ans.add(0); // 0阶格雷码
        for (int i = 1; i <= n; i++) {
            for (int j = ans.size() - 1; j >= 0; j--) { // 相当于每次生成后一半
                ans.add(1 << (i - 1) | ans.get(j)); // 逆序前面+1
            }
        }
        return ans;
    }
}
