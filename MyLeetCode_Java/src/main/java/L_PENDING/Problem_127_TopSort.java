package L_PENDING;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

// LintCode

public class Problem_127_TopSort {

    // Definition for Directed graph.

    // 该定义类似邻接表
    class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public class Record{
        public DirectedGraphNode node;
        public long nodes;
        public Record(DirectedGraphNode n, long o) {
            node = n;
            nodes = o;
        }
    }

    public class MyComparator implements Comparator<Record> {

        @Override
        public int compare(Record o1, Record o2) {
            return Long.compare(o2.nodes, o1.nodes);
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
        for(Record r: recordArr) {
            res.add(r.node);
        }
        return res;
    }

    // 当前来到 cur 点, 返回 cur 点出发能走的所有点的点次和
    // 返回 (cur, 点次)
    // order: 缓存
    //   key: 某一个点点次, 如果存在代表之前算过了
    //   value: 该点的点次
    private Record f(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> order) {
        if (order.containsKey(cur)) {
            return order.get(cur);
        }
        // cur 的点次没有算过
        long nodes = 0;
        for(DirectedGraphNode next : cur.neighbors) { // 枚举所有 next 点
            nodes += f(next, order).nodes; // 累积所有邻居的点次
        }
        Record ans = new Record(cur, nodes+1); // 加上自己的点次
        order.put(cur, ans);
        return ans;
    }
}
