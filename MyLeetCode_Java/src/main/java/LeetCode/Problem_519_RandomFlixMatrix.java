package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class Problem_519_RandomFlixMatrix {
    private int row;
    private int col;
    private int right; // 无效区第一个位置
    // key: 1Dpos
    // value: 代表的 pos
    private Map<Integer, Integer> map= new HashMap<>();;

    public Problem_519_RandomFlixMatrix(int m, int n) {
        row = m;
        col = n;
        right = m * n;
    }

    public int[] flip() {
        int randPos = (int) (Math.random() * right--);
        int retPos = map.getOrDefault(randPos, randPos);
        map.put(randPos, map.getOrDefault(right, right)); // 注意! right的这个位置也有可能已经用了
        return new int[]{retPos / col, retPos % col};
    }

    public void reset() {
        map.clear();
        right = row * col;
    }

    public static void main(String[] args) {
        var solution = new Problem_519_RandomFlixMatrix(3, 1);
        var pos = solution.flip();  // 返回 [1, 0]，此时返回 [0,0]、[1,0] 和 [2,0] 的概率应当相同
        pos = solution.flip();  // 返回 [2, 0]，因为 [1,0] 已经返回过了，此时返回 [2,0] 和 [0,0] 的概率应当相同
        pos = solution.flip();  // 返回 [0, 0]，根据前面已经返回过的下标，此时只能返回 [0,0]
        solution.reset(); // 所有值都重置为 0 ，并可以再次选择下标返回
        pos = solution.flip();  // 返回 [2, 0]，此时返回 [0,0]、[1,0] 和 [2,0] 的概率应当相同

    }
}
