import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static char[][] rotate(char[][] bd) {
		int bdY = bd.length;
		int bdX = bd[0].length;

		char[][] tmp = new char[bdX][bdY];
		for (int i = 0; i < bd.length; i++) {
			for (int j = 0; j < bd[0].length; j++) {
				tmp[bdX - 1 - j][i] = bd[i][j];
			}
		}
		return tmp;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int fY = Integer.parseInt(st.nextToken());
		int fX = Integer.parseInt(st.nextToken());

		char[][] f = new char[fY][fX];
		char[] tmp;
		for (int i = 0; i < fY; i++) {
			tmp = br.readLine().toCharArray();
			for (int j = 0; j < fX; j++) {
				f[i][j] = tmp[j];
			}
		}
		st = new StringTokenizer(br.readLine());
		int sY = Integer.parseInt(st.nextToken());
		int sX = Integer.parseInt(st.nextToken());
		char[][] s = new char[sY][sX];
		for (int i = 0; i < sY; i++) {
			tmp = br.readLine().toCharArray();
			for (int j = 0; j < sX; j++) {
				s[i][j] = tmp[j];
			}
		}
		int maxY = Integer.MAX_VALUE, maxX = Integer.MAX_VALUE;
		int bdY, bdX;
		char[][] bd;
		boolean isFiled = false;
		int answer = Integer.MAX_VALUE;
		// 3번만 회전하면 전체 검사 가능
		for (int i = 0; i < 4; i++) {
			// bd의 현재 높이는 정해진 f의 높이 + s가 반복하면서 놓일 수 있을만큼의 높이
			bdY = sY * 2 + fY;
			// bd의 현재 너비는 정해진 f의 너비 + s가 반복하면서 놓일 수 있을만큼의 너비
			bdX = sX * 2 + fX;
			isFiled = false;

			// 회전 전까지 고정
			bd = new char[bdY][bdX];

			for (int i2 = 0; i2 < fY; i2++) {
				for (int j2 = 0; j2 < fX; j2++) {
					// f액자는 s액자의 가로, 세로, 길이부터 놓이게 됨
					bd[sY + i2][sX + j2] = f[i2][j2];
				}
			}

			// s가 가능한 범위
			// 좌상, 우하까지 놓이는 걸 검사
			for (int j = 0; j < fY + sY + 1; j++) {
				for (int k = 0; k < fX + sX + 1; k++) {
					
					isFiled = false;
					// s입력
					LOOP: for (int i2 = 0; i2 < sY; i2++) {
						for (int j2 = 0; j2 < sX; j2++) {
							// 동일한 자리에 두번째 액자가 겹치는 경우
							if (bd[j + i2][k + j2] == '1' && s[i2][j2] == '1') {
								isFiled = true;
								break LOOP;
							}
						}
					}

					// 겹치지 않은 경우
					if (!isFiled) {
						maxY = Math.max(sY + fY, j + sY) - Math.min(sY, j);
						maxX = Math.max(sX + fX, k + sX) - Math.min(sX, k);
						answer = Math.min(answer, maxY * maxX);
						System.out.print("");
					}
				}
			}

			s = rotate(s);
			sY = s.length;
			sX = s[0].length;
		}
		System.out.println(answer);
	}
}
