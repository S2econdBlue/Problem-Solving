import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[][] dydx = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int[][] bd = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                bd[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int clean = 0, nY = 0, nX = 0, tmpD = 0, turnCnt = 0;
        boolean isNotCleaned = false;

        while (true) {
            // 1. 현재 위치 청소
            if (bd[r][c] == 0) {
                bd[r][c] = 2;
                clean++;
            }


            // 2-3,4. 확인
            tmpD = -1;
            isNotCleaned = false;

            // 4방향 중 청소 가능한 곳을 탐색
            for (int i = 0; i < 4; i++) {
                tmpD = tmpD + 1;
                nY = r + dydx[tmpD][0];
                nX = c + dydx[tmpD][1];

                // 해당 방향은 청소가 가능한 경우 벗어나기
                if (bd[nY][nX] == 0) {
                    isNotCleaned = true;
                    break;
                }
            }
            // 어느 방향으로 청소가 불가능한 경우
            if (!isNotCleaned) {
                // 뒷 방향
                tmpD = (d + 2) % 4;
                nY = r + dydx[tmpD][0];
                nX = c + dydx[tmpD][1];
                // 뒤도 벽인 경우
                if (bd[nY][nX] == 1) {
                    System.out.println(clean);
                    return;
                }
                // 후진 가능한 경우
                else {
                    r = nY;
                    c = nX;
                    continue;
                }
            }

            // 2번 반복
            while (true) {
                d--;
                if (d < 0)
                    d = 3;
                nY = r + dydx[d][0];
                nX = c + dydx[d][1];

                // 2-2. 왼쪽 방향 청소 공간 X => 왼쪽 방향 회전, 2-1번으로
                // 벽인 경우만 생각하면 되는건가??
                if (bd[nY][nX] == 0) {
                    r = nY;
                    c = nX;
                    break;
                }
            }
        }
    }
}
