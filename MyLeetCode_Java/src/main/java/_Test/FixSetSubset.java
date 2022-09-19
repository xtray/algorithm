package _Test;

import java.util.ArrayList;
import java.util.List;

public class FixSetSubset {


    public List<String> getSubset(String s, int size) {
        List<String> ans = new ArrayList<>();
        process(s.toCharArray(), 0, size, "", ans);
        return ans;
    }

    private void process(char[] str, int i, int limit, String path, List<String> ans) {
        if (i == str.length) {
            if (path.length() == limit) {
                ans.add(path);
                return;
            }
            return;
        }
        // 没到最后
        if(path.length() == limit) {
            ans.add(path);
            return;
        }
        // 可能性1: 不要当前位置
        process(str, i + 1, limit, path, ans);
        // 可能性2: 要当前位置
        process(str, i + 1, limit, path + str[i], ans);
    }

    public List<String> getPermutationLimit(String s, int size) {
        List<String> ans = new ArrayList<>();
        process2(s.toCharArray(), 0, size, ans);
        return ans;
    }

    // 当前处理到index位置, 最大长度限制limit
    private void process2(char[] str, int index, int limit, List<String> ans) {
        if(index >= limit) {
            ans.add(String.valueOf(str, 0, limit));
            return;
        }
        // 没有到limit
        for(int i = index; i < str.length; i++) {
            // index...结束每一个位置来到index
            swap(str, index, i);
            process2(str, index+1, limit, ans);
            swap(str, index, i); // 恢复现场
        }
    }
    public static void swap(char[] str, int i, int j) {
        char tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
    }

    public static void main(String[] args) {
        String str = "12345";
        int size = 3;
        var ans = new FixSetSubset().getSubset(str, size);
        System.out.println(ans);
        var ans2 = new FixSetSubset().getPermutationLimit(str, size);
        System.out.println(ans2);
    }
}
