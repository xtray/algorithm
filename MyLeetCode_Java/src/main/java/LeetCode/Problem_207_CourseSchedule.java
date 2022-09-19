package LeetCode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Problem_207_CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
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
        if (queue.isEmpty()) return false;
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
        return idx == numCourses;
    }
}
