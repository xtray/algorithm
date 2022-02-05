package LeetCode;

import java.util.Arrays;
import java.util.LinkedList;

public class Problem_1996_NumberOfWeekCharacters {


    // O(N*logN)
    // TLE
    public int numberOfWeakCharacters(int[][] properties) {
        if (properties == null || properties.length <= 1) {
            return 0;
        }

        Arrays.sort(properties, ((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]));

        // int L = 0;
        int R = 0;
        int ans = 0;
        for (int L = 0; L < properties.length; L++) { //枚举前一个
            for (R = properties.length - 1; R >= L + 1; R--) { // 不回退
                if (properties[R][0] > properties[L][0] &&
                        properties[R][1] > properties[L][1]) {
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }

    // https://leetcode-cn.com/problems/the-number-of-weak-characters-in-the-game/solution/wei-rao-li-lun-pai-xu-hou-yi-ci-bian-li-amgu8/
    public int numberOfWeakCharacters1(int[][] properties) {
        if (properties == null || properties.length <= 1) {
            return 0;
        }
        // 攻击从大到小排序, 当前出现的角色攻击力一定<=之前的, 记录一个之前遍历过的最大防御力
        // 攻击力相同的防御从小到大排序
        // 如果当前防御力< 之前记录最大防御力==>
        //   那么攻击力一定不可能相等，因为攻击力相等时防御力是递增的
        Arrays.sort(properties, ((o1, o2) -> o1[0] != o2[0] ? o2[0] - o1[0] : o1[1] - o2[1]));
        int maxDef = -1; // 之前的最大攻击力
        int ans = 0;
        for (int i = 0; i < properties.length; i++) { //枚举前一个
            // 当前 i 位置攻击力一定<=之前的, 所以只用判断防御力
            // 如果之前的防御力 maxDef 比当前防御力大
            // 那么 是否可能攻击力和maxDef的攻击力相等呢？ 因为防御力递增排序，如果攻击力相等，那么当前的防御值不可能小于maxDef
            if (properties[i][1] < maxDef ) {
                ans++;
            } else {
                maxDef = properties[i][1]; // 更新最大防御值
            }
        }
        return ans;
    }

    // 单调栈解法
    public int numberOfWeakCharacters2(int[][] properties) {
        //排序
        // 按攻击力从雄安到大排序, 攻击力相同时,按防御力从大到小排序
        Arrays.sort(properties, (o1, o2) -> o1[0] == o2[0] ? (o2[1] - o1[1]) : (o1[0] - o2[0]));
        int count = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        for (int[] p : properties) {
            // 越往后的攻击力大于前面的, 所以只用判断防御力
            // 如果新到来的防御力大于当前的栈顶
            //  则存在攻击力>=栈顶的, 但是因为攻击力相等的时候, 靠后的防御力小
            //  如果当前防御力>=栈顶的, 说明攻击力不相等, 则满足条件
            while (!queue.isEmpty() && queue.peek() < p[1]) {
                queue.pop(); // 所有满足条件的元素结算并弹出
                count++;
            }
            queue.push(p[1]); // 当前元素入栈, 后面再结算
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] p = new int[][]{{1, 5}, {10, 4}, {4, 3}};
        int ans = new Problem_1996_NumberOfWeekCharacters().numberOfWeakCharacters1(p);
        System.out.println(ans);
    }


}
