import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	static int tc = 0;
	static int visit = 0;
	static int[][] HlthHppy = null;
	static int maxHppy = 0;

	static void findMaxHappy(int crntPos, int crntHlth) {
		if (crntPos == tc) {
			int sum = 0;
			for (int i = 0; i < tc; i++) {
				if ((visit & (1 << i)) > 0)
					sum += HlthHppy[i][1];
			}
			maxHppy = Math.max(maxHppy, sum);
			return;
		}

		for (int i = crntPos; i < tc; i++) {
			if ((visit & 1 << i) == 0 && (crntHlth + HlthHppy[i][0]) < 100) {
				visit |= (1 << i);
				findMaxHappy(i + 1, crntHlth + HlthHppy[i][0]);
				visit &= ~(1 << i);
			} else {
				findMaxHappy(i + 1, crntHlth);
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		tc = Integer.parseInt(br.readLine());
		String[] health = br.readLine().split(" ");
		String[] happy = br.readLine().split(" ");
		HlthHppy = new int[tc][2];
		for (int i = 0; i < tc; i++) {
			HlthHppy[i][0] = Integer.parseInt(health[i]);
			HlthHppy[i][1] = Integer.parseInt(happy[i]);
		}
		findMaxHappy(0, 0);
		System.out.println(maxHppy);
	}
}