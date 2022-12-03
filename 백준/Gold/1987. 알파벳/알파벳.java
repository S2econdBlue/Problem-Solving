import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Main {

	static char[][] board;
	static int[][] visit;
	static int max = 0;
	static int[][] dxdy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int sizeY;
	static int sizeX;

	static void dfs(int y, int x, int bit, int cnt) {

		visit[y][x] = bit;
		max = Math.max(max, cnt);
		for (int i = 0; i < 4; i++) {
			int nY = y + dxdy[i][0];
			int nX = x + dxdy[i][1];
			if (nY < 0 || nX < 0 || nX == sizeX || nY == sizeY || (bit & (1 << board[nY][nX] - 'A')) != 0
					|| (bit | 1 << board[nY][nX] - 'A') == visit[nY][nX])
				continue;

			dfs(nY, nX, bit | 1 << board[nY][nX] - 'A', cnt + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		sizeY = Integer.parseInt(st.nextToken());
		sizeX = Integer.parseInt(st.nextToken());
		board = new char[sizeY][sizeX];
		visit = new int[sizeY][sizeX];
		for (int i = 0; i < sizeY; i++) {
			board[i] = br.readLine().toCharArray();
		}
		dfs(0, 0, 1 << board[0][0] - 'A', 1);
		sb.append(max);
		bw.write(sb.toString());
		bw.flush();
	}
}