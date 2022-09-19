package _LintCode;

import java.util.HashMap;
import java.util.Map;

public class Problem_1186_TinyURL {


    private static final String codes = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final String prefix = "https://tinyurl.com/";
    private static final int k = 6; // 固定随机长度
    private Map<String, String> tinyToLong = new HashMap<>();
    private Map<String, String> longToTiny = new HashMap<>();

    private String getRandHashKey() {
        char[] str = new char[k];
        for (int i = 0; i < k; i++) {
            int idx = (int) (Math.random() * 62);
            str[i] = codes.charAt(idx);
        }
        return prefix + String.valueOf(str);
    }

    public String encode(String longUrl) {
        while (!longToTiny.containsKey(longUrl)) { // 如果已经生成过
            String url = getRandHashKey(); // 生成一个短网址
            if (!tinyToLong.containsKey(url)) { // 如果是重复的
                longToTiny.put(longUrl, url);
                tinyToLong.put(url, longUrl);
                break;
            }
        }
        return longToTiny.get(longUrl);
    }

    public String decode(String shortUrl) {
        return tinyToLong.getOrDefault(shortUrl, "");
    }

}

