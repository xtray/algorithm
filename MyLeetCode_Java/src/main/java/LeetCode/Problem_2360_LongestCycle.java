package LeetCode;

import java.util.*;

public class Problem_2360_LongestCycle {

    // 利用拓扑序把环外节点剔除
    public int longestCycle(int[] edges) {
        int N = edges.length;
        // 计算每个节点的入度
        int[] indegree = new int[N];
        for (int i = 0; i < N; i++) {
            if (edges[i] == -1) continue;
            // i-->edges[i]
            indegree[edges[i]]++;
        }
        boolean[] notInCycle = new boolean[N];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        // 入度为 0 的节点加入队列
        for (int i = 0; i < N; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        // 找出所有不在环中的节点
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            // 标记点是否在环中
            notInCycle[cur] = true;
            if (edges[cur] == -1) continue;
            if (--indegree[edges[cur]] == 0) {
                queue.offer(edges[cur]);
            }
        }
        int ans = -1;
        for (int i = 0; i < N; i++) {
            // 不在环中，跳过
            if (notInCycle[i]) continue;
            // 碰到第一个在环里的点
            indegree[i] = 0;
            int cnt = 0; // 环大小
            queue.offer(i);// 放入队列
            // 求一个环的长度
            // 一个环里的点, 入度-1后, 会变为0
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                cnt++;
                notInCycle[i] = true;
                if (--indegree[edges[cur]] == 0) {
                    queue.offer(edges[cur]);
                }
            }
            // 更新 ans
            ans = Math.max(ans, cnt);
        }
        return ans;
    }

    // 强连通分量的解法
    public int longestCycle1(int[] edges) {
        int N = edges.length;
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < N; i++) {
            int from = i;
            int to = edges[i];
            if (to != -1) {
                list.get(from + 1).add(to + 1);
            }
        }
        StronglyConnectedComponents scc = new StronglyConnectedComponents(list);
        int maxSize = scc.getMaxScc();
        return maxSize == 1 ? -1 : maxSize;
    }

    public static class StronglyConnectedComponents {
        public List<List<Integer>> nexts;
        public int N;
        public int[] stack;
        public int stackSize;
        public int[] dfn;
        public int[] low;
        public int cnt;
        public int[] scc;
        public int sccn;

        // 请保证点的编号从1开始，不从0开始
        // 注意：
        // 如果edges里有0、1、2...n这些点，那么容器edges的大小为n+1
        // 但是0点是弃而不用的，所以1..n才是有效的点，所以有效大小是n
        public StronglyConnectedComponents(List<List<Integer>> edges) {
            nexts = edges;
            init();
            scc();
        }

        private void init() {
            N = nexts.size();
            stack = new int[N];
            stackSize = 0;
            dfn = new int[N];
            low = new int[N];
            cnt = 0;
            scc = new int[N];
            sccn = 0;
            N--; // 恢复成数据真实大小
        }

        private void scc() {
            for (int i = 1; i <= N; i++) {
                if (dfn[i] == 0) { // 没有被编号, 即: 没有访问过
                    tarjan(i);
                }
            }
        }

        private void tarjan(int p) {
            low[p] = dfn[p] = ++cnt;
            stack[stackSize++] = p;
            for (int q : nexts.get(p)) {
                // q 当前p的每一个孩子
                if (dfn[q] == 0) {
                    tarjan(q);
                }
                // q 肯定遍历过  1) 遍历过，结算了！2）遍历过，没结算
                if (scc[q] == 0) { // scc[q]!=0 q已经属于某个集团了！不能用来更新
                    low[p] = Math.min(low[p], low[q]);
                }
            }
            if (low[p] == dfn[p]) {
                sccn++;
                int top = 0;
                do {
                    top = stack[--stackSize];
                    scc[top] = sccn;
                } while (top != p);
            }
        }

        // 找到DAG缩点图的最大大小
        public int getMaxScc() {
            int maxScc = 0;
            int[] sccSize = new int[sccn + 1];
            for (int i = 1; i <= N; i++) {
                sccSize[scc[i]]++;
                maxScc = Math.max(maxScc, sccSize[scc[i]]);
            }
            return maxScc;
        }
    }


    // 从每一个点出发, 一直向下走, 要么到-1, 要么遇到走过的
    public int longestCycle2(int[] edges) {
        int N = edges.length;
        boolean[] used = new boolean[N];
        int[] dfn = new int[N];
        int idx = 1; // 1~N
        int ans = -1;
        for (int i = 0; i < N; i++) {
            if (!used[i]) { // 没算过
                // 从每一个节点出发的每一条链, 检查有没有环
                // 走节点i的next
                int cur = i;
                Set<Integer> set = new HashSet<>(); // 检测i出发的本次有没有环路
                while (cur != -1 && !used[cur]) {
                    set.add(cur);
                    used[cur] = true;
                    dfn[cur] = idx++;
                    cur = edges[cur];
                }
                if(cur != -1 && set.contains(cur)) {
                    ans = Math.max(ans, idx - dfn[cur]);
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] edges = {3, 3, 4, 2, 3}; // 3
        // int[] edges = {3, 4, 0, 2, -1, 2}; // 3
        // int[] edges = {-1, 4, -1, 2, 0, 4}; // -1
        var ans = new Problem_2360_LongestCycle().longestCycle2(edges);
        System.out.println(ans);
    }

}
