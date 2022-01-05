package _Test;

public class ModTwo {

    public int getTotalNum(int target, int range) {
        int t = getBinForm(target);
        int ans = 0;
        for (int i = 0; i <= range; i++) {
            int cur = getBinForm(i);
            int add1 = t + cur;
            int add2 = getBinForm(target + i);
            if (add1 == add2) {
                System.out.println(i);
                ans++;
            }
        }
        return ans;
    }

    private int getBinForm(int target) {
        int ans = 0;
        int move = 0;
        while (target != 0) {
            int cur = target % 10;
            if ((cur & 1) == 1) { // 该位置是奇数
                ans |= 1 << move;
            }
            move++;
            target /= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        int target = 23;
        int ans1 = new ModTwo().getTotalNum(target, 9);
        int ans2 = new ModTwo().getTotalNum(target, 99);
        System.out.println("Ans: " + (ans2 - ans1));
    }
}
