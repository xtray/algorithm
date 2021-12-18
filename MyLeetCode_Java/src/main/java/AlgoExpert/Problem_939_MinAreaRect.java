package AlgoExpert;

import java.util.*;

public class Problem_939_MinAreaRect {


    // 方法 1: 枚举每一条右侧的边
    public static int minAreaRect(int[][] points) {
        if (points == null || points.length == 0 || points[0] == null || points[0].length == 0) {
            return 0;
        }
        Map<Integer, List<Integer>> rows = new TreeMap<>(); // 按 x排序
        for (int[] point : points) {
            int x = point[0];
            int y = point[1];
            if (!rows.containsKey(x)) {
                rows.put(x, new ArrayList<>());
            }
            rows.get(x).add(y);
        }
        int ans = Integer.MAX_VALUE;
        Map<Integer, Integer> border = new HashMap<>(); // 存每一个左侧的边
        for (int x : rows.keySet()) { // 按顺序枚举每一个 x
            List<Integer> row = rows.get(x); // 取出所有的 y 并排序
            Collections.sort(row);
            // 任意两点做右侧边跟左边存在的边构成矩形
            for (int i = 0; i < row.size(); i++) {
                for (int j = i + 1; j < row.size(); j++) {
                    int y1 = row.get(i);
                    int y2 = row.get(j);
                    int hashcode = y1 * 40001 + y2;
                    if (border.containsKey(hashcode)) { // 有对应的左侧边
                        ans = Math.min(ans, (x - border.get(hashcode)) * (y2 - y1));
                    }
                    border.put(hashcode, x); // (左边特征, 左边的 x 值)
                }
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    // 方法 2: 枚举每一条对角线
    public static int minAreaRect2(int[][] points) {
        if (points == null || points.length == 0 || points[0] == null || points[0].length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int[] point : points) {
            set.add(40001 * point[0] + point[1]);
        }
        int N = points.length;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int x1 = points[i][0];
                int y1 = points[i][1];
                int x2 = points[j][0];
                int y2 = points[j][1];
                if (x1 != x2 && y1 != y2) { // 是一条对角线
                    // 找另外两个点看是否存在
                    // (x1, y2) , (x2, y1)
                    if (set.contains(x1 * 40001 + y2) && set.contains(x2 * 40001 + y1)) {
                        ans = Math.min(ans, Math.abs((x2 - x1) * (y2 - y1)));
                    }
                }

            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public static void main(String[] args) {
        int[][] points = new int[][]{{1, 1}, {1, 3}, {3, 1}, {3, 3}, {2, 2}};
        int res = minAreaRect(points);
        System.out.println(res);
    }
}
