package _LintCode;

public class Problem_716_AddAndSearch {

    public boolean addAndSearch(int[] inputs, int[] tests) {
        // 0. 存储所有值
        boolean[] set = new boolean[4001];
        for (int num : tests) set[num + 2000] = true;
        // 1.得到所有的和
        int N = inputs.length;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (set[inputs[i] + inputs[j] + 2000]) return true;
            }
        }
        return false;
    }
}
