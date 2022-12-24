import java.io.*;
import java.util.*;

public class Main {
    // 서:1, 북:2, 동:4, 남:8
    static int[][] dydx = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    static int[][] bd;
    static int[][] apart;
    static boolean[][] chk;
    // row, col
    static int M, N, cnt = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // col, row
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        bd = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                bd[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        chk = new boolean[M][N];
        apart = new int[M][N];

        Queue<int[]> q = new LinkedList<>();
        int[] crnt;
        int nY, nX;
        List<Integer> roomData = new ArrayList<>();
        int roomCnt = 0, roomSize = 0, maxRoomSize = 0;
        // 각 방을 구분하는 과정
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (chk[i][j])
                    continue;

                roomSize = 1;
                chk[i][j] = true;
                apart[i][j] = roomCnt;
                q.add(new int[]{i, j});
                while (!q.isEmpty()) {
                    crnt = q.poll();

                    // 4방 탐색
                    for (int k = 0; k < 4; k++) {
                        nY = crnt[0] + dydx[k][0];
                        nX = crnt[1] + dydx[k][1];

                        if (nY < 0 || nY == M || nX < 0 || nX == N || chk[nY][nX])
                            continue;

                        // 방향에 벽이 존재하지 않는 경우
                        if (((1 << k) & bd[crnt[0]][crnt[1]]) == 0) {
                            roomSize++;
                            chk[nY][nX] = true;
                            apart[nY][nX] = roomCnt;
                            q.add(new int[]{nY, nX});
                        }
                    }
                }
                roomData.add(roomSize);
                maxRoomSize = Math.max(roomSize, maxRoomSize);
                roomCnt++;
            }
        }
        // *** 방 구분 및 크기 파악 완료 ***

        int answer = 0, otherRoomNum;
        chk = new boolean[M][N];
        // 각 공간의 각 방을 돌면서 이웃 공간과 합쳤을 때 생길 수 있는 가장 큰 공간을 계산
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (chk[i][j])
                    continue;

                chk[i][j] = true;
                q.add(new int[]{i, j});
                while (!q.isEmpty()) {
                    crnt = q.poll();

                    for (int k = 0; k < 4; k++) {
                        nY = crnt[0] + dydx[k][0];
                        nX = crnt[1] + dydx[k][1];

                        if (nY < 0 || nY == M || nX < 0 || nX == N || chk[nY][nX])
                            continue;

                        // 방향에 벽이 존재하는 경우
                        if ((((1 << k) & bd[crnt[0]][crnt[1]]) > 1)) {
                            otherRoomNum = apart[nY][nX];
                            // 벽 너머가 나와 다른 공간일 때
                            if (otherRoomNum != apart[crnt[0]][crnt[1]])
                                answer = Math.max(answer, roomData.get(otherRoomNum) + roomData.get(apart[crnt[0]][crnt[1]]));
                        }
                        // 벽이 없는 경우 == 같은 공간인 경우
                        else {
                            chk[nY][nX] = true;
                            q.add(new int[]{nY, nX});
                        }
                    }
                }

            }
        }
        System.out.println(roomCnt + "\n" + maxRoomSize + "\n" + answer);
    }
}