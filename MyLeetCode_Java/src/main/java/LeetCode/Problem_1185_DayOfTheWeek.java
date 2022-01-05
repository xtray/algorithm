package LeetCode;

public class Problem_1185_DayOfTheWeek {

    static String[] ss = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    static int[] nums = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    // 计算给定日期距离 1970 的最后一天（星期四）间隔了多少天，从而得知给定日期是周几。
    // 通过查询日历可知，1970 年 12 月 31 日是星期四，我们只需要算出输入的日期距离
    // 1970 年 12 月 31 日有几天，再加上 3 后对 7 求余，即可得到输入日期是一周中的第几天。
    public String dayOfTheWeek(int day, int month, int year) {
        int ans = 4;
        for (int i = 1971; i < year; i++) {
            boolean isLeap = (i % 4 == 0 && i % 100 != 0) || i % 400 == 0;
            ans += isLeap ? 366 : 365;
        }
        for (int i = 1; i < month; i++) {
            ans += nums[i - 1];
            if (i == 2 && ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)) ans++;
        }
        ans += day;
        return ss[ans % 7];
    }

    public static void main(String[] args) {
        int day = 5;
        int mon = 1;
        int year = 2022;
        var ans = new Problem_1185_DayOfTheWeek().dayOfTheWeek(day, mon, year);
        System.out.println(ans);
    }
}
