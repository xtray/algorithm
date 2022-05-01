package L_INPRG;

import java.util.*;

/**
 * 有一个需要维修的地点数组，有一个维修工的数组存着维修工的接单范围，
 * 求最多能维修多少单，每个维修工只能维修一单，
 * 用例1:
 * 维修工: {{1,2},{2,4},{3,6},{7,8},{1,10}}
 * 维修地点: 2,4,6,9,7
 * 输出: 5
 * 用例2:
 * 维修工: {{23, 24}, {16, 19}, {6, 12}, {2, 9}, {2, 10}}
 * 维修地点: {9, 23, 6, 17, 11}
 * 输出: 5
 * 数据范围:
 * 维修工, 维修地点 数组长度: 10^5
 * 数值大小: 10^9
 */
public class Problem_000_RepairMan_P {

    public class Node {
        public int value;
        public int in;
        public int out;
        public ArrayList<Node> nexts;
        public ArrayList<Edge> edges;

        public Node(int value) {
            this.value = value;
            in = 0;
            out = 0;
            nexts = new ArrayList<>();
            edges = new ArrayList<>();
        }
    }

    public class Edge {
        public int weight;
        public Node from;
        public Node to;

        public Edge(int weight, Node from, Node to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }

    // 图的解法
    public static int repair2(int[][] man, int[] loc) {
        Arrays.sort(loc);
        // 1.数据离散化
        HashMap<Integer, Integer> map = index(man, loc);
        int N = map.size();
        for (int[] m : man) {
            int L = map.get(m[0]);
            int R = map.get(m[1]);


        }

        return 0;
    }


    public static int repair(int[][] man, int[] loc) {
        // 1.数据离散化
        HashMap<Integer, Integer> map = index(man, loc);
        int N = map.size();
        SegmentTree segmentTree = new SegmentTree(N);
        int ans = 0;
        for (int[] m : man) {
            int L = map.get(m[0]);
            int R = map.get(m[1]);
            segmentTree.add(L, R, 1, 1, N, 1);
        }

        for (int l : loc) {
            int L = map.get(l);
            long q = segmentTree.query(L, L, 1, N, 1);
            if (q >= 1) {
                ans++;
                // 线段树中减去这个用掉的影响

            }
        }
        return ans;
    }

