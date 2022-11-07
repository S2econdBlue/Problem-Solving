import java.io.*;
import java.util.*;

public class Main {
	static int N, L, answer = 0, cnt = 0;
	// 시작 지점 기록
	static int[][] bd;
	static int[][] origin;
	static boolean[][] chk;
	// 0: 미검사, 1:검사: 길 불가능,2: 길 가능
	static int[][] LRChk;
	static int[][][] LRSave;

	// 3 2 2 2 2 3
	// 3 2 2 2 3
	// 3 2 2 3
	static void dfs(int LR, int Pos, int cnt) {

		if (LR == 1 && Pos == N) {
			answer = Math.max(answer, cnt);
			return;
		}

		int road = 1;
		int roadHeight = 0;
		boolean isRoad = true;

		// 설치 하고
		// 가로 검사인 경우
		if (LR == 0) {
			LOOP: for (int i = 0; i < N - 1; i++) {
				// 내리막길인 경우
				if (bd[Pos][i] - bd[Pos][i + 1] == 1) {
					// 발판만큼 길이 이어져있는지 확인
					roadHeight = bd[Pos][i + 1];

					// 이후 L만큼 길이가 있는지 확인
					for (int j = 1; j <= L; j++) {
						// 길을 벗어나는 경우 길을 설치하지 못하는 것이기 때문에 탈출
						// 다른 높이가 존재하는 경우
						if (i + j == N || bd[Pos][i + j] != roadHeight) {
							isRoad = false;
							break LOOP;
						}

					}
					// 길을 놓을 수 있기 때문에 체크
//					for (int j = 1; j <= L; j++) {
//						chk[Pos][i + j] = true;
//					}
					// L만큼 길을 설치한 상태이기 때문에 다음 것 확인
					i += L - 1;
					road = 0;
				}
				// 오르막 길
				else if (bd[Pos][i] - bd[Pos][i + 1] == -1) {
					// 이전에 평탄화된 길이 L만큼 존재하는 경우
					if (road >= L) {
						// 현재 자리부터 L만큼 이전까지
//						for (int j = 0; j < L; j++) {
//							chk[Pos][i - j] = true;
//						}
					}
					// 충분한 길이 없으면 벗어남
					else {
						isRoad = false;
						break;
					}
					road = 1;
				}
				// 단차 없는 경우
				else if (bd[Pos][i] - bd[Pos][i + 1] == 0) {
					road++;
				}
				// 단차 2 이상
				else {
					isRoad = false;
					break;
				}
			}
		}
		// 세로 검사인 경우
		else {
			LOOP: for (int i = 0; i < N - 1; i++) {
				if (bd[i][Pos] - bd[i + 1][Pos] == 1) {
					roadHeight = bd[i + 1][Pos];
					for (int j = 1; j <= L; j++) {
						if (i + j == N || bd[i + j][Pos] != roadHeight) {
							isRoad = false;
							break LOOP;
						}
					}
//					for (int j = 1; j <= L; j++)
//						chk[i + j][Pos] = true;
					i += L - 1;
					road = 0;
				} else if (bd[i][Pos] - bd[i + 1][Pos] == -1) {
					if (road >= L) {
//						for (int j = 0; j < L; j++) {
//							chk[i - j][Pos] = true;
//						}
					} else {
						isRoad = false;
						break;
					}
					road = 1;
				} else if (bd[i][Pos] - bd[i + 1][Pos] == 0)
					road++;
				else {
					isRoad = false;
					break;
				}
			}
		}

		// 현재 길이 된다면
		if (isRoad) {
			if (LR == 0) {
				if (Pos + 1 == N)
					dfs(1, 0, cnt + 1);
				else
					dfs(0, Pos + 1, cnt + 1);
			}
			// LR==1
			else
				dfs(1, Pos + 1, cnt + 1);

			// 원 상태로 복구
//			for (int i = 0; i < N; i++) {
//				bd[Pos][i] = origin[Pos][i];
//			}
		}

		// 길이 되지 않거나 그냥 길을 만들지 않는 경우
//		if (LR == 0) {
//			if (Pos + 1 == N)
//				dfs(1, 0, cnt);
//			else
//				dfs(0, Pos + 1, cnt);
//		} else {
//			dfs(1, Pos + 1, cnt);
//		}
	}

