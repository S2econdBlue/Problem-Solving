import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        List<Character> l = new ArrayList<>();
        while (N != 0) {
            if (N % B > 9) {
                l.add((char) ('A' + (N % B - 10)));
            } else
                l.add((char) ('0' + N % B));
            N /= B;
        }
        for (int i = l.size() - 1; i >= 0; i--) {
            System.out.print((char) l.get(i));
        }
    }
}