package LeetCode;

import java.util.*;

public class Problem_1447_SimpleFrac {

    public List<String> simplifiedFractions(int n) {
        if (n <= 1) {
            return new ArrayList<>();
        }

        // key : 分子  value : 分母表
        Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
        for (int i = 2; i <= n; i++) { // 分母
            for (int j = 1; j < i; j++) { // 分子
                int gcd = gcd(i,j);
                int up = j/gcd;
                int down = i/gcd;
                if(!map.containsKey(up)) {
                    map.put(j, new HashSet<>());
                }
                Set<Integer> set = map.get(up);
                set.add(down);
            }
        }
        List<String> ans = new ArrayList<>();
        for(int up : map.keySet()) {
            for(int down: map.get(up)) {
                ans.add(up + "/" + down);
            }
        }
        return ans;
    }

    public List<String> simplifiedFractions2(int n) {
        if (n <= 1) {
            return new ArrayList<>();
        }

        List<String> ans = new ArrayList<>();
        for (int i = 2; i <= n; i++) { // 分母
            for (int j = 1; j < i; j++) { // 分子
                //分子分母的最大公约数为 11，则我们找到了一个最简分数。
                if(gcd(i,j) == 1) {
                    ans.add(j + "/" + i);
                }
            }
        }
        return ans;
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        int n  = 6;
        var ans = new Problem_1447_SimpleFrac().simplifiedFractions(n);
        for(String str : ans) {
            System.out.print(str + " ");
        }
        System.out.println();
    }
}
