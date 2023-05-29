import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int basketN = Integer.parseInt(st.nextToken());
        int[] baskets = new int[basketN + 1];
        for (int i = 1; i <= basketN; i++) {
            baskets[i] = i;
        }
        int changeM = Integer.parseInt(st.nextToken());

        int leftRange, rightRange;

        int[] tmp = new int[basketN + 1];

        // 기존 순서를 저장 후 순번을 뒤집어서 넣기
        for (int i = 0; i < changeM; i++) {
            st = new StringTokenizer(br.readLine());
            leftRange = Integer.parseInt(st.nextToken());
            rightRange = Integer.parseInt(st.nextToken());
            for (int j = leftRange; j <= rightRange; j++) {
                tmp[j] = baskets[j];
            }
            for (int j = 0; j <= rightRange - leftRange; j++) {
                baskets[j + leftRange] = tmp[rightRange - j];
            }
        }

        for (int i = 1; i <= basketN; i++) {
            System.out.print(baskets[i] + " ");
        }
    }
}