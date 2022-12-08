import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int A = 0, B = 0;
		try {
			while (true) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				A = Integer.parseInt(st.nextToken());
				B = Integer.parseInt(st.nextToken());
				System.out.println(A + B);
			}
		} catch (Exception e) {

		}

	}
}