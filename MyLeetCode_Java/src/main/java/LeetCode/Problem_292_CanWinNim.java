package LeetCode;

public class Problem_292_CanWinNim {

    // 1~3
    public static boolean canWinNim(int n) {

        if (n <= 3) {
            return true;
        }

//        return process(n);
        return process(n).equals("先手");
    }

    // 以先手的视角考虑
    // 先手可以拿 1~3 块手头, 返回先手会不会赢
    // 参数 2: 是不是先手
    private static String process(int n) {
        if (n <= 0) {
            return "后手";
        }
        // 先手开始做决定
        for (int i=1;i<=3;i++) {
               String ans = process(n-i);
               if (ans.equals("后手")) {
                   return "先手";
               }
        }
        return "后手";
    }

    public static boolean canWinNim2(int n) {
        if (n <= 3) {
            return true;
        }
        return  n % 4 != 0;
    }

    public static void main(String[] args) {
        boolean ans = canWinNim(4);
        System.out.println(ans);

        int N = 100;
        for (int i=1; i<=N;i++) {
            ans = canWinNim(i);
            System.out.printf("%d : %b \n", i, ans);
        }
        System.out.println("================Finish");
    }

}
