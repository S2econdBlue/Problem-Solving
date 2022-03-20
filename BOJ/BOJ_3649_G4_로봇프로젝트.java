package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ_3649_G4_로봇프로젝트 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		Scanner r = new Scanner(System.in);
		String Swidth;
		while (br.ready()) {
			int width = Integer.parseInt(br.readLine());
			int legoCnt = Integer.parseInt(br.readLine());
			int[] legoArr = new int[legoCnt];
			for (int i = 0; i < legoCnt; i++)
				legoArr[i] = Integer.parseInt(br.readLine());

			if (legoCnt == 0 || legoCnt == 1) {
				sb.append("danger").append("\n");
				continue;
			}
			Arrays.sort(legoArr);
			boolean isChecked = false;
			for (int i = 0; i < legoCnt - 1; i++) {
				int left = i + 1;
				int right = legoCnt - 1;
				int destNum = Math.abs(width * 10000000 - legoArr[i]);
				while (true) {
					if (left > right)
						break;
					int mid = (left + right) / 2;
					if (legoArr[mid] == destNum) {
						sb.append("yes ").append(legoArr[i]).append(" ").append(destNum).append("\n");
						isChecked = true;
						break;
					}
					if (destNum < legoArr[mid])
						right = mid - 1;
					else if (legoArr[mid] < destNum)
						left = mid + 1;
				}
				if (isChecked)
					break;

			}
			if (!isChecked)
				sb.append("danger").append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
}
