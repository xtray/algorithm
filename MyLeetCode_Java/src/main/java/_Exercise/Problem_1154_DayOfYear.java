package _Exercise;

public class Problem_1154_DayOfYear {

    private static final int[] daysOfMon = {
            0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    private static final int[] sum = new int[13];

    static {
        for (int i = 1; i <= 12; i++) {
            sum[i] = sum[i - 1] + daysOfMon[i];
        }
    }

    public int dayOfYear(String date) {
        if (date == null || date.length() == 0) {
            return 0;
        }
        String[] ss = date.split("-");
        int year = Integer.parseInt(ss[0]);
        int mon = Integer.parseInt(ss[1]);
        int day = Integer.parseInt(ss[2]);
        boolean leapYear = year % 100 == 0 ? (year % 400 == 0) : (year % 4 == 0);
        int add = leapYear ? mon > 2 ? 1 : 0 : 0;
        return sum[mon - 1] + day + add;
    }
}
