package _DailyTarget;

import java.util.ArrayDeque;

public class Problem_591_TagValidator {

    public boolean isValid(String code) {
        if (code == null || code.length() == 0) return false;
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
                    if (stack.isEmpty()) return false;
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
}
