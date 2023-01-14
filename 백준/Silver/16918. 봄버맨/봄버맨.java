import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		boolean[][] tmp;
		int[][] bd = new int[R][C], dydx = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		char[] line;
		for (int i = 0; i < R; i++) {
			line = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (line[j] == 'O')
					bd[i][j] = 3;
			}
		}
		int nY = 0, nX = 0;
		for (int k = 1; k <= N; k++) {
			// 폭탄 걸치
			if (k % 2 == 0) {
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						if (bd[i][j] == 0)
							bd[i][j] = k + 3;
					}
				}
			}
			// 폭발
			else {
				tmp = new boolean[R][C];
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						if (bd[i][j] == k) {
							tmp[i][j] = true;
							for (int l = 0; l < 4; l++) {
								nY = i + dydx[l][0];
								nX = j + dydx[l][1];
								if (nY < 0 || nY == R || nX < 0 || nX == C)
									continue;
								tmp[nY][nX] = true;
							}
						}

					}
				}
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						if (tmp[i][j])
							bd[i][j] = 0;

					}
				}
			}

		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (bd[i][j] == 0)
					sb.append('.');
				else
					sb.append('O');
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}