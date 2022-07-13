package LeetCode;

// https://leetcode.cn/problems/design-a-text-editor/


import java.util.LinkedList;

public class Problem_2296_DesignEditor {

    private LinkedList<Character> left;
    private LinkedList<Character> right;

    public Problem_2296_DesignEditor() {
        left = new LinkedList<>();
        right = new LinkedList<>();
    }

    public void addText(String text) {
        for (char ch : text.toCharArray()) {
            left.addLast(ch);
        }
    }

    public int deleteText(int k) {
        int cnt = 0;
        while (!left.isEmpty() && k > 0) {
            left.pollLast();
            k--;
            cnt++;
        }
        return cnt;
    }

    public String cursorLeft(int k) {
        while (!left.isEmpty() && k > 0) {
            right.addFirst(left.pollLast());
            k--;
        }
        return getLeft();
    }

    public String cursorRight(int k) {
        while (!right.isEmpty() && k > 0) {
            left.addLast(right.pollFirst());
            k--;
        }
        return getLeft();
    }

    private String getLeft() {
        StringBuilder sb = new StringBuilder();
        int len = Math.min(left.size(), 10);
        for (int i = left.size() - len; i < left.size(); i++) {
            sb.append(left.get(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Problem_2296_DesignEditor editor = new Problem_2296_DesignEditor();

        // editor.addText("leetcode");
        //
        // var ans = editor.deleteText(4);
        // System.out.print(ans + " ");
        //
        // editor.addText("practice");
        //
        // var str = editor.cursorRight(3);
        // System.out.print(str + " ");
        //
        // str = editor.cursorLeft(8);
        // System.out.print(str + " ");
        //
        // ans = editor.deleteText(10);
        // System.out.print(ans + " ");
        //
        //
        // str = editor.cursorLeft(2);
        // System.out.print(str + " ");
        //
        // str = editor.cursorRight(6);
        // System.out.print(str + " ");

        editor.addText("arnvmumatgmyw");

        var ans = editor.deleteText(5);
        System.out.print(ans + " ");

        editor.addText("zrlufuifuy");

        var str = editor.cursorLeft(2);
        System.out.print(str + " ");
    }
}
