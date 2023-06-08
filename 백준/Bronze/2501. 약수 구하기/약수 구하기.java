import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    //
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 약수는 자기 자신을 제외하면 항상 자기의 절반보다 작거나 같다
        //
        int count = 0;
        // 9 1 3 9, 15: 1 3 5 15, 25: 1 5 25, 33: 1 3 11 33(15 이하만, ), 36: 1 2 3 4 6 9 12 18 36
        for (int i = 1; i <= N / 2; i++) {
            if (N % i == 0) {
                count++;
            }
            if (count == K) {
                System.out.println(i);
                return;
            }
        }
        // 위에 반복문은 자기 자신이 제외되어있는 케이스
        // 따라서 구한 약수의 개수에서 자기 자신을 더했을 때 구하고자 한 순번이라면
        if (++count == K) {
            System.out.println(N);
        } else
            System.out.println(0);
    }
}