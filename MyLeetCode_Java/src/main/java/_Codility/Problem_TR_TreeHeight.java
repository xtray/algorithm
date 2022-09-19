package _Codility;

/**
 * TreeHeight
 * Compute the height of a binary tree.
 * https://app.codility.com/demo/results/trainingPHND7E-KV4/
 */

public class Problem_TR_TreeHeight {
    class Tree {
        public int x;
        public Tree l;
        public Tree r;
    }

    public int solution(Tree T) {
        if (T == null) return -1;
        return Math.max(solution(T.l), solution(T.r)) + 1;
    }

}
