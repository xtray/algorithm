package LeetCode;

public class Problem_1399_CountLargestGroup {
    public int countLargestGroup(int n) {
        int[] cnt = new int[37];
        for (int i = 1; i <= n; i++) {
            int sum = getDigitSum(i);
            cnt[sum]++;
        }
        int maxVal = 0;
        int maxCnt = 0;
        for (int num : cnt) {
            if(num > maxVal) {
                maxVal = num;
                maxCnt = 1;
            } else if(num == maxVal) {
                maxCnt++;
            }
        }
        return maxCnt;
    }

    private int getDigitSum(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        int num = 24;
        var ans = new Problem_1399_CountLargestGroup().countLargestGroup(num);
        System.out.println(ans);
    }
}
