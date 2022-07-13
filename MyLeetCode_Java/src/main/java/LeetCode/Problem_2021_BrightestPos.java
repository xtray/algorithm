package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// IMP: 学习用TreeMap处理区间不好处理的差分问题
// TAG: 扫描线 / 差分

public class Problem_2021_BrightestPos {

    // 差分1
    public int brightestPosition(int[][] lights) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int[] light : lights) {
            int left = light[0] - light[1];
            int right = light[0] + light[1] + 1;
            map.put(left, map.getOrDefault(left, 0) + 1);
            map.put(right, map.getOrDefault(right, 0) - 1);
        }
        int maxPos = -1;
        int maxVal = 0;
        int cur = 0;
        for (int pos : map.keySet()) {
            cur += map.get(pos);
            if (cur > maxVal) {
                maxVal = cur;
                maxPos = pos;
            }
        }
        return maxPos;
    }

    // 差分2
    public int brightestPosition2(int[][] lights) {
        List<int[]> list = new ArrayList<>();
        for (int[] light : lights) {
            int left = light[0] - light[1];
            int right = light[0] + light[1] + 1;
            list.add(new int[]{left, 1}); // 开始的时间 +1
            list.add(new int[]{right, -1}); // 结束的下一个位置 -1
        }
        list.sort((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        int pos = -1;
        int cnt = 0;
        int max = 0;
        for (int[] p : list) {
            cnt += p[1];
            if (cnt > max) {
                max = cnt;
                pos = p[0];
            }
        }
        return pos;
    }


    // 扫描线
    public int brightestPosition1(int[][] lights) {
        List<int[]> list = new ArrayList<>();
        for (int[] light : lights) {
            int left = light[0] - light[1];
            int right = light[0] + light[1];
            list.add(new int[]{left, 1}); // 上车+1
            list.add(new int[]{right, -1}); // 下车-1
        }
        // NOTE: 按时间排序, 时间一样的点需要先加后减, 区别于传统的扫描线, 这里的排序是反的, 因为灯光可以叠加
        list.sort((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o2[1] - o1[1]);
        // 进行扫描
        int pos = -1;
        int cnt = 0;
        int max = 0;
        for (int[] p : list) {
            cnt += p[1];
            if (cnt > max) {
                max = cnt;
                pos = p[0];
            }
        }
        return pos;
    }

    public static void main(String[] args) {
        int[][] lights = {{-3, 2}, {1, 2}, {3, 3}};
        var ans = new Problem_2021_BrightestPos().brightestPosition1(lights);
        System.out.println(ans);
    }
}
