import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        int basketN = Integer.parseInt(st.nextToken());
        int[] baskets = new int[basketN + 1];
        for (int i = 1; i <= basketN; i++) {
            baskets[i] = i;
        }
        int changeM = Integer.parseInt(st.nextToken());

        int left, right, tmp;
        for (int i = 0; i < changeM; i++) {
            st = new StringTokenizer(br.readLine());
            left = Integer.parseInt(st.nextToken());
            right = Integer.parseInt(st.nextToken());
            tmp = baskets[left];
            baskets[left] = baskets[right];
            baskets[right] = tmp;
        }

        for (int i = 1; i <= basketN; i++) {
            System.out.print(baskets[i] + " ");
        }
    }
}