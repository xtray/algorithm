package _Misc;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Problem_003_TransferStation {

    public int buildTransferStation(int[][] grid) {
		int ans = Integer.MAX_VALUE;
		int N = grid.length;
		int M = grid[0].length;
		int buildings = 0;
		Position[][] positions = new Position[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (grid[i][j] == 1) {
					buildings++;
				}
				positions[i][j] = new Position(i, j, grid[i][j]);
			}
		}
		if (buildings == 0) {
			return 0;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				ans = Math.min(ans, bfs(positions, buildings, i, j));
			}
		}
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}

	public static int bfs(Position[][] positions, int buildings, int i, int j) {
		// if (positions[i][j].v != 0) {
		// 	return Integer.MAX_VALUE;
		// }
		HashMap<Position, Integer> levels = new HashMap<>();
		Queue<Position> queue = new LinkedList<>();
		Position from = positions[i][j];
		levels.put(from, 0);
		queue.add(from);
		int ans = 0;
		int solved = 0;
		while (!queue.isEmpty() && solved != buildings) {
			Position cur = queue.poll();
			int level = levels.get(cur);
			if (cur.v == 1) {
				ans += level;
				solved++;
			} else {
				add(queue, levels, positions, cur.r - 1, cur.c, level + 1);
				add(queue, levels, positions, cur.r + 1, cur.c, level + 1);
				add(queue, levels, positions, cur.r, cur.c - 1, level + 1);
				add(queue, levels, positions, cur.r, cur.c + 1, level + 1);
			}
		}
		return solved == buildings ? ans : Integer.MAX_VALUE;
	}

	public static class Position {
		public int r;
		public int c;
		public int v;

		public Position(int row, int col, int value) {
			r = row;
			c = col;
			v = value;
		}
	}

	public static void add(Queue<Position> q, HashMap<Position, Integer> l, Position[][] p, int i, int j, int level) {
		if (i >= 0 && i < p.length && j >= 0 && j < p[0].length && !l.containsKey(p[i][j])) {
			l.put(p[i][j], level);
			q.add(p[i][j]);
		}
	}
}
