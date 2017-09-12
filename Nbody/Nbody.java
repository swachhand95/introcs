
public class Nbody {
	
	public static final double G = 6.67e-11; // Gravitational Constant
	public static final String BG_FILENAME = "data/starfield.jpg"; // The background image filename
	
	// calculates the Euclidean distance between two objects
	public static double distance(double x1, double y1, double x2, double y2) {
		return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
	}
	
	// calculates the gravitational force between two objects
	public static double force(double m1, double m2, double r) {
		return G * (m1 * m2) / (r * r);
	}

	public static void main(String[] args) {
		
		// total time to simulate
		double T = 0;
		// granularity of the simulation
		double dt = 0;
		
		if (args.length != 2) {
			System.out.println("Usage: java Nbody 'T' 'delta_t'");
			return;
		}
		else {
			T = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
		}
		
		// number of objects
		int N = StdIn.readInt();
		
		// radius of the universe
		double R = StdIn.readDouble();
		
		// positions of the objects
		double px[] = new double[N];
		double py[] = new double[N];
		
		// velocities of the objects
		double vx[] = new double[N];
		double vy[] = new double[N];
		
		// mass of the objects
		double m[] = new double[N];
		
		// name of the file containing the image of the objects
		String image[] = new String[N];

		// read all the values and store them in the parallel arrays
		for (int i = 0; i < N; ++i) {
			px[i] = StdIn.readDouble();
			py[i] = StdIn.readDouble();
			vx[i] = StdIn.readDouble();
			vy[i] = StdIn.readDouble();
			m[i] = StdIn.readDouble();
			image[i] = "data/" + StdIn.readString();
		}
		
		// prepare the canvas and draw the background image
		StdDraw.setScale(-R, R);
		StdDraw.enableDoubleBuffering();
		StdDraw.picture(0, 0, BG_FILENAME);
		
		// the time loop
		for (double t = 0; t <= T; t += dt) {
			
			// calculate the x and y components of the forces
			double fx[] = new double[N];
			double fy[] = new double[N];
			
			for (int i = 0; i < N; ++i) {
				fx[i] = 0;
				fy[i] = 0;
				
				for (int j = 0; j < N; ++j) {
					if (j == i) continue;
					
					// calculate force between i and j
					double r = distance(px[i], py[i], px[j], py[j]);
					double f = force(m[i], m[j], r);
					
					// calculate the x and y component of the force
					fx[i] += f * ((px[j] - px[i]) / r);
					fy[i] += f * ((py[j] - py[i]) / r);
				}
			}
			
			// calculate the x and y components of the acceleration
			double ax[] = new double[N];
			double ay[] = new double[N];
			
			for (int i = 0; i < N; ++i) {
				ax[i] = fx[i] / m[i];
				ay[i] = fy[i] / m[i];
			}
			
			// calculate the velocities
			for (int i = 0; i < N; ++i) {
				vx[i] += dt * ax[i];
				vy[i] += dt * ay[i];
			}
			
			// calculate the new positions
			for (int i = 0; i < N; ++i) {
				px[i] += dt * vx[i];
				py[i] += dt * vy[i];
			}
			
			StdDraw.picture(0, 0, BG_FILENAME);
			// draw the objects
			for (int i = 0; i < N; ++i) {
				StdDraw.picture(px[i], py[i], image[i]);
			}
			StdDraw.show();
			StdDraw.pause(20);
		}
		
		// display final values
		StdOut.println(N);
		StdOut.println(R);
		for (int i = 0; i < N; ++i) {
			StdOut.printf("%14.5e %14.5e %14.5e %14.5e %14.5e %14s\n", 
					px[i], py[i], vx[i], vy[i], m[i], image[i]);
		}
	}

}
