package _Contest.LC.W307;

public class Problem_2383_MinTimeToWin {

    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int cnt = 0;
        int sum = 0;
        for (int num : energy) {
            sum += num;
        }
        cnt = Math.max(0, sum + 1 - initialEnergy);
        for (int exp : experience) {
            int gap = 0;
            if (exp >= initialExperience) {
                gap = exp + 1 - initialExperience;
                cnt += gap;
            }
            initialExperience += exp + gap;
        }
        return cnt;
    }

    public static void main(String[] args) {
        // int initialEnergy = 5, initialExperience = 3;
        // int[] energy = {1, 4, 3, 2};
        // int[] experience = {2, 6, 3, 1};
        int initialEnergy = 1, initialExperience = 1;
        int[] energy = {1, 1, 1, 1};
        int[] experience = {1, 1, 1, 50}; // 51
        var ans = new Problem_2383_MinTimeToWin().minNumberOfHours(initialEnergy, initialExperience, energy, experience);
        System.out.println(ans);
    }
}
