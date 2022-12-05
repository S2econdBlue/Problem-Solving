import java.io.*;
import java.util.*;

class Main {

	static int[][] bd;
	static int row, col;

	static boolean out(int y, int x) {
		if (y < 0 || x < 0 || y == row || x == col)
			return true;
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int[][] dydx = new int[][] { //
//				{ -1, -1 }, 
				{ -1, 0 },
//				{ -1, 1 }, //
				{ 0, 1 },
//				{ 1, 1 }, //
				{ 1, 0 },
//				{ 1, -1 }, 
				{ 0, -1 } };
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());

		int startY = 0, startX = 0;

		Queue<int[]> q1 = new LinkedList<>();
		Queue<int[]> q2 = new LinkedList<>();

		bd = new int[row][col];
		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				bd[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int cnt = 0, nextY, nextX;
		int[] crnt;
		boolean[][] chk;
		int[] numCnt;
		int answer = 0;
		// 치즈가 없어질 때까지 계속 반복
		while (true) {
			numCnt = new int[row * col];
			q1.add(new int[] { 0, 0 });
			chk = new boolean[row][col];
			chk[0][0] = true;

			while (!q1.isEmpty()) {
				crnt = q1.poll();

				// 사방 탐색
				for (int i = 0; i < 4; i++) {
					nextY = crnt[0] + dydx[i][0];
					nextX = crnt[1] + dydx[i][1];
					if (out(nextY, nextX) || chk[nextY][nextX])
						continue;

					// 몇 번 중첩됬는지 확인
					if (bd[nextY][nextX] == 1) {
						numCnt[col * nextY + nextX]++;
					}
					// 일반 땅
					else {
						chk[nextY][nextX] = true;
						q1.add(new int[] { nextY, nextX });
					}
				}
			}

			cnt = 0;

			for (int i = 0; i < row * col; i++) {
				if (numCnt[i] >= 2) {
					cnt++;
					bd[i / col][i % col] = 0;
				}
			}

			if (cnt == 0) {
				System.out.println(answer);
				break;
			}
			answer++;
		}
	}
}