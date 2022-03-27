package L_PENDING;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Problem_488_FindMinStep_100 {

    /////////////////
    // https://leetcode-cn.com/problems/zuma-game/solution/acmjin-pai-ti-jie-dfsmei-ju-bian-cheng-x-30qj/
    int res = Integer.MAX_VALUE;
    int handLen;
    Set<String> vis = new HashSet<>();

    public int findMinStep(String board, String hand) {
        if (board == null || board.length() == 0) {
            return 0;
        }
        handLen = hand.length();
        process(board, hand);
        return res == Integer.MAX_VALUE ? -1:res;
    }

    private void process(String board, String hand) {
        if (board.length() == 0) {
            res = Math.min(res, handLen - hand.length());
            return;
        }
        if (hand.length()==0) return;
        for (int i = 0; i < board.length(); i++) {
            for (int j = 0; j < hand.length(); j++) {
                String s = board.substring(0,i) + hand.charAt(j) + board.substring(i);
                if (!vis.contains(s)){
                    vis.add(s);
                    process(eliminate(s) , hand.substring(0,j) + hand.substring(j + 1));
                }
            }
        }
    }

    public int findMinStep2(String board, String hand) {
        if (board == null || board.length() == 0) {
            return 0;
        }
        handLen = hand.length();
        HashMap<String, Integer> dp = new HashMap<>();
        process2(board, hand, dp);
        return res == Integer.MAX_VALUE ? -1:res;
    }

    private void process2(String board, String hand, HashMap<String, Integer> dp) {
        if (dp.containsKey(board+"_"+hand)) {
            return;
        }

        if (board.length() == 0) {
            res = Math.min(res, handLen - hand.length());
            dp.put(board+"_"+hand, res);
            return;
        }
        if (hand.length()==0) return;
        for (int i = 0; i < board.length(); i++) {
            for (int j = 0; j < hand.length(); j++) {
                String s = board.substring(0,i) + hand.charAt(j) + board.substring(i);
                if (!vis.contains(s)){
                    vis.add(s);
                    process2(eliminate(s) , hand.substring(0,j) + hand.substring(j + 1), dp);
                }
            }
        }
    }

    private String eliminate(String s) {
        for(int L=0,R=0;R<s.length();R++) {
            while (R<s.length()&&s.charAt(R)==s.charAt(L)){
                R++;
            }
            if(R-L >=3) {
                s = s.substring(0, L) + s.substring(R);
                R=0;
            }
            L = R;
        }
        return s;
    }


    private String eliminate1(String s) {
        boolean flag = true;
        while (flag) {
            flag = false;
            for(int i=0, j=0; i < s.length();i=j) {
                while(j<s.length()&&s.charAt(i) == s.charAt(j) ) {
                    ++j;
                }
                if (j-i >=3) {
                    flag = true;
                    s=s.substring(0,i) + s.substring(j);
                }
            }
        }
        return s;
    }


    public static void main(String[] args) {
        Problem_488_FindMinStep_100 sl = new Problem_488_FindMinStep_100();
//        String board = "WWRRBBWW";
//        String hand = "WRBRW";
//        String board = "WRRBBW";
//        String hand = "RB";
//        String board = "RBYYBBRRB";
//        String hand = "YRBGB";
        String board = "RRGGBBYYWWRRGGBB";
        String hand = "RGBYW";
//        int res = sl.findMinStep(board, hand);
        int res = sl.findMinStep2(board, hand);
        System.out.println(res);
    }
}
