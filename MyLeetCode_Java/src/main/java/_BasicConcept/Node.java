package _BasicConcept;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public int in; // 入度
    public int out; // 出度
    public int val; // 值
    public List<Node> nexts; // 下级的点 toNodes
    public List<Edge> edges; // 出去的边

    public Node(int v) {
        val = v;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
