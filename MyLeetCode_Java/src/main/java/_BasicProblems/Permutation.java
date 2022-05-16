package _BasicProblems;

import java.util.ArrayList;
import java.util.List;

// IMP: 字符串的全排列, 非常非常重要!!!

public class Permutation {

    // 返回字符串s的全排列
    public static List<String> permutation(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        List<Character> charList = new ArrayList<>();
        for (char ch : str) {
            charList.add(ch);
        }
        process(charList, "", ans);
        return ans;
    }

    // rest: 剩余的可选字符集合
    // path: 之前的选择
    // ans: 返回答案
    private static void process(List<Character> rest, String path, List<String> ans) {
        if (rest.isEmpty()) {
            ans.add(path);
            return;
        }
        // 选择rest中的每一个来到当前位置
        int size = rest.size();
        for (int i = 0; i < size; i++) {
            char cur = rest.get(i);
            rest.remove(i); // 后面补回去了, 所以没有问题
            process(rest, path + cur, ans);
            rest.add(i, cur);
        }
        // List.remove(i) 更保险的写法, 倒着遍历
        // for (int i = size - 1; i >= 0; i--) {
        //     char cur = rest.get(i);
        //     rest.remove(i);
        //     process(rest, path + cur, ans);
        //     rest.add(i, cur);
        // }
    }

    // 返回字符串s的全排列
    public static List<String> permutation2(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        process2(str, 0, ans);
        return ans;
    }

    // 当前处理到index位置字符, 考虑后面index...N-1所有字符来到index位置
    private static void process2(char[] str, int index, List<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(str));
            return;
        }
        // index...N-1每一个位置都来到index位置
        for (int i = index; i < str.length; i++) {
            swap(str, index, i);
            // 去下一个位置做选择
            process2(str, index + 1, ans);
            swap(str, index, i);
        }
    }

    private static void swap(char[] str, int i, int j) {
        char tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
    }

    // 返回字符串s的全排列, 要求去重
    public static List<String> permutation3(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        process3(str, 0, ans);
        return ans;
    }

    // 当前处理到index位置字符, 考虑后面index...N-1所有字符来到index位置
    private static void process3(char[] str, int index, List<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(str));
            return;
        }
        // index...N-1每一个位置都来到index位置
        boolean[] visited = new boolean[256];
        for (int i = index; i < str.length; i++) {
            // 之前这个字符来过index位置, 跳过
            if (visited[str[i]]) continue; // NOTE: 剪枝!!
            visited[str[i]] = true;
            swap(str, index, i);
            // 去下一个位置做选择
            process3(str, index + 1, ans);
            swap(str, index, i);
        }
    }


    public static void main(String[] args) {
        String s = "aac";
        var ans = permutation(s);
        System.out.println(ans);
        var ans2 = permutation2(s);
        System.out.println(ans2);
        var ans3 = permutation3(s);
        System.out.println(ans3);
    }
}
