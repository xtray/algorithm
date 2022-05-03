package _aTemplate.Graph;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public int in; // 入度
    public int out; // 出度
    public int value; // 值
    public List<Node> nexts; // 当前点下级的点 toNodes
    public List<Edge> edges; // 从当前点出去的边

    public Node(int val) {
        value = val;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
