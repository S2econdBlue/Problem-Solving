import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static char[][] bd;
    static Queue<int[]> startPos;
    static boolean[][] isVisit;

    static void changeStatAndVisit(int Y, int X) {
        bd[Y][X] = '.';
        startPos.add(new int[]{Y, X});
    }

    public static void main(String[] args) throws IOException {


        // 여러 입구를 시작 위치에 저장,
        // BD는 방문 중복 불가능한 상태
        // 열쇠를 얻은 뒤 다른 입구로만 접근 가능한 문의 열쇠를 열 수도 있음
        // 문을 만날 때마다 해당 문의 알파벳과 위치를 저장
        // 열쇠를 먹으면 이전에 만났던 문들 중 매칭되는 문이 있는지 확인, 만약 있다면 해당 문의 위치부터 시작
        // 문을 만난 후 열쇠를 먹은 경우
        // 열쇠가 있는데 특정 문을 만나는 경우
        // a = 97, A = 65
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int tc = Integer.parseInt(br.readLine());
        int h, w;
        char[] input;
        boolean[] keys;
        // 동일한 대문자 알파벳의 문이 여러 개 존재 가능
        Queue<int[]>[] doors = new LinkedList[26];

        int getP, nextY, nextX; // 먹은 문서 개수
        int[] startPoint, visitedDoorPos;
        int[][] dirYX = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        startPos = new LinkedList<>();
        for (int i = 0; i < tc; i++) {

            for (int j = 0; j < doors.length; j++) {
                doors[j] = new LinkedList<>();
            }
            //초기화
            getP = 0;
            keys = new boolean[26];
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            isVisit = new boolean[h][w];
            bd = new char[h][w];

            // 지도 입력
            for (int j = 0; j < h; j++) {
                input = br.readLine().toCharArray();
                for (int k = 0; k < w; k++)
                    bd[j][k] = input[k];
            }


            input = br.readLine().toCharArray();
            // 열쇠가 있을 때
            if (input[0] != '0') {
                for (int j = 0; j < input.length; j++) {
                    keys[input[j] - 'a'] = true;
                }
            }

            for (int j = 0; j < h; j++) {
                for (int k = 0; k < w; k++) {
                    // 외곽 조건 해당
                    if ((j == 0 || j == h - 1 || k == 0 || k == w - 1)) {

                        // 외곽의 빈 칸
                        if (bd[j][k] == '.') {
                            bd[j][k] = '.';
                            startPos.add(new int[]{j, k});
                        }
                        else if(bd[j][k] == '$') {
                            getP++;
                            changeStatAndVisit(j, k);
                        }
                        // 외곽의 문
                        else if('A' <= bd[j][k] && bd[j][k] <= 'Z'){
                            // 해당 키를 이미 먹은 경우
                            if (keys[bd[j][k] - 'A']) {
                                changeStatAndVisit(j, k);
                            } else {
                                // 열쇠 없는 문을 만나면 더 이상 진행 불가
                                doors[bd[j][k] - 'A'].add(new int[]{j , k});
                            }
                        }
                        // 외곽의 열쇠
                        else if('a' <= bd[j][k] && bd[j][k] <= 'z'){
                            keys[bd[j][k] - 'a'] = true;
                            // 해당 문을 방문해서 위치가 저장되어 있는 경우
                            if (doors[bd[j][k] - 'a'].size() > 0) {
                                // 해당 알파벳 문의 저장된 위치를 모두 순회
                                while (!doors[bd[j][k] - 'a'].isEmpty()) {
                                    visitedDoorPos = doors[bd[j][k] - 'a'].poll();
                                    changeStatAndVisit(visitedDoorPos[0], visitedDoorPos[1]);
                                }
                            }
                            changeStatAndVisit(j, k);
                        }
                    }
                }
            }

            // 모든 시작 가능한 지점로부터 시작
            while (!startPos.isEmpty()) {
                startPoint = startPos.poll();
                // 시작점을 이미 방문한 경우 pass
                if (isVisit[startPoint[0]][startPoint[1]])
                    continue;

                isVisit[startPoint[0]][startPoint[1]] = true;
                for (int j = 0; j < 4; j++) {
                    nextY = startPoint[0] + dirYX[j][0];
                    nextX = startPoint[1] + dirYX[j][1];

                    if (nextY < 0 || nextY == h || nextX < 0 || nextX == w) continue;// 범위 벗어나는 경우
                    else if (isVisit[nextY][nextX]) continue; // 방문한 경우
                    else if (bd[nextY][nextX] == '*') continue; // 벽인 경우
                    else if (bd[nextY][nextX] == '$') { // 문서인 경우
                        changeStatAndVisit(nextY, nextX);
                        getP++;
                    } else if (bd[nextY][nextX] == '.') {
                        changeStatAndVisit(nextY, nextX);
                    }
//                    열쇠인 경우
                    else if ('a' <= bd[nextY][nextX] && bd[nextY][nextX] <= 'z') {
                        keys[bd[nextY][nextX] - 'a'] = true;
                        // 해당 문을 방문해서 위치가 저장되어 있는 경우
                        if (doors[bd[nextY][nextX] - 'a'].size() > 0) {
                            // 해당 알파벳 문의 저장된 위치를 모두 순회
                            while (!doors[bd[nextY][nextX] - 'a'].isEmpty()) {
                                visitedDoorPos = doors[bd[nextY][nextX] - 'a'].poll();
                                changeStatAndVisit(visitedDoorPos[0], visitedDoorPos[1]);
                            }
                        }
                        changeStatAndVisit(nextY, nextX);
                    } else { // 문인 경우
                        // 해당 키를 이미 먹은 경우
                        if (keys[bd[nextY][nextX] - 'A']) {
                            changeStatAndVisit(nextY, nextX);
                        } else {
                            // 열쇠 없는 문을 만나면 더 이상 진행 불가
                            doors[bd[nextY][nextX] - 'A'].add(new int[]{nextY, nextX});
                        }
                    }

                }
            }
            System.out.println(getP);
        }
    }
}