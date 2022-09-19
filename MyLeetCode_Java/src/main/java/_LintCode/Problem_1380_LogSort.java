package _LintCode;

import java.util.Arrays;

public class Problem_1380_LogSort {

    public static class Log {
        public String id;
        public String content;
        public int type; // 0 字符串, 1 数字

        public Log(String id, String content, int type) {
            this.id = id;
            this.content = content;
            this.type = type;
        }
    }

    public String[] logSort(String[] logs) {
        if (logs == null || logs.length == 0) {
            return logs;
        }
        int N = logs.length;
        Log[] logArr = new Log[N];
        int idx = 0;
        for (String log : logs) {
            logArr[idx++] = getLog(log);
        }
        Arrays.sort(logArr, (o1, o2) ->
                (o1.type == o2.type && o1.type == 0) ?
                        (o1.content.equals(o2.content) ? (o1.id.compareTo(o2.id)) : o1.content.compareTo(o2.content)) :
                        (o1.type - o2.type)
        );
        String[] ans = new String[N];
        idx = 0;
        for (Log item : logArr) {
            ans[idx++] = item.id + " " + item.content;
        }
        return ans;
    }

    private Log getLog(String log) {

        int pos = 0;
        int N = log.length();
        while (pos < N && log.charAt(pos) != ' ') {
            pos++;
        }
        String id = log.substring(0, pos++);
        int type = (log.charAt(pos) >= '0' && log.charAt(pos) <= '9') ? 1 : 0;
        String content = log.substring(pos);
        return new Log(id, content, type);
    }
}
