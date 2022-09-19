package _Test;

import java.util.regex.Pattern;

public class NumSlash {

    public static void main(String[] args) {
        Pattern exp = Pattern.compile("(\\-)");
        String str = "---";
        System.out.println(str.split("-").length);
        var matcher = exp.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
        var ans = matcher.groupCount();
        System.out.println(ans);
    }
}
