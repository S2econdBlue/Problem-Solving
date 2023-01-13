import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		// y, x, 높이[0번째: 해당 좌표 최고 높이] => 0: 번호, 1: 방향
		int[][][][] stack = new int[N][N][K + 1][2];
		int[][] bd = new int[N][N];
		// 각 번호 => y, x, 방향, z
		int[][] eachPosDir = new int[K + 1][4];
		int[][] dydx = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				bd[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int y, x, dir;
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			// 행, 열, 이동
			y = Integer.parseInt(st.nextToken()) - 1;
			x = Integer.parseInt(st.nextToken()) - 1;
			dir = Integer.parseInt(st.nextToken());
			stack[y][x][0][0] = 1; // 해당 자리 최고 높이
			stack[y][x][1][0] = i; // 1번째 높이 번호
			if (dir == 1) {
				stack[y][x][1][1] = 1; // 방향
				eachPosDir[i][2] = 1;
			} else if (dir == 2) {
				stack[y][x][1][1] = 3;
				eachPosDir[i][2] = 3;
			} else if (dir == 3) {
				stack[y][x][1][1] = 0;
				eachPosDir[i][2] = 0;
			} else {
				stack[y][x][1][1] = 2;
				eachPosDir[i][2] = 2;
			}
			eachPosDir[i][0] = y;
			eachPosDir[i][1] = x;

			eachPosDir[i][3] = 1; // z

		}

		int cY = 0, cX = 0, cDir = 0, nY = 0, nX = 0, nDir = 0, fromStart = 0, toStart = 0, exchStkSz = 0,
				targetNum = 0;
		for (int i = 1; i <= 1000; i++) {
			for (int j = 1; j <= K; j++) {
				cY = eachPosDir[j][0];
				cX = eachPosDir[j][1];
				cDir = eachPosDir[j][2];
				nY = cY + dydx[cDir][0];
				nX = cX + dydx[cDir][1];

				// 외곽, 파랑 => 방향 전환 and 한칸 전진
				if (nY < 0 || nY == N || nX < 0 || nX == N || bd[nY][nX] == 2) {
					eachPosDir[j][2] = (cDir + 2) % 4;
					nY = cY + dydx[eachPosDir[j][2]][0];
					nX = cX + dydx[eachPosDir[j][2]][1];

					// 그래도 파란색이라면
					if (nY < 0 || nY == N || nX < 0 || nX == N || bd[nY][nX] == 2)
						continue;

					// 파란색이 아닌 경우 밖에서 빨강, 흰색 과정 밟기
				}
				fromStart = stack[cY][cX][0][0]; // 옮기려는 곳의 최고 높이
				exchStkSz = fromStart - eachPosDir[j][3] + 1; // 옮기려는 곳 최고 높이 - 현재 말의 높이 = 옮기려는 개수
				toStart = stack[nY][nX][0][0]; // 옮겨지는 곳의 최고 높이
				// 빨강 == 이동하되, 순서를 뒤집은 채로
				if (bd[nY][nX] == 1) {

					// 이동하려는 곳에서 이동하려는 번호의 높이까지만큼 반복
					for (int k = 0; k < exchStkSz; k++) {
						// 옮기려는 스택의 데이터 갱신
						eachPosDir[stack[cY][cX][fromStart - k][0]][0] = nY;
						eachPosDir[stack[cY][cX][fromStart - k][0]][1] = nX;
						eachPosDir[stack[cY][cX][fromStart - k][0]][3] = toStart + k + 1;
						// 쌓아주기
						stack[nY][nX][toStart + k + 1][0] = stack[cY][cX][fromStart - k][0];
						stack[nY][nX][toStart + k + 1][1] = stack[cY][cX][fromStart - k][1];
						// 이전 자리 초기화
						stack[cY][cX][fromStart - k][0] = 0;
						stack[cY][cX][fromStart - k][1] = 0;
					}

				}
				// 흰색
				else {
					toStart += 1;
					// 옮기려는 곳의 현재 높이부터 최고 높이까지 이동
					for (int k = eachPosDir[j][3]; k <= fromStart; k++) {
						// 옮기려는 스택의 데이터 갱신
						eachPosDir[stack[cY][cX][k][0]][0] = nY;
						eachPosDir[stack[cY][cX][k][0]][1] = nX;
						eachPosDir[stack[cY][cX][k][0]][3] = toStart;
						// 쌓아주기
						stack[nY][nX][toStart][0] = stack[cY][cX][k][0];
						stack[nY][nX][toStart][1] = stack[cY][cX][k][1];
						// 이전 자리 초기화
						stack[cY][cX][k][0] = 0;
						stack[cY][cX][k][1] = 0;

						toStart++;
					}
				}

				// 스택 크기 교환
				stack[nY][nX][0][0] += exchStkSz;
				stack[cY][cX][0][0] -= exchStkSz;

				if (stack[nY][nX][0][0] >= 4) {
					System.out.println(i);
					return;
				}
			}
		}
		System.out.println(-1);
	}
}