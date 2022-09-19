package _DailyTarget;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem_2034_SockPrice {


    static class StockPrice0 {

        private int curTime = -1;
        // [时间戳, [时间戳, 价格]]
        private Map<Integer, int[]> map = new HashMap<>();
        // [时间戳, 价格]
        private TreeSet<int[]> treeSet
                = new TreeSet<>((o1, o2) -> o1[0] == o2[0] ?
                // (o1.hashCode() - o2.hashCode()) :
                (o1.toString().compareTo(o2.toString())) :
                o1[0] - o2[0]);

        // IMP: 错的, 相同的会覆盖,( 相同的是由比较器比较得出是否相同)
        // private TreeSet<int[]> treeSet = new TreeSet<>((o1, o2) -> o1[0] - o2[0]);

        public void update(int timestamp, int price) {
            if (timestamp > curTime) {
                curTime = timestamp;
            }
            int[] item;
            if (!map.containsKey(timestamp)) {
                item = new int[]{price};
                map.put(timestamp, item);
            } else {
                item = map.get(timestamp);
                treeSet.remove(item); // IMP: 必须先删除, 后修改, 如果先修改后删除会导致删除失败(找不到了)
                item[0] = price;      //  会更新hashcode, 造成失败, 除非重写compare, 跟hashcode
            }
            treeSet.add(item);
        }

        public int current() {
            return map.get(curTime)[0];
        }

        public int maximum() {
            return treeSet.last()[0];
        }

        public int minimum() {
            return treeSet.first()[0];
        }
    }

    static class StockPrice {

        class Price {
            private Integer price;

            public Price(int p) {
                price = p;
            }

            // @Override
            // public boolean equals(Object obj) {
            //     if (obj == null) {
            //         return false;
            //     }
            //     // 对象是当前对象，直接返回 true
            //     if (this == obj) {
            //         return true;
            //     }
            //     // 判断对象类型是否是Price
            //     if (obj instanceof Price) {
            //         Price vo = (Price) obj;
            //         // 比较每个属性的值一致时才返回true
            //         if (vo.price.equals(this.price)) {
            //             return true;
            //         }
            //     }
            //     return false;
            // }
            //
            // // 重写hashcode方法，返回的hashCode一样才再去比较每个属性的值
            // @Override
            // public int hashCode() {
            //     return this.price.hashCode();
            // }
        }

        private int curTime = -1;
        // [时间戳, [时间戳, 价格]]
        private Map<Integer, Price> map = new HashMap<>();
        // [时间戳, 价格]
        private TreeSet<Price> treeSet = new TreeSet<>((o1, o2) -> Objects.equals(o1.price, o2.price) ? (o2.hashCode() - o1.hashCode()) : o1.price - o2.price);

        public StockPrice() {

        }

        public void update(int timestamp, int price) {
            if (timestamp > curTime) {
                curTime = timestamp;
            }
            Price item;
            if (!map.containsKey(timestamp)) {
                item = new Price(price);
                map.put(timestamp, item);
            } else {
                item = map.get(timestamp);
                treeSet.remove(item); // IMP: 必须先删除, 后修改, 如果先修改后删除会导致删除失败(找不到了)
                item.price = price;   //  会更新hashcode, 造成失败, 除非重写compare, 跟hashcode
            }
            treeSet.add(item);
        }

        public int current() {
            return map.get(curTime).price;
        }

        public int maximum() {
            return treeSet.last().price;
        }

        public int minimum() {
            return treeSet.first().price;
        }
    }

    static class StockPrice1 {

        Map<Integer, Integer> map; // key: 时间戳, value: 价格
        PriorityQueue<int[]> maxQ; // int[timestamp, value]
        PriorityQueue<int[]> minQ;
        int curPrice;
        int curTime;

        public StockPrice1() {
            map = new HashMap<>();
            maxQ = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
            minQ = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
            curTime = 0;
            curPrice = 0;
        }

        public void update(int timestamp, int price) {
            map.put(timestamp, price);
            curTime = Math.max(curTime, timestamp);
            curPrice = curTime == timestamp ? price : curPrice;
            maxQ.add(new int[]{timestamp, price});
            minQ.add(new int[]{timestamp, price});
        }

        public int current() {
            return curPrice;
            //return map.get(curTime);
        }

        public int maximum() {
            // maxQ top
            // map 里存的是准确的
            // 如果 maxQ 里的 top 对应的时间戳取到的值跟 map 不一致,说明是过时的
            int[] max = maxQ.peek();
            while (max != null && max[1] != map.get(max[0])) {
                maxQ.poll();
                max = maxQ.peek();
            }
            return max[1];
        }

        public int minimum() {
            // minQ top
            int[] min = minQ.peek();
            while (min != null && min[1] != map.get(min[0])) {
                minQ.poll();
                min = minQ.peek();
            }
            return min[1];
        }

    }

    public static void main(String[] args) throws IOException {
        // StockPrice sl = new StockPrice();
        StockPrice0 sl = new StockPrice0();
        StockPrice1 sl1 = new StockPrice1();
        // sl.update(1, 10);
        // sl.update(2, 5);
        // var ans = sl.current();
        // System.out.println(ans);
        // ans = sl.maximum();
        // System.out.println(ans);
        // sl.update(1, 3);
        // ans = sl.maximum();

        // ["StockPrice","update","update","update","update","update","update","minimum"]
        // [[],[58,1866],[43,121],[40,5339],[32,5339],[43,6414],[49,9369],[]]
        // sl.update(58, 1866);
        // sl.update(43, 121);
        // sl.update(40, 5339);
        // sl.update(32, 5339);
        // sl.update(43, 6414);
        // sl.update(49, 9369);
        // var ans = sl.minimum(); // 1866
        // System.out.println(ans);


        // String file = "/tmp/2034.data";
        // String file = "/tmp/2034_big.data";
        String file = "/tmp/17.data";
        String cmd;
        String data;
        try (Scanner sc = new Scanner(new FileReader(file))) {
            cmd = sc.nextLine();
            data = sc.nextLine();
        }
        String[] cmds = cmd.substring(1, cmd.length() - 1).replace("\"", "").split(",");
        int N = cmds.length;
        String regex = "\\[([\\d,]*)\\]";
        // String regex = "\\[(\\d{1,},\\d{1,})*\\]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);
        String[] datas = new String[N];
        int idx = 0;
        while (matcher.find()) {
            datas[idx++] = matcher.group().replaceAll("\\[|\\]", "");
        }

        List<String> res = new ArrayList<>();
        List<String> res1 = new ArrayList<>();

        for (int i = 1; i < N; i++) {
            switch (cmds[i]) {
                case "update":
                    String[] nums = datas[i].split(",");
                    int timeStamp = Integer.parseInt(nums[0]);
                    int price = Integer.parseInt(nums[1]);
                    sl.update(timeStamp, price);
                    res.add("null");

                    sl1.update(timeStamp, price);
                    res1.add("null");
                    break;
                case "current":
                    var ans = sl.current();
                    res.add(String.valueOf(ans));

                    var ans1 = sl1.current();
                    res1.add(String.valueOf(ans1));
                    break;
                case "maximum":
                    var max = sl.maximum();
                    res.add(String.valueOf(max));

                    var max1 = sl1.maximum();
                    res1.add(String.valueOf(max1));
                    break;
                case "minimum":
                    var min = sl.minimum();
                    res.add(String.valueOf(min));

                    var min1 = sl1.minimum();
                    res1.add(String.valueOf(min1));
                    break;
                default:
                    break;
            }
        }

        if (res.size() != res1.size()) {
            System.out.println("NG, Size Not Equal");
        }
        int size = res.size();
        for (int i = 0; i < size; i++) {
            if (!res.get(i).equals(res1.get(i))) {
                System.out.println("NG, String Not Equal");
                System.out.println("res: " + i + ", val: " + res.get(i));
                System.out.println("res1: " + i + ", val1: " + res1.get(i));
                // break;
            }
        }

        // System.out.println(res);
        // String outFile = "/tmp/2034.out";
        // String outFile = "/tmp/2034_big.out";
        // PrintWriter out = new PrintWriter(outFile);
        // out.println(res);
        // out.close();


    }
}
