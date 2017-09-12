
public class Ordered {
	public static void main(String[] args) {
		boolean isOrdered = false;
		
		int a = Integer.parseInt(args[0]);
		int b = Integer.parseInt(args[1]);
		int c = Integer.parseInt(args[2]);
		
		isOrdered = (a < b && b < c || a > b && b > c);
		System.out.println(isOrdered);
	}
}
