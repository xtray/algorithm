package AlgoExpert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem_000_RectangleMania {

    // 枚举对角线上的两个点
    // 所有点放入 hash 表
    public static int rectangleMania(List<Integer[]> coords) {
        // Write your code here.
        Set<String> hashSet = new HashSet<>();
        hashSet = getHashSet(coords);
        return getRectCount(coords, hashSet);
    }

    private static int getRectCount(List<Integer[]> coords, Set<String> set) {
        int count = 0;
        // 枚举一对对角线上的点
        for (Integer[] coord1 : coords) {
            for (Integer[] coord2 : coords) {
                if(isInUpperRight(coord1, coord2)) {
                    // 定位另外一对对角线上的点
                    int x1 = coord1[0];
                    int y1 = coord1[1];
                    int x2 = coord2[0];
                    int y2 = coord2[1];
                    String x3y3 = x1 + "_" + y2;
                    String x4y4 = x2 + "_" + y1;
                    if(set.contains(x3y3)&&set.contains(x4y4)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private static boolean isInUpperRight(Integer[] coord1, Integer[] coord2) {
        return coord2[0] > coord1[0] && coord2[1] > coord1[1];
    }

    private static Set<String> getHashSet(List<Integer[]> coords) {
        Set<String> hashSet = new HashSet<>();
        for (Integer[] coord : coords) {
            hashSet.add(coord[0] + "_" + coord[1]);
        }
        return hashSet;
    }

    static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
