package LeetCode;

public class Problem_1491_Average {

    public double average(int[] salary) {
        if( salary == null || salary.length ==0) {
            return 0.0;
        }
        int max = salary[0];
        int min = salary[0];
        double sum = 0;
        for(int sa : salary) {
            max = Math.max(max, sa);
            min = Math.min(min, sa);
            sum += sa;
        }
        return (sum - max -min)/(salary.length -2);
    }
}
