package _Contest.LC.W303;

import java.util.HashMap;
import java.util.Map;

public class Problem_2352_EqualRowColPairs {

    public int equalPairs(int[][] grid) {
        Map<String, Integer> map = new HashMap<>();
        int n = grid.length;
        int m = grid[0].length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(grid[i][j]).append("_");
            }
            String key = sb.toString();
            map.put(key, map.getOrDefault(key, 0) + 1);
            sb.setLength(0);
        }
        int ans = 0;
        sb.setLength(0);
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                sb.append(grid[i][j]).append("_");
            }
            String key = sb.toString();
            if (map.containsKey(key)) {
                ans += map.get(key);
            }
            sb.setLength(0);
        }
        return ans;
    }
}
