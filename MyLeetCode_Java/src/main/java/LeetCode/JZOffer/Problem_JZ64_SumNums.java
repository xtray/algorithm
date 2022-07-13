package LeetCode.JZOffer;

public class Problem_JZ64_SumNums {

    public int sumNums(int n) {
        return n == 1 ? 1 : n + sumNums(n - 1);
    }
}
