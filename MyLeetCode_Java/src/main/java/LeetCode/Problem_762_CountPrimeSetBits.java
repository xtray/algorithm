package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class Problem_762_CountPrimeSetBits {
    private Map<Integer, Boolean> map;

    public Problem_762_CountPrimeSetBits() {
        map = new HashMap<>();
        for (int i = 1; i <= 32; i++) {
            map.put(i, isPrimNumber(i));
        }
    }

    private Boolean isPrimNumber(int n) {
        if (n < 3) return n > 1;

        int limit = (int) Math.sqrt(n);

        for (int i = 2; i <= limit; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int countPrimeSetBits(int left, int right) {

        int ans = 0;
        for(int i = left; i<= right; i++) {
            int num = getOneNum(i);
            if(map.get(num)) {
                ans++;
            }
        }
        return ans;
    }

    private int getOneNum(int n) {
        int ans = 0;
        while (n !=0) {
            if((n&1) != 0) {
                ans++;
            }
            n>>>=1;
        }
        return ans;
    }
}
