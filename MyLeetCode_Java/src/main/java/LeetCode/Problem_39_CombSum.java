package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_39_CombSum {

    public List<List<Integer>> combinationSum(int[] cand, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (cand == null || cand.length == 0) {
            return res;
        }
        process(cand, 0, target, new ArrayList<>(), res);
        return res;
    }

    private void process(int[] cand, int i, int rest, List<Integer> ans, List<List<Integer>> res) {
        if (rest < 0) {
            return;
        }
        if (i == cand.length) {
            if (rest == 0) {
                res.add(new ArrayList<>(ans));
            }
            return;
        }
        // 不要i位置
        process(cand, i + 1, rest, ans, res);
        // 要i位置
        ans.add(cand[i]);
        process(cand, i, rest - cand[i], ans, res);
        ans.remove(Integer.valueOf(cand[i]));
    }


}
