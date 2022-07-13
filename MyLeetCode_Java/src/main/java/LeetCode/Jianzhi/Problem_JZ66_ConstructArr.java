package LeetCode.Jianzhi;

public class Problem_JZ66_ConstructArr {
    public int[] constructArr(int[] a) {
        if (a == null || a.length == 0) {
            return new int[0];
        }
        int N = a.length;

        int[] mul = new int[N];
        mul[N - 1] = a[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            mul[i] = a[i] * mul[i + 1];
        }
        int[] ans = new int[N];
        int product = 1;
        for (int i = 0; i < N - 1; i++) {
            ans[i] = product * mul[i + 1];
            product *= a[i];
        }
        ans[N - 1] = product;
        return ans;
    }
}
