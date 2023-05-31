import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N * 2 - 1; i++) {
            if (i < N) {
                for (int j = 0; j < N - 1 - i; j++) {
                    sb.append(" ");
                }
                for (int j = 0; j < i * 2 + 1; j++) {
                    sb.append("*");
                }
            } else {
                for (int j = 0; j < i - N + 1; j++) {
                    sb.append(" ");
                }
                for (int j = 0; j < (N * 2 - 1) - (i - N + 1) * 2; j++) {
                    sb.append("*");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}