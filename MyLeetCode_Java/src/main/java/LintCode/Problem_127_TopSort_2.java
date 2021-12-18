package LintCode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Problem_127_TopSort_2 {

    // Definition for Directed graph.

    // 该定义类似邻接表
    static class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public static class Record {
        public DirectedGraphNode node;
        public int deep;

        public Record(DirectedGraphNode n, int o) {
            node = n;
            deep = o;
        }
    }

    public static class MyComparator implements Comparator<Record> {

        @Override
        public int compare(Record o1, Record o2) {
            return o2.deep - o1.deep;
        }
    }

    /**
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        HashMap<DirectedGraphNode, Record> order = new HashMap<>();
        for (DirectedGraphNode cur : graph) { // 计算图中所有点的点次
            f(cur, order);
        }
        // 转成 list 按点次大小从大到小排序
        List<Record> recordArr = new ArrayList<>(order.values());
        recordArr.sort(new MyComparator()); // 按点次排序
        ArrayList<DirectedGraphNode> res = new ArrayList<>();
        for (Record r : recordArr) {
            res.add(r.node);
        }
        return res;
    }

    // 当前来到 cur 点, 返回 cur 点出发能走出的最大深度
    // 返回 (cur, 最大深度)
    // order: 缓存
    //   key: 某一个点最大深度, 如果存在代表之前算过了
    //   value: 该点的最大深度
    private Record f(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> order) {
        if (order.containsKey(cur)) {
            return order.get(cur);
        }
        // cur 的最大深度没有算过
        int depth = 0;
        for (DirectedGraphNode next : cur.neighbors) { // 枚举所有 next 点
            depth = Math.max(depth, f(next, order).deep); // 累积所有邻居的最大深度
        }
        Record ans = new Record(cur, depth + 1); // 加上自己的点次
        order.put(cur, ans);
        return ans;
    }
}
