package _aTemplate.Graph;

import java.util.*;

// 最小生成树是无向图
// 在不影响所有点都连通的情况下, 你所有边权值加起来最小值是多少。

// 最小生成树Kruskal算法 并查集+优先队列(边权值)
// 考察所有的边, 从权值小的边到权值大的依次考察, 如果当前边不会形成环就要当前边, 否则不要
// 用并查集检查是否出现环

// 一开始需要所有点搞出自己的集合(并查集)

//undirected graph only
public class Kruskal {

	// Union-Find Set
	public static class UnionFind {
		// Note: 因为Node节点已经是封装数据结构, 所以不需要 HashMap<V, Node<V>> nodes 节点映射结构

		// key 某一个节点， value: key节点父节点
		private HashMap<Node, Node> fatherMap;
		// key 某一个集合的代表节点, value: key所在集合的节点个数
		private HashMap<Node, Integer> sizeMap;

		public UnionFind() {
			fatherMap = new HashMap<>();
			sizeMap = new HashMap<>();
		}
		
		public void makeSets(Collection<Node> nodes) {
			fatherMap.clear();
			sizeMap.clear();
			for (Node node : nodes) {
				fatherMap.put(node, node);
				sizeMap.put(node, 1);
			}
		}

		private Node findFather(Node n) {
			Stack<Node> path = new Stack<>();
			while(n != fatherMap.get(n)) {
				path.add(n);
				n = fatherMap.get(n);
			}
			while(!path.isEmpty()) {
				fatherMap.put(path.pop(), n);
			}
			return n;
		}

		public boolean isSameSet(Node a, Node b) {
			return findFather(a) == findFather(b);
		}

		public void union(Node a, Node b) {
			if (a == null || b == null) {
				return;
			}
			Node aDai = findFather(a);
			Node bDai = findFather(b);
			if (aDai != bDai) {
				int aSetSize = sizeMap.get(aDai);
				int bSetSize = sizeMap.get(bDai);
				if (aSetSize <= bSetSize) {
					fatherMap.put(aDai, bDai);
					sizeMap.put(bDai, aSetSize + bSetSize);
					sizeMap.remove(aDai);
				} else {
					fatherMap.put(bDai, aDai);
					sizeMap.put(aDai, aSetSize + bSetSize);
					sizeMap.remove(bDai);
				}
			}
		}
	}
	

	public static class EdgeComparator implements Comparator<Edge> {

		@Override
		public int compare(Edge o1, Edge o2) {
			return o1.weight - o2.weight;
		}

	}

	public static Set<Edge> kruskalMST(Graph graph) {
		UnionFind uf = new UnionFind();
		uf.makeSets(graph.nodes.values()); // 每个点生成自己的集合
		PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator()); // 小根堆
		for (Edge edge : graph.edges) { // M 条边
			priorityQueue.add(edge);  // O(logM)
		}
		Set<Edge> result = new HashSet<>();
		while (!priorityQueue.isEmpty()) { // M 条边
			Edge edge = priorityQueue.poll(); // O(logM)
			if (!uf.isSameSet(edge.from, edge.to)) { // O(1)
				result.add(edge); // 弹出一条边, 不是一个集合就要这条边, 边上的两个点去合并
				uf.union(edge.from, edge.to);
			}
		}
		return result;
	}
}
