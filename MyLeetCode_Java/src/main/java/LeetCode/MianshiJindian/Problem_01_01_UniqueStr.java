package LeetCode.MianshiJindian;

public class Problem_01_01_UniqueStr {

    public boolean isUnique(String s) {
        int mask = 0;
        for(char ch : s.toCharArray()) {
            int cur = 1<<(ch - 'a');
            if((mask & cur) != 0) {
                return false;
            }
            mask |= cur;
        }
        return true;
    }
}
