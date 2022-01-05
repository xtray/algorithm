package LeetCode;

public class Problem_1154_DayOfTheYear {

    // 1.分割年月日
    // 2.判断是否闰年, 闰年 2 月 29 天
    public int dayOfYear(String date) {

        int[] monthDay = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] splits = date.split("-");
        int year = Integer.parseInt(splits[0]);
        int mon = Integer.parseInt(splits[1]);
        int day = Integer.parseInt(splits[2]);
        boolean leapYear = year % 100 == 0 ? year % 400 == 0 : year % 4 == 0;
        int sum = 0;
        for (int i = 1; i < mon; i++) {
            sum += monthDay[i];
        }
        if (leapYear && mon > 2) {
            sum++;
        }
        sum += day;
        return sum;
    }
}
