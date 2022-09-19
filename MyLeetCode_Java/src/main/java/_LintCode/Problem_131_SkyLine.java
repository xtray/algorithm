package _LintCode;

import java.util.*;

public class Problem_131_SkyLine {

    // tag: 扫描线

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        List<List<Integer>> ans = new ArrayList<>();

        int N = buildings.length;
        // 整理为事件
        // [类型, 高度, 时间]
        // 0: 开始, 1: 结束
        int[][] events = new int[N << 1][3];
        int idx = 0;
        for (int[] b : buildings) {
            events[idx][0] = 0;
            events[idx][1] = b[2];
            events[idx][2] = b[0];
            events[idx + N][0] = 1; // 结束
            events[idx + N][1] = b[2];
            events[idx + N][2] = b[1];
            idx++;
        }
        // 按时间排序
        Arrays.sort(events, (o1, o2) -> o1[2] == o2[2] ? o1[0] - o2[0] : o1[2] - o2[2]);
        // 保存每个时间点上最大高度的次数
        // 单点最大高度 --> 线段树?
        TreeMap<Integer, Integer> cntMap = new TreeMap<>();// 每个高度对应次数, 找到最大
        LinkedHashMap<Integer, Integer> posMap = new LinkedHashMap<>(); // 保存每个点的高度
        for (int[] e : events) {
            if (e[0] == 0) { // 开始事件
                cntMap.put(e[1], cntMap.getOrDefault(e[1], 0) + 1);
            } else {
                cntMap.put(e[1], cntMap.get(e[1]) - 1);
                if (cntMap.get(e[1]) == 0) {
                    cntMap.remove(e[1]);
                }
            }
            // 获取当前x点的最大高度
            int height = cntMap.isEmpty() ? 0 : cntMap.lastKey();
            posMap.put(e[2], height);
        }
        // 根据posMap生成答案
        int start = -1;
        int height = -1;
        int end = -1;
        int preH = -1;
        for (int key : posMap.keySet()) {
            int curX = key;
            int curH = posMap.get(key);
            if (curH != preH) { // 高度有变化, 生成数据
                if (start == -1) {
                    start = curX;
                    height = curH;
                } else {
                    end = curX;
                    List<Integer> list = new ArrayList<>();
                    list.add(start);
                    list.add(end);
                    list.add(height);
                    ans.add(list);
                    if (curH != 0) { // 非0时持续生成数据
                        start = curX;
                        height = curH;
                    } else { // 遇到0, 重置, 重新开始
                        start = -1;
                    }
                }
            }
            preH = curH;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] buildings = new int[][]{
                {1, 3, 3},
                {2, 4, 4},
                {5, 6, 1}
        };
        var ans = new Problem_131_SkyLine().buildingOutline(buildings);
        System.out.println(ans);
    }
}
