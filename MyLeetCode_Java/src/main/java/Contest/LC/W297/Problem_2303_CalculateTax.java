package Contest.LC.W297;

public class Problem_2303_CalculateTax {

    public double calculateTax(int[][] brackets, int income) {
        double ans = 0;
        int pre = 0;
        for (int[] b : brackets) {
            if (income > b[0]) {
                ans += (b[0] - pre) * b[1] / 100.0;
                pre = b[0];
            } else {
                ans += (income - pre) * b[1] / 100.0;
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] brackets = {{3,50},{7,10},{12,25}};
        int income = 10;
        var ans = new Problem_2303_CalculateTax().calculateTax(brackets, income);
        System.out.println(ans);
    }
}
