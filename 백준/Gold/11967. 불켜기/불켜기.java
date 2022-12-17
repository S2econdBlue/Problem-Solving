import java.io.*;
import java.util.*;

class Main {
	static boolean isOut(int Y, int X, int N) {
		if (Y < 1 || Y > N || X < 1 || X > N)
			return true;
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 상, 우, 하, 좌
		int[][] dydx = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		// 방문 처리
		boolean[][] chck = new boolean[N + 1][N + 1];
		// 불켜짐 처리
		boolean[][] lightOn = new boolean[N + 1][N + 1];
		List<int[]>[][] swi = new List[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				swi[i][j] = new LinkedList<>();
			}
		}
		int fromX, fromY, toX, toY;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			fromY = Integer.parseInt(st.nextToken());
			fromX = Integer.parseInt(st.nextToken());
			toY = Integer.parseInt(st.nextToken());
			toX = Integer.parseInt(st.nextToken());

			swi[fromY][fromX].add(new int[] { toY, toX });

		}
		// bfs로 탐색하면서 스위치가 있다면 해당 방의 불을 켬
		// 당장 방문을 못하더라도 이후에 다른 방의 스위치를 올렸을 때 방문이 가능해지는 방도 고려
		// 스위치로 켤 수 있는 방은 인접하지 않은 방도 가능
		// 큐 2개로 관리, 큐 하나 돌리고 방문 가능한 애들은 큐 2번에 저장
		// 큐 2번에서 다시 방문 가능한 애들 중 불켜져있는 곳에 방문

		// 1. 불을 켜고
		// 2. 현재 자리 기준 네 방향
		Queue<int[]> q1 = new LinkedList<>();
		Queue<int[]> q2 = new LinkedList<>();

		q1.add(new int[] { 1, 1 });
		lightOn[1][1] = true;
		int[] crnt;
		int nextY, nextX, answer = 0;

		while (!q1.isEmpty()) {
			crnt = q1.poll();

			// 먼저 처리되었다면
			if (chck[crnt[0]][crnt[1]])
				continue;
			chck[crnt[0]][crnt[1]] = true;

			// 해당 자리의 불을 켜기
			for (int[] j : swi[crnt[0]][crnt[1]]) {
				if (lightOn[j[0]][j[1]])
					continue;
				lightOn[j[0]][j[1]] = true;
				// 불 킨 자리 주변이 연결 가능하다면
				for (int k = 0; k < 4; k++) {
					nextY = j[0] + dydx[k][0];
					nextX = j[1] + dydx[k][1];

					if (isOut(nextY, nextX, N))
						continue;
					// 주변 자리에 연결이 가능할 때
					if (chck[nextY][nextX]) {
						q1.add(j);
						chck[j[0]][j[1]] = false;
						break;
					}

				}
			}

			for (int i = 0; i < 4; i++) {
				nextY = crnt[0] + dydx[i][0];
				nextX = crnt[1] + dydx[i][1];
				// 외곽, 불꺼진 경우, 방문한 경우
				if (isOut(nextY, nextX, N) || !lightOn[nextY][nextX] || chck[nextY][nextX])
					continue;
				q1.add(new int[] { nextY, nextX });
			}
		}

		answer = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (lightOn[i][j])
					answer++;
			}
		}
		System.out.println(answer);
	}
}