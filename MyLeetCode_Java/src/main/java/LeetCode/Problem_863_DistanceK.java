package LeetCode;

import java.util.*;

public class Problem_863_DistanceK {

  public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
  
    public ArrayList<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // 建立所有节点的父亲对应表
        HashMap<TreeNode, TreeNode> parents = new HashMap<>();
        parents.put(root, null);
        createParentMap(root, parents);
        // 从 target 节点开始 BFS
        // 先找到 target 节点
        if (target == null) {
            return new ArrayList<Integer>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(target);
        Set<TreeNode> set= new HashSet<>();
        set.add(target);
        int currentLevel = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-->0) {
                TreeNode node = queue.poll();
                if(currentLevel == k) {
                    ans.add(node.val);
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
    private void createParentMap(TreeNode cur, HashMap<TreeNode, TreeNode> parents) {
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
