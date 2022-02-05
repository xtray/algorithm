package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class Problem_2013_DetectSquares {

    class DetectSquares {

        // (x, (y, cnt))
        private Map<Integer, Map<Integer, Integer>> cntxMap;

        public DetectSquares() {
            cntxMap = new HashMap<>();
        }

        public void add(int[] point) {
            int x = point[0];
            int y = point[1];
            if(!cntxMap.containsKey(x)) {
                cntxMap.put(x, new HashMap<>());
            }
            Map<Integer, Integer> cntMap = cntxMap.get(x);
            cntMap.put(y, cntMap.getOrDefault(y, 0) + 1);
        }

        public int count(int[] point) {
            int x = point[0];
            int y = point[1];
            int ans = 0;
            // 枚举每一个 x 相同的所有的 Y
            Map<Integer, Integer> yCntMap = cntxMap.getOrDefault(x, new HashMap<>());
            for(int ny : yCntMap.keySet()) {
                if(ny == y ) continue;
                int edge = Math.abs(ny - y);
                // int nx = x;
                // x, y   nx, ny  edge
                int cnt1 = yCntMap.get(ny);
                // x+edge, y
                Map<Integer, Integer> rightMap = cntxMap.getOrDefault(x+edge, new HashMap<>());
                int right1 = rightMap.getOrDefault(y, 0);
                // x+edge, ny
                int right2 = rightMap.getOrDefault(ny, 0);

                // x-edge, y
                Map<Integer, Integer> leftMap = cntxMap.getOrDefault(x-edge, new HashMap<>());
                int left1 = leftMap.getOrDefault(y, 0);
                // x-edge, ny
                int left2 = leftMap.getOrDefault(ny, 0);
                ans += cnt1*(right1*right2 + left1*left2);
            }
            return ans;
        }
    }
}
