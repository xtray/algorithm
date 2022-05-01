package LeetCode;

import java.util.*;

// ref: Problem_127_TopSort
// NOTE: 仿照拓扑排序: 入度为零的入队列, 这里是出度为1 的位最边缘的点, 入队列
public class Problem_310_MinHeightTrees {

    // https://leetcode-cn.com/problems/minimum-height-trees/solution/zui-rong-yi-li-jie-de-bfsfen-xi-jian-dan-zhu-shi-x/
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n == 1) {
            res.add(0);
            return res;
        }
        int[] outDegree = new int[n]; // 每个点的出度
        List<List<Integer>> map = new ArrayList<>(); // 邻接表
        for (int i = 0; i < n; i++) {
            map.add(new ArrayList<>());
        }
        // 建立每个点的邻接表跟出度数据
        for (int[] edge : edges) { // 无向图, 正反都要加
            outDegree[edge[0]]++;
            outDegree[edge[1]]++;
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        /*把所有出度为1的节点，也就是叶子节点入队*/
        for (int i = 0; i < n; i++) {
            if (outDegree[i] == 1) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            res = new ArrayList<>();/*这个地方注意，我们每层循环都要new一个新的结果集合，
            这样最后保存的就是最终的最小高度树了*/
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                // 把当前节点加入结果集，不要有疑问，为什么当前只是叶子节点为什么要加入结果集呢?
                // 因为我们每次循环都会新建一个list，所以最后保存的就是最后一个状态下的叶子节点，
                // 这也是很多题解里面所说的剪掉叶子节点的部分，你可以想象一下图，每层遍历完，
                // 都会把该层（也就是叶子节点层）这一层从队列中移除掉，
                // 不就相当于把原来的图给剪掉一圈叶子节点，形成一个缩小的新的图吗
                res.add(cur);
                // 把当前节点的相邻接点都拿出来，把它们的出度都减1，因为当前节点已经不存在了，所以，
                // 它的相邻节点们就有可能变成叶子节点
                for (int next : map.get(cur)) {
                    outDegree[next]--;
                    if (outDegree[next] == 1) { // 如果是叶子节点我们就入队
                        queue.offer(next);
                    }
                }
            }
        }
        // 返回最后一次保存的list
        return res;
    }


    public static void main(String[] args) {
        int n = 4;
        int[][] edges = {{1, 0}, {1, 2}, {1, 3}};
        var ans = new Problem_310_MinHeightTrees().findMinHeightTrees(n, edges);
        for (int num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
