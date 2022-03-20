package LeetCode;

public class Problem_233_CountDigitOne {

    public int countDigitOne(int n) {
        if( n == 0) {
            return 0;
        }
        int len = getNumLen(n); // 数字长度为 k
        if(len == 1) {
            return 1;
        }
        int mask = getPowerOfK(len-1);
        int firstDigit = n/mask;
        int firstOneNum = firstDigit == 1? n%mask + 1 : mask;
        int leftOneNum = firstDigit*(len-1)*(int)Math.pow(10, len-2);
        return firstOneNum + leftOneNum + countDigitOne(n%mask);
    }

    private int getPowerOfK(int k) {
        return (int)Math.pow(10, k);
    }

    // 获取num的数字位数长度
    private int getNumLen(int num) {
        int cnt = 0;
        while (num !=0 ) {
            cnt++;
            num/=10;
        }
        return cnt;
    }
}