    // 离散化
    private static HashMap<Integer, Integer> index(int[][] man, int[] loc) {
        TreeSet<Integer> pos = new TreeSet<>();
        for (int[] m : man) {
            pos.add(m[0]);
            pos.add(m[1]);
        }
        for (int l : loc) {
            pos.add(l);
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (Integer index : pos) {
            map.put(index, ++count);
        }
        return map;
    }

    public static class SegmentTree {
        private int MAXN;
        private int[] arr;
        private int[] sum;
        private int[] lazy;
        private int[] change;
        private boolean[] update;

        public SegmentTree(int size) {
            MAXN = size + 1;
            sum = new int[MAXN << 2]; // 用来支持脑补概念中，某一个范围的累加和信息
            lazy = new int[MAXN << 2]; // 用来支持脑补概念中，某一个范围沒有往下傳遞的纍加任務
            change = new int[MAXN << 2]; // 用来支持脑补概念中，某一个范围有没有更新操作的任务
            update = new boolean[MAXN << 2]; // 用来支持脑补概念中，某一个范围更新任务，更新成了什么
        }

        private void pushUp(int rt) {
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }

        // ln表示左子树元素结点个数，rn表示右子树结点个数
        private void pushDown(int rt, int ln, int rn) {
            if (update[rt]) {
                update[rt << 1] = true;
                update[rt << 1 | 1] = true;
                change[rt << 1] = change[rt];
                change[rt << 1 | 1] = change[rt];
                lazy[rt << 1] = 0;
                lazy[rt << 1 | 1] = 0;
                sum[rt << 1] = change[rt] * ln;
                sum[rt << 1 | 1] = change[rt] * rn;
                update[rt] = false;
            }
            if (lazy[rt] != 0) {
                lazy[rt << 1] += lazy[rt];
                sum[rt << 1] += lazy[rt] * ln;
                lazy[rt << 1 | 1] += lazy[rt];
                sum[rt << 1 | 1] += lazy[rt] * rn;
                lazy[rt] = 0;
            }
        }


        // L~R, C 任务！
        // rt，l~r
        public void add(int L, int R, int C, int l, int r, int rt) {
            // 任务如果把此时的范围全包了！
            if (L <= l && r <= R) {
                sum[rt] += C * (r - l + 1);
                lazy[rt] += C;
                return;
            }
            // 任务没有把你全包！
            // l  r  mid = (l+r)/2
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            // L~R
            if (L <= mid) {
                add(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                add(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        public long query(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return sum[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            long ans = 0;
            if (L <= mid) {
                ans += query(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                ans += query(L, R, mid + 1, r, rt << 1 | 1);
            }
            return ans;
        }

    }


    // 暴力解法, 尝试每一个人去解决第一个
    public static int repairBao(int[][] manRange, int[] loc) {
        List<int[]> range = new ArrayList<>();
        for (int[] m : manRange) {
            range.add(m);
        }
        return processBao(range, 0, loc);
    }

    // 尝试每一个人去解决index位置的工作, 使用的人从 range里删除
    // 从 index 开始到工作结束能处理的个数
    private static int processBao(List<int[]> range, int index, int[] loc) {
        int N = loc.length;
        if (index == N) {
            return 0;
        }
        int maxCnt = 0;
        int size = range.size();
        for (int i = 0; i < size; i++) {
            int point = loc[index];
            int[] cur = range.get(i);
            int curCnt = 0;
            if (cur[0] <= point && point <= cur[1]) {
                range.remove(i);
                curCnt = processBao(range, index + 1, loc) + 1;
                range.add(i, cur);
            } else {
                curCnt = processBao(range, index + 1, loc);
            }
            maxCnt = Math.max(maxCnt, curCnt);
        }

        return maxCnt;
    }


    // 作为对数器检查
    public static int repairCheck(int[][] range, int[] positions) {
        Arrays.sort(positions);
        Arrays.sort(range, Comparator.comparingInt(o -> o[0]));
        int count = 0;
        int index = 0;
        int posNum = positions.length;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int position : positions) {
            while (index < posNum && range[index][0] <= position) {
                queue.add(range[index][1]);
                index++;
            }
            while (!queue.isEmpty()) {
                Integer cur = queue.poll();
                if (cur >= position) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }


    public static void main(String[] args) {
        // int[][] man = {{1,2},{2,4},{3,6},{7,8},{1,10}};
        // int[] loc = {2,4,6,9,7};
        // // int[][] man = new int[][]{{23, 24}, {16, 19}, {6, 12}, {2, 9}, {2, 10}};
        // // int[] loc = new int[]{9, 23, 6, 17, 11};
        // // var ans = repair(man, loc);
        // // System.out.println(ans);

        // int[][] man = {{1,2},{4,5}};
        // int[] loc = {5,7};

        // int[][] man = {{2,4},{4,4},{4,5}};
        // int[] loc = {2,3,5};

        // var ansBao = repairBao(man, loc);
        // System.out.println(ansBao);
        // var ansChk = repairCheck(man, loc);
        // System.out.println(ansChk);

        int[][] man = {{1,1},{6,8},{8,10}};
        int[] loc = {1,9};
        var ansChk = repairCheck(man, loc);
        System.out.println(ansChk);

        int times = 0;
        int manMaxCnt = 5;
        int locMaxCnt = 5;
        int maxRange = 10;
        System.out.println("测试开始");
        for (int i = 0; i < times; i++) {
            int manCnt = (int) (Math.random() * manMaxCnt) + 1;
            int locCnt = (int) (Math.random() * locMaxCnt) + 1;
            int manRange = (int) (Math.random() * maxRange) + 1;
            int locRange = (int) (Math.random() * maxRange) + 1;
            int[][] manArr = generateRandMan(manCnt, manRange);
            int[] locArr = generateRandLoc(locCnt, locRange);
            int ans1 = repairBao(manArr, locArr);
            int ans2 = repairCheck(manArr, locArr);
            if (ans1 != ans2) {
                System.out.println("Ooops!");
                System.out.println("ansBao: " + ans1);
                System.out.println("ans2: " + ans2);
                printManArr(manArr);
                printLocArr(locArr);
                break;
            }
            System.out.println("Finish turn: " + (i + 1));
        }
        System.out.println("测试结束");
    }

    private static void printLocArr(int[] locArr) {
        for (int loc : locArr) {
            System.out.print(loc + " ");
        }
        System.out.println();
    }

    private static void printManArr(int[][] manArr) {
        for (int[] m : manArr) {
            System.out.print("[" + m[0] + "--" + m[1] + "]");
            System.out.println();
        }
        System.out.println();
    }

    private static int[] generateRandLoc(int locCnt, int locRange) {
        int[] loc = new int[locCnt];
        Set<Integer> set = new HashSet<>();
        int cur = 0;
        for (int i = 0; i < locCnt; i++) {
            do {
                cur = (int) (Math.random() * locRange) + 1;
            } while (set.contains(cur));
            set.add(cur);
            loc[i] = cur;
        }
        return loc;
    }

    private static int[][] generateRandMan(int manCnt, int manRange) {
        int[][] ans = new int[manCnt][2];
        for (int[] item : ans) {
            int start = (int) (Math.random() * manRange + 1);
            int end = start + (int) (Math.random() * manRange / 2);
            item[0] = start;
            item[1] = end;
        }
        return ans;
    }
}
