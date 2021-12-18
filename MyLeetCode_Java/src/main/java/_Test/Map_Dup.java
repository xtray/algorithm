package _Test;

import java.util.HashMap;
import java.util.Map;

public class Map_Dup {

    public static class Node {
        int val;
        String name;
        public Node(int v, String n) {
            val = v;
            name =n;
        }
    }

    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap();
        map.put(1,1);
        map.put(1,2);
        map.put(2,2);
        map.put(2,3);
        for(int num : map.keySet()) {
            System.out.println(map.get(num));
        }
        Map<Node, Integer> map1 = new HashMap<>();
        Node node1 = new Node(1,"A");
        Node node2 = new Node(1,"A");
        Node node3 = new Node(1,"B");
        map1.put(node1,node1.val);
        map1.put(node2,node1.val);
        map1.put(node3,node1.val);
        for(Node node : map1.keySet()) {
            System.out.println(node.name + ": " + map1.get(node));
        }
    }
}
