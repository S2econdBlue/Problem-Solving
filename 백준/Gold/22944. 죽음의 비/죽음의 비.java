import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 한변 길이
		int N = Integer.parseInt(st.nextToken());
		// 현재 체력
		int H = Integer.parseInt(st.nextToken());
		// 우산 내구도
		int D = Integer.parseInt(st.nextToken());

		// 격자
		char[][] bd = new char[N][N];
		// 격자 방문 체크
		int[][] chk = new int[N][N];

		int[][] dydx = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		List<int[]> umbs = new ArrayList<>();
		int[][] fromTo = new int[2][2];

		for (int i = 0; i < N; i++) {
			bd[i] = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				if (bd[i][j] == 'S') {
					fromTo[0][0] = i;
					fromTo[0][1] = j;
				} else if (bd[i][j] == 'U') {
					umbs.add(new int[] { i, j });
				} else if (bd[i][j] == 'E') {
					fromTo[1][0] = i;
					fromTo[1][1] = j;
				}
			}
		}
		Queue<int[]> q = new LinkedList<>();
		// 시작 Y,X, 우산 체력, 현재 체력, 이동 횟수
		q.add(new int[] { fromTo[0][0], fromTo[0][1], 0, H, 0 });

		int crnt[];
		int nextY, nextX;
		chk[fromTo[0][0]][fromTo[0][1]] = H;

		while (!q.isEmpty()) {
			crnt = q.poll();
			// 이전에 해당 자리에 더 큰 체력으로 도달 가능한 경우
			if (chk[crnt[0]][crnt[1]] > crnt[3])
				continue;

			for (int i = 0; i < 4; i++) {
				nextY = crnt[0] + dydx[i][0];
				nextX = crnt[1] + dydx[i][1];

				if (nextY < 0 || nextX < 0 || nextY == N || nextX == N)
					continue;

				// 목적지라면
				if (nextY == fromTo[1][0] && nextX == fromTo[1][1]) {
					System.out.println(crnt[4] + 1);
					return;
				}
				// 우산이 있고 해당 자리보다 더 큰 체력으로 도달 가능할 때
				if (bd[nextY][nextX] == 'U' && chk[nextY][nextX] < crnt[3]) {
					chk[nextY][nextX] = crnt[3];
					q.add(new int[] { nextY, nextX, D - 1, crnt[3], crnt[4] + 1 });
				}
				// 우산이 없다면
				else {
					// 쓰고있는 우산이 있을 때
					if (crnt[2] > 0 && chk[nextY][nextX] < crnt[3]) {
						chk[nextY][nextX] = crnt[3];
						q.add(new int[] { nextY, nextX, crnt[2] - 1, crnt[3], crnt[4] + 1 });
					}

					else {
						// 체력이 1 이상 있을 때
						if (crnt[3] > 1 && chk[nextY][nextX] < crnt[3] - 1) {
							chk[nextY][nextX] = crnt[3] - 1;
							q.add(new int[] { nextY, nextX, 0, crnt[3] - 1, crnt[4] + 1 });
						}
					}

				}

			}
		}
		System.out.println(-1);
	}
}