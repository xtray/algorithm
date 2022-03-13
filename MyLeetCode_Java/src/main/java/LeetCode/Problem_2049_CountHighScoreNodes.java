package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem_2049_CountHighScoreNodes {

    // 当前节点的子节点个数
    public static class Info {
        public int childCnt;
        public Info(int v) {
            childCnt = v;
        }
    }

    private long maxScore = Integer.MIN_VALUE;
    private int maxScoreCnt = 0;

    public int countHighestScoreNodes(int[] parents) {
        // 每一个节点的左右孩子
        Map<Integer, List<Integer>> nodeMap = new HashMap<>();
        int N = parents.length;
        for (int i = 0; i < N; i++) {
            int nodeId = i;
            int nodeParentId = parents[i];
            if (!nodeMap.containsKey(nodeParentId)) {
                nodeMap.put(nodeParentId, new ArrayList<>());
            }
            nodeMap.get(nodeParentId).add(nodeId);
        }
        process(nodeMap, N, -1);
        return maxScoreCnt;
    }

    // 从head节点遍历所有节点做统计
    // DFS
    private Info process(Map<Integer, List<Integer>> nodeMap,
                         int N, int head) {
        if (!nodeMap.containsKey(head)) { // 没有子节点
            if (N - 1 == maxScore) {
                maxScoreCnt++;
            }
            if (N - 1 > maxScore) {
                maxScore = N - 1;
                maxScoreCnt = 1;
            }
            // 节点本身计数是1个
            return new Info(1);
        }
        // 至少有1个子树, 遍历所有子树
        int cnt = 0;
        long score = 1;
        for (int child : nodeMap.get(head)) {
            Info childInfo = process(nodeMap, N, child);
            cnt += childInfo.childCnt;
            if (childInfo.childCnt != 0) {
                score *= childInfo.childCnt;
            }
        }
        int upperCnt = N - 1 - cnt;
        if (upperCnt != 0) {
            score *= upperCnt;
        }
        if (score == maxScore) {
            maxScoreCnt++;
        }
        if (score > maxScore) {
            maxScore = score;
            maxScoreCnt = 1;
        }
        return new Info(cnt + 1);
    }

    public static void main(String[] args) {
        int[] parents = new int[]{-1,2,0,2,0};
        // int[] parents = new int[]{-1, 2, 0};
        var ans = new Problem_2049_CountHighScoreNodes().countHighestScoreNodes(parents);
        System.out.println(ans);
    }
}
