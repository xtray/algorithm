package _aTemplate.Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class GraphDFS {

    // 需要指定出发点
    // 一条路没走完就走到死, 不能走出环路
    public void dfs(Node start) {
        if (start == null) {
            return;
        }
        LinkedList<Node> stack = new LinkedList<>();
        Set<Node> set = new HashSet<>();
        stack.addFirst(start);
        set.add(start);
        System.out.println(start.value); // 入栈就打印
        while (!stack.isEmpty()) {
            Node cur = stack.pollFirst();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.addFirst(cur); // NOTE: 先把自己加回去!!!
                    stack.addFirst(next);
                    set.add(next);
                    System.out.println(next.value); // 入栈就打印
                    break; // 发现有一个不在set里就不遍历了
                }
            }
        }
    }


    public void dfs2(Node start) {
        if (start == null) {
            return;
        }
        Set<Node> visited = new HashSet<>();
        dfs(start, visited);
    }

    private void dfs(Node cur, Set<Node> visited) {
        System.out.println(cur.value);
        visited.add(cur);
        for (Node next : cur.nexts) {
            if (!visited.contains(next)) {
                dfs(next, visited);
            }
        }
    }
}
