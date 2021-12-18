package LeetCode;

public class Problem_869_GetNum {

    private static int getRealNum(int[] input) {
        int N = input.length;
        if (input[N-1]==0) {
            return -1;
        }
        int pre = 0;
        for(int i=N-1; i>=0; i--) {
            int cur = input[i];
            pre = pre * 10 + cur;
        }
        return pre;
    }


    public static void main(String[] args) {
        int[] list = {3,2,7};
        int num = getRealNum(list);
        System.out.println(num);
    }
}
