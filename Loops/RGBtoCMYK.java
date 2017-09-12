
public class RGBtoCMYK {
	
	public static double max(double a, double b, double c) {
		return Math.max(a, Math.max(b, c));
	}
	
	public static void main(String[] args) {
		int red = Integer.parseInt(args[0]);
		int green = Integer.parseInt(args[1]);
		int blue = Integer.parseInt(args[2]);
		
		double white = max(red/255.0, green/255.0, blue/255.0);
		double cyan = (white - red/255.0)/white;
		double magenta = (white - green/255.0)/white;
		double yellow = (white - blue/255.0)/white;
		double black = 1 - white;
		
		System.out.format("red \t\t= %d\n", red);
		System.out.format("green \t\t= %d\n", green);
		System.out.format("blue \t\t= %d\n", blue);
		System.out.format("white\t\t= %f\n", white);
		System.out.format("cyan \t\t= %f\n", cyan);
		System.out.format("magenta \t= %f\n", magenta);
		System.out.format("yellow \t\t= %f\n", yellow);
		System.out.format("black \t\t= %f\n", black);
	}
}
