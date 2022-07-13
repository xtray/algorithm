package _Misc;

// value[] : [100, 500, 1000]

public class KnapSackII {

    public int maxValue(int[] weight, int[] value, int bag, int count) {
        return process(weight, value, 0, bag, count);
    }

    private int process(int[] weight, int[] value, int i, int restBag, int restCnt) {
        int n = weight.length;
        if (restBag < 0 || restCnt >= 10) {
            return -1;
        }
        if (i == n) {
            return 0;
        }
        // 第一种选择: 不要i位置的货
        int p1 = process(weight, value, i + 1, restBag, restCnt);
        // 第二种选择: 要i位置的货
        int p2 = -1;
        int next = process(weight, value, i + 1, restBag - weight[i], restCnt - 1);
        if (next != -1) {
            int val = getVal(value, weight[i]);
            p2 = val + next;
        }
        return Math.max(p1, p2);
    }

    private int getVal(int[] value, int weight) {
        if (weight <= (int) 1e6) {
            return value[0];
        }
        if (weight <= 2 * (int) 1e6) {
            return value[1];
        }
        return value[2];
    }
}
