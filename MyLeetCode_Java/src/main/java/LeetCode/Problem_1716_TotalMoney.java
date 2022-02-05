package LeetCode;

public class Problem_1716_TotalMoney {

    // 1,2,3,4,5,6,7-->28
    // 1+()
    //
    public int totalMoney(int n) {
        int week = n / 7; // 也是每周每天多出的钱
        int day = n % 7;
        int deltaW = 0;
        int deltaD = 0;
        if (week >= 1) {
            deltaW = 7 * week * (week - 1) / 2; // 周数多出来的钱
            deltaD = week * day; // 不满一周的多出来的钱
        }
        // 每周 28 天, 1..day 的和,         周数多出来  天数多出来
        return week * 28 + (1+day)*day/2 + deltaW + deltaD;
    }

    public static void main(String[] args) {
        int n = 20;
        int ans = new Problem_1716_TotalMoney().totalMoney(n);
        System.out.println(ans);

    }
}
