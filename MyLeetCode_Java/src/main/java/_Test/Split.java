package _Test;

// IMP: 重要, split函数的使用

public class Split {

    public static void main(String[] args) {

        // "a\b\c"
        String test = "a\\b\\c"; // 两个斜杠表示一个斜杠本身

        // "a\b\c" 按斜杠切分, \ 是转义字符, 所以前面得加\\
        String[] arr = test.split("\\\\");
        // String[] arr = test.split("\\\\", 2); // 分割为 a  b\c 两组
        for (String str : arr) {
            System.out.println(str);
        }
    }
}
