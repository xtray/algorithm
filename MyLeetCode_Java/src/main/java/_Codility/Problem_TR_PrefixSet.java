package _Codility;

import java.util.*;

/**
 * 1. PrefixSet
 * Given a table A of N integers from 0 to N-1 calculate the smallest such index P,
 * that that {A[0],...,A[N-1]} = {A[0],...,A[P]}.
 * https://app.codility.com/demo/results/trainingCKBKPG-MQA/
 */
public class Problem_TR_PrefixSet {

    public int solution(int[] A) {
        // write your code in Java SE 8
        Set<Integer> set = new HashSet<>();
        for (int num : A) {
            set.add(num);
        }
        int size = set.size();
        set.clear();
        for (int i = 0; i < A.length; i++) {
            set.add(A[i]);
            if (set.size() == size) {
                return i;
            }
        }
        return A.length;
    }
}
