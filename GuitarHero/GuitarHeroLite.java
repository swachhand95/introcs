/******************************************************************************
 *  Name:    
 *  NetID:   
 *  Precept: 
 *
 *  Partner Name:    
 *  Partner NetID:   
 *  Partner Precept: 
 * 
 *  Description:  Plays two guitar strings (concert A and concert C)
 *                when the user types the lowercase letters 'a' and 'c',
 *                respectively in the standard drawing window.
 *
 ******************************************************************************/

public class GuitarHeroLite {

	public static final double CONCERT_A = 440.0;
	
	public static void main(String[] args) {
		String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
		GuitarString[] strings = new GuitarString[keyboard.length()];
		for (int i = 0; i < strings.length; ++i) {
			strings[i] = new GuitarString(CONCERT_A * Math.pow(2, (double) (i - 24) / 12.0));
		}
		
		while (true) {
			// check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) {
 
                // the user types this character
                char key = StdDraw.nextKeyTyped();

                // pluck the corresponding string
                int idxString = keyboard.indexOf(key);
                if (idxString != -1)
					strings[idxString].pluck();
            }
            
            double sample = 0;
            for (int i = 0; i < strings.length; ++i)
            	sample += strings[i].sample();
            
            StdAudio.play(sample);

            for (int i = 0; i < strings.length; ++i)
            	strings[i].tic();
		}
		
		
	}
    

}