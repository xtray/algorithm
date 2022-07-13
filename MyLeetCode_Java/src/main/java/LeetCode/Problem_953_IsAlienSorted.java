package LeetCode;

public class Problem_953_IsAlienSorted {

    public boolean isAlienSorted(String[] words, String order) {
        if (words == null || words.length < 2) {
            return false;
        }
        int[] orderDict = new int[26];
        char[] str = order.toCharArray();
        for (int i = 0; i < str.length; i++) {
            orderDict[str[i] - 'a'] = i;
        }
        int N = words.length;
        char[] pre = words[0].toCharArray();
        boolean isOk = false;
        for (int i = 1; i < N; i++) {
            char[] cur = words[i].toCharArray();
            for (int j = 0; j < Math.min(pre.length, cur.length); j++) {
                if (pre[j] != cur[j]) {
                    if (orderDict[pre[j] - 'a'] > orderDict[cur[j] - 'a']) {
                        return false;
                    } else {
                        isOk = true;
                        break;
                    }
                }
            }
            if (!isOk && pre.length > cur.length) {
                return false;
            }
            pre = cur;
        }
        return true;
    }
}
