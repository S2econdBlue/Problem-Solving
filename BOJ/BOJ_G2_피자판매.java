import java.io.*;
import java.util.*;


// 퀵 코드
// 1. 배열을 백만개 저장하고 해당 배열의 순번에 몇 개나 저장되어있는지 
public class BOJ_G2_피자판매 {
	// 한 번에 사용하는 피자 조각 수 별로 피자 값의 합을 저장
	static int[] arrA, arrB;
	// A, B 피자의 개수, 원하는 값, 출력 결과
	static int cntA, cntB, size, answer = 0;
	// 각 피자별 조각 개수별 크기의 합 계산
	static int[][][] save;

	// A 조각 수 입력
	static int[] dfsA(int A) {
		// 값을 저장할 배열
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
		// === 여기까지 입력

		// === 원형이기 때문에 뒤에 값이 앞의 값과 더해질 수 있도록 연장했음
		for (int i = cntA; i < cntA + cntA - 2; i++) {
			arrA[i] = arrA[i - cntA];
		}
		for (int i = cntB; i < cntB + cntB - 2; i++) {
			arrB[i] = arrB[i - cntB];
		}

		// [각 피자][사용한 피자 조각 수][0~cntA-1번까지 피자 합]
		save = new int[2][][];
		save[0] = new int[cntA + 1][cntA];
		save[1] = new int[cntB + 1][cntB];

		// cntA 피자 사용할 조각 별 값 구하기
		for (int i = 1; i <= cntA; i++) {
			save[0][i] = dfsA(i);
		}
		// cntB 피자 사용할 조각 별 값 구하기
		for (int j = 1; j <= cntB; j++) {
			save[1][j] = dfsB(j);
		}
		//피자별로 특정 비용이 몇 번 나왔는지 저장
		Map<Integer, Integer> mapA = new HashMap<>();
		Map<Integer, Integer> mapB = new HashMap<>();
		
		// A 단일 피자 비용별 저장
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

		// B 피자 비용별 저장
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
		
		// A만 혹은 A + B로 비용 산출 가능
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
		// B만으로 비용 산출 가능할 때
		for (int k : mapB.keySet()) {
			if (k == size) {
				answer += mapB.get(k);
			}
		}
		System.out.println(answer);
	}
}