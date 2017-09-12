
public class RandomWalkers {
	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		
		double totalSquaredDist = 0;
		for (int trial = 0; trial < T; ++trial) {
			int x = 0;
			int y = 0;
			for (int steps = 0; steps < N; ++steps) {
				int dir = (int) (Math.random() * 10) % 4;
				
				switch (dir) {
				case 0:
					x += -1;
					break;
				case 1:
					x += 1;
					break;
				case 2:
					y += 1;
					break;
				case 3:
					y -= 1;
					break;
				}
			}
			
			totalSquaredDist += (x*x + y*y);
		}
		double meanSquaredDist = totalSquaredDist / T;
		System.out.println("mean squared distance = " + meanSquaredDist);
	}
}
