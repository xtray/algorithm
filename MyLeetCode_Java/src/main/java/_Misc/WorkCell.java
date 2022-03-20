package _Misc;

import java.util.ArrayList;
import java.util.List;

/**
 * 广联达--工作细胞 Black
 * 如果血管中内源性胆固醇过多, 可能会堵塞血管, 为了简化这个问题,
 * 我们将 x 轴与直线 y=b(b>0) 之间形成的区域看做血管, 每个内源性胆固醇是元心啊位于血管内的一个圆.
 * 每一秒, 改血管内会堆积一个内源性胆固醇, 最多 n 个. 现在红细胞小哥希望知道他能通过改血管的最晚时间.
 * 输入描述:
 *   第一行两个整数 n, b.
 *   接下来 n 行, 每行三个数 u,v,r 表示第 i 秒生成的内源性胆固醇圆心为(u,v),半径为 r.
 *   (0<=v<=b).
 *   输入的所有数字的绝对值都不大于 1000
 * 输出描述:
 *   仅一行表示能够通过该血管的最晚时间, 即, 若第 i 个内源性胆固醇生成后, 血管没有堵塞, 但第 i+1 个
 *   内源性胆固醇申城后, 血管被堵塞, 则输出 i. 若第一个内源性胆固醇就会堵塞血管, 输出 0. 若所有内源性
 *   胆固醇都生成后仍不会堵塞, 输出 n.
 * 输入样例:
 * 3 4
 * 0 1 1
 * 0 3 1
 * 0 2 2
 * 输出样例:
 * 1
 */

public class WorkCell {


    public static class SegmentTree {
        private int[] sum;
        private int[] change;
        private boolean[] update;

        public SegmentTree(int size) {
            int N = size + 1;
            sum = new int[N << 2];
            change = new int[N << 2];
            update = new boolean[N << 2];

        }

        private void pushUp(int rt) {
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }

        private void pushDown(int rt, int ln, int rn) {
            if (update[rt]) {
                update[rt << 1] = true;
                update[rt << 1 | 1] = true;
                change[rt << 1] = change[rt];
                change[rt << 1 | 1] = change[rt];
                sum[rt << 1] = change[rt] * ln;
                sum[rt << 1 | 1] = change[rt] * rn;
                update[rt] = false;
            }
        }

