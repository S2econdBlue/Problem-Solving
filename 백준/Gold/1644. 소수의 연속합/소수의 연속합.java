import java.io.DataInputStream;
import java.io.IOException;

class Main {
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

	static boolean sosu(int ori) {
		for (int i = 3; i <= Math.sqrt(ori); i += 2) {
			if (ori % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		Reader br = new Reader();
		int N = br.nextInt();

		long[] arr = new long[400000];

		int cnt = 1;
		arr[0] = 2;
		for (int i = 3; i <= N+100; i += 2) {
			if (sosu(i)) {
				arr[cnt] = i;
				cnt++;
			}
		}
		// 슬라이딩 윈도우
		int sum = 2, left = 0, right = 0, ans = 0;

		while (true) {
			if (sum == N) {
				ans += 1;
				sum += arr[++right];
				sum -= arr[left++];
			}
			else if (sum < N) {
				sum += arr[++right];
			} else if (sum > N) {
				sum -= arr[left++];
			}
			
			if(arr[left] > N)
				break;
		}
		System.out.println(ans);
	}
}
