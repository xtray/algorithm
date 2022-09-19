package _LintCode;

public class Problem_157_UniqueChar {

    public boolean isUnique(String str) {

        long[] exist = new long[4];

        for (int i = 0; i < str.length(); i++) {
            int idx = str.charAt(i);
            if (((exist[idx/64] >> (idx%64)) & 1) == 1) {
                return false;
            }
            exist[idx/64] |= 1L << (idx%64);
        }
        return true;
    }
}