        public void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {
                update[rt] = true;
                change[rt] = C;
                sum[rt] = (r - l + 1) * C;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                update(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                update(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        public int query(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return sum[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            int left = 0;
            int right = 0;
            if (L <= mid) {
                left = query(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                right = query(L, R, mid + 1, r, rt << 1 | 1);
            }

            return Math.max(left, right);
        }
    }

    // circles[][]
    // [x, y, r], 0<=y<=b
    // [0 1 1]
    // [0 3 1]
    // [0 2 2]
    // (a,b) -- (c,d)
    boolean mix(int a, int b, int r1, int c, int d, int r2) {
        return Math.sqrt(Math.pow(a - c, 2) + Math.pow(b - d, 2)) <= r1 + r2;
    }

    private SegmentTree[] seg;
    private int N;
    private int b;

    public WorkCell(int N, int b) {
        seg = new SegmentTree[N + 1]; // 1~1000
        for (int i = 1; i <= N; i++) {
            seg[i] = new SegmentTree(N);
        }
        this.N = N;
        this.b = b;
    }

    public int fallingCells(int n, int b, int[][] circles) {

        List<int[]> cellList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            boolean check = checkAndUpdate(cellList, circles[i]);
            if (!check) {
                return i; //i+1 -1
            }
        }
        return n;
    }

    private boolean checkAndUpdate(List<int[]> cellList, int[] circle) {

        seg[circle[2]].update(Math.max(0, circle[1] - circle[2]), Math.min(b, circle[1] + circle[2]), 1, 1, N, 1);

        for (int[] cell : cellList) { // 两圆相交， 则更新对应区间
            if (mix(cell[0], cell[1], cell[2], circle[0], circle[1], circle[2])) {
                // update segment tree
                int min = Math.min(circle[1] - circle[2], cell[1] - cell[2]);
                min = Math.max(0, min);
                int max = Math.max(circle[1] + circle[2], cell[1] + cell[2]);
                max = Math.min(max, b);
                seg[cell[2]].update(min, max, 1, N, 1, 1);
                seg[circle[2]].update(min, max, 1, N, 1, 1);
            }
        }

        // 检测是否堵塞
        for (int i = 1; i <= N; i++) {
            int count = seg[i].query(1, b, 1, N, 1);
            if (count == b) {
                return false;
            }
        }
        return true;
    }

    // 使用并查集的解法
    // 每一秒生成的圆, 跟它之前所有出现的圆合并, 合并后检查这个圆所在的新集合是否会堵塞血管
    // 参数:
    // n: 出现的圆的个数
    // b: 血管的宽度[0,b]
    // circle[x,y,r]: 圆心(x,y), 半径 r, y>=0 && y<=b
    // 算法复杂度 1000*1000 = 10^6
    public int fallingCells2(int n, int b, int[][] circles) {
        UnionFind uf = new UnionFind(circles, n);
        // 先检查第 0 个圆
        if (uf.block(0, b)) {
            return 0;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) { // 每一个圆跟它之前所有出现的圆合并
                if (touch(circles[i][0], circles[i][1], circles[j][0], circles[j][1], circles[j][2], circles[j][2])) {
                    uf.union(i, j);
                }
                if (uf.block(i, b)) {
                    return i;
                }
            }
        }
        return n;
    }

    // 两圆圆心距离<=R1+R2, 两圆相交
    public static boolean touch(int x1, int y1, int x2, int y2, int r1, int r2) {
        return  Math.sqrt((Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 - y2), 2))) <= r1 + r2;
    }

    public static class UnionFind {
        public int[] father;
        public int[] size;
        // 每一个圆用他的y最上, y 最下描述
        public int[] yTop;
        public int[] yDown;
        // 辅助数组, 用于做并查集 findFather 路径扁平化处理
        public int[] help;

        public UnionFind(int[][] circles, int n) {
            father = new int[n];
            size = new int[n];
            yTop = new int[n];
            yDown = new int[n];
            help = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
                size[i] = 1;
                int r = circles[i][2];
                yTop[i] = circles[i][1] + r;
                yDown[i] = circles[i][1] - r;
            }
        }

        private int find(int i) {
            int hi = 0;
            while (i != father[i]) {
                help[hi++] = i;
                i = father[i];
            }
            for (hi--; hi >= 0; hi--) {
                father[help[hi]] = i;
            }
            return i;
        }

        public void union(int i, int j) {
            int fatheri = find(i);
            int fatherj = find(j);
            if (fatheri != fatherj) {
                int sizei = size[fatheri];
                int sizej = size[fatherj];
                int big = sizei >= sizej ? fatheri : fatherj;
                int small = big == fatheri ? fatherj : fatheri;
                father[small] = big; // 小挂大
                size[big] = sizei + sizej;
                yTop[big] = Math.max(yTop[big], yTop[small]);
                yDown[big] = Math.min(yDown[big], yDown[small]);
            }
        }

        // 阻塞检查
        // 如果一个圆的上下边界超过了[0,b]则血管堵塞
        public boolean block(int i, int b) {
            i = find(i);
            return  (yDown[i] <= 0 && yTop[i] >= b);
        }
    }


    public static void main(String[] args) {
        int n = 4;
        int b = 10;
        int[][] circles = {{0, 1, 1}, {0, 3, 1}, {0, 4, 1}, {0, 7, 2}, {0, 8, 2}};
        WorkCell wc = new WorkCell(1000, b);
        int res = wc.fallingCells(n, b, circles);
        int res2 = wc.fallingCells2(n, b, circles);
        System.out.println(res);
        System.out.println(res2);
    }

}
