package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class Problem_387_FirstUniqChar {
    public int firstUniqChar(String s) {
        if(s == null || s.length()==0) {
            return -1;
        }
        Map<Character, Integer> map = new HashMap<>();
        int index = 0;
        char[] str = s.toCharArray();
        for(Character ch: str) {
            map.put(ch, map.getOrDefault(ch, 0)+1);
        }
        for(int i = 0; i< str.length; i++) {
            if(map.get(str[i]) == 1) {
                return i;
            }
        }
        return -1;
    }

}
