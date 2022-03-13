package LeetCode;

public class Problem_393_ValidUTF8 {

    public boolean validUtf8(int[] data) {
        return process(data, 0);
    }

    private boolean process(int[] data, int i) {
        if (i == data.length) {
            return true;
        }
        // 取第一个数前4位看有几个1
        int num = data[i];
        int cnt = getOneCnt(num);
        boolean res = false;
        switch (cnt) {
            case 0:
                res = process(data, i + 1);
                break;
            case 2:
                res = validate(data, i + 1, 1);
                if (!res) return false;
                res = process(data, i + 2);
                break;
            case 3:
                res = validate(data, i + 1, 2);
                if (!res) return false;
                res = process(data, i + 3);
                break;
            case 4:
                res = validate(data, i + 1, 3);
                if (!res) return false;
                res = process(data, i + 4);
                break;
            default:
                res = false;
        }
        return res;
    }

    // 从i位置开始判断cnt个
    private boolean validate(int[] data, int i, int cnt) {
        if (i + cnt > data.length) {
            return false;
        }
        for (int j = i; j < i + cnt; j++) {
            int num = data[j];
            if ((num & (1 << 7)) != 0 && (num & (1 << 6)) == 0) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean validUtf82(int[] data) {
        int N = data.length;
        for (int i = 0; i < N; ) {
            int t = data[i], j = 7;
            while (j >= 0 && (((t >> j) & 1) == 1)) {
                j--;
            }
            int cnt = 7 - j; // 碰到0前, 1的个数
            if (cnt == 1 || cnt > 4) return false;
            if (i + cnt - 1 >= N) return false; // 超过数组长度
            for (int k = i + 1; k < i + cnt; k++) {
                if ((((data[k] >> 7) & 1) == 1) && (((data[k] >> 6) & 1) == 0)) continue;
                return false;
            }
            if (cnt == 0) {
                i++;
            } else {
                i += cnt;
            }
        }
        return true;
    }


    private int getOneCnt(int num) {
        int cnt = 0;
        for (int i = 7; i >= 7 - 5; i--) {
            if ((num & (1 << i)) == 0) {
                break;
            } else {
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] num = {197};
        var ans = new Problem_393_ValidUTF8().validUtf8(num);
        System.out.println(ans);
    }
}
