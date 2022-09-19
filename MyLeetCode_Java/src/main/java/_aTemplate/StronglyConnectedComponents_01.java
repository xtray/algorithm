package _aTemplate;

import java.util.*;

// tarjan算法求有向图的强连通分量
public class StronglyConnectedComponents_01 {
	
	public static class StronglyConnectedComponents {
		public List<List<Integer>> nexts; // 邻接表
		public int N;
		public int[] stack;
		public int stackSize;
		public int[] dfn;
		public int[] low;
		public int cnt;
		public int[] scc;
		public int sccn;
		// 请保证点的编号从1开始，不从0开始
		// 注意：
		// 如果edges里有0、1、2...n这些点，那么容器edges的大小为n+1
		// 但是0点是弃而不用的，所以1..n才是有效的点，所以有效大小是n
		public StronglyConnectedComponents(List<List<Integer>> edges) {
			nexts = edges;
			init();
			scc();
		}

		private void init() {
			N = nexts.size(); // 比真实大小+1, 0位置弃而不用
			stack = new int[N];
			stackSize = 0;
			dfn = new int[N];
			low = new int[N];
			cnt = 0;
			scc = new int[N];
			sccn = 0;
			N--; // 恢复成真实大小 [1, N]
		}

		private void scc() {
			for (int i = 1; i <= N; i++) {
				if (dfn[i] == 0) { // 没有被编号, 即: 没有访问过
					tarjan(i);
				}
			}
		}

		// low[]
		// dfn[]
		// stack[]
		// int stackSize
		// int cnt;
		// int sccn;
		// scc[]
		private void tarjan(int p) {
			low[p] = dfn[p] = ++cnt;
			stack[stackSize++] = p;
			for (int q : nexts.get(p)) {
				// q 当前p的每一个孩子
				if (dfn[q] == 0) {
					tarjan(q);
				}
				// q 肯定遍历过  1) 遍历过，结算了！2）遍历过，没结算
				if (scc[q] == 0) { // scc[q]!=0 q已经属于某个集团了！不能用来更新
					low[p] = Math.min(low[p], low[q]);
				}
			}
			if (low[p] == dfn[p]) { // 扎口袋
				sccn++; // NOTE: sccn的编号是从0开始编号的
				int top = 0;
				do {
					top = stack[--stackSize];
					scc[top] = sccn; // 一个口袋里的东西编一个sccn的号
				} while (top != p);
			}
		}

		public int[] getScc() {
			return scc;
		}

		public int getSccn() {
			return sccn;
		}

		// 找到缩点图的最大大小
		public int getMaxScc() {
			int maxScc = 0;
			int[] sccSize = new int[sccn];
			for (int i = 1; i <= N; i++) {
				sccSize[scc[i]]++;
				maxScc = Math.max(maxScc, sccSize[scc[i]]);
			}
			return maxScc;
		}

		// 根据scc信息得到DAG: 有向无环缩点图
		public List<List<Integer>> getShortGraph() {
			List<List<Integer>> shortGraph = new ArrayList<>();
			for (int i = 0; i <= sccn; i++) {
				// 垫的0位置弃而不用
				shortGraph.add(new ArrayList<>());
			}
			for (int u = 1; u <= N; u++) {
				for (int v : nexts.get(u)) {
					if (scc[u] != scc[v]) {
						shortGraph.get(scc[u]).add(scc[v]);
					}
				}
			}
			return shortGraph;
		}

	}
}
