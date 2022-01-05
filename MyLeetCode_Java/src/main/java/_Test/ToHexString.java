package _Test;

public class ToHexString {

    public String toHexStr(String input) {
        if(input == null || input.length() ==0) {
            return input;
        }
        char[] str = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char ch : str) {
            var hex = Integer.toHexString(ch-'0');
            sb.append(hex);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String input = "363063313";
        var res = new ToHexString().toHexStr(input);
        System.out.println(res);
    }
}
