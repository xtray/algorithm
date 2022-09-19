package _LintCode;

import java.util.ArrayDeque;

public class Problem_1151_ValidTag {

    // 总结: 类似括号匹配, 使用栈做每一对的tag匹配, 分四类, 以开头 <, </, <![CDATA[, content分别讨论
    // 关键点:
    // 1. 最外层必须被TAG包含
    // 2. CDATA必须在TAG内(stack非空)
    // 3. 最后检查stack必须是空的
    // 4. TAG必须是大写, 且长度1~9
    // 5. 普通字符必须被TAG包含(stack非空)

    public boolean isValid(String code) {

        ArrayDeque<String> stack = new ArrayDeque<>();
        char[] str = code.toCharArray();
        int N = str.length;
        boolean tagFound = false;

        int idx = 0;
        while (idx < N) {
            if (str[idx] == '<' && (idx + 1) < N && str[idx + 1] == '/') { // close tag
                idx += 2;
                int s = idx;
                while (idx < N && str[idx] != '>') {
                    if (str[idx] >= 'A' && str[idx] <= 'Z') {
                        idx++;
                    } else {
                        return false;
                    }
                }
                if (idx == N) return false;
                // 取得tag, idx == '>'
                String curTag = String.valueOf(str, s, idx - s);
                if (curTag.length() == 0 || curTag.length() > 9) return false;
                // 检查tag是否跟栈顶一致
                if (stack.isEmpty()) return false;
                if (!stack.peekLast().equals(curTag)) return false;
                stack.pollLast();
                idx++;
                continue;
            }
            if (str[idx] == '<' && (idx + 8) < N) { // 如果是CDDATA就跳过
                String cddata = String.valueOf(str, idx, 9);
                if (cddata.equals("<![CDATA[")) {
                    if (stack.isEmpty()) return false; // CDDATA必须在TAG内
                    int cddataPos = code.indexOf("]]>", idx);
                    if (cddataPos == -1) return false;
                    idx = cddataPos + 3;
                    continue;
                }
            }
            if (str[idx] == '<') { // start tag
                int s = ++idx;
                while (idx < N && str[idx] != '>') {
                    if (str[idx] >= 'A' && str[idx] <= 'Z') {
                        idx++;
                    } else {
                        return false;
                    }
                }
                if (idx == N) return false;
                // 取得tag, idx == '>'
                String curTag = String.valueOf(str, s, idx - s);
                if (curTag.length() == 0 || curTag.length() > 9) return false;
                if (tagFound && stack.isEmpty()) return false; // 不是第一次发现tag, stack不能空
                if (!tagFound) tagFound = true; // 第一次发现tag
                stack.addLast(curTag);
                idx++;
            } else {
                if (!stack.isEmpty()) {
                    idx++;
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String code = "<DIV>This is the first line <![CDATA[<div>]]></DIV>"; // t
        // String code = "<a><a></a></a>"; // f
        // String code = "<A>  <B> </A>   </B>"; // f
        // String code = "<DIV>This is the first line <![CDATA[<div>]]><DIV>"; // f
        // String code = "<![CDATA[wahaha]]]><![CDATA[]> wahaha]]>"; // f, CDDATA必须包含在tag内
        // String code = "<A></A><B></B>"; // f, 标签最外层必须包含
        var ans = new Problem_1151_ValidTag().isValid(code);
        System.out.println(ans);
    }
}
