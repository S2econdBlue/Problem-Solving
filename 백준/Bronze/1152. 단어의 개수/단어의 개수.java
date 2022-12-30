import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int answer = 0;

        for (int i = 0, j = s.length(); i < j; i++) {
            if (s.charAt(i) == ' ') {
                if (i == 0 || i == j - 1)
                    continue;
                answer++;
            }
        }
        if (s.compareTo(" ") == 0)
            System.out.println(0);
        else
            System.out.println((answer + 1));
    }
}
