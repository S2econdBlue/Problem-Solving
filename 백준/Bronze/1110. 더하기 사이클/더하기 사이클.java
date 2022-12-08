import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int calc = N;
		int answer = 0;
		do {
			calc = calc % 10 * 10 + (calc / 10 + calc % 10) % 10;
			answer++;
		} while (calc != N);
		System.out.println(answer);
	}
}