package L_PENDING;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
/**
 * Not Pass!
 * DW74
 */
public class Problem_6021_MaxNumberOfSubSeq_F {

    public long maximumSubsequenceCount(String text, String pattern) {
        if(text == null || text.length() ==0 || pattern == null || pattern.length() != 2) {
            return 0;
        }
        Map<Character, TreeSet<Integer>> map = new HashMap<>();
        map.put(pattern.charAt(0), new TreeSet<>());
        map.put(pattern.charAt(1), new TreeSet<>());
        char[] str = text.toCharArray();
        for(int i =0; i< str.length; i++) {
            if(map.containsKey(str[i])) {
                map.get(str[i]).add(i);
            }
        }
        // 假设pattern A : B
        // map
        //     A: 1, 3, 7
        //     B: 2, 6
        // 可能性1: 选A, 需要插在B的最前面
        // 针对每一个B看前面有几个A都加起来就是结果
        long res1 = 0;
        TreeSet<Integer> aTreeSet = map.get(pattern.charAt(0));
        for(int bPos : map.get(pattern.charAt(1))) { // 针对每一个B, 找有前面多少个A
            int cnt = getLowerCnt(aTreeSet, bPos);
            res1 += aTreeSet.size() == 1? 1: cnt+1;
        }
        long res2 = 0;
        TreeSet<Integer> bTreeSet = map.get(pattern.charAt(1));
        for(int aPos : map.get(pattern.charAt(0))) { // 针对每一个A, 找后面有多少个B
            int cnt = getUpperCnt(bTreeSet, aPos);
            res2 += bTreeSet.size() == 1? 1: cnt+1;
        }
        return Math.max(res1, res2);
    }

    // treeset找到所有<bPos的个数
    private int getLowerCnt(TreeSet<Integer> aTreeSet, int bPos) {
        int cnt = 0;
        for(int num : aTreeSet) {
            if(num < bPos) {
                cnt++;
            } else {
                break;
            }
        }
        return cnt;
    }

    private int getUpperCnt(TreeSet<Integer> bTreeSet, int aPos) {
        int cnt = 0;
        for(int num : bTreeSet) {
            if(num < aPos) {
                cnt++;
            } else {
                break;
            }
        }
        return bTreeSet.size() - cnt;
    }

    public static void main(String[] args) {
        // String text = "abdcdbc", pattern = "ac";
        // String text = "aabb", pattern = "ab";
        // String text = "zigfj";
        // String pattern = "ju";
        // String text = "zigfu";
        // String pattern = "ju";
        String text = "mpmp";
        String pattern = "mp"; // 5
        // String text ="fwymvreuftzgrcrxczjacqovduqaiig";
        // String pattern = "yy"; // 1

        // "abdcdbc"
        // "ac" // 4
        var ans = new Problem_6021_MaxNumberOfSubSeq_F().maximumSubsequenceCount(text, pattern);
        System.out.println(ans);
    }

}