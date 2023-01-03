import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] arr = br.readLine().toCharArray();
        int answer = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 'A' || arr[i] == 'B' || arr[i] == 'C')
                answer += 3;
            else if (arr[i] == 'D' || arr[i] == 'E' || arr[i] == 'F')
                answer += 4;
            else if (arr[i] == 'G' || arr[i] == 'H' || arr[i] == 'I') {
                answer += 5;
            } else if (arr[i] == 'J' || arr[i] == 'K' || arr[i] == 'L') {
                answer += 6;
            } else if (arr[i] == 'M' || arr[i] == 'N' || arr[i] == 'O') {
                answer += 7;
            } else if (arr[i] == 'P' || arr[i] == 'Q' || arr[i] == 'R' || arr[i] == 'S') {
                answer += 8;
            } else if (arr[i] == 'T' || arr[i] == 'U' || arr[i] == 'V')
                answer += 9;
            else
                answer += 10;
        }
        System.out.println(answer);
    }
}