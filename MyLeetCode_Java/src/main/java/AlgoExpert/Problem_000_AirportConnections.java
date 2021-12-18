package AlgoExpert;

import java.util.*;

public class Problem_000_AirportConnections {

    public static int airportConnections(
            List<String> airports, List<List<String>> routes, String startingAirport) {

        Map<String, Integer> idMap = generateIdMap(airports);
        Graph graph = createAirPortGraph(airports, routes, idMap);
        int start = idMap.get(startingAirport);
        List<Node> unReadched = getUnreachableAirport(graph, start, idMap);
        markUnreachableConnections(graph, unReadched);
        return getMinNumberOfNewConnections(graph, unReadched);
    }

    private static int getMinNumberOfNewConnections(Graph graph, List<Node> unReadched) {
        unReadched.sort((a1, a2)->a2.score - a1.score); // 按分数由大到小排序
        int resNum = 0;
        for(Node node : unReadched) {
            if (node.reachable) continue;
            resNum++;
            for(int conn : node.connections) {
                graph.nodes.get(conn).reachable = true;
            }
        }
        return resNum;
    }

    // 对所有没法到达的机场按 DFS 深度排序
    private static void markUnreachableConnections(Graph graph, List<Node> unReadched) {
        for (Node node : unReadched) {
            // 对每一个不能到达的节点, 计算它的分数(从 node 出发可以够到的节点数)
            HashSet<Integer> set = new HashSet<>();
            node.score = dfsComputeScore(graph, node.value, set);
            node.connections.addAll(set);
        }
    }

    // 从 node 出发能走到的所有点的数目, 不计算重复的
    private static int dfsComputeScore(Graph graph, int nodeIdx, HashSet<Integer> visited) {
        if(graph.nodes.get(nodeIdx).reachable) {
            return 0;
        }
        if (visited.contains(nodeIdx)) {
            return 0;
        }
        visited.add(nodeIdx);
        Node node = graph.nodes.get(nodeIdx);
        int score = 1;
        for (Node next : node.nexts) {
            score += dfsComputeScore(graph, next.value, visited);
        }
        return score;
    }

    // 所有从 start 点无法到达的所有机场
    private static List<Node> getUnreachableAirport(Graph graph, int startingAirport, Map<String, Integer> idMap) {
        Set<Integer> visited = new HashSet<>();
        dfsTraverseAirport(graph, startingAirport, visited);

        List<Node> unReadched = new ArrayList<>();
        for(int airport : idMap.values()) {
            if(visited.contains(airport)) continue;
            Node node = graph.nodes.get(airport);
            unReadched.add(node);
        }
        return unReadched;
    }

    // 标记所有能到达的机场
    private static void dfsTraverseAirport(Graph graph, int airport, Set<Integer> visited) {
        if(visited.contains(airport)) return;
        visited.add(airport);
        graph.nodes.get(airport).reachable = true;
        for(Node next : graph.nodes.get(airport).nexts) { // 邻接的机场
            dfsTraverseAirport(graph, next.value, visited);
        }
    }

    private static Map<String, Integer> generateIdMap(List<String> airports) {
        Map<String, Integer> idMap = new HashMap<>();
        // 机场名字跟数字做映射
        int index = 0;
        for (String name : airports) {
            idMap.put(name, index++);
        }
        return idMap;
    }

    private static  Graph createAirPortGraph(List<String> airports, List<List<String>> routes, Map<String, Integer> idMap) {
        Graph graph = new Graph();
        for(int id : idMap.values()) {
            if (!graph.nodes.containsKey(id)) {
                graph.nodes.put(id, new Node(id));
            }
        }

        for(int i =0; i< routes.size();i++) {
            Integer from = idMap.get(routes.get(i).get(0));
            Integer to = idMap.get(routes.get(i).get(1));
//            if (!graph.nodes.containsKey(from)) {
//                graph.nodes.put(from, new Node(from));
//            }
//            if (!graph.nodes.containsKey(to)) {
//                graph.nodes.put(to, new Node(to));
//            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge newEdge = new Edge(1, fromNode, toNode);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(newEdge);
            graph.edges.add(newEdge);
        }
        return graph;
    }


    public static class Graph {
        public HashMap<Integer, Node> nodes;
        public HashSet<Edge> edges;

        public Graph() {
            nodes = new HashMap<>();
            edges = new HashSet<>();
        }
    }

    public static class Edge {
        public int weight;
        public Node from;
        public Node to;

        public Edge(int weight, Node from, Node to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }

    // 点结构的描述  A  0
    public static class Node {
        public int value;
        public int in;
        public int out;
        public ArrayList<Node> nexts;
        public ArrayList<Edge> edges;
        public boolean reachable;
        public int score;
        public ArrayList<Integer> connections;

        public Node(int value) {
            this.value = value;
            in = 0;
            out = 0;
            nexts = new ArrayList<>();
            edges = new ArrayList<>();
            reachable = false;
            score = 0;
            connections = new ArrayList<>();
        }
    }

    private static List<String> AIRPORTS =
            new ArrayList<String>(
                    Arrays.asList(
                            "BGI", "CDG", "DEL", "DOH", "DSM", "EWR", "EYW", "HND", "ICN", "JFK", "LGA", "LHR",
                            "ORD", "SAN", "SFO", "SIN", "TLV", "BUD"));

    private static String STARTING_AIRPORT = "LGA";

    public static void main(String[] args) {

        List<List<String>> routes = new ArrayList<List<String>>();
        routes.add(new ArrayList<String>(Arrays.asList("DSM", "ORD")));
        routes.add(new ArrayList<String>(Arrays.asList("ORD", "BGI")));
        routes.add(new ArrayList<String>(Arrays.asList("BGI", "LGA")));
        routes.add(new ArrayList<String>(Arrays.asList("SIN", "CDG")));
        routes.add(new ArrayList<String>(Arrays.asList("CDG", "SIN")));
        routes.add(new ArrayList<String>(Arrays.asList("CDG", "BUD")));
        routes.add(new ArrayList<String>(Arrays.asList("DEL", "DOH")));
        routes.add(new ArrayList<String>(Arrays.asList("DEL", "CDG")));
        routes.add(new ArrayList<String>(Arrays.asList("TLV", "DEL")));
        routes.add(new ArrayList<String>(Arrays.asList("EWR", "HND")));
        routes.add(new ArrayList<String>(Arrays.asList("HND", "ICN")));
        routes.add(new ArrayList<String>(Arrays.asList("HND", "JFK")));
        routes.add(new ArrayList<String>(Arrays.asList("ICN", "JFK")));
        routes.add(new ArrayList<String>(Arrays.asList("JFK", "LGA")));
        routes.add(new ArrayList<String>(Arrays.asList("EYW", "LHR")));
        routes.add(new ArrayList<String>(Arrays.asList("LHR", "SFO")));
        routes.add(new ArrayList<String>(Arrays.asList("SFO", "SAN")));
        routes.add(new ArrayList<String>(Arrays.asList("SFO", "DSM")));
        routes.add(new ArrayList<String>(Arrays.asList("SAN", "EYW")));

        var res = airportConnections(AIRPORTS, routes, STARTING_AIRPORT );
        System.out.println(res);
    }
}
