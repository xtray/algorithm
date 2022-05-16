package AlgoClass;

// T38

public class EatGrass {

    // n份草, 返回"先手"/"后手"
    public static String whoWin(int n) {
        if (n < 5) {
            return n == 0 || n == 2 ? "后手" : "先手";
        }
        // 目前是当前过程中的先手先选, 可以选择所有4的每次放
        int want = 1;
        while (want <= n) {
            if (whoWin(n - want).equals("后手")) {
                return "先手";
            }
            want *= 4;
            // if(want <= n/4) {
            //     want *= 4;
            // } else {
            //     break;
            // }
        }
        return "后手";
    }

    public static String whoWinAwesome(int n) {
        return n%5 == 0 || n%5 == 2 ? "后手" : "先手";
    }

    public static void main(String[] args) {
        int n = 200;
        for(int i = 0; i<=80; i++) {
            System.out.println(i + ": " + whoWin(i));
        }
    }
}
