
public class LFSR {
	
	private String register;
	private int tap;
	
	public LFSR(String seed, int t) {
		register = seed;
		tap = register.length() - t - 1;
	}
	
	private int chartoint(char a) {
		return a - '0';
	}
	
	public int step() {
		String newreg = register.substring(1, register.length());
		int newbit = chartoint(register.charAt(0)) ^ chartoint(register.charAt(tap));
		newreg += newbit;
		register = newreg;
		return newbit;
	}
	
	public String toString() {
		return register;
	}
	
	public int generate(int k) {
		int res = 0;
		
		for (int i = 0; i < k; ++i) {
			int newbit = step();
			res = res*2 + newbit;
		}
		
		return res;
	}

	public static void main(String[] args) {
		LFSR lfsr = new LFSR("01101000010", 8);
		StdOut.println(lfsr);
		
		for (int i = 0; i < 10; i++) {
		    int r = lfsr.generate(5);
		    StdOut.println(lfsr + " " + r);
		}
	}

}
