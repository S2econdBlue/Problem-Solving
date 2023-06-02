import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public enum grade{
        AP( "A+",4.5),
        A0("A0", 4.0),
        BP("B+", 3.5),
        B0("B0", 3.0),
        CP("C+", 2.5),
        C0("C0", 2.0),
        DP("D+", 1.5),
        D0("D0", 1.0),
        F("F", 0);

        public String grade;
        public double value;
        grade(String s, double v) {
            this.grade = s;
            this.value = v;
        }
        double toNum(String grade){
            return this.value;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String input, eachgrade;
        double eachvalue;
        double finals = 0, sumValue = 0;
        int real = 0;

        for (int i = 0; i < 20; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            eachvalue = Double.parseDouble(st.nextToken());
            eachgrade = st.nextToken();
            if(eachgrade.equals("P"))
                continue;
            real++;
            sumValue+=eachvalue;
            finals += grade.valueOf(eachgrade.chars()
                    .mapToObj(c -> c == '+' ? 'P' : (char) c)
                    .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                    .toString()).value * eachvalue;

        }
        System.out.printf("%.6f",finals/ sumValue);
    }
}