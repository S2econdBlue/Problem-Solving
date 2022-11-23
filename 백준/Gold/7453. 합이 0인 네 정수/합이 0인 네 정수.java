import java.io.*;
import java.util.*;

public class Main {
	// 네 배열 중 하나씩 합쳤을 때 0이 나오는 쌍 개수
	// 완전 탐색은 시간 초과 확실
	// DP 방식으로 저장하는 방식이 필요할 것 같은데

	// 1차: 모든 조합을 확인 후 나오는 조합을 저장, 겹칠 수 있기 때문에 map에 저장

	// 2차: RGB 거리같은건가?
	static int getLowerPos(long[] arr, long V) {
		int left = 0, right = arr.length;
		while (left < right) {
			int mid = left + (right - left) / 2;
			// V보다 작거나 같을 때
			if (arr[mid] >= V)
				right = mid;
			// V보다 큰 값인 경우
			else
				left = mid + 1;
		}
		return left;
	}

	static int getUpperPos(long[] arr, long V) {
		int left = 0, right = arr.length;
		while (left < right) {
			int mid = left + (right - left) / 2;
			// V보다 작거나 같을 때
			if (V >= arr[mid])
				left = mid + 1;

			// V보다 클 때
			else
				right = mid;
		}
		return left;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[][] bd = new int[n][4];
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				bd[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		long[] arr1 = new long[n * n];
		long[] arr2 = new long[n * n];

		// 4000 * 4000
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arr1[cnt] = bd[i][0] + bd[j][1];
				cnt++;
			}
		}
		cnt = 0;
		// 4000 * 4000
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arr2[cnt] = bd[i][2] + bd[j][3];
				cnt++;
			}
		}

		Arrays.sort(arr1);
		Arrays.sort(arr2);

		// arr1의 한 값에 대해 arr2에서 lower,upper bound로 개수를 구하고 곱
		// arr1의 그 다음 값에 대해 동일한 작업을 반복
		int arr1Pos = 0, arr2Pos = 0;
		long answer = 0;
		int arr1Right = 0;
		while (arr1Right < n * n) {
			// arr1의 lower, upper 구하기
			long arr1Left = getLowerPos(arr1, arr1[arr1Pos]);
			arr1Right = getUpperPos(arr1, arr1[arr1Pos]);
			long arr1Size = arr1Right - arr1Left;
			long arr2Right = getUpperPos(arr2, -arr1[arr1Pos]);
			long arr2Left = getLowerPos(arr2, -arr1[arr1Pos]);
			answer += arr1Size * (arr2Right - arr2Left);
			arr1Pos = arr1Right;
		}
		System.out.println(answer);
	}
}
