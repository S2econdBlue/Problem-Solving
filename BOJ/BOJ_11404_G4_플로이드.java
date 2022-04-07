package BOJ;

import java.io.DataInputStream;
import java.io.IOException;

public class BOJ_11404_G4_플로이드 {
	static class Reader {
		int bfs = 1 << 16;
		byte[] buffer = new byte[bfs];
		int bufferPos = 0, bufferState = 0;
		DataInputStream dis = new DataInputStream(System.in);

		byte read() throws IOException {
			if (bufferPos == bufferState) {
				bufferState = dis.read(buffer, bufferPos = 0, bfs);
				if (bufferState == -1)
					buffer[0] = -1;
			}
			return buffer[bufferPos++];

		}

		int nextInt() throws IOException {
			int rtn = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do
				rtn = rtn * 10 + c - '0';
			while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -rtn;
			return rtn;
		}
	}

	public static void main(String[] args) throws IOException {
		Reader r = new Reader();
		int nodeCnt = r.nextInt();
		int busCnt = r.nextInt();
		int[][] city = new int[nodeCnt + 1][nodeCnt + 1];
		for (int i = 0; i < busCnt; i++) {
			int start = r.nextInt();
			int end = r.nextInt();
			int weight = r.nextInt();
			if (city[start][end] != 0) 
				city[start][end] = Math.min(city[start][end], weight);
			else
				city[start][end] = weight;
		}

		for (int k = 1; k <= nodeCnt; k++) {
			for (int i = 1; i <= nodeCnt; i++) {
				for (int j = 1; j <= nodeCnt; j++) {
					// 자기 자신 제외
					if (i == j)
						continue;
					// 두 값이 경유지를 거쳐 이어져있을 때
					if ((city[i][k] != 0 && city[k][j] != 0)) {
						// 두 값이 바로 이어져있기도 하는 경우
						if (city[i][j] != 0)
							city[i][j] = Math.min(city[i][j], city[i][k] + city[k][j]);
						// 두 값이 바로 이어져있지 않을 때
						else
							city[i][j] = city[i][k] + city[k][j];
					}
				}
			}
		}
		for (int i = 1; i <= nodeCnt; i++) {
			for (int j = 1; j <= nodeCnt; j++) {
				System.out.print(city[i][j] + " ");
			}
			System.out.println();
		}
	}
}
