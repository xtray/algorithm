package _Test;

public class MaxPowerOfThree {

    public static int maxPowOfThree() {
        long n = 1;
        while ( n*3 < Integer.MAX_VALUE) {
            n *= 3;
        }
        return (int)n;
    }

    public static void main(String[] args) {
        var ans = maxPowOfThree();
        System.out.println(ans);
    }
}
