import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		float[] arr = new float[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		float maxScore = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			maxScore = Math.max(maxScore, arr[i]);
		}
		float answer = 0;
		for (int i = 0; i < N; i++) {
			answer += arr[i] / maxScore * 100;
		}
		System.out.println(answer / N);
	}
}