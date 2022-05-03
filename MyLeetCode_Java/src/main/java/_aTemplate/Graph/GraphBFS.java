package _aTemplate.Graph;

import java.util.*;

public class GraphBFS {

    // 说BFS一定要指定一个出发点
    public void bfs(Node start) {
        if (start == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        Set<Node> set = new HashSet<>(); // 1.防止一个点多次进入
        queue.add(start); // 从尾部加入
        set.add(start); // 入队就注册
        while (!queue.isEmpty()) {
            Node cur = queue.poll(); // 从头部弹出
            System.out.println(cur.value); // 弹出就打印
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }
}
