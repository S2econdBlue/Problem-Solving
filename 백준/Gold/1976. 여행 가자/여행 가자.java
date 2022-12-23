import java.io.*;
import java.util.*;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		int[][] city_con = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				city_con[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] trip = new int[M];
		for (int i = 0; i < M; i++) {
			trip[i] = Integer.parseInt(st.nextToken());
		}

		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (city_con[i][k] == 1 && city_con[k][j] == 1) {
						city_con[i][j] = 1;
						city_con[j][i] = 1;
					}

				}
			}
		}

		for (int i = 1; i < M; i++) {
			// can't travel And different each other
			if (city_con[trip[i - 1] - 1][trip[i] - 1] != 1 && trip[i - 1] - 1 != trip[i] - 1) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}
}