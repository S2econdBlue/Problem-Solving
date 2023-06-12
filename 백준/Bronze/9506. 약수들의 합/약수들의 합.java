import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input;
        int n, maxNum, sum;
        List<Integer> list;
        while (!(input = br.readLine()).equals("-1")) {
            n = Integer.parseInt(input);
            list = new ArrayList<>();
            sum = 1;
            maxNum = n / 2;
            for (int i = 2; i <= maxNum; i++) {
                if (n % i == 0) {
                    sum += i;
                    list.add(i);
                    list.add(n / i);
                    maxNum = n / i - 1;
                    sum += n / i;
                }
            }
            if (sum == n) {
                Collections.sort(list);
                sb.append(n).append(" = ").append(1);

                for (int i = 0; i < list.size(); i++) {
                    sb.append(" + ").append(list.get(i));
                }
                sb.append("\n");
            } else {
                sb.append(n).append(" is NOT perfect.\n");
            }
        }
        System.out.print(sb);
    }
}