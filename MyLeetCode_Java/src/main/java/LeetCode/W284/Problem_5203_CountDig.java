package LeetCode.W284;

import java.util.HashMap;
import java.util.Map;

public class Problem_5203_CountDig {
    public int digArtifacts(int n, int[][] artifacts, int[][] dig) {

        // UID, BlockID
        Map<Integer, Integer> idxMap = new HashMap<>();
        // BlockID, Size
        Map<Integer, Integer> sizeMap = new HashMap<>();
        int idx = 0;
        for (int[] block : artifacts) {
            int x1 = block[0];
            int y1 = block[1];
            int x2 = block[2];
            int y2 = block[3];
            int size = (x2 - x1 + 1) * (y2 - y1 + 1);
            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    int onePos = x * n + y;
                    idxMap.put(onePos, idx);
                }
            }
            sizeMap.put(idx++, size);
        }
        int ans = 0;
        for (int[] d : dig) {
            int dx = d[0];
            int dy = d[1];
            int oneD = dx * n + dy;
            if (idxMap.containsKey(oneD)) {
                int bid = idxMap.get(oneD);
                sizeMap.put(bid, sizeMap.get(bid) - 1);
                if(sizeMap.get(bid) == 0) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // int n = 2;
        // int[][] artifacts = {{0, 0, 0, 0}, {0, 1, 1, 1}};
        // int[][] dig = {{0, 0}, {0, 1}};
        int n = 2;
        int[][] artifacts = {{0, 0, 0, 0}, {0, 1, 1, 1}};
        int[][] dig = {{0, 0}, {0, 1}, {1,1}};
        var ans = new Problem_5203_CountDig().digArtifacts(n, artifacts, dig);
        System.out.println(ans);
    }


}
