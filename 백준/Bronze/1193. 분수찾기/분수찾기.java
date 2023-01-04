import java.io.*;
import java.util.*;

public class Main {
    // 짝수 시작 1/2 끝 2/1
    // 홀수 시작 3/1 끝 1/3
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());
        long left = 1, right = 10000000, mid = 0, max = 0;
        long v = 0;

        // 동일 값 불가능?
        while (left <= right) {
            mid = (left + right) / 2;
            v = (1 + mid) * mid / 2;
            // 더 큰 값이 나오는 경우 오른쪽 범위 축소
            if (X < v) {
                right = mid - 1;
            }
            // 더 작은 값이 나오는 경우 혹시라도 더 큰 값이 되는지 파악하기 위해 왼쪽 범위 축소
            else {
                max = Math.max(mid, max);
                left = mid + 1;
            }
        }
        long allV = (1 + max) * max / 2;
        // 계산한 값이 동일할 때
        if (X == allV) {
            if (max % 2 == 0) {
                System.out.println(max + "/1");
            } else {
                System.out.println("1/" + max);
            }
        } else {
            //7일 때 1/4
            //4일 때 3/1
            long diff = X - allV - 1;
            if (max % 2 == 0) {
                System.out.println((max + 1 - diff) + "/" + (1 + diff));
            } else {
                System.out.println((1 + diff) + "/" + (max + 1 - diff));
            }
        }
    }
}