package _Codility;

/**
 * Dominator
 * 众数 - 摩尔投票法
 * 血量为0立候选
 *
 * Find an index of an array such that its value occurs at more than half of indices in the array.
 * https://app.codility.com/demo/results/trainingK7RJMW-3TE/
 */
public class Problem_TR801_Dominator {

    public int solution(int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int candidate = -1;
        int HP = 0;
        for (int num : A) {
            if (HP == 0) {
                candidate = num;
                HP++;
            } else {
                if (num != candidate) {
                    HP--;
                } else {
                    HP++;
                }
            }
        }
        if (HP == 0) {
            return -1;
        }
        HP = 0;
        int idx = 0;
        int pos = -1;
        for (int num : A) {
            if (num == candidate) {
                HP++;
                pos = idx;
            }
            idx++;
        }
        if (HP <= A.length >> 1) {
            return -1;
        }
        return pos;
    }


}
