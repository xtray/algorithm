package LeetCode;


import java.util.*;

public class Problem_318_MaxProduct {


    public int maxProduct(String[] words) {
        if(words == null || words.length ==0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(String word : words) {
            int hash = getCuntNum(word);
            map.put(hash, Math.max(map.getOrDefault(hash, 0), word.length()));
        }
        if(map.size()==1) {
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        for(int num1 : map.keySet()) {
            for(int num2 : map.keySet()) {
                if((num1 & num2) == 0) {
                    ans = Math.max(ans, map.get(num1) * map.get(num2));
                }
            }
        }
        return ans == Integer.MIN_VALUE?0:ans;
    }

    // 数组代替哈希表
    public int maxProduct2(String[] words) {
        if(words == null || words.length ==0) {
            return 0;
        }
        int[]hash = new int[words.length];
        for(int i=0;i<words.length;i++) {
            char[] str = words[i].toCharArray();
            int hashVal = 0;
            for (char c : str) {
                hashVal |= 1 << (c - 'a');
            }
            hash[i] = hashVal;
        }
        int ans = Integer.MIN_VALUE;
        for(int i = 0; i<hash.length;i++) {
            for(int j = i+1; j<hash.length;j++) {
                if((hash[i] & hash[j]) == 0) {
                    ans = Math.max(ans, words[i].length() * words[j].length());
                }
            }
        }
        return ans == Integer.MIN_VALUE?0:ans;
    }

    private int getCuntNum(String word) {
        char[] str = word.toCharArray();
        int num = 0;
        for(char ch : str) {
            int index = ch - 'a';
            num |= 1<<index;
        }
        return num;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"abcw","baz","foo","bar","xtfn","abcdef"};
//        String[] words = new String[]{"a","aa","aaa","aaaa"};
        Problem_318_MaxProduct sl = new Problem_318_MaxProduct();
        int ans = sl.maxProduct(words);
        System.out.println(ans);
    }
}
