// package BigCom;
//
// import java.util.*;
//
// /**
//  * 有一个需要维修的地点数组，有一个维修工的数组存着维修工的接单范围，
//  * 求最多能维修多少单，每个维修工只能维修一单，
//  * 用例1:
//  *   维修工: {{1,2},{2,4},{3,6},{7,8},{1,10}}
//  *   维修地点: 2,4,6,9,7
//  *   输出: 5
//  * 用例2:
//  *  维修工: {{23, 24}, {16, 19}, {6, 12}, {2, 9}, {2, 10}}
//  *  维修地点: {9, 23, 6, 17, 11}
//  *  输出: 5
//  * 数据范围:
//  *   维修工, 维修地点 数组长度: 10^5
//  *   数值大小: 10^9
//  */
// public class Problem_000_RepairMan {
//
//     public class Node {
//         public int value;
//         public int in;
//         public int out;
//         public ArrayList<Node> nexts;
//         public ArrayList<Edge> edges;
//
//         public Node(int value) {
//             this.value = value;
//             in = 0;
//             out = 0;
//             nexts = new ArrayList<>();
//             edges = new ArrayList<>();
//         }
//     }
//
//     public class Edge {
//         public int weight;
//         public Node from;
//         public Node to;
//
//         public Edge(int weight, Node from, Node to) {
//             this.weight = weight;
//             this.from = from;
//             this.to = to;
//         }
//     }
//
//     // 图的解法
//     public static int repair2(int[][] man, int[] loc) {
//         Arrays.sort(loc);
//         // 1.数据离散化
//         HashMap<Integer, Integer> map = index(man, loc);
//         int N = map.size();
//         for (int[] m : man) {
//             int L = map.get(m[0]);
//             int R = map.get(m[1]);
//
//
//         }
//
//     }
//
//
//     public static int repair(int[][] man, int[] loc) {
//         // 1.数据离散化
//         HashMap<Integer, Integer> map = index(man, loc);
//         int N = map.size();
//         SegmentTree segmentTree = new SegmentTree(N);
//         int ans = 0;
//         for (int[] m : man) {
//             int L = map.get(m[0]);
//             int R = map.get(m[1]);
//             segmentTree.add(L, R, 1, 1, N, 1);
//         }
//
//         for (int l : loc) {
//             int L = map.get(l);
//             long q = segmentTree.query(L, L, 1, N, 1);
//             if (q >= 1) {
//                 ans++;
//                 // 线段树中减去这个用掉的影响
//
//             }
//         }
//         return ans;
//     }
//
//     // 离散化
//     private static HashMap<Integer, Integer> index(int[][] man, int[] loc) {
//         TreeSet<Integer> pos = new TreeSet<>();
//         for (int[] m : man) {
//             pos.add(m[0]);
//             pos.add(m[1]);
//         }
//         for (int l : loc) {
//             pos.add(l);
//         }
//         HashMap<Integer, Integer> map = new HashMap<>();
//         int count = 0;
//         for (Integer index : pos) {
//             map.put(index, ++count);
//         }
//         return map;
//     }
//
//     public static class SegmentTree {
//         private int MAXN;
//         private int[] arr;
//         private int[] sum;
//         private int[] lazy;
//         private int[] change;
//         private boolean[] update;
//
//         public SegmentTree(int size) {
//             MAXN = size + 1;
//             sum = new int[MAXN << 2]; // 用来支持脑补概念中，某一个范围的累加和信息
//             lazy = new int[MAXN << 2]; // 用来支持脑补概念中，某一个范围沒有往下傳遞的纍加任務
//             change = new int[MAXN << 2]; // 用来支持脑补概念中，某一个范围有没有更新操作的任务
//             update = new boolean[MAXN << 2]; // 用来支持脑补概念中，某一个范围更新任务，更新成了什么
//         }
//
//         private void pushUp(int rt) {
//             sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
//         }
//
//         // ln表示左子树元素结点个数，rn表示右子树结点个数
//         private void pushDown(int rt, int ln, int rn) {
//             if (update[rt]) {
//                 update[rt << 1] = true;
//                 update[rt << 1 | 1] = true;
//                 change[rt << 1] = change[rt];
//                 change[rt << 1 | 1] = change[rt];
//                 lazy[rt << 1] = 0;
//                 lazy[rt << 1 | 1] = 0;
//                 sum[rt << 1] = change[rt] * ln;
//                 sum[rt << 1 | 1] = change[rt] * rn;
//                 update[rt] = false;
//             }
//             if (lazy[rt] != 0) {
//                 lazy[rt << 1] += lazy[rt];
//                 sum[rt << 1] += lazy[rt] * ln;
//                 lazy[rt << 1 | 1] += lazy[rt];
//                 sum[rt << 1 | 1] += lazy[rt] * rn;
//                 lazy[rt] = 0;
//             }
//         }
//
//
//         // L~R, C 任务！
//         // rt，l~r
//         public void add(int L, int R, int C, int l, int r, int rt) {
//             // 任务如果把此时的范围全包了！
//             if (L <= l && r <= R) {
//                 sum[rt] += C * (r - l + 1);
//                 lazy[rt] += C;
//                 return;
//             }
//             // 任务没有把你全包！
//             // l  r  mid = (l+r)/2
//             int mid = (l + r) >> 1;
//             pushDown(rt, mid - l + 1, r - mid);
//             // L~R
//             if (L <= mid) {
//                 add(L, R, C, l, mid, rt << 1);
//             }
//             if (R > mid) {
//                 add(L, R, C, mid + 1, r, rt << 1 | 1);
//             }
//             pushUp(rt);
//         }
//
//         public long query(int L, int R, int l, int r, int rt) {
//             if (L <= l && r <= R) {
//                 return sum[rt];
//             }
//             int mid = (l + r) >> 1;
//             pushDown(rt, mid - l + 1, r - mid);
//             long ans = 0;
//             if (L <= mid) {
//                 ans += query(L, R, l, mid, rt << 1);
//             }
//             if (R > mid) {
//                 ans += query(L, R, mid + 1, r, rt << 1 | 1);
//             }
//             return ans;
//         }
//
//     }
//
//
//     public static void main(String[] args) {
//         int[][] man = {{1,2},{2,4},{3,6},{7,8},{1,10}};
//         int[] loc = {2,4,6,9,7};
//         // int[][] man = new int[][]{{23, 24}, {16, 19}, {6, 12}, {2, 9}, {2, 10}};
//         // int[] loc = new int[]{9, 23, 6, 17, 11};
//         var ans = repair(man, loc);
//         System.out.println(ans);
//     }
// }
