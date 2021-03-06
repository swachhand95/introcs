public class MarkovModel {
	
	private static final int TOTAL_CHARS = 127;

	private int order;
	private ST<String, int[]> st;

	public MarkovModel(String text, int k) {
		st = new ST<String, int[]>();
		order = k;

		// create the circular version of the text
		text += text.substring(0, k);

		/*
		 * for each k-gram, insert it into the ST. If it already exists,
		 * increment the counter of the next occuring character
		 */
		for (int i = 0; (i + k) < text.length(); ++i) {
			String kgram = text.substring(i, i + k);
			if (!st.contains(kgram)) {
				st.put(kgram, new int[TOTAL_CHARS]);
			}
			char nextchar = text.charAt(i+k);
			st.get(kgram)[nextchar]++;
		}
	}

	public int order() {
		return order;
	}
	
	private int sumArr(int[] arr) {
		int sum = 0;
		
		for (int i = 0; i < arr.length; ++i) {
			sum += arr[i];
		}

		return sum;
	}
	
	public int freq(String kgram) {
		if (kgram.length() != order) {
			throw new RuntimeException("kgram is not of length k");
		}
		
		int freq = 0;
		int[] arr = st.get(kgram);
		if (arr != null) {
			freq = sumArr(arr);
		}
		
		return freq;
	}
	
	public int freq(String kgram, char c) {
		if (kgram.length() != order) {
			throw new RuntimeException("kgram is not of length k");
		}
		
		if (st.contains(kgram)) {
			return st.get(kgram)[c];
		}
		return 0;
	}
	
	public char rand(String kgram) {
		if (!st.contains(kgram)) {
			throw new RuntimeException("No such kgram");
		}
		if (kgram.length() != order) {
			throw new RuntimeException("kgram is not of length k");
		}
		
		int[] occurrences = st.get(kgram);
		return (char) StdRandom.discrete(occurrences);
	}
	
	public void printKgramArr(String kgram) {
		if (!st.contains(kgram)) {
			throw new RuntimeException("No such kgram");
		}
		if (kgram.length() != order) {
			throw new RuntimeException("kgram is not of length k");
		}
		
		int[] occurrences = st.get(kgram);
		
		for (int i = 0; i < occurrences.length; ++i) {
			if (occurrences[i] != 0) {
				System.out.print((char) i + " = "  + occurrences[i] + ", ");
			}
		}
		System.out.println();
	}
	
	public String gen(String kgram, int T) {
		if (kgram.length() != order) {
			throw new RuntimeException("kgram is not of length k");
		}
		
		StringBuilder trajectory = new StringBuilder(kgram);
		StringBuilder kgramBuilder = new StringBuilder(kgram);
		for (int i = 0; (i + order) < T; ++i) {
			kgramBuilder.deleteCharAt(0);
			char nextchar = rand(kgram);
			kgramBuilder.append(nextchar);
			trajectory.append(nextchar);
			kgram = kgramBuilder.toString();
		}
		return trajectory.toString();
	}

    public static void main(String[] args) {
    	if (args.length != 2) {
    		System.err.println("Usage: java-introcs MarkovModel 'k' 'T'");
    		return;
    	}
    	
    	int k = Integer.parseInt(args[0]);
    	int T = Integer.parseInt(args[1]);
    	
    	String text = StdIn.readAll();
    	MarkovModel mm = new MarkovModel(text, k);
    	String generatedText = mm.gen(text.substring(0, k), T);
    	System.out.println(generatedText);
    }


}
