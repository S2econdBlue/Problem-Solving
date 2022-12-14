import java.io.*;
import java.util.*;

//1. 막대기를 던진다.
//2. 막대기가 미네랄을 만나면 해당 미네랄을 파괴한다.
//3. 클러스터가 새로 생성되었는지 확인한다.
//4. 새로 생성된 경우 분리된 클러스터라면 중력에 의해 떨어진다.
//5. 모든 차례가 끝날 때까지 1번부터 반복한다.
class Main {

	// 높이 1은 행렬 가장 아래
	// R은 가장 위
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 상 우 하 좌
		int[][] dydx = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		// 왼쪽 상0, 우1, 하2 ,,, 오른쪽 상0, 좌3, 하2
		int[][] breakdown = new int[][] { { 0, 1, 2 }, { 0, 3, 2 } };
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());

		char[][] bd = new char[row + 2][col + 2];

		char[] arr;
		for (int i = 0; i <= row + 1; i++) {
			if (i == 0 || i == row + 1) {
				Arrays.fill(bd[i], 'o');
				continue;
			}
			arr = br.readLine().toCharArray();
			for (int j = 0; j <= col + 1; j++) {
				if (j == 0 || j == col + 1) {
					bd[i][j] = 'o';
					continue;
				}
				bd[i][j] = arr[j - 1];
			}
		}
		boolean canBreak;
		int stickCnt = Integer.parseInt(br.readLine());
		int stick, height, destM, nextY, nextX, crntY, crntX, dist, tmp, shst, destY = 0, destX = 0;
		int[] crnt;

		PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);

		Queue<int[]> canFall = new LinkedList<int[]>();
		boolean[][] visit, eachVisit;
		st = new StringTokenizer(br.readLine());

		// 막대기 수만큼 반복
		for (int i = 0; i < stickCnt; i++) {
			canBreak = false;
			stick = Integer.parseInt(st.nextToken());
			height = row + 1 - stick;

			// left throw
			if (i % 2 == 0) {
				destM = 1;
				while (bd[height][destM] != 'x' && bd[height][destM] != 'o')
					destM++;
			}

			// right throw
			else {
				destM = col;
				while (bd[height][destM] != 'x' && bd[height][destM] != 'o')
					destM--;
			}

			// 해당 높이에 미네랄이 하나도 없을 때
			if (bd[height][destM] == 'o')
				continue;

			// 미네랄 있을 때 파괴되었기 때문에 .로 변경
			bd[height][destM] = '.';

			// bfs로 바로 위에 있던 미네랄부터 탐색
			// 만약 연결된 미네랄들이 부웅 떠있을 경우 낙하

			// 좌측에서 부셔졌을 경우 상 우 하 중 하나에 분리된 클러스터가 존재할 확률이 있음
			// 3가지 경우를 다 q에 추가해서 검사하며 검사해야함

			// 방문 노드를 확인해서 세 방향 모두 돌지않고 연결된 경우는 제외할 수 있도록
			visit = new boolean[row + 2][col + 2];

			// 세 방향으로 검사를 시도
			// 미네랄이 있어야겠지?
			for (int m = 0; m < 3; m++) {
				shst = row + 1;
				// 각각 돌면서 해당되는 범위 파악
				eachVisit = new boolean[row + 2][col + 2];
				nextY = height + dydx[breakdown[i % 2][m]][0];
				nextX = destM + dydx[breakdown[i % 2][m]][1];

				// 방문하지 않았고 미네랄일 때만 첫 시작점으로 넣음
				if (!visit[nextY][nextX] && bd[nextY][nextX] == 'x') {
					q.add(new int[] { nextY, nextX });
					visit[nextY][nextX] = true;
					eachVisit[nextY][nextX] = true;
				}

				else
					continue;

				// 미네랄이 가장 높은 높이에 위치하고 있는 경우
				dist = row - 1;

				while (!q.isEmpty()) {
					crnt = q.poll();
					crntY = crnt[0];
					crntX = crnt[1];
					for (int j = 0; j < 4; j++) {
						nextY = crntY + dydx[j][0];
						nextX = crntX + dydx[j][1];

						// 방문했다면 재방문 X
						if (visit[nextY][nextX] || bd[nextY][nextX] == 'o')
							continue;

						// 미네랄이라면 q 넣기, 방문처리, 가장 낮은 미네랄 탐색
						if (bd[nextY][nextX] == 'x') {
							q.add(new int[] { nextY, nextX });
							visit[nextY][nextX] = true;
							eachVisit[nextY][nextX] = true;
						}
					}

					// 검사할 여지가 있다면
					if (dist >= 1) {

						// 아래가 미네랄이면 검사 X
						if (bd[crntY + 1][crntX] == 'x')
							continue;

						// 아래가 바닥이라면 빈칸 0, 추후 검사할 필요 없음
						else if (bd[crntY + 1][crntX] == 'o')
							dist = 0;

						// 빈칸이라면 거리 측정
						else {
							// 해당 높이
							tmp = crntY + 2;

							// 바닥 or 미네랄 탐지 or 이전 최대 거리를 넘길 경우
							while (bd[tmp][crntX] != 'o' && bd[tmp][crntX] != 'x') {
								tmp += 1;
							}
							// 모든 낙하점의 위치를 저장
							// 외곽 지역, x

							dist = tmp - 1 - crntY;

							canFall.add(new int[] { tmp, crntX, dist });
						}
					}
				}

				// 0이면 낙하할 것이 존재하지 않음
				if (dist == 0) {
					canFall.clear();
					continue;
				}

				// 기록된 낙하점 중 낙하점이 자신 클러스터면 제외, 다른 클러스터이거나 바닥일 때만 OK, 그중 가장 짧은 것 선택
				while (!canFall.isEmpty()) {
					crnt = canFall.poll();
					crntY = crnt[0];
					crntX = crnt[1];
					if (eachVisit[crntY][crntX])
						continue;
					if (shst > crnt[2]) {
						shst = crnt[2];
						destY = crntY;
						destX = crntX;
					}
				}

				// 검사한 클러스터가 분리되었다면 shst만큼 낙하
				if (shst == row + 2)
					continue;

				canBreak = true;
				// bfs로 모든 노드 탐색하면서 아래로 내려주기
				// 첫 노드는 이전 bfs 시작 노드와 같음
				canFall.add(new int[] { height + dydx[breakdown[i % 2][m]][0], destM + dydx[breakdown[i % 2][m]][1] });
				q.add(new int[] { height + dydx[breakdown[i % 2][m]][0], destM + dydx[breakdown[i % 2][m]][1] });
				visit = new boolean[row + 2][col + 2];
				visit[height - 1][destM] = true;

				while (!canFall.isEmpty()) {
					crnt = canFall.poll();
					crntY = crnt[0];
					crntX = crnt[1];

					for (int j = 0; j < 4; j++) {
						nextY = crntY + dydx[j][0];
						nextX = crntX + dydx[j][1];

						// 방문했다면 재방문 X
						if (visit[nextY][nextX])
							continue;

						// 다음 x면 위치 변경
						if (bd[nextY][nextX] == 'x') {
							visit[nextY][nextX] = true;
							canFall.add(new int[] { nextY, nextX });
							q.add(new int[] { nextY, nextX });
						}
					}
				}
				while (!q.isEmpty()) {
					crnt = q.poll();
					bd[crnt[0]][crnt[1]] = '.';
					bd[crnt[0] + shst][crnt[1]] = 'x';
				}
				// 분리된 클러스터는 무조건 1개이기 때문에 처리를 해주면 다른 방향은 검사하지 않고 다음 막대기로 넘어감
				break;
			}
		}

		// 정답 출력
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				System.out.print(bd[i][j]);
			}
			System.out.println();
		}
	}
}