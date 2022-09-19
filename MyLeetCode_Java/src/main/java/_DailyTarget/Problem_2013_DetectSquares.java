package _DailyTarget;

import java.util.*;

public class Problem_2013_DetectSquares {

    // 构建: (x, (y, cnt))的哈希表, 枚举跟检测点相同x的所有y, 得到边长, 再推算另外两个点的个数
    // 注意哈希表查不到的点赋一个空的默认值
    class DetectSquares {

        private Map<Integer, Map<Integer, Integer>> map;

        public DetectSquares() {
            map = new HashMap<>();
        }

        public void add(int[] point) {
            int x = point[0];
            int y = point[1];
            map.computeIfAbsent(x, k -> new HashMap<>());
            Map<Integer, Integer> cntMap = map.get(x);
            cntMap.put(y, cntMap.getOrDefault(y, 0) + 1);
        }

        public int count(int[] point) {
            int x = point[0];
            int y = point[1];
            if (!map.containsKey(x)) return 0;
            Map<Integer, Integer> cntMap = map.get(x);
            int ans = 0;
            // 枚举跟检查点x相同的每一个y
            for (int ny : cntMap.keySet()) {
                if (ny == y) continue; // 跟检测点共点的跳过
                int cnt1 = cntMap.get(ny);
                if (cnt1 == 0) continue;
                // int edge = Math.abs(ny - y);
                int edge = ny - y; // 负值无所谓, 因为是左右侧
                // (x, y),  (x, ny)
                // 查找另外两个点
                // 统计左侧: x - edge
                Map<Integer, Integer> leftMap = map.getOrDefault(x - edge, new HashMap<>());
                int left1 = leftMap.getOrDefault(y, 0);
                int left2 = leftMap.getOrDefault(ny, 0);
                // 统计右侧: x + edge
                Map<Integer, Integer> rightMap = map.getOrDefault(x + edge, new HashMap<>());
                int right1 = rightMap.getOrDefault(y, 0);
                int right2 = rightMap.getOrDefault(ny, 0);

                ans += cnt1 * (left1 * left2 + right1 * right2);
            }
            return ans;
        }
    }

    class DetectSquares1 {
        private int N = 1001;
        private int[][] cnts = new int[N][N];

        public void add(int[] point) {
            int x = point[0];
            int y = point[1];
            cnts[x][y]++;
        }

        public int count(int[] point) {
            int x = point[0];
            int y = point[1];
            int ans = 0;
            for (int ny = 0; ny < N; ny++) {
                if (y == ny) continue;
                int c1 = cnts[x][ny];
                if (c1 == 0) continue;
                int len = y - ny;
                int[] nums = new int[]{x + len, x - len};
                for (int nx : nums) {
                    if (nx < 0 || nx >= N) continue; // 注意检查越界
                    int c2 = cnts[nx][y];
                    int c3 = cnts[nx][ny];
                    ans += c1 * c2 * c3;
                }
            }
            return ans;
        }
    }

}
