import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    // 소수
    // https://www.acmicpc.net/problem/2581
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int M = Integer.parseInt(br.readLine());
        if(M == 1)
            M = 2;
        int N = Integer.parseInt(br.readLine());

        int primeNo = N, limit, sum = 0;
        boolean isPrimeNo;
        for (int i = N; i >= M; i--) {
            limit = i / 2;
            isPrimeNo = true;
            for (int j = 2; j <= limit; j++) {
                // 소수가 아니라면
                if (i % j == 0) {
                    isPrimeNo = false;
                    break;
                }
                limit = i / j;
            }
            if (isPrimeNo) {
                sum += i;
                primeNo = i;
            }

        }
        if (sum == 0)
            System.out.println(-1);
        else
            System.out.println(sum + "\n" + primeNo);
    }
}