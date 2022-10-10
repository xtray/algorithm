package _Contest.LC.W314;

public class Problem_2433_FindOrgArray {

    public int[] findArray(int[] pref) {
        int N = pref.length;
        int[] arr = new int[N];
        arr[0] = pref[0];
        for (int i = 1; i < N; i++) {
            arr[i] = pref[i] ^ pref[i - 1];
        }
        return arr;
    }

}
