package LeetCode;

public class Problem_1614_MaxDepth {

    public int maxDepth(String s) {
        if(s == null || s.length() ==0) {
            return 0;
        }
        char[] str = s.toCharArray();
        // 定义左括号( -1, 右括号 ) 1
        int[] stack = new int[s.length()>>1];
        int size =0 ; //栈的最大深度
        int maxDepth = 0;
        for(char ch : str) {
            if(ch == '(') {
                stack[size++] = 1;
                maxDepth = Math.max(maxDepth, size);
            } else if(ch == ')') {
                int top = size == 0 ? 0:stack[size-1];
                if(top == 1) {
                    size--;
                }
            }
        }
        return maxDepth;
    }


    // 其实就是求最大的连续左括号的数量（跳过普通字符，且与 ) 抵消后），只需要边遍历边统计即可。
    public int maxDepth2(String s) {
        int N = s.length();
        int ans = 0;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (s.charAt(i) == '(') {
                cnt++;
                ans = Math.max(ans, cnt);
            }
            else if (s.charAt(i) == ')') {
                cnt--;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        String s = "(1+(2*3)+((8)/4))+1";
        var ans = new Problem_1614_MaxDepth().maxDepth(s);
        System.out.println(ans);
    }
}
