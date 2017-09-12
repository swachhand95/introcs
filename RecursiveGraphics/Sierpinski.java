
public class Sierpinski {
	
	private static final double COS30 = Math.sqrt(3)/2;
	
	// draw shaded equilateral triangle with bottom vertex (x, y), side length s
	public static void filledTriangle(double x, double y, double s) {
		double[] xs = new double[3];
		double[] ys = new double[3];
		
		xs[0] = x;
		ys[0] = y;
		
		xs[1] = x + s/2;
		ys[1] = y + s * COS30;
		
		xs[2] = x - s/2;
		ys[2] = ys[1];
		
		StdDraw.filledPolygon(xs, ys);
	}
	
	/* 
	 * draw one triangle, bottom vertex (x, y), side s; recursively call
	 * itself three times to generate the next order Sierpinski triangles 
	 * above, left and right of current triangle. n is the order
	 */
	public static void sierpinski(int n, double x, double y, double s) {
		if (n == 0)
			return;
		
		filledTriangle(x, y, s);
		sierpinski(n - 1, x, y + s * COS30, s/2);
		sierpinski(n - 1, x - s/2, y, s/2);
		sierpinski(n - 1, x + s/2, y, s/2);
	}

	public static void main(String[] args) {
		
		int N = 0;
		
		if (args.length != 1) {
			System.out.println("Usage: java Sierpinski 'order'");
			return;
		}
		else {
			N = Integer.parseInt(args[0]);
		}
		
		// draw the outline
		double[] x = {0, 1, 0.5};
		double[] y = {0, 0, Math.sqrt(3)/2};
		StdDraw.polygon(x, y);
		
		// draw the sierpinski pattern
		sierpinski(N, 0.5, 0, 0.5);
		
	}

}
