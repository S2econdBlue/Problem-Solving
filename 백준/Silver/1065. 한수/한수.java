import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static boolean recur(String ori, int dif, int left, int right) {
        if (ori.charAt(left) - ori.charAt(right) == dif) {
            if (ori.length() - 1 == right)
                return true;
            else return recur(ori, dif, left + 1, right + 1);
        } else return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int answer = 0;
        String toS;
        for (int i = 1; i <= N; i++) {
            if (i < 100)
                answer++;
            else {
                toS = Integer.toString(i);
                if (recur(toS, toS.charAt(0) - toS.charAt(1), 1, 2))
                    answer++;
            }
        }
        System.out.println(answer);
    }
    // 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110
}
