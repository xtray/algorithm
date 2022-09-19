package LeetCode;

public class Problem_2384_MaxPalindrome {

    public String largestPalindromic(String s) {
        int[] cnt = new int[10];
        for (char num : s.toCharArray()) {
            cnt[num - '0']++;
        }
        int maxOddCnt = 0;
        int maxOddCntNum = 0;
        int evenCnt = 0;
        for (int i = 1; i <= 9; i++) {
            if (cnt[i] == 0) continue;
            if ((cnt[i] & 1) != 0) {
                maxOddCntNum = i;
                maxOddCnt = Math.max(maxOddCnt, cnt[i]);
                if (cnt[i] >= maxOddCnt) {
                    maxOddCnt = cnt[i];
                }
                if (cnt[i] > 1) {
                    evenCnt += cnt[i] - 1;
                }
            } else {
                evenCnt += cnt[i];
            }
        }
        if(maxOddCnt == 0 && evenCnt == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        // 要求除了0以外的偶数个数>0
        if (evenCnt == 0) {
            // 如果偶数的个数为零, 那么0不可用, 只能用最大的奇数
            while (maxOddCnt > 0) {
                sb.append(maxOddCntNum);
                maxOddCnt--;
            }
        } else {
            StringBuilder tmp = new StringBuilder();
            // 如果偶数不为0, 外边放偶数
            for (int i = 9; i >= 1; i--) {
                if (cnt[i] > 1) {
                    for (int j = 0; j < cnt[i] / 2; j++) {
                        tmp.append(i);
                    }
                }
            }
            // 放0
            if (cnt[0] > 1) {
                for (int j = 0; j < cnt[0]/2; j++) {
                    tmp.append(0);
                }
                if (maxOddCnt > 0) {
                    sb.append(tmp).append(maxOddCntNum).append(tmp.reverse());
                } else {
                    if((cnt[0]&1)!=0) {
                        sb.append(tmp).append(0).append(tmp.reverse());
                    } else {
                        sb.append(tmp).append(tmp.reverse());
                    }
                }
            } else if (cnt[0] == 1) {
                if (maxOddCnt > 0) {
                    sb.append(tmp).append(maxOddCntNum).append(tmp.reverse());
                } else {
                    sb.append(tmp).append(0).append(tmp.reverse());
                }
            } else {
                // 没有0
                if (maxOddCnt > 0) {
                    sb.append(tmp).append(maxOddCntNum).append(tmp.reverse());
                } else {
                    sb.append(tmp).append(tmp.reverse());
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // String s = "444947137";
        // String s = "00000991";
        // String s = "00000";
        String s = "97231404236749078329522372611037933";
        var ans = new Problem_2384_MaxPalindrome().largestPalindromic(s);
        System.out.println(ans);
    }
}
