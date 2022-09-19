package _Contest.LC.W311;

public class Problem_2413_SmallestMultiple {

    public int smallestEvenMultiple(int n) {
        return n * 2 / gcd(n, 2);
    }

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
