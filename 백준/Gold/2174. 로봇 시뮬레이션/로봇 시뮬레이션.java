import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int col = Integer.parseInt(st.nextToken());
		int row = Integer.parseInt(st.nextToken());

		// 북, 동, 남, 서
		int dydx[][] = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		int[][][] bd = new int[row + 1][col + 1][2];

		st = new StringTokenizer(br.readLine());

		int robotsCnt = Integer.parseInt(st.nextToken());
		int order = Integer.parseInt(st.nextToken());
		int[][] robotsPos = new int[robotsCnt + 1][2];
		int x = 0, y = 0;
		char dir;
		for (int i = 1; i <= robotsCnt; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = row - (Integer.parseInt(st.nextToken()) - 1);

			robotsPos[i][0] = y;
			robotsPos[i][1] = x;

			bd[y][x][0] = i;
			dir = st.nextToken().charAt(0);
			if (dir == 'N')
				bd[y][x][1] = 0;
			else if (dir == 'E')
				bd[y][x][1] = 1;
			else if (dir == 'S')
				bd[y][x][1] = 2;
			else
				bd[y][x][1] = 3;
		}
		int robotNum, repeatCnt, nY, nX, robotDir;
		char RO;
		for (int i = 0; i < order; i++) {
			st = new StringTokenizer(br.readLine());
			robotNum = Integer.parseInt(st.nextToken());
			RO = st.nextToken().charAt(0);
			repeatCnt = Integer.parseInt(st.nextToken());

			if (RO == 'L') {
				bd[robotsPos[robotNum][0]][robotsPos[robotNum][1]][1] += -repeatCnt;
				while (bd[robotsPos[robotNum][0]][robotsPos[robotNum][1]][1] < 0)
					bd[robotsPos[robotNum][0]][robotsPos[robotNum][1]][1] += 4;
			} else if (RO == 'R') {
				bd[robotsPos[robotNum][0]][robotsPos[robotNum][1]][1] += repeatCnt;
				bd[robotsPos[robotNum][0]][robotsPos[robotNum][1]][1] %= 4;
			}

			// 기존 방향 0 ~ 3
			else {
				robotDir = bd[robotsPos[robotNum][0]][robotsPos[robotNum][1]][1];
				nY = robotsPos[robotNum][0];
				nX = robotsPos[robotNum][1];
				// 명령을 횟수만큼 수행
				for (int j = 0; j < repeatCnt; j++) {
					nY += dydx[robotDir][0];
					nX += dydx[robotDir][1];

					if (nY == 0 || nY > row || nX == 0 || nX > col) {
						System.out.println("Robot " + robotNum + " crashes into the wall");
						return;
					} else if (bd[nY][nX][0] != 0) {
						System.out.println("Robot " + robotNum + " crashes into robot " + bd[nY][nX][0]);
						return;
					}
				}
				// 최신 자리에 넣기
				bd[nY][nX][0] = bd[robotsPos[robotNum][0]][robotsPos[robotNum][1]][0];
				bd[nY][nX][1] = bd[robotsPos[robotNum][0]][robotsPos[robotNum][1]][1];
				// 이전 자리 정보 삭제
				bd[robotsPos[robotNum][0]][robotsPos[robotNum][1]][0] = 0;
				bd[robotsPos[robotNum][0]][robotsPos[robotNum][1]][1] = 0;
				// 로봇별 위치 저장 최신화
				robotsPos[robotNum][0] = nY;
				robotsPos[robotNum][1] = nX;
			}

		}
		System.out.println("OK");
	}
}