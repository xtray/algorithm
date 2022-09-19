package _Contest.LC.W295;

public class Problem_2288_DiscountPrices {

    public String discountPrices(String sentence, int discount) {
        String[] strs = sentence.split(" ");
        int N = strs.length;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            String cur = getEvaluateStr(strs[i], discount);
            sb.append(cur);
            if (i != N - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    // 判断s是否是价格, 如果是返回 disc形式, 否则原样返回
    private String getEvaluateStr(String s, int discount) {
        char[] str = s.toCharArray();
        int N = str.length;
        if (str[0] != '$' || N == 1) {
            return s;
        }
        // 2147483647, 有可能溢出啊
        for (int i = 1; i < N; i++) {
            if (!Character.isDigit(str[i])) {
                return s;
            }
        }
        long val = Long.parseLong(s.substring(1));
        Double value = val * ((double) (100 - discount) / 100.0);
        return "$" + String.format("%.2f", value);
    }

    public static void main(String[] args) {
        // String sentence = "there are $1 $2 and 5$ candies in the shop";
        // int discount = 50;
        // String sentence = "1 2 $3 4 $5 $6 7 8$ $9 $10$";
        // int discount = 100;
        // String sentence = "1 2 $3247483647";
        // int discount = 70;
        String sentence = "$76111 ab $6 $";
        int discount = 48;
        var ans = new Problem_2288_DiscountPrices().discountPrices(sentence, discount);
        System.out.println(ans);
    }
}
