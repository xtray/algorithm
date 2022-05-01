package LeetCode;

public class Problem_804_LemonadeChange {

    public boolean lemonadeChange(int[] bills) {
        if (bills == null || bills.length == 0) {
            return false;
        }
        int fiveCnt = 0;
        int tenCnt = 0;
        if (bills[0] != 5) {
            return false;
        } else {
            fiveCnt++;
        }
        for (int i = 1; i < bills.length; i++) {
            if (bills[i] == 5) {
                fiveCnt++;
            } else if (bills[i] == 10) {
                if (fiveCnt < 1) {
                    return false;
                }
                tenCnt++;
                fiveCnt--;
            } else { // 20元 -- > 15
                // 1. 10 + 5
                // 2. 5,5,5
                if (fiveCnt >= 3 || (fiveCnt >= 1 && tenCnt >= 1)) {
                    if(tenCnt>=1) {
                        fiveCnt--;
                        tenCnt--;
                    } else {
                        fiveCnt-=3;
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
