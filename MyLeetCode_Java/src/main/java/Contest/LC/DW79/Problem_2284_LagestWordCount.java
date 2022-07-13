package Contest.LC.DW79;

import java.util.*;

public class Problem_2284_LagestWordCount {

    public String largestWordCount(String[] messages, String[] senders) {
        Map<String, Integer> map = new HashMap<>();
        int maxCnt = 0;
        for (int i = 0; i < messages.length; i++) {
            int cnt = messages[i].split(" ").length;
            map.put(senders[i], map.getOrDefault(senders[i], 0) + cnt);
            maxCnt = Math.max(maxCnt, map.get(senders[i]));
        }
        List<String> list = new ArrayList<>();
        for (String key : map.keySet()) {
            if (map.get(key) == maxCnt) {
                list.add(key);
            }
        }
        list.sort((o1, o2) -> o2.compareTo(o1));
        return list.get(0);
    }

    public static void main(String[] args) {
        String[] messages = {"Hello userTwooo","Hi userThree","Wonderful day Alice","Nice day userThree"};
        String[] senders = {"Alice","userTwo","userThree","alice"};
        // String[] messages = {"How is leetcode for everyone","Leetcode is useful for practice"};
        // String[] senders = {"Bob","Charlie"};
        var ans = new Problem_2284_LagestWordCount().largestWordCount(messages, senders);
        System.out.println(ans);
    }
}
