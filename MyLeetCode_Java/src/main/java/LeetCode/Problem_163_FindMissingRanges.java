package LeetCode;

import java.util.ArrayList;
import java.util.List;

// IMP: 区间处理重要基础题, 多看!!, 分情况讨论的技巧, 注意开始结尾的特判处理

public class Problem_163_FindMissingRanges {

    // NOTE: 需要额外注意的是，需要对开始位置 lower 到 nums[0] 和 nums[n -1] 到 upper 进行检查
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            // NOTE: 注意数组为空时, 返回[lower, upper]
            ans.add(genRange(lower, upper));
            return ans;
        }
        int N = nums.length;
        // 处理开头
        if (lower != nums[0]) {
            ans.add(genRange(lower, nums[0] - 1));
        }
        // 中间
        for (int i = 1; i < N; i++) {
            if (nums[i] != nums[i - 1] + 1) {
                ans.add(genRange(nums[i - 1] + 1, nums[i] - 1));
            }
        }
        // 处理结尾
        if (upper != nums[N - 1]) {
            ans.add(genRange(nums[N - 1] + 1, upper));
        }
        return ans;
    }

    // 生成[l,r]的区间
    private String genRange(int l, int r) {
        StringBuilder sb = new StringBuilder();
        if (l == r) {
            sb.append(l);
        } else {
            sb.append(l).append("->").append(r);
        }
        return sb.toString();
    }
}
