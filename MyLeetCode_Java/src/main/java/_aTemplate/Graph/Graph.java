package _aTemplate.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 点集 + 边集
public class Graph {
    public Map<Integer, Node> nodes; // 每一个数指向一个Node
    public Set<Edge> edges; // NOTE: 边集用的是Set, 因为value不重要

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
