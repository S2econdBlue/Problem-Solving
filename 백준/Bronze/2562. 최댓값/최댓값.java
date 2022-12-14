import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int max = 0;
		int pos = 0;
		int next = 0;
		for (int i = 1; i <= 9; i++) {
			next = Integer.parseInt(br.readLine());
			if (next > max) {
				pos = i;
				max = next;
			}
		}

		System.out.println(max+"\n"+pos);
	}
}