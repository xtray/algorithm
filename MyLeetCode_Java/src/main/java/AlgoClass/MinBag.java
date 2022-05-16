package AlgoClass;

// T38

public class MinBag {

    public static int minBag(int apple) {
        if (apple < 0) {
            return -1;
        }
        int maxEight = apple / 8;
        int cnt = -1;
        while (maxEight >= 0) {
            int rest = apple - maxEight * 8;
            if (rest % 6 == 0) {
                cnt = maxEight + rest / 6;
                break;
            }
            maxEight--;
        }
        return cnt;
    }

    public static int minBagAwesome(int apple) {
        if (apple < 0 || (apple & 1) != 0) {
            return -1;
        }
        if (apple < 18) {
            return apple == 0 ? 0 : (apple == 6 || apple == 8) ? 1 :
                    (apple == 12 || apple == 14 || apple == 16) ? 2 : -1;
        }
        return (apple - 18) / 8 + 3;
    }

    public static void main(String[] args) {
        int totalCnt = 200;
        for (int i = 1; i <= 200; i++) {
            var ans = minBag(i);
            System.out.println(i + ": " + ans);
        }
    }
}
