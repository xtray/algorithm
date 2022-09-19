package _Codility;

/**
 * EquiLeader
 *
 * Find the index S such that the leaders of the sequences A[0], A[1], ..., A[S]
 * and A[S + 1], A[S + 2], ..., A[N - 1] are the same.
 *
 * https://app.codility.com/demo/results/trainingE6WBSB-R5N/
 */
public class Problem_TR802_EquiLeader {

    public int solution(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int N = A.length;
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
            return 0;
        }
        HP = 0;
        for (int num : A) {
            if (num == candidate) {
                HP++;
            }
        }
        if (HP <= N / 2) {
            return 0;
        }
        int cnt = 0;
        int HPLeft = 0;
        int HPRight = HP;
        // 枚举每一个left分割点
        for (int i = 0; i < N - 1; i++) {
            if (A[i] == candidate) {
                HPLeft++;
            }
            HPRight = HP - HPLeft;
            if ((HPLeft > (i + 1)/2) && (HPRight > (N-i-1)/2)) {
                cnt++;
            }
        }
        return cnt;
    }
}
