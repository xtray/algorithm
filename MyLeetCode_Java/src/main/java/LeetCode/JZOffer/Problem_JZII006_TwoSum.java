package LeetCode.JZOffer;

public class Problem_JZII006_TwoSum {

    public int[] twoSum(int[] numbers, int target) {
        if(numbers == null || numbers.length ==0) {
            return new int[]{};
        }
        int N = numbers.length;
        int L = 0;
        int R = N - 1;
        while (L<R) {
            int sum = numbers[L] + numbers[R];
            if(sum < target) {
                L++;
            } else if(sum > target) {
                R--;
            } else {
                return new int[]{L,R};
            }
        }
        return new int[]{};
    }
}
