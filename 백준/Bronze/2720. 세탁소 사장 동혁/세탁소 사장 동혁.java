import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int quarter = 25;
        int dime = 10;
        int nickel = 5;
        int penny = 1;

        int T = Integer.parseInt(br.readLine()), t;
        for (int i = 0; i < T; i++) {
            t = Integer.parseInt(br.readLine());
            System.out.print(t/quarter+" ");
            t%=quarter;
            System.out.print(t/dime+" ");
            t%=dime;
            System.out.print(t/nickel+" ");
            t%=nickel;
            System.out.println(t/penny);
            t%=penny;
        }       
    }
}