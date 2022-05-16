package _Test;

public class Ln2 {

    public static void main(String[] args) {
        var ans = Math.log(2); // ~ 0.7
        System.out.println(ans);

        long n = (long)1e10;
        double p = 0.0001;
        var m = -(int)((n*Math.log(p)/Math.pow(Math.log(2), 2))/(1e9));
        System.out.println(m);
    }
}
