package _Codility;

/**
 * OddOccurrencesInArray
 * Find value that occurs in odd number of elements.
 * https://app.codility.com/demo/results/training5SFY9G-3XZ/
 */
public class Problem_TR202_OddOccurrence {

    public int solution(int[] A) {
        // write your code in Java SE 8
        int xor = 0;
        for (int num : A) {
            xor ^= num;
        }
        return xor;
    }
}
