import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int min = 1000000, max = -1000000;

        StringTokenizer st = new StringTokenizer(br.readLine());
        int each = 0;
        for(int i = 0; i < N; i++){
            each = Integer.parseInt(st.nextToken());
            min = Math.min(each, min);
            max = Math.max(each, max);
        }
        System.out.println(min +" " + max);
    }
}