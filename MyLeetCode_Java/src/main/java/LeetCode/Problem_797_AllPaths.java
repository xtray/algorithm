package LeetCode;

import java.util.*;

public class Problem_797_AllPaths {

    private static class Pair {
        public int id;
        public List<Integer> path;

        public Pair(int id, List<Integer> path) {
            this.id = id;
            this.path = path;
        }
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> ans = new ArrayList<>();
        int N = graph.length;
        ArrayDeque<Pair> queue = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();
        list.add(0);
        queue.add(new Pair(0, list));
        while (!queue.isEmpty()) {
            Pair cur = queue.pollFirst();
            if (cur.id == N - 1) {
                List<Integer> path = new ArrayList<>(cur.path);
                ans.add(path);
                continue;
            }
            for (int next : graph[cur.id]) {
                List<Integer> tmpList = new ArrayList<>(cur.path);
                tmpList.add(next);
                queue.add(new Pair(next, tmpList));
            }
        }
        return ans;
    }

    public List<List<Integer>> allPathsSourceTarget2(int[][] graph) {
        List<List<Integer>> ans = new ArrayList<>();
        int N = graph.length;
        List<Integer> list = new ArrayList<>();
        list.add(0);
        dfs(graph, 0, list, ans);
        return ans;
    }

    private void dfs(int[][] graph, int i, List<Integer> path, List<List<Integer>> ans) {
        int N = graph.length;
        if (i == N - 1) {
            List<Integer> list = new ArrayList<>(path);
            ans.add(list);
            return;
        }
        for (int next : graph[i]) {
            path.add(next);
            dfs(graph, next, path, ans);
            path.remove(path.size() - 1);
        }

    }

    public static void main(String[] args) {
        int[][] graph = {
                {1,2},
                {3},
                {3},
                {}
        };
        var ans = new Problem_797_AllPaths().allPathsSourceTarget(graph);
        System.out.println(ans);
    }
}
