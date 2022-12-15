import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[] arr = new int[501];

		int l, r;
		int[] dp = new int[501];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			l = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			arr[l] = r;
			dp[l] = 1;
		}
		int cnt = 0;
		int max = 0;
		int answer = 0;
		for (int i = 1; i <= 500; i++) {
			// 총 갯수 카운트되면 종료
			if (cnt == N)
				break;
			// 빈자리는 패스
			if (arr[i] == 0)
				continue;
			cnt++;
			max = 0;
			// 이전 값 확인
			for (int j = i - 1; j >= 1; j--) {
				// 빈 자리 패스
				if (arr[j] != 0) {
					// 나보다 연결된 전봇대 높이가 낮은 애
					if (arr[j] < arr[i]) {
						max = Math.max(dp[j], max);
					}
				}
			}
			dp[i] = max + 1;
			answer = Math.max(dp[i], answer);
		}

		System.out.println(N - answer);
	}
}