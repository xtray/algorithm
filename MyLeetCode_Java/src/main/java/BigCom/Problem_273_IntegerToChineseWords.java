package BigCom;

public class Problem_273_IntegerToChineseWords {
    public String numberToWords(int num) {
        if(num == 0) {
            return "零";
        }
        String res = "";
        if (num < 0) {
            res = "负";
        }
        if (num == Integer.MIN_VALUE) {
            res += "二十亿";
            num %= -2000000000;
        }
        num = Math.abs(num);
        int high = 100000000; // 亿
        int hiIndex = 0;
        String[] names = {"亿", "万", ""};
        int zeroCnt = 0;
        while(num !=0){ // 每 4 个一组
            int cur = num/high;
            num %= high;
            if(cur!=0) {
                res += num1To9999(cur, res.length()!=0 && res.compareTo("负") != 0);
                res += names[hiIndex];
            } else {
                zeroCnt++;
            }

            high /= 10000;
            hiIndex++;
        }
        return res.trim();
    }

    private String num1To9999(int num, boolean hasPre) {
        if(num <1 || num >9999) {
            return "";
        }
        String res = "";
        int high = 1000; // 千
        int hiIndex = 0;
        String[] names = {"千", "百", "十", ""};
        int zeroCnt = 0;
        // 5005
        while(num !=0){
            int cur = num/high;
            num %= high;
            if(cur!=0) {
                if(zeroCnt>0 && hasPre){
                    res +="零";
                }
                res += num1To9(cur);
                res += names[hiIndex];
                zeroCnt=0;
            } else {
                zeroCnt++;
            }
            high /= 10;
            hiIndex++;
        }
        return res;
    }

    private String num1To9(int num) {
        if(num <0 || num >10) {
            return "";
        }
        String[] names = {"一","二","三","四","五","六","七","八","九"};
        return names[num-1];
    }


    public static void main(String[] args) {
        int num = -20010089;
        var res = new Problem_273_IntegerToChineseWords().numberToWords(num);
        System.out.println(res);

    }
}
