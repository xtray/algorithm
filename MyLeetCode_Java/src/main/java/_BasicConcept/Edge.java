package _BasicConcept;

public class Edge {
    public Node from;
    public Node to;
    public int weight; // 权重

    public Edge(Node f, Node t, int w) {
        from = f;
        to = t;
        weight = w;
    }
}

