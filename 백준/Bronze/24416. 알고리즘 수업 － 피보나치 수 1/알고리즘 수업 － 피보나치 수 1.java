import java.io.*;
import java.util.*;

class Main {
	static int answer = 0;

	static int fib(int N) {

		if (N == 1 || N == 2) {
			answer++;
			return 1;
		}

		return (fib(N - 1) + fib(N - 2));
	}

	static int[] f;

	static int fibonacci(int N) {
		f[1] = f[2] = 1;
		for (int i = 3; i <= N; i++) {
			answer++;
			f[i] = f[i - 1] + f[i - 2];
		}
		return f[N];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		f = new int[N + 1];

		fib(N);
		System.out.print(answer+" ");
		answer = 0;
		fibonacci(N);
		System.out.println(answer);
	}
}