package LeetCode;

import java.util.HashSet;
import java.util.Set;

public class Problem_2350_ShortestSubSeq {

    /**
     * 要想出现长度为1的子序列, 那么需要有1~k的所有数
     * 在长度为1的子序列基础上, 如果想出现长度为2的所有子序列, 即第2位是1~k, 必须要再有一组完整的1~k出现
     * 在长度为2的子序列基础上, 如果想出现长度为3的所有子序列, 即第3位是1~k, 必须要再有一组完整的1~k出现
     * 依次类推
     */

    public int shortestSequence(int[] rolls, int k) {
        int ans = 1;
        Set<Integer> set = new HashSet<>();
        for (int num : rolls) {
            set.add(num);
            if (set.size() == k) { // NOTE: set长度等于k, 说明1~k都出现了!!
                ans++;
                set.clear();
            }
        }
        return ans;
    }


}
