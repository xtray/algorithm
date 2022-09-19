package _Codility;

import java.util.HashSet;
import java.util.Set;

/**
 * FrogRiverOne
 * Find the earliest time when a frog can jump to the other side of a river.
 * https://app.codility.com/demo/results/trainingZ6VP3S-JV9/
 */

public class Problem_TR401_FrogRiverOne {


    // 返回最早收集全1...X的时刻
    public int solution(int X, int[] A) {
        // write your code in Java SE 8
        // 数组做set
        Set<Integer> set = new HashSet<>();
        int N = A.length;
        for (int i = 0; i < N; i++) {
            set.add(A[i]);
            if (set.size() == X) {
                return i;
            }
        }
        return -1;
    }
}
