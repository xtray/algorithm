package LeetCode;

public class Problem_1732_LargestAltitude {

    public static int largestAltitude(int[] gain) {
        int max = 0; // NOTE: 不要忘了开始有个0
        int pre = 0;
        for (int num : gain) {
            pre = num + pre;
            max = Math.max(max, pre);
        }
        return max;
    }

    public static void main(String[] args) {
        // int[] gain = {-5,1,5,0,-7}; // 1
        int[] gain = {-4, -3, -2, -1, 4, 3, 2}; // 0
        var ans = largestAltitude(gain);
        System.out.println(ans);
    }
}
