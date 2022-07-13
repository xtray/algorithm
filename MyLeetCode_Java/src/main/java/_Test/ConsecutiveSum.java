package _Test;

public class ConsecutiveSum {

    public static boolean isConsecutiveSum(int num) {

        // 尝试num是否可以表示为以每一个i开头的连续正数和
        for (int i = 1; i <= num; i++) {
            int sum = i;
            for (int j = i + 1; j <= num; j++) {
                if(sum + j > num) {
                    // 前一个小于, 后一个大于, 不可能表示为i开头的连续正数和
                    break;
                }
                if(sum + j == num) {
                    return true;
                }
                sum += j;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int number = 200;
        for(int i = 1; i<=number; i++) {
            var ans = isConsecutiveSum(i);
            System.out.println(i + ": " + ans);
        }
    }
}
