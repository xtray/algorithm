package _BasicConcept;


import java.util.*;

public class MapDFS {

    public class Node{
        public int value;
        public int in;
        public int out;
        public List<Node> nexts;
        public List<Edge> edges;
        public Node(int v){
            value = v;
            in = 0;
            out = 0;
            nexts = new ArrayList<>();
            edges = new ArrayList<>();
        }
    }
    public class Edge{
        public int weight;
        public Node from;
        public Node to;
        public Edge(int w, Node f, Node t) {
            weight = w;
            from = f;
            to = t;
        }
    }
    public class Graph{
        public Map<Integer, Node> nodes;
        public Set<Edge> edges;
        public Graph(){
            nodes = new HashMap<>();
            edges = new HashSet<>();
        }
    }

    public void bfs(Node start) {
        if(start == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        Set<Node> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            for(Node next : cur.nexts) {
                if(!set.contains(next)){
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }

    public void dfs(Node start) {
        if (start == null) {
            return;
        }
        LinkedList<Node> stack = new LinkedList<>();
        Set<Node> set = new HashSet<>();
        stack.addFirst(start);
        set.add(start);
        System.out.println(start.value);
        while (!stack.isEmpty()) {
            Node cur = stack.pollFirst();
            for(Node next: cur.nexts) {
                if(!set.contains(next)) {
                    stack.addFirst(cur);
                    stack.addFirst(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }
}
