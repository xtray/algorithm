package BigComQues.MS;

public class Problem_273_IntegerToChineseWords {
    public String numberToWords(int num) {
        if(num == 0) {
            return "Zero";
        }
        String res = "";
        if (num < 0) {
            res = "Negative ";
        }
        if (num == Integer.MIN_VALUE) {
            res += "Two Billion ";
            num %= -2000000000;
        }
        num = Math.abs(num);
        int high = 1000000000;
        int hiIndex = 0;
        String[] names = {"Billion ", "Million ", "Thousand ", ""};
        while(num !=0){
            int cur = num/high;
            num %= high;
            if(cur!=0) {
                res += num1To999(cur);
                res += names[hiIndex];
            }
            high /= 1000;
            hiIndex++;
        }
        return res.trim();
    }

    private String num1To999(int num) {
        if(num <1 || num >999) {
            return "";
        }
        if(num <100) {
            return num1To99(num);
        }
        // 大于 100
        int hi = num /100;
        return num1To19(hi) + "Hundred " + num1To99(num % 100);
    }

    private String num1To19(int num) {
        if(num <1 || num >19) {
            return "";
        }
        String[] names = { "One ", "Two ", "Three ", "Four ", "Five ", "Six ", "Seven ", "Eight ", "Nine ", "Ten ",
                "Eleven ", "Twelve ", "Thirteen ", "Fourteen ", "Fifteen ", "Sixteen ", "Seventeen", "Eighteen ",
                "Nineteen " };
        return names[num - 1];
    }

    private String num1To99(int num) {
        if(num <1 || num >99) {
            return "";
        }
        if (num <20){
            return num1To19(num);
        }
        int hi = num / 10;
        String[] tyNames = { "Twenty ", "Thirty ", "Forty ", "Fifty ", "Sixty ", "Seventy ", "Eighty ", "Ninety " };
        return tyNames[hi -2] + num1To19(num%10);

    }

    public static void main(String[] args) {
        int num = Integer.MIN_VALUE;
        var res = new Problem_273_IntegerToChineseWords().numberToWords(num);
        System.out.println(res);

    }
}
