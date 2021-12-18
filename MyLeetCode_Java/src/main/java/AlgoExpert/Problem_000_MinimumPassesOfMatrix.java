package AlgoExpert;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Problem_000_MinimumPassesOfMatrix {

    public class Node{
        int row;
        int col;
        int val;
        public Node(int r, int c, int v) {
            row = r;
            col = c;
            val = v;
        }
    }

    // 收集所有负值位置,set, 当空了表示成功
    // 收集所有正数, 每次从正数做扫描, 如果某次扫描没有操作, 结束
    public int minimumPassesOfMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        Set<Integer> set = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        int N = matrix.length;
        int M = matrix[0].length;
        for(int i = 0; i<N;i++) {
            for (int j =0;j<M;j++) {
                if(matrix[i][j] < 0) {
                    set.add(i*M+j);
                }
                if(matrix[i][j] > 0) {
                    queue.add(new Node(i, j, matrix[i][j]));
                }
            }
        }
        // 扫描每一个正数点
        // 当操作完成一次后负数点没有减少, 退出循环
        int[][] dir = new int[][]{{1,0},{-1,0},{0, -1},{0, 1}};
        int pre = 0;
        int times = 0;
       while (!set.isEmpty() && set.size() != pre) {
           pre = set.size();
           int size = queue.size();
           times++;
           for(int i=0; i<size;i++) {
               Node node = queue.poll();
               for(int j=0; j<dir.length;j++) {
                   int dx = node.row + dir[j][0];
                   int dy = node.col + dir[j][1];
                   if(dx >=0 && dx < N && dy >=0 && dy <M && set.contains(dx*M+dy)) {
                       set.remove(dx*M + dy);
                       queue.add(new Node(dx, dy, -matrix[dx][dy]));
                   }
               }
           }
       }
       return set.isEmpty()?times:-1;
    }

    public static void main(String[] args) {

        int[][] matrix = new int[][]{
                {0, -2, -1},
                {-5, 2, 0},
                {-6, -2, 0}
        };
        Problem_000_MinimumPassesOfMatrix sl = new Problem_000_MinimumPassesOfMatrix();
        int res = sl.minimumPassesOfMatrix(matrix);
        System.out.println(res);
    }
}
