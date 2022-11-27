import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int l, r;
		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			l = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());

			sb.append("Case #").append(i).append(": ").append(l).append(" + ").append(r).append(" = ").append(l + r)
					.append("\n");
		}
		System.out.println(sb);
	}
}