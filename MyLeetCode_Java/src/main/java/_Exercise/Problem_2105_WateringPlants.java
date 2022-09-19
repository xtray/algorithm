package _Exercise;

public class Problem_2105_WateringPlants {

    public int minimumRefill(int[] plants, int ca, int cb) {
        int N = plants.length;
        int L = 0;
        int R = N - 1;
        int ans = 0;
        int alice = ca;
        int bob = cb;
        while (L < R) {
            if (alice >= plants[L]) {
                alice -= plants[L];
            } else {
                alice = ca - plants[L];
                ans++;
            }
            if (bob >= plants[R]) {
                bob -= plants[R];
            } else {
                bob = cb - plants[R];
                ans++;
            }
            L++;
            R--;
        }
        if (L == R) {
            if (Math.max(alice, bob) < plants[L]) {
                ans++;
            }
        }
        return ans;
    }
}
