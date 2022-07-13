package LeetCode;

public class Problem_2309_GreatestLetter {

    // A~Z: 65~90
    // a~z: 97~122
    public String greatestLetter(String s) {
        int[] cnt = new int[26];
        char[] str = s.toCharArray();
        String ans = "";
        for (char ch : str) {
            if (Character.isLowerCase(ch)) {
                cnt[ch - 'a'] |= 1;
            }
            if (Character.isUpperCase(ch)) {
                cnt[ch - 'A'] |= 2;
            }
        }
        for (int i = 25; i >= 0; i--) {
            if (cnt[i] == 3) {
                ans = String.valueOf((char) (i + 'A'));
                break;
            }
        }
        return ans;
    }


    private static String printBin(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 31; i >= 0; i--) {
            if ((num >> i & 1) != 0) {
                sb.append("1");
            } else {
                sb.append("0");
            }
        }
        System.out.println(sb);
        return sb.toString();
    }

    public static void main(String[] args) {
        // printBin(65);
        // printBin(90);
        // System.out.println();
        // printBin(97);
        // printBin(122);

        // String s = "lEeTcOdE";
        String s = "EtaJrJnREOEaurJiCrwnMbUFElllBiCEKbTpbxKUfMVGkBPfrIyOljQQRsVjHqzgAGRQUjoymVNtWKwaylLGiATlpogDvzOqvkpoCpAsFaRNoZlBPygruHhPPqaCHAZzBCcLqAAxxCWrznQpmOMhcqpWIsdKXRiMQTXQBmfPhavhFtUHEVOGHtpisJUVhYvFYvhCChqzwyNXfVPIWpQAdPXttDurohPkjXgaETvoCJQmFpVSoYgOMwxZPniKDmvDEoSxLEIHacAageesFOfXyBwiGIrhxgqKjEKbqpONKEkwlyQxlbMzdEYORwXwAqbnDcaijrVuEvtqjDUWiNCgBLYyNRPIrAkRACFtyzBvDpqJbuZhNlVPWpmKfxeuxIMFgtKZSsXJBCFkgybAKLLouWBFNIacSwvxRKcFUOVzrOcLCCnmHIHUDoVzAJTnZLtjdCHIrmpylnOZQaXwIYXARFbpVMnGiFhgZNcfEjAyPXUPPfKeCbuKQndqHQFCIwmDxeADMdidDusoRVPHUQHTsesjnshnvrUstvyrUuVfhsNVukFuhtibcAhwljaoBQUIrBlVukJorxmjmvJbjqOYycWuiairqSCPtzCYesqMnEaKcmXGeaspjWOcpYgsPakeMRhIUyjEMyIzgHLHSwwbDmOfpVkGFYsVakurdxllEzXkUtUCDYQrRNXEJihyheicfPyShXJDlnFpgMyhdlouubKPsBDETQKoUzDfJuBgOVkwzflYihhKgjzTNtlDUqxBcRCbDVMYFbESsQzLeLMTbBdtDAdzYZiHgnXagkUfGbGMPrwXVhAWWJudUDSFyDhJRmrulylFNdsLSvHnSYmIPwDncowYlfrSoQRbbDLPmzSfbtYmZPndnMjvuTBcSWNegdTCIPYehqkrpGocrWeBOUyjUPMuZPDqJxCbVihdrVYeYiITFKsuPVuuplSIMCcRSekydnEeNrXKjAQoicZMfrkMSEaTevwBpTqmKwzv";
        var ans = new Problem_2309_GreatestLetter().greatestLetter(s);
        System.out.println(ans);

    }
}
