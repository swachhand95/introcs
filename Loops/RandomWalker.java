
public class RandomWalker {
	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		
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
			
			System.out.println("(" + x + ", " + y + ")");
		}
		
		System.out.println("squared distance = " + (x*x + y*y));
	}
}
