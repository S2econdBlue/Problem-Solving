import java.io.*;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a;
		for (int i = 0; i < 6; i++) {
			a = Integer.parseInt(st.nextToken());
			if (i == 0 || i == 1) {
				bw.write(Integer.toString(1 - a));
				bw.write(" ");
			} else if (i == 2 || i == 3 || i == 4) {
				bw.write(Integer.toString(2 - a));
				bw.write(" ");
			} else if (i == 5) {
				bw.write(Integer.toString(8 - a));
			}
		}
		bw.flush();
	}
}