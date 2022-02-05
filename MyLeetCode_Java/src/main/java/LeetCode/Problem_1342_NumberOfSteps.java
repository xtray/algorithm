package LeetCode;

public class Problem_1342_NumberOfSteps {

    public int numberOfSteps(int num) {
        if (num < 0) {
            return 0;
        }
        return process1(num);
    }


    private int process(int num) {
        if (num == 0) {
            return 0;
        }
        if ((num & 1) == 1) {
            return 1 + process(--num);
        }
        return 1 + process(num >>> 1);
    }

    private int process1(int num) {
        int step = 0;
        while (num !=0) {
            if((num &1) ==1){
                num -=1;
            } else {
                num>>>=1;
            }
            step++;
        }
        return step;
    }

    // 如果当前的 num 最低位不为 1（偶数），则不断进行右移，直到最低位为 1（奇数），
    // 然后再对最低位的 1 进行消减，直到二进制表示中的所有11 均被消减完（结果为 0），模拟过程结束。
    // 换句话说，总的操作次数为 = 右移次数 + 消减次数 ：
    //
    // 右移次数：num 中最高位 1 的所在的位置；
    // 消减次数：num 中 1 的个数。
    public int numberOfSteps2(int num) {
        return Math.max(getLoc(num) + getCnt(num) - 1, 0);
    }
    // 得到最高位 1 的位置
    int getLoc(int x) {
        for (int i = 31; i >= 0; i--) {
            if (((x >> i) & 1) == 1) return i + 1;
        }
        return -1; // never
    }
    // 1 的个数
    int getCnt(int x) {
        int ans = 0;
        for (int i = 31; i >= 0; i--) {
            if (((x >> i) & 1) == 1) ans++;
        }
        return ans;
    }


    public static void main(String[] args) {
        int times = 100;
        for (int i = 10; i < times; i++) {
            var ans = new Problem_1342_NumberOfSteps().numberOfSteps(i);
            System.out.println(i + ": " + ans);
        }
    }
}
