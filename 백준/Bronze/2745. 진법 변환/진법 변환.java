import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String N = st.nextToken(); // 변경해야 할 문자열
        int B = Integer.parseInt(st.nextToken()); // 진법

        int sum = 0, posCount = 0;
        int ans;
        for (int i = N.length() - 1; i >= 0; i--) {

            if ('A' <= N.charAt(i) && N.charAt(i) <= 'Z') {
                ans = (int) (((N.charAt(i) - 'A') + 10) * Math.pow(B, posCount));
                sum += ans;

            } else {
                ans = (int) ((N.charAt(i) -'0' )* Math.pow(B, posCount));
                sum += ans;

            }
            posCount++;
        }
        System.out.println(sum);

    }
}        