import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 * 1. 높이가 정해진 한덩이의 빙하가 존재<br/>
 * 2. 바다와 접한 면의 개수에 따라 빙하가 녹음<br/>
 * 3. 한덩이의 빙하가 두덩이가 될 때까지의 시간 기록<br/>
 * 4. 만약 빙하가 모두 녹을 때까지 두덩이가 되지 않으면 0 출력<br/><br/>
 */
public class Main {
    public static int[][] dydx = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public static int row;
    public static int col;
    public static char[][] icesSet1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        icesSet1 = new char[row][col];
        int nextJ;
        Queue<int[]> posOfIcesPhase1 = new LinkedList<>();
        Queue<int[]> posOfIcesPhase2 = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            nextJ = 0;
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j ++) {
                icesSet1[i][nextJ] = (char)(Integer.parseInt(st.nextToken()) + '0');
                if (icesSet1[i][nextJ] != '0') {
                    posOfIcesPhase1.add(new int[]{i, nextJ});
                }
                nextJ++;
            }
        }

        int iceBlockStatus;
        for (int i = 1; ; i++) {
            // 1. 얼음 녹이기
            meltIces(posOfIcesPhase1, posOfIcesPhase2);

            // 2. 얼음 덩어리 개수 체크하기
            iceBlockStatus = CheckIceBlocks(posOfIcesPhase2, posOfIcesPhase1);
            if (iceBlockStatus == 0) {
                System.out.print(0);
                return;
            } else if (iceBlockStatus == 1) {
                System.out.print(i);
                return;
            }
//            for (int j = 0; j < row; j++) {
//                for (int k = 0; k < col; k++) {
//                    System.out.print(icesSet1[j][k]+" ");
//                }
//                System.out.println();
//            }
//            System.out.print("");
        }
    }

    private static void meltIces(Queue<int[]> output, Queue<int[]> input) {
        int[] posOfCurrentIce;
        int nextY;
        int nextX;
        int seaCount;
        char[][] tmp = new char[row][col];
        for (int i = 0; i < row; i++) {
            Arrays.fill(tmp[i], '0');
        }
        while (!output.isEmpty()) {
            seaCount = 0;
            posOfCurrentIce = output.poll();
            for (int[] eachPos : dydx) {
                nextY = posOfCurrentIce[0] + eachPos[0];
                nextX = posOfCurrentIce[1] + eachPos[1];

                if (nextY < 0 || nextX < 0 || nextY == row || nextX == col) continue;
                if (icesSet1[nextY][nextX] == '0') {
                    seaCount++;
                }
            }
            if (icesSet1[posOfCurrentIce[0]][posOfCurrentIce[1]] - '0' > seaCount) {
                tmp[posOfCurrentIce[0]][posOfCurrentIce[1]] = (char) (icesSet1[posOfCurrentIce[0]][posOfCurrentIce[1]] - seaCount);
                input.add(new int[]{posOfCurrentIce[0], posOfCurrentIce[1]});
            } else {
                tmp[posOfCurrentIce[0]][posOfCurrentIce[1]] = '0';
            }

        }
        for (int j = 0; j < row; j++) {
            icesSet1[j] = Arrays.copyOf(tmp[j], tmp[j].length);
        }
    }

    private static int CheckIceBlocks(Queue<int[]> output, Queue<int[]> input) {
        // 얼음이 하나도 없는 경우 종료
        if (output.size() == 0) {
            return 0;
        }

        int[] posOfCurrentIce;
        int[] posOfCurrentIceByBfs;
        boolean[][] isVisited = new boolean[row][col];
        Queue<int[]> findOtherIceByBfs;

        boolean isSecondIce = false;
        while (!output.isEmpty()) {
            int nextY;
            int nextX;

            posOfCurrentIce = output.poll();

            // 첫 방문인 경우
            if (!isVisited[posOfCurrentIce[0]][posOfCurrentIce[1]]) {
                // 첫 얼음인 경우
                if (!isSecondIce)
                    isSecondIce = true;
                    // 첫 얼음도 아닌 경우 종료
                else
                    return 1;
            }
            findOtherIceByBfs = new LinkedList<>();
            findOtherIceByBfs.add(new int[]{posOfCurrentIce[0], posOfCurrentIce[1]});
            isVisited[posOfCurrentIce[0]][posOfCurrentIce[1]] = true;

            // bfs로 연결된 얼음을 계속 타고 들어가며 방문 처리
            while (!findOtherIceByBfs.isEmpty()) {
                posOfCurrentIceByBfs = findOtherIceByBfs.poll();
                for (int[] eachPos : dydx) {
                    nextY = posOfCurrentIceByBfs[0] + eachPos[0];
                    nextX = posOfCurrentIceByBfs[1] + eachPos[1];
                    if (nextY < 0 || nextX < 0 || nextY == row || nextX == col) continue;
                    if (isVisited[nextY][nextX]) continue;

                    if (icesSet1[nextY][nextX] != '0') {
                        findOtherIceByBfs.add(new int[]{nextY, nextX});
                        isVisited[nextY][nextX] = true;
                    }
                }
            }

            // 확인했으니 다시 넣어줌
            input.add(new int[]{posOfCurrentIce[0], posOfCurrentIce[1]});
        }
        return 2;
    }
}