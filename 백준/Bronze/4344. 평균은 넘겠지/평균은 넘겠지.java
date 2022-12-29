import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());
        int[] arr;
        int cnt, sum = 0;
        float avg,upper;
        for (int i = 0; i < TC; i++) {
            st = new StringTokenizer(br.readLine());
            sum = 0;
            cnt = Integer.parseInt(st.nextToken());
            upper = 0;
            arr = new int[cnt];
            for (int j = 0; j < cnt; j++) {
                sum += arr[j] = Integer.parseInt(st.nextToken());
            }
            avg = (float) sum / cnt;

            for (int j = 0; j < cnt; j++) {
                if(arr[j] > avg)
                    upper++;
            }
            System.out.printf("%.3f%%\n", (float) (upper / cnt) * 100);
        }
    }
}