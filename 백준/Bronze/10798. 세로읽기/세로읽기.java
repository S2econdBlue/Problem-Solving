import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        char[][] alphas = new char[5][15];

        char[] input;
        for (int i = 0; i < 5; i++) {
            input = br.readLine().toCharArray();
            for (int j = 0; j < input.length; j++) {
                alphas[i][j] = input[j];
            }
        }

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 5; j++) {
                if (alphas[j][i] != '\u0000') {
                    sb.append(alphas[j][i]);
                }
            }
        }
        System.out.println(sb);
    }
}        