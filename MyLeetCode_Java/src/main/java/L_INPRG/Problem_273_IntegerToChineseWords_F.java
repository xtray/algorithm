package L_INPRG;

// TODO: 先看数字转英文的题目!!

public class Problem_273_IntegerToChineseWords_F {
    public String numberToWords(int num) {
        if (num == 0) {
            return "零";
        }
        int unit = 100000000;
        StringBuilder result = new StringBuilder();
        if (num < 0) {
            result.append("负");
        }
        long l = Math.abs((long) num);
        String[] unitNames = {"亿", "万", ""};
        boolean lastZero = false;
        for (int i = 0; l != 0 && i < 3; unit /= 10000, i++) {
            long high = l / unit;
            l %= unit;
            if (high != 0) {
                String convert = num1To9999((int) high, result.length() > 1 && !lastZero);
                result.append(convert);
                result.append(unitNames[i]);
                if (high % 10 == 0 && l > 0) {
                    result.append("零");
                    lastZero = true;
                } else {
                    lastZero = false;
                }
            }
        }
        return result.toString();
    }

    private String num1To9999(int num, boolean fillZero) {
        if (num < 1 || num > 9999) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        int unit = 1000;
        int index = 0;
        String[] names = {"千", "百", "十", ""};
        int lastBit = -1;
        while (num != 0) {
            int high = num / unit;
            if (high != 0) {
                if (index - lastBit > 1 && (fillZero || result.length() > 0)) {
                    result.append("零");
                    fillZero = false;
                }
                result.append(num1To9(high));
                result.append(names[index]);
                lastBit = index;
            }
            num %= unit;
            unit /= 10;
            index++;
        }
        return result.toString();
    }

    private String num1To9(int num) {
        if (num < 0 || num > 10) {
            return "";
        }
        String[] names = {"一", "二", "三", "四", "五", "六", "七", "八", "九"};
        return names[num - 1];
    }

    public static void main(String[] args) {
        Problem_273_IntegerToChineseWords_F test = new Problem_273_IntegerToChineseWords_F();
        System.out.println("-20010089:" + test.numberToWords(-20010089));
        System.out.println("-21010109:" + test.numberToWords(-21010109));
        System.out.println("-20100009:" + test.numberToWords(-20100009));
        System.out.println("-20015009:" + test.numberToWords(-20015009));
        System.out.println("-23015609:" + test.numberToWords(-23015609));
        System.out.println("-23415679:" + test.numberToWords(-23415679));
        System.out.println("20000000:" + test.numberToWords(20000000));
        System.out.println("20100000:" + test.numberToWords(20100000));
        System.out.println("20001100:" + test.numberToWords(20001100));
        System.out.println("20001010:" + test.numberToWords(20001010));
        System.out.println("20001006:" + test.numberToWords(20001006));
        System.out.println("247483647:" + test.numberToWords(247483647));
        System.out.println("200403607:" + test.numberToWords(200403607));
        System.out.println(Integer.MAX_VALUE + ":" + test.numberToWords(Integer.MAX_VALUE));
        System.out.println("-107483648:" + test.numberToWords(-107483648));
        System.out.println(Integer.MIN_VALUE + ":" + test.numberToWords(Integer.MIN_VALUE));
    }
}