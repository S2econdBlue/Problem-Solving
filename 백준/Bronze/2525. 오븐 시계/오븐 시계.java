import java.io.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] time = br.readLine().split(" ");
		int[] ans = new int[] {Integer.parseInt(time[0]),Integer.parseInt(time[1])};
		int oven =Integer.parseInt(br.readLine());
		if(ans[1] + oven % 60 >= 60) {
			ans[0]++;
			
		}
		ans[1] = (ans[1] + oven) % 60; 
		ans[0] += oven / 60;
		ans[0] %=24;
		
		System.out.println(ans[0] + " " + ans[1]);
	}
}