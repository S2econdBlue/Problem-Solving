import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] dot = new int[16];
        dot[0] = 2;

        for (int i = 1; i <= N; i++) {
            dot[i] = (dot[i-1] * 2 - 1);
        }
        System.out.println(dot[N] * dot[N]);
    }
}