	// case1. 설치 가능한 경우
	// case2. 설치 불가능해서 설치 안하는 경우
	// case3. 설치 가능하지만 설치 안하는 경우
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		origin = new int[N][N];
		bd = new int[N][N];
		chk = new boolean[N][N];
		LRChk = new int[2][N];
		LRSave = new int[2][N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				bd[i][j] = origin[i][j] = Integer.parseInt(st.nextToken());
//				System.out.println(bd[i][j]);
			}
		}

		int road = 1;
		int roadHeight = 0;
		boolean isRoad = true;
		// 가로, 세로
		for (int LR = 0; LR < 2; LR++) {
			if (LR == 0) {
				// N개
				for (int Pos = 0; Pos < N; Pos++) {
					road = 1;
					roadHeight = 0;
					isRoad = true;
					// 0~N-1개 검사
					LOOP: for (int i = 0; i < N - 1; i++) {
						// 내리막길인 경우
						if (bd[Pos][i] - bd[Pos][i + 1] == 1) {
							// 발판만큼 길이 이어져있는지 확인
							roadHeight = bd[Pos][i + 1];

							// 이후 L만큼 길이가 있는지 확인
							for (int j = 1; j <= L; j++) {
								// 길을 벗어나는 경우 길을 설치하지 못하는 것이기 때문에 탈출
								// 다른 높이가 존재하는 경우
								if (i + j == N || bd[Pos][i + j] != roadHeight) {
									isRoad = false;
									break LOOP;
								}

							}
							// 길을 놓을 수 있기 때문에 체크
//							for (int j = 1; j <= L; j++) {
//								chk[Pos][i + j] = true;
//							}
							// L만큼 길을 설치한 상태이기 때문에 다음 것 확인
							i += L - 1;
							road = 0;
						}
						// 오르막 길
						else if (bd[Pos][i] - bd[Pos][i + 1] == -1) {
							// 이전에 평탄화된 길이 L만큼 존재하는 경우
							if (road >= L) {
								// 현재 자리부터 L만큼 이전까지
//								for (int j = 0; j < L; j++) {
//									chk[Pos][i - j] = true;
//								}
							}
							// 충분한 길이 없으면 벗어남
							else {
								isRoad = false;
								break;
							}
							road = 1;
						}
						// 단차 없는 경우
						else if (bd[Pos][i] - bd[Pos][i + 1] == 0) {
							road++;
						}
						// 단차 2 이상
						else {
							isRoad = false;
							break;
						}
					}
					if (isRoad)
						answer++;
				}
			} else {
				for (int Pos = 0; Pos < N; Pos++) {
					road = 1;
					roadHeight = 0;
					isRoad = true;

					LOOP: for (int i = 0; i < N - 1; i++) {
						if (bd[i][Pos] - bd[i + 1][Pos] == 1) {
							roadHeight = bd[i + 1][Pos];
							for (int j = 1; j <= L; j++) {
								if (i + j == N || bd[i + j][Pos] != roadHeight) {
									isRoad = false;
									break LOOP;
								}
							}
//							for (int j = 1; j <= L; j++)
//								chk[i + j][Pos] = true;
							i += L - 1;
							road = 0;
						} else if (bd[i][Pos] - bd[i + 1][Pos] == -1) {
							if (road >= L) {
//								for (int j = 0; j < L; j++) {
//									chk[i - j][Pos] = true;
//								}
							} else {
								isRoad = false;
								break;
							}
							road = 1;
						} else if (bd[i][Pos] - bd[i + 1][Pos] == 0)
							road++;
						else {
							isRoad = false;
							break;
						}
					}
					if (isRoad)
						answer++;
				}
				
			}
		}
//		dfs(0, 0, 0);
		System.out.println(answer);
	}
}
