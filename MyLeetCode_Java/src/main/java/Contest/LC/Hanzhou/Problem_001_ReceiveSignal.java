package Contest.LC.Hanzhou;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.cn/contest/zj-future2022/problems/WYKGLO/
public class Problem_001_ReceiveSignal {

    // 扫描线
    public boolean canReceiveAllSignals(int[][] intervals) {
        List<int[]> list = new ArrayList<>();
        for (int[] interval : intervals) {
            list.add(new int[]{interval[0], 1}); // 开始的时间+1
            list.add(new int[]{interval[1], -1}); // 结束的时间-1
        }
        // NOTE: 按时间排序, 时间一样的, 下车的在前, 上车的在后
        list.sort((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        // 进行扫描
        int cnt = 0;
        int ans = 0;
        for (int[] p : list) {
            cnt += p[1];
            ans = Math.max(ans, cnt);
        }
        return ans <= 1;
    }

    public static void main(String[] args) {
        int[][] signals = {{2,8}, {8,14}};
        var ans = new Problem_001_ReceiveSignal().canReceiveAllSignals(signals);
        System.out.println(ans);
    }
}
