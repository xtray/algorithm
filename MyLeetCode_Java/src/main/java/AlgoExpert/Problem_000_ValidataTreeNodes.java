package AlgoExpert;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定三个节点, one, two, three
 * one, three之一可能是 two 的父节点, 如果一个是父节点, 那么另外一个就是 two 的子节点
 * 如果满足上面描述返回 true, 否则返回 false
 * 例: 比如 one 是 two 的父节点, 那么 three 是 two 的子节点时返回 true
 * 例: 比如 three 是 two 的父节点, 那么 one 是 two 的子节点时返回 true
 */
public class Problem_000_ValidataTreeNodes {

    // This is an input class. Do not edit.
    static class BST {
        public int value;
        public BST left = null;
        public BST right = null;

        public BST(int value) {
            this.value = value;
        }
    }

    // 只有两种可能:
    // 1-->2-->3
    // 3-->2-->1
    public boolean validateThreeNodes(BST nodeOne, BST nodeTwo, BST nodeThree) {
        Set<BST> path = new HashSet<>();
        // 从 1 出发找 3
        boolean p1 = process(nodeOne, nodeTwo, nodeThree);
        // 从 3 出发找 1
        boolean p2 = process(nodeThree, nodeTwo, nodeOne);
        return p1||p2;
    }

    // 从 head 出发 找 tail, 中间看经不经过 mid
    private boolean process(BST head, BST mid, BST tail) {
        boolean step1 = find(head, mid);
        if(!step1) return false;
        return find(mid, tail);
    }

    // 从 start 出发找 target
    private boolean find(BST head, BST target) {
        if(head == null) {
            return false;
        }
        if(head.value == target.value) {
            return true;
        }
        if(target.value < head.value) {
            return find(head.left, target);
        } else {
            return find(head.right, target);
        }
    }
}
