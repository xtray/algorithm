package AlgoExpert;

import java.util.*;

public class Problem_863_DistanceK {

    static class BinaryTree {
        public int value;
        public BinaryTree left = null;
        public BinaryTree right = null;

        public BinaryTree(int value) {
            this.value = value;
        }
    }

    private BinaryTree targetNode = null;

    public ArrayList<Integer> findNodesDistanceK(BinaryTree root, int target, int k) {
        // 建立所有节点的父亲对应表
        HashMap<BinaryTree, BinaryTree> parents = new HashMap<>();
        parents.put(root, null);
        createParentMap(root, parents);
        // 从 target 节点开始 BFS
        // 先找到 target 节点
        findTargetNode(root, target);
        if (targetNode == null) {
            return new ArrayList<Integer>();
        }
        Queue<BinaryTree> queue = new LinkedList<>();
        queue.add(targetNode);
        Set<BinaryTree> set= new HashSet<>();
        set.add(targetNode);
        int currentLevel = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-->0) {
                BinaryTree node = queue.poll();
                if(currentLevel == k) {
                    ans.add(node.value);
                }
                // BFS, 左右子树 + 父
                if (node.left !=null&&!set.contains(node.left)) {
                    queue.add(node.left);
                    set.add(node.left);
                }
                if (node.right !=null&&!set.contains(node.right)) {
                    queue.add(node.right);
                    set.add(node.right);
                }
                if(parents.get(node)!=null&&!set.contains(parents.get(node))) {
                    queue.add(parents.get(node));
                    set.add(parents.get(node));
                }
            }
            currentLevel++;
            if (currentLevel>k){
                break;
            }
        }
        return ans;
    }

    private void findTargetNode(BinaryTree cur, int target) {
        if(cur == null) {
            return;
        }
        if (cur.value == target) {
            targetNode = cur;
            return;
        }
        if(cur.left != null) {
            findTargetNode(cur.left, target);
        }
        if(cur.right != null) {
            findTargetNode(cur.right, target);
        }
    }

    private void createParentMap(BinaryTree cur, HashMap<BinaryTree, BinaryTree> parents) {
        if (cur == null) {
            return;
        }
        if (cur.left !=null) {
            parents.put(cur.left, cur);
            createParentMap(cur.left, parents);
        }
        if (cur.right !=null) {
            parents.put(cur.right, cur);
            createParentMap(cur.right, parents);
        }
    }
}
