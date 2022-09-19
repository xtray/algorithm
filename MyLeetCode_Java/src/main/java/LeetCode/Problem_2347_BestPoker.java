package LeetCode;

public class Problem_2347_BestPoker {

    public String bestHand(int[] ranks, char[] suits) {
        // 1.检测是否五张花色一样
        int sum = 0;
        for (char ch : suits) {
            if(ch == suits[0])  sum++;
        }
        if (sum == 5) {
            return "Flush";
        }
        // 2. 检查是否有三张相同的, 1~13
        int[] cnt = new int[14];
        for (int num : ranks) {
            cnt[num]++;
            if (cnt[num] == 3) {
                return "Three of a Kind";
            }
        }
        for (int num : cnt) {
            if (num == 2) {
                return "Pair";
            }
        }
        return "High Card";
    }

    public static void main(String[] args) {
        int[] ranks = {9, 2, 13, 1, 2};
        char[] suits = {'b', 'd', 'd', 'b', 'c'};
        var ans = new Problem_2347_BestPoker().bestHand(ranks, suits);
        System.out.println(ans);
    }
}
