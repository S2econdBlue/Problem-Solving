import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int eachNum, maxSize = 0, maxNoRow = 0, maxNoCol = 0;
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                eachNum = Integer.parseInt(st.nextToken());
                if (maxSize < eachNum) {
                    maxSize = eachNum;
                    maxNoRow = i;
                    maxNoCol = j;
                }

            }
        }
        System.out.println(maxSize);
        System.out.println(maxNoRow+1 + " " + (maxNoCol+1));
    }
}