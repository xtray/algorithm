package _DailyTarget;

import java.util.*;
import java.util.Map;

public class Problem_2013_DetectSquares_2 {

    class DetectSquares {

        // [ X, [Y, 个数]]
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

        public DetectSquares() {

        }

        public void add(int[] point) {
            int x = point[0];
            int y = point[1];
            // NOTE: 不能用getOrDefault, new出来的map并没有关联到map上
            // Map<Integer, Integer> yMap = map.getOrDefault(x, new HashMap<>());
            Map<Integer, Integer> yMap = map.computeIfAbsent(x, key -> new HashMap<>());
            yMap.put(y, yMap.getOrDefault(y, 0) + 1);
        }

        public int count(int[] point) {
            int ans = 0;
            int x = point[0];
            int y = point[1];
            // 如果没有跟检测点在同一个x方向上的点, 无法构成
            if (!map.containsKey(x)) return 0;
            // 查找同x方向的所有y
            Map<Integer, Integer> yMap = map.get(x);
            // 枚举 y, 得到 边长
            for (int ny : yMap.keySet()) {
                // (x, y) (x, ny) 定出边长, 再找另外两个点
                if (ny == y) continue; // 跳过重合点
                int cnt2 = yMap.get(ny); // 第二个点的个数, 第一个检测点重叠时只算一份
                int edge = ny- y;
                // 统计左侧
                Map<Integer, Integer> leftMap = map.getOrDefault(x - edge, new HashMap<>());
                int leftUp = leftMap.getOrDefault(ny, 0);
                int leftDown = leftMap.getOrDefault(y, 0);
                // 统计右侧
                Map<Integer, Integer> rightMap = map.getOrDefault(x + edge, new HashMap<>());
                int rightUp = rightMap.getOrDefault(ny, 0);
                int rightDown = rightMap.getOrDefault(y, 0);
                ans += cnt2 * (leftUp * leftDown + rightUp * rightDown);
            }
            return ans;
        }
    }

}
