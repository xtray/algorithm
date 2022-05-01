package LeetCode;

public class Problem_427_QuadTree {

    public static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }

    public Node construct(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return null;
        }
        int N = grid.length;

        int[][] sum = new int[N][N]; // 二维前缀和数组
        sum[0][0] = grid[0][0];
        for (int j = 1; j < N; j++) { // 第0行
            sum[0][j] = sum[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < N; i++) { // 第0列
            sum[i][0] = sum[i - 1][0] + grid[i][0];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + grid[i][j];
            }
        }
        return process(0, 0, N - 1, N - 1, sum);
    }

    // 左上: (a,b)  右下: (c,d)
    // sum: 二维前缀和数组
    private Node process(int a, int b, int c, int d, int[][] sum) {
        int curSum = getRangeSum(a, b, c, d, sum);
        int w = d - b + 1;
        int boxSize = w * w;
        if (curSum == 0 || curSum == boxSize) {
            // 全是0, 或者全是1
            return new Node(curSum != 0, true);
        }
        // 不是全是0, 或者全是1, 需要拆分成4块
        Node root = new Node(false, false);
        w >>= 1;
        // NOTE: 注意左上, 右下两个顶点 四个坐标 +1, -1的边界问题
        root.topLeft = process(a, b, a + w - 1, b + w - 1, sum);
        root.topRight = process(a, b + w, a + w - 1, d, sum);
        root.bottomLeft = process(a + w, b, c, b + w - 1, sum);
        root.bottomRight = process(a + w, b + w, c, d, sum);
        return root;
    }

    private int getRangeSum(int a, int b, int c, int d, int[][] sum) {
        int ans = sum[c][d] - (a == 0 ? 0 : sum[a - 1][d]) - (b == 0 ? 0 : sum[c][b - 1]);
        if (a != 0 && b != 0) {
            ans += sum[a - 1][b - 1];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 1, 1, 1, 0, 0, 0, 0}, {1, 1, 1, 1, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 0, 0, 0, 0}, {1, 1, 1, 1, 0, 0, 0, 0}, {1, 1, 1, 1, 0, 0, 0, 0}, {1, 1, 1, 1, 0, 0, 0, 0}};
        var ans = new Problem_427_QuadTree().construct(grid);
        System.out.println(ans);
    }
}
