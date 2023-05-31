import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input = br.readLine();

        // abba abcba
        if (input.length() % 2 == 0) {
            for (int i = 0; i < input.length() / 2; i++) {
                if (input.charAt(input.length() / 2 - 1 - i) != input.charAt(input.length() / 2 + i)) {
                    System.out.println(0);
                    return;
                }
            }
        } else {
            for (int i = 0; i < input.length() / 2; i++) {
                if (input.charAt(input.length() / 2 - 1 - i) != input.charAt(input.length() / 2 + 1 + i)) {
                    System.out.println(0);
                    return;
                }
            }
        }
        System.out.println(1);
    }
}