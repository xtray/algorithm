package L_INPRG;

import java.util.List;
import java.util.TreeSet;

public class Problem_632_MinRange {

    public static class Node {
        public int val;
        public int arrIdx;
        public int idx;

        public Node(int v, int aid, int id) {
            val = v;
            arrIdx = aid;
            idx = id;
        }
    }

    public int[] smallestRange(List<List<Integer>> nums) {
        if (nums == null || nums.size() == 0) {
            return new int[]{};
        }
        int N = nums.size();
        TreeSet<Node> treeSet = new TreeSet<>((o1, o2) -> o1.val != o2.val ? o1.val - o2.val : o1.arrIdx - o2.arrIdx);
        for (int i = 0; i < N; i++) {
            treeSet.add(new Node(nums.get(i).get(0), i, 0));
        }
        int r = Integer.MAX_VALUE;
        int a = 0;
        int b = 0;
        while (treeSet.size() == N) {
            Node max = treeSet.last();
            Node min = treeSet.pollFirst(); // NOTE: 先取最后, 再poll最小, 否则当只有一个数组一个元素时: [[1]] 会报空错误
            if (max.val - min.val < r) {
                r = max.val - min.val;
                a = min.val;
                b = max.val;
            }
            if (min.idx < nums.get(min.arrIdx).size() - 1) {
                treeSet.add(new Node(nums.get(min.arrIdx).get(min.idx + 1), min.arrIdx, min.idx + 1));
            }
        }
        return new int[]{a, b};
    }

}
