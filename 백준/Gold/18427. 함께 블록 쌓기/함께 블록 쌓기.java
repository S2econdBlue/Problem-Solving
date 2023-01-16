import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static int H;
	static int answer = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// student
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());

		arr = new int[N][];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = new int[st.countTokens()];
			for (int j = 0, l = st.countTokens(); j < l; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		long[][] dp = new long[N][1000 + 1];

		// 초기화
		for (int i = 0; i < arr[0].length; i++) {
			dp[0][arr[0][i]] += 1;
		}

		for (int i = 1; i < N; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				dp[i][arr[i][j]]++;
			}

			// 1 ~ H개수만큼 반복
			for (int j = 1; j <= H; j++) {
				dp[i][j] += dp[i - 1][j];
				// 이전에 해당 값이 사용되었을 때
				if (dp[i - 1][j] > 0) {
					for (int l = 0; l < arr[i].length; l++) {
						if (j + arr[i][l] <= H) {
							dp[i][j + arr[i][l]] += dp[i - 1][j];
							dp[i][j + arr[i][l]] %= 10007;
						}

					}

				}
			}
		}
		System.out.println(dp[N - 1][H] % 10007);
	}
}