package LeetCode;

public class Problem_468_ValidIP {

    public String validIPAddress(String ip) {
        if (ip.contains(".") && checkV4(ip)) {
            return "IPv4";
        }
        if (ip.contains(":") && checkV6(ip)) {
            return "IPv6";
        }
        return "Neither";
    }

    private boolean checkV6(String ip) {
        int N = ip.length();
        int cnt = 0;
        char[] str = ip.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (str[i] == ':') {
                cnt++;
                if (sb.length() < 1 || sb.length() > 4) {
                    return false;
                }
                sb.setLength(0);
            } else if (Character.isDigit(str[i])) {
                sb.append(str[i]);
            } else if (Character.isLetter(str[i])) {
                if ((str[i] >= 'a' && str[i] <= 'f') ||
                        (str[i] >= 'A' && str[i] <= 'F')) {
                    sb.append(str[i]);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        if (sb.length() < 1 || sb.length() > 4) {
            return false;
        }
        if (cnt != 7) {
            return false;
        }
        return true;
    }

    private boolean checkV4(String ip) {
        int N = ip.length();
        int cnt = 0;
        int num = 0;
        int numLen = 0;
        char[] str = ip.toCharArray();
        for (int i = 0; i < N; i++) {
            if (str[i] == '.') {
                cnt++;
                if (numLen == 0) {
                    return false;
                }
                num = 0;
                numLen = 0;
            } else if (Character.isDigit(str[i])) {
                if (numLen > 0 && num == 0) { // 前导0
                    return false;
                }
                numLen++;
                num = num * 10 + str[i] - '0';
                if (num > 255) {
                    return false;
                }
            } else {
                return false;
            }
        }
        if (numLen == 0) { // 判断最后一段
            return false;
        }

        if (cnt != 3) {
            return false;
        }
        return true;
    }

    private boolean checkV42(String ip) {
        int N = ip.length();
        int cnt = 0;
        char[] str = ip.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (str[i] == '.') {
                cnt++;
                if(sb.length() == 0) {
                    return false;
                }
                sb.setLength(0);
            } else if (Character.isDigit(str[i])) {
                if(sb.length() != 0 && sb.charAt(0) == '0') {
                    return false;
                }
                sb.append(str[i]);
                if(Integer.parseInt(sb.toString()) > 255) {
                    return false;
                }
            } else {
                return false;
            }
        }
        if(sb.length() == 0 || Integer.parseInt(sb.toString()) > 255) {
            return false;
        }
        if (cnt != 3) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        // String ip = "256.16.253.1";
        // String ip = "192.0.0.1";
        // String ip = "1.0.1.";
        // String ip = "12..33.4";
        String ip = "01.01.01.01";
        // String ip = "2001:0db8:85a3:0:0:8A2E:0370:7334";
        // String ip = "2001:0db8:85a3::8A2E:037j:7334";
        // String ip = "02001:0db8:85a3:0000:0000:8a2e:0370:7334";
        // String ip = "20EE:FGb8:85a3:0:0:8A2E:0370:7334";
        // String ip = "2001:0db8:85a3:0:0:8A2E:0370:7334";
        var ans = new Problem_468_ValidIP().validIPAddress(ip);
        System.out.println(ans);
    }
}
