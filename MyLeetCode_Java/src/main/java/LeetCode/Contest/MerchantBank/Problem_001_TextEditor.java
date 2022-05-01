package LeetCode.Contest.MerchantBank;

public class Problem_001_TextEditor {
    public String deleteText(String article, int index) {
        if (article == null || article.length() == 0) {
            return article;
        }
        char[] str = article.toCharArray();
        int N = str.length;
        int L = 0;
        int R = 0;
        int idx = -1;
        int cnt = 0;
        while (R < N) {
            // L开始, 找一个单词
            while (R < N && str[R] != ' ') {
                R++;
            }
            if (index >= L && index < R) {
                idx = cnt; // 看是哪一个单词要被删除
            }
            // R == N 或是 空格
            if (R == N) break; // 到了末尾退出
            // 跳过单词后的空格
            while (R < N && str[R] == ' ') {
                R++;
            }
            if (R == N) break;
            // R来到下一个单词的开始
            L = R;
            cnt++; // 单词序号
        }
        String[] words = article.split("\\s+"); // NOTE: 多个空格的切分技巧
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (i != idx) {
                sb.append(words[i]);
                sb.append(" ");
            }
        }
        // NOTE: 不要忘了删除最后的空格, 注意需要判断空!
        return  sb.length() != 0 ? sb.substring(0, sb.length()-1) : "";
    }

    public static void main(String[] args) {
        // String article = "Singing   dancing in the rain";
        // int index = 10;
        // String article = "Hello Word";
        // int index = 2;
        // String article = "e RSg c R cf";
        // int index = 10;
        String article = "Hello";
        int index = 0;
        var ans = new Problem_001_TextEditor().deleteText(article, index);
        System.out.println(ans);
    }
}
