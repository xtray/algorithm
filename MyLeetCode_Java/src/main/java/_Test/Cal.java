package _Test;

public class Cal {
    public static void main(String[] args) {

        int num = Integer.MAX_VALUE;
        int ans = (int)(Math.log10(num) / Math.log10(2));
        System.out.println(ans);
        System.out.println((long)Math.pow(2,31));
    }
}
