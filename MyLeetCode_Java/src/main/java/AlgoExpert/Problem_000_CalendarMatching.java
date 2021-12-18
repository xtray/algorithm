package AlgoExpert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem_000_CalendarMatching {
    public static List<StringMeeting> calendarMatching(
            List<StringMeeting> calendar1,
            StringMeeting dailyBounds1,
            List<StringMeeting> calendar2,
            StringMeeting dailyBounds2,
            int dur) {

        List<Node> cal1 = generateCal(calendar1, dailyBounds1);
        List<Node> cal2 = generateCal(calendar2, dailyBounds2);
//        List<Node> all = new ArrayList<>();
//        all.addAll(cal1);
//        all.addAll(cal2);
//        Collections.sort(all, (a, b) -> a.start - b.start);
        List<Node> all = mergeCal(cal1, cal2);
        int s = all.get(0).start;
        int e = all.get(0).end;
        List<int[]> mergedList = new ArrayList<>();
        for (int i = 1; i < all.size(); i++) {
            if (all.get(i).start > e) { // 新组开始时间>=上一组结束时间, 结算
                mergedList.add(new int[]{s, e});
                s = all.get(i).start;
                e = all.get(i).end;
            } else {
                e = Math.max(e, all.get(i).end);
            }
        }
        mergedList.add(new int[]{s, e}); // 不要忘了最后一组
        List<StringMeeting> res = new ArrayList<>();
        int slotStart = mergedList.get(0)[1];
        int i = 1;
        for (; i < mergedList.size(); i++) {
            int curStart = mergedList.get(i)[0];
            int curEnd = mergedList.get(i)[1];
            if (slotStart + dur <= curStart) {
                String smStart = minutesToTime(slotStart);
                String smEnd = minutesToTime(curStart);
                res.add(new StringMeeting(smStart, smEnd));
            }
            slotStart = curEnd;
        }
        return res;
    }

    // 两个有序数组合并
    public static List<Node> mergeCal(List<Node> cal1, List<Node> cal2) {
        List<Node> mergedList = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < cal1.size() && j < cal2.size()) {
            if (cal1.get(i).start <= cal2.get(j).start) {
                mergedList.add(cal1.get(i++));
            } else {
                mergedList.add(cal2.get(j++));
            }
        }
        while (i < cal1.size()) {
            mergedList.add(cal1.get(i++));
        }
        while (j < cal2.size()) {
            mergedList.add(cal1.get(j++));
        }
        return mergedList;
    }

    public static String minutesToTime(int minutes) {
        int hours = minutes / 60;
        int mins = minutes % 60;
        String hoursString = Integer.toString(hours);
        String minutesString = mins < 10 ? "0" + mins : Integer.toString(mins);
        return hoursString + ":" + minutesString;
    }

    private static List<Node> generateCal(List<StringMeeting> calendar1, StringMeeting db) {
        calendar1.add(0, new StringMeeting("0:00", db.start)); // 非工作时间填充!!
        calendar1.add(new StringMeeting(db.end, "23:59"));
        List<Node> list = new ArrayList<>();
        for (StringMeeting sm : calendar1) {
            int start = getTime(sm.start);
            int end = getTime(sm.end);
            list.add(new Node(start, end));
        }
        return list;
    }

    // 8:45 -> xxxx
    private static int getTime(String time) {
        String[] str = time.split(":");
        int hour = Integer.parseInt(str[0]);
        int min = Integer.parseInt(str[1]);
        return hour * 60 + min;
    }

    public static class Node {
        public int start;
        public int end;

        public Node(int s, int e) {
            start = s;
            end = e;
        }
    }

    static class StringMeeting {
        public String start;
        public String end;

        public StringMeeting(String start, String end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        List<StringMeeting> calendar1 = new ArrayList<>();
        StringMeeting dailyBounds1 = new StringMeeting("9:00", "20:00");
        List<StringMeeting> calendar2 = new ArrayList<>();
        StringMeeting dailyBounds2 = new StringMeeting("10:00", "18:30");
        int meetingDuration = 30;
        calendar1.add(new StringMeeting("9:00", "10:30"));
        calendar1.add(new StringMeeting("12:00", "13:00"));
        calendar1.add(new StringMeeting("16:00", "18:00"));

        calendar2.add(new StringMeeting("10:00", "11:30"));
        calendar2.add(new StringMeeting("12:30", "14:30"));
        calendar2.add(new StringMeeting("14:30", "15:00"));
        calendar2.add(new StringMeeting("16:00", "17:00"));

        List<StringMeeting> res = calendarMatching(
                calendar1,
                dailyBounds1,
                calendar2,
                dailyBounds2,
                meetingDuration
        );

        for (var sm : res) {
            System.out.printf("[%s--%s]\t", sm.start, sm.end);
        }
        System.out.println();
    }
}
