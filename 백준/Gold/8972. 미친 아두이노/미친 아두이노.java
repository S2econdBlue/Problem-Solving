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
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		// 아두이노 위치 저장
		int[][] bd = new int[R][C];
		char[] tmpCharArr;
		// 지도 입력
		List<int[]> tmp = new LinkedList<>();
		int[] jong = new int[2];
		int nextNum = 2;
		for (int i = 0; i < R; i++) {
			tmpCharArr = br.readLine().toCharArray();

			for (int j = 0; j < C; j++) {
				// 종수
				if (tmpCharArr[j] == 'I') {
					jong[0] = i;
					jong[1] = j;
					bd[i][j] = 1;
				}
				// 미친 아두이노
				else if (tmpCharArr[j] == 'R') {
					bd[i][j] = nextNum;
					nextNum++;
					tmp.add(new int[] { i, j, 0 });
				}
			}
		}

		// y, x, 존재유무
		int[][] robots = new int[tmp.size() + 2][3];

		for (int i = 2; i < tmp.size() + 2; i++) {
			robots[i] = tmp.get(i - 2);
		}

		// 명령문 구분자
		String order = br.readLine();
		int[] orderList = new int[order.length()];
		for (int i = 0; i < order.length(); i++) {
			orderList[i] = order.charAt(i) - '0';
		}
		// 좌하 하 우하
		// 좌 중 우
		// 좌상 상 우상
		int[][] dydx = new int[][] { //
				{ 0, 0 }, //
				{ 1, -1 }, { 1, 0 }, { 1, 1 }, //
				{ 0, -1 }, { 0, 0 }, { 0, 1 }, //
				{ -1, -1 }, { -1, 0 }, { -1, 1 } };

		int jongNextY, jongNextX, rNY, rNX, another = 0;
		int[][] tmpBoard;
		Queue<int[]> xList = new LinkedList<>();
		int[] crntR;

		// 게임 실행
		int shortest, shortestDir = 0;
		for (int i = 0; i < orderList.length; i++) {
			// 1. 종수의 다음 위치
			jongNextY = jong[0] + dydx[orderList[i]][0];
			jongNextX = jong[1] + dydx[orderList[i]][1];

			// 2. 빈칸이 아니고 자기 자신이 아닌 경우
			if (bd[jongNextY][jongNextX] >= 2) {
				System.out.println("kraj " + (i + 1));
				return;
			}
			// 종수 위치 최신화
			jong[0] = jongNextY;
			jong[1] = jongNextX;
			tmpBoard = new int[R][C];

			tmpBoard[jong[0]][jong[1]] = 1;

			// 로봇 순회
			for (int j = 2; j < robots.length; j++) {
				// 이미 파괴된 경우
				if (robots[j][2] == 1)
					continue;

				// 초기화
				shortest = Integer.MAX_VALUE;
				// 로봇 구방 탐색
				for (int k = 1; k <= 9; k++) {
					if (k == 5)
						continue;
					rNY = robots[j][0] + dydx[k][0];
					rNX = robots[j][1] + dydx[k][1];

					// 가장 짧아지는 방향 탐색
					if (Math.abs(jong[0] - rNY) + Math.abs(jong[1] - rNX) < shortest) {
						shortest = Math.abs(jong[0] - rNY) + Math.abs(jong[1] - rNX);
						shortestDir = k;
					}
				}

				// 3. 로봇 위치 최신화
				robots[j][0] += dydx[shortestDir][0];
				robots[j][1] += dydx[shortestDir][1];

				// 4. 종수와 겹칠 때
				if (tmpBoard[robots[j][0]][robots[j][1]] == 1) {
					System.out.println("kraj " + (i + 1));
					return;
				}
				// 빈칸인 경우
				else if (tmpBoard[robots[j][0]][robots[j][1]] == 0) {
					tmpBoard[robots[j][0]][robots[j][1]] = j;
				}
				// 5. 다른 로봇들과 겹쳐서 파괴되는 자리인 경우
				else if (tmpBoard[robots[j][0]][robots[j][1]] == -1) {
					robots[j][2] = 1;
				}
				// 다른 아두이노와 겹치는 경우
				else {
					// 이전에 그 자리에 이동한 아두이노 번호
					another = tmpBoard[robots[j][0]][robots[j][1]];
					tmpBoard[robots[j][0]][robots[j][1]] = -1;// 해당 자리 터지는 표시
					// 두 로봇 사용 불가 처리
					xList.add(new int[] { robots[j][0], robots[j][1] });
					robots[another][2] = 1;
					robots[j][2] = 1;
				}
				System.out.print("");
			}

			while (!xList.isEmpty()) {
				crntR = xList.poll();
				tmpBoard[crntR[0]][crntR[1]] = 0;
			}

			// 보드 갱신
			for (int j = 0; j < R; j++)
				bd[j] = tmpBoard[j];
		}

		for (int l = 0; l < R; l++) {
			for (int l2 = 0; l2 < C; l2++) {
				// 종수
				if (bd[l][l2] == 1)
					System.out.print('I');
				// 빈칸
				else if (bd[l][l2] == 0)
					System.out.print('.');
				// 미친 아두이노
				else
					System.out.print('R');
			}
			System.out.println();
		}
	}
}