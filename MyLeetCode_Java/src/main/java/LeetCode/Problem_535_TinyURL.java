package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class Problem_535_TinyURL {

    public class Codec {

        private static final String codes = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        private static final String prefix = "https://tinyurl.com/";
        private static final int k = 6;
        private Map<String, String> longToTiny = new HashMap<>();
        private Map<String, String> tinyToLong = new HashMap<>();

        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            while (!longToTiny.containsKey(longUrl)) {
                char[] str = new char[k];
                for (int i = 0; i < k; i++) {
                    int idx = (int) (Math.random() * codes.length());
                    str[i] = codes.charAt(idx);
                }
                String url = prefix + String.valueOf(str);
                if(!tinyToLong.containsKey(url)) {
                    longToTiny.put(longUrl, url);
                    tinyToLong.put(url, longUrl);
                    break;
                }
            }
            return longToTiny.get(longUrl);
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            return tinyToLong.getOrDefault(shortUrl, "");
        }
    }
}
