
public class GuitarString {
	
	private RingBuffer rb;
	private static final double DECAY_FACTOR = 0.996;
	private int time;
	
	private int roundUp(double n) {
		if ((int) n == n)
			return (int) n;
		return (int) n + 1;
	}

	public GuitarString(double frequency) {
		time = 0;
		int N = roundUp(44100 / frequency);
		rb = new RingBuffer(N);
		while (!rb.isFull())
			rb.enqueue(0);
	}
	
	public GuitarString(double[] init) {
		time = 0;
		rb = new RingBuffer(init.length);
		for (int i = 0; i < init.length; ++i) {
			rb.enqueue(init[i]);
		}
	}
	
	public void pluck() {
		while (!rb.isEmpty())
			rb.dequeue();
		while (!rb.isFull())
			rb.enqueue(Math.random() * 0.5 - 0.5);
	}
	
	public void tic() {
		double first = rb.dequeue();
		double second = rb.peek();
		double avg = (first + second) / 2;
		rb.enqueue(avg * DECAY_FACTOR);
		++time;
	}
	
	public double sample() {
		return rb.peek();
	}
	
	public int time() {
		return time;
	}

	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		double[] samples = { .2, .4, .5, .3, -.2, .4, .3, .0, -.1, -.3 };
		GuitarString testString = new GuitarString(samples);
		for (int i = 0; i < N; i++) {
			int t = testString.time();
			double sample = testString.sample();
			System.out.printf("%6d %8.4f\n", t, sample);
			testString.tic();
		}
	}
}
