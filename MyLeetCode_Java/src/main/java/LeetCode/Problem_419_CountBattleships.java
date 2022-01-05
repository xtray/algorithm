package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Problem_419_CountBattleships {

    public int countBattleships(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return 0;
        }
        // 从每一个点出发, 向左或者向下做扫描检查
        int ans = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X') {
                    infect(board, i, j);
                    ans++;
                }
            }
        }
        restore(board);
        return ans;
    }

    private void restore(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'T') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    // 从 i,j 位置出发开始感染, 不同于岛问题, 以 i,j 作为左上角, 只用向左或者向下即可
    private void infect(char[][] board, int i, int j) {
        int N = board.length;
        int M = board[0].length;
        board[i][j] = 'T';
        // 向左
        if (j + 1 < M && board[i][j + 1] == 'X') {
            infect(board, i, j + 1);
        }
        // 向下
        if (i + 1 < N && board[i + 1][j] == 'X') {
            infect(board, i + 1, j);
        }
    }

    // 并查集的做法
    public static class Node {
        public int value;

        public Node(int v) {
            value = v;
        }
    }

    public static class UnionFind {
        // 所有的点
        public HashMap<Integer, Node> nodeHashMap;
        // 每个节点的父节点
        public HashMap<Node, Node> fatherMap;
        // 每个结合的代表头结点及其集合大小
        public HashMap<Node, Integer> sizeMap;

        public UnionFind(List<Integer> nodelist) {
            nodeHashMap = new HashMap<>();
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (int val : nodelist) {
                Node node = new Node(val);
                nodeHashMap.put(val, node);
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        // 返回当前节点的代表头结点
        private Node findFather(Node node) {
            // 把往上找的途径节点都记在 stack, 后面扁平化用
            LinkedList<Node> path = new LinkedList<>();
            while (node != fatherMap.get(node)) {
                path.push(node);
                node = fatherMap.get(node);
            }
            while (!path.isEmpty()) {
                fatherMap.put(path.pop(), node);
            }
            return node;
        }

        // 判断两个节点是不是一个集合的
        private boolean isSameSet(Integer a, Integer b) {
            if (nodeHashMap.containsKey(a) && nodeHashMap.containsKey(b)) {
                return findFather(nodeHashMap.get(a)) == findFather(nodeHashMap.get(b));
            }
            return false;
        }

        // 合并两个节点
        public void union(Integer a, Integer b) {
            if (nodeHashMap.containsKey(a) && nodeHashMap.containsKey(b)) {
                Node aF = findFather(nodeHashMap.get(a));
                Node bF = findFather(nodeHashMap.get(b));
                if (aF != bF) {
                    Node big = sizeMap.get(aF) >= sizeMap.get(bF) ? aF : bF;
                    Node small = big == aF ? bF : aF;
                    fatherMap.put(small, big);
                    sizeMap.put(big, sizeMap.get(aF) + sizeMap.get(bF));
                    sizeMap.remove(small);
                }
            }
        }

        // 返回并查集中集合的数量
        public int getSetSum() {
            return sizeMap.size();
        }
    }

    public int countBattleships2(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return 0;
        }

        List<Integer> nodeList = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X') {
                    nodeList.add(i * board[0].length + j);
                }
            }
        }
        UnionFind uf = new UnionFind(nodeList);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X') {
                    int current = i * board[0].length + j;
                    // 上面
                    if (i - 1 >= 0 && board[i - 1][j] == 'X') {
                        int up = (i - 1) * board[0].length + j;
                        uf.union(current, up);
                    }
                    // 左侧
                    if (j - 1 >= 0 && board[i][j - 1] == 'X') {
                        int left = i * board[0].length + j - 1;
                        uf.union(current, left);
                    }
                }

            }
        }
        return uf.getSetSum();
    }


    public static void main(String[] args) {
        char[][] board = new char[][]{{'X', '.', '.', 'X'}, {'.', '.', '.', 'X'}, {'.', '.', '.', 'X'}};
        var ans = new Problem_419_CountBattleships().countBattleships(board);
        System.out.println(ans);
        var ans2 = new Problem_419_CountBattleships().countBattleships2(board);
        System.out.println(ans2);
    }
}
