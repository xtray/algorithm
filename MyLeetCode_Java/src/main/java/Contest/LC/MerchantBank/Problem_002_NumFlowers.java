package Contest.LC.MerchantBank;

import java.util.HashMap;
import java.util.Map;

public class Problem_002_NumFlowers {

    public static class Node {
        public int val;
        public int out;
        public Node(int v) {
            val = v;
            out = 0;
        }
    }

    private Map<Integer, Node> nodeMap;

    public Problem_002_NumFlowers() {
        nodeMap = new HashMap<>();
    }

    // 最大出度+1
    public int numFlowers(int[][] roads) {
        if(roads == null || roads.length ==0 ||
           roads[0] == null || roads[0].length ==0 ) {
            return 0;
        }
        for(int[] r : roads) {
            int one = r[0];
            int two = r[1];
            if(!nodeMap.containsKey(one)) {
                nodeMap.put(one, new Node(one));
            }
            if(!nodeMap.containsKey(two)) {
                nodeMap.put(two, new Node(two));
            }
            Node nodeOne = nodeMap.get(one);
            Node nodeTwo = nodeMap.get(two);
            nodeOne.out++;
            nodeTwo.out++;
        }
        int maxOut = 0;
        for(Node node : nodeMap.values()) {
            maxOut = Math.max(maxOut, node.out);
        }
        return maxOut + 1;
    }

    public static void main(String[] args) {
        // int[][] roads = {{0,1}, {1,3}, {1,2}};
        int[][] roads = {{0,1},{0,2},{1,3},{2,5},{3,6},{5,4}};
        var ans = new Problem_002_NumFlowers().numFlowers(roads);
        System.out.println(ans);
    }
}
