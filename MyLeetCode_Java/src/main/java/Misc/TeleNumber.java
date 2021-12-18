package Misc;

import java.util.Arrays;
import java.util.Comparator;

public class TeleNumber {

    public class Bucket {
        int id;
        String name;
        int min;
        int max;


        public Bucket(int i, String n, int mi, int mx) {
            id = i;
            name = n;
            min = mi;
            max = mx;
        }
    }

    public class TeleNumComparator implements Comparator<Bucket> {

        @Override
        public int compare(Bucket o1, Bucket o2) {
            return o1.max - o2.max;
        }
    }

    public void solve(int number) {
        Bucket[] bu = new Bucket[400];
        bu[0] = new Bucket(354, "北京朝阳.354", 1310000, 1316666);
        bu[1] = new Bucket(271, "北京海淀.271", 1330000, 1352222);
        bu[2] = new Bucket(169, "上海浦东.169", 1510000, 1516666);
        bu[3] = new Bucket(111, "上海嘉定.111", 1610000, 1618888);
        // ....
        int base = 1700000;
        int delta = 100;
        for (int i = 4; i < 399; i++) {

            bu[i] = new Bucket(i, "测试地址", base, base + delta);
            base += delta + 100;
        }
        bu[399] = new Bucket(883, "拉萨城关.883", 1910000, 1999999);

        Arrays.sort(bu, new TeleNumComparator());
        // 对number进行二分
        int left = 0;
        int right = 399;
        int ans = -1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            Bucket bt = bu[mid];
            if (bt.max < number) {
                left = mid + 1;
            } else if (bt.min > number) {
                right = mid - 1;
            } else {
                // 找到number 位于 min～max之间
                ans = mid;
                break;
            }
        }

        if (ans != -1) {
            System.out.println(number + ": " + bu[ans].name);
        } else {
            System.out.println("Opps, no found!");
        }

    }

    public static void main(String[] args) {
        TeleNumber tn = new TeleNumber();
//        tn.solve(1511234);
        tn.solve(1911234);

    }
}
