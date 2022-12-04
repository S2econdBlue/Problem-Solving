import java.util.*;
import java.io.*;

//1. 테케 다 통과해서 행복했다가 시간초과 받고 절망..
//2. DP방식
class Main {
	static int row, col;
	static int[][] bd;
	static boolean[][] visit;
	// 0 : 상, 1 : 우, 2 : 하, 3: 좌
	static int[][][] dp;
	static int[][] dydx = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int maxDpth = 0;

	static int dfs(int crntY, int crntX) {
		int nextY, nextX, crntV = bd[crntY][crntX], returned;

		for (int i = 0; i < 4; i++) {
			if (maxDpth == -1)
				return -1;

			nextY = crntY + dydx[i][0] * crntV;
			nextX = crntX + dydx[i][1] * crntV;

			// 범위 밖일 경우 게임 종료
			if (nextY >= row || nextX >= col || nextY < 0 || nextX < 0) {
				dp[crntY][crntX][i] = 1;
				continue;
			}
			//다음 자리가 구멍이라면
			if(bd[nextY][nextX] == 0) {
				dp[crntY][crntX][i] = 1;
				continue;
			}
			
			// 이미 방문한 곳이 있다면 순환 가능
			if (visit[nextY][nextX]) {
				return -1;
			}

			// 방문 이력이 없는 경우
			if (dp[crntY][crntX][i] == 0) {
				visit[nextY][nextX] = true;
				returned = dfs(nextY, nextX);
				if (returned == -1)
					return -1;
				
				visit[nextY][nextX] = false;
				dp[crntY][crntX][i] = returned + 1;
			}
			// 방문이력이 있는 경우
		}

		return Math.max(dp[crntY][crntX][0],
				Math.max(dp[crntY][crntX][1], Math.max(dp[crntY][crntX][2], dp[crntY][crntX][3])));

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		bd = new int[row][col];
		dp = new int[row][col][4];
		visit = new boolean[row][col];
		char[] each;
		for (int i = 0; i < row; i++) {
			each = br.readLine().toCharArray();
			for (int j = 0; j < col; j++) {
				if (each[j] == 'H') {
					bd[i][j] = 0;
					continue;
				}
				bd[i][j] = each[j] - '0';
			}
		}
		System.out.println(dfs(0, 0));
	}
}
