package _Contest.LC.W282;

public class Problem_2186_MinSteps {

    private int[] count = new int[26];

    public int minSteps(String s, String t) {

        int[] smap = getNumCountArr(s);
        int[] tmap = getNumCountArr(t);
        return getCount(smap, tmap);
    }

    private int getCount(int[] smap, int[] tmap) {
        int ans = 0;
        for(int i = 0; i< 26; i++) {
            // if((smap[i] ^ tmap[i]) != 0) {
                ans +=  Math.abs(smap[i] - tmap[i]);
            // }
        }
        return ans;
    }

    // private int getCount(int smap, int tmap) {
    //     int ans = 0;
    //     for(int i = 0; i<= 31; i++) {
    //         int ones = (1<<i ) & smap;
    //         int onet = (1<<i ) & tmap;
    //         if((ones ^ onet) != 0) {
    //             ans+=count[i];
    //         }
    //     }
    //     return ans;
    // }

    // 返回数字表示的字符分布
    private int getNumCount(String s) {
        char[] str = s.toCharArray();
        int N = str.length;
        int ans = 0;
        for(int i = 0; i< N; i++) {
            int idx = str[i] - 'a';
            count[idx]++;
            ans |= 1<<idx;
        }
        return ans;
    }
    private int[] getNumCountArr(String s) {
        char[] str = s.toCharArray();
        int N = str.length;
        int[] ans = new int[26];
        for(int i = 0; i< N; i++) {
            int idx = str[i] - 'a';
            ans[idx]++;
        }
        return ans;
    }


    public static void main(String[] args) {
        // String s = "aa";
        // String t = "a";
        String s = "cotxazilut";
        String t = "nahrrmcchxwrieqqdwdpneitkxgnt";
        var ans = new Problem_2186_MinSteps().minSteps(s, t);
        System.out.println(ans);
    }
}
