import java.io.*;
import java.util.*;

class Main {
	static Map<Integer, Integer> map = new HashMap<>();
	static int next;
	static int[] pre, in, post;
	static StringBuilder sb = new StringBuilder();

	// 트리에 위치, 왼쪽, 오른
	static void post(int crnt, int pos, int left, int right) {
		if (left >= right) {
			return;
		}
		next++;
		// 왼쪽
		post(next, pos * 2, left, map.get(pre[crnt]));

		// 오른쪽
		post(next, pos * 2 + 1, map.get(pre[crnt]) + 1, right);

		sb.append(pre[crnt]).append(" ");
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());
			pre = new int[N];
			in = new int[N];
			next = 0;
			st = new StringTokenizer(br.readLine());

			// 전위
			for (int j = 0; j < N; j++) {
				pre[j] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());

			// 중위
			for (int j = 0; j < N; j++) {
				in[j] = Integer.parseInt(st.nextToken());
				map.put(in[j], j);
			}
			post(next, 1, 0, N);
			map.clear();
			sb.append("\n");

		}
		System.out.println(sb);
	}
}