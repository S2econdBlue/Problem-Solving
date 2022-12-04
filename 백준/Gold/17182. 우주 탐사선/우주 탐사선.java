import java.io.*;
import java.util.*;

class Main {
	static int[][] bd;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		bd = new int[N][N];

		// ASC
		Queue<int[]> pq = new LinkedList<>();

		for (int k = 0; k < N; k++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				bd[k][i] = Integer.parseInt(st.nextToken());
			}
		}

		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (bd[i][k] + bd[k][j] < bd[i][j])
						bd[i][j] = bd[i][k] + bd[k][j];
				}
			}
		}

		// 출발 도시, 비용, 방문체크
		pq.add(new int[] { K, 0, 1 << K });

		int crnt[];
		int cnt = 0, answer = Integer.MAX_VALUE;
		while (!pq.isEmpty()) {
			crnt = pq.poll();

			cnt = 0;
			for (int i = 0; i < N; i++) {
				if ((crnt[2] & (1 << i)) == 0) {
					cnt++;
					pq.add(new int[] { i, crnt[1] + bd[crnt[0]][i], (crnt[2] | (1 << i)) });
				}
			}
			if (cnt == 0) {
				answer = Math.min(answer, crnt[1]);
			}
		}
		System.out.println(answer);
	}
}