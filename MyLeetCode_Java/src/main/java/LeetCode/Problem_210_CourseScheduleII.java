package LeetCode;

import java.util.*;

public class Problem_210_CourseScheduleII {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        int[] inOrder = new int[numCourses]; // 入度表
        for (int[] p : prerequisites) {
            int from = p[1];
            int to = p[0];
            graph.get(from).add(to);
            inOrder[to]++;
        }
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inOrder[i] == 0) {
                queue.addLast(i);
            }
        }
        if (queue.isEmpty()) return new int[0];
        int[] ans = new int[numCourses];
        int idx = 0;
        while (!queue.isEmpty()) {
            int cur = queue.pollFirst();
            ans[idx++] = cur;
            for (int next : graph.get(cur)) {
                if (--inOrder[next] == 0) {
                    queue.addLast(next);
                }
            }
        }
        if (idx < numCourses) return new int[0];
        return ans;
    }
}
