import java.io.*;
import java.util.*;

public class Main {
	static int[] arrA, arrB;
	static int cntA, cntB, size, answer = 0;
	// 각 피자별 조각 개수별 크기의 합 계산
	static int[][][] save;

	// A 조각 수 입력
	static int[] dfsA(int A) {
		int[] slideA;
		// 전체 조각을 사용해야 할 때
		if (A == cntA) {
			slideA = new int[1];
			for (int i = 0; i < cntA; i++) {
				slideA[0] += arrA[i];
			}
		} else {
			// A조각만큼 슬라이스 생성 가능
			slideA = new int[cntA];
			// 사용할 조각만큼 우선 더해주기
			for (int i = 0; i < A; i++) {
				slideA[0] += arrA[i];
			}
			// 이후 슬라이스들을 계산
			for (int i = 1; i < cntA; i++) {
				slideA[i] = slideA[i - 1] - arrA[i - 1] + arrA[i + A - 1];
			}
		}

		return slideA;
	}

	static int[] dfsB(int B) {
		int[] slideB;
		// 전체 조각
		if (B == cntB) {
			slideB = new int[1];
			for (int i = 0; i < cntB; i++) {
				slideB[0] += arrB[i];
			}
		} else {
			slideB = new int[cntB];
			for (int i = 0; i < B; i++) {
				slideB[0] += arrB[i];
			}
			for (int i = 1; i < cntB; i++) {
				slideB[i] = slideB[i - 1] - arrB[i - 1] + arrB[i + B - 1];
			}
		}
		return slideB;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		size = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		cntA = Integer.parseInt(st.nextToken());
		cntB = Integer.parseInt(st.nextToken());

		arrA = new int[cntA + (cntA - 2)];
		arrB = new int[cntB + (cntB - 2)];

		for (int i = 0; i < cntA; i++) {
			arrA[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 0; i < cntB; i++) {
			arrB[i] = Integer.parseInt(br.readLine());
		}

		for (int i = cntA; i < cntA + cntA - 2; i++) {
			arrA[i] = arrA[i - cntA];
		}
		for (int i = cntB; i < cntB + cntB - 2; i++) {
			arrB[i] = arrB[i - cntB];
		}
		save = new int[2][][];
		save[0] = new int[cntA + 1][cntA];
		save[1] = new int[cntB + 1][cntB];

		// cntA 피자 사용할 조각
		for (int i = 1; i <= cntA; i++) {
			save[0][i] = dfsA(i);
		}
		// cntB 피자 사용할 조각
		for (int j = 1; j <= cntB; j++) {
			save[1][j] = dfsB(j);
		}
		Map<Integer, Integer> mapA = new HashMap<>();
		Map<Integer, Integer> mapB = new HashMap<>();
		// A 단일 피자 조합으로 가능할 때
		for (int i = 1; i <= cntA; i++) {
			for (int j = 0; j < cntA; j++) {
				if (mapA.containsKey(save[0][i][j])) {
					mapA.put(save[0][i][j], mapA.get(save[0][i][j]) + 1);
				} else
					mapA.put(save[0][i][j], 1);
				if (i == cntA)
					break;
			}
		}

		// B 피자
		for (int i = 1; i <= cntB; i++) {
			for (int j = 0; j < cntB; j++) {
				if (mapB.containsKey(save[1][i][j])) {
					mapB.put(save[1][i][j], mapB.get(save[1][i][j]) + 1);
				} else
					mapB.put(save[1][i][j], 1);
				if (i == cntB)
					break;
			}
		}
		for (int k : mapA.keySet()) {

			if (k == size) {
				answer += mapA.get(k);
			}

			else {
				if (mapB.containsKey(size - k)) {

					answer += mapA.get(k) * mapB.get(size - k);

				}
			}

		}

		for (int k : mapB.keySet()) {
			if (k == size) {
				answer += mapB.get(k);
			}
		}
		System.out.println(answer);
	}

}
