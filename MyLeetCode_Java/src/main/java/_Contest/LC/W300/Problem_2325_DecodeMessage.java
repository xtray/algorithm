package _Contest.LC.W300;

public class Problem_2325_DecodeMessage {
    public String decodeMessage(String key, String message) {
        char[] decode = new char[256];
        int idx = 0; // 出现顺序
        // 每一个字母对应其第一次出现顺序在字母表中的字母
        for (char ch : key.toCharArray()) {
            if (decode[ch] == decode[0] && ch != ' ') {
                decode[ch] = (char) ('a' + idx++);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char ch : message.toCharArray()) {
            sb.append(ch != ' ' ? decode[ch] : ch);
        }
        return sb.toString();
    }

    // 把空格映射到空格上，这样就不需要额外判断空格了。
    public String decodeMessage1(String key, String message) {
        char[] decode = new char[256];
        decode[' '] = ' ';
        char idx = 'a'; // 出现顺序
        // 每一个字母对应其第一次出现顺序在字母表中的字母
        for (char ch : key.toCharArray()) {
            if (decode[ch] == 0) {
                decode[ch] = idx++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char ch : message.toCharArray()) {
            sb.append(decode[ch]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // String key = "the quick brown fox jumps over the lazy dog", message = "vkbs bs t suepuv";
        String key = "eljuxhpwnyrdgtqkviszcfmabo", message = "zwx hnfx lqantp mnoeius ycgk vcnjrdb";
        var ans = new Problem_2325_DecodeMessage().decodeMessage1(key, message);
        System.out.println(ans);
    }


}
