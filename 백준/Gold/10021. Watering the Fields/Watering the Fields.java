import java.io.*;
import java.util.*;

public class Main {
    static int[] set;

    static int FindSet(int a) {
        if (set[a] < 0)
            return a;
        return set[a] = FindSet(set[a]);
    }

    static boolean UnionSet(int a, int b) {
        a = FindSet(a);
        b = FindSet(b);

        if (a == b)
            return false;
        if (set[a] < set[b]) {
            set[a] += set[b];
            set[b] = a;
        } else {
            set[b] += set[a];
            set[a] = b;
        }
        return true;
    }

    // MST로 조건을 만족하는 애들부터 연결
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][2];
        set = new int[N];

        for (int i = 0; i < N; i++) {
            set[i] = -1;
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        int calc, answer = 0, cnt = 0;
        int[] crnt;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                calc = (int) (Math.pow(arr[i][0] - arr[j][0], 2) + Math.pow(arr[i][1] - arr[j][1], 2));
                // 조건에 맞는 것만 입력
                if (calc >= C) {
                    pq.add(new int[]{i, j, calc});
                }
            }
        }

        while (!pq.isEmpty()) {
            crnt = pq.poll();
            if (UnionSet(crnt[0], crnt[1])) {
                answer+=crnt[2];
                cnt++;
                if (cnt == N - 1) {
                    System.out.println(answer);
                    return;
                }
            }
        }
        System.out.println(-1);
    }
}