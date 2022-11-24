import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        char[] input;
        input = br.readLine().toCharArray();

        //dp: 남은 숫자들을 돌면서 걔를 제거했을 때 큰 수가 되는지 확인
        //stack: 이후 들어온 숫자가 이전 숫자보다 큰 경우 이전 숫자들 제거
        // 제거하는 것은 들어온 숫자보다 더 큰 숫자를 만났을 때

        Stack<Character> stk = new Stack<>();

        Loop:for(int i = 0; i < input.length; i++){

            // 스택에 값있고 현재 값보다 더 작은 이전 값들을 제거하는 과정
            if(!stk.isEmpty() && stk.peek() < input[i] && K != 0){
                // 스택이 값이 있고 제거할 기회가 있을 때
                while(!stk.isEmpty() && K != 0){
                    // 들어있는 값중 가장 상위 값이 현재 값보다 작을 때 제거
                    if(stk.peek() < input[i]){
                        stk.pop();
                        K--;
                    }
                    // 현재 값과 같거나 더 큰 값을 만나면 탈출
                    else{
                        break;
                    }
                }
            }
            stk.add(input[i]);
        }
        // K가 남는 경우 연속해서 작은 값들이 나왔다는 것이기 때문에 가장 뒷 값들부터 삭제
        if(K != 0){
            while(K > 0){
                stk.pop();
                K--;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < stk.size(); i++)
            sb.append(stk.get(i));
        System.out.println(sb);
    }
}