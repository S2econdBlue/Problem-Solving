import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int i = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());

		if (i == s && s == t) {
			System.out.println(10000+i * 1000);
		}
		else if(i == s || s == t || i == t) {
			if( i == s )
			System.out.println(1000 +i * 100);
			else if(s == t)
				System.out.println(1000 + s * 100);
			else
				System.out.println(1000 + t * 100);
		}
		else {
			System.out.println(Math.max(i, Math.max(s, t)) * 100);
		}
	}
}
