import java.awt.Color;


public class PhotoMagic {
	
	public static Picture transform(Picture picture, LFSR lfsr) {
		Picture transformed = new Picture(picture.width(), picture.height());
		
		for (int i = 0; i < transformed.height(); ++i) {
			for (int j = 0; j < transformed.width(); ++j) {
				Color curr = picture.get(j, i);
				int r = curr.getRed() ^ lfsr.generate(8);
				int g = curr.getGreen() ^ lfsr.generate(8);
				int b = curr.getBlue() ^ lfsr.generate(8);
				Color newcol = new Color(r, g, b);
				transformed.set(j, i, newcol);
			}
		}
		
		return transformed;
	}

	public static void main(String[] args) {
		String filename;
		String seed;
		int tap;
		
		if (args.length != 3) {
			System.err.println("Usage: java-introcs PhotoMagic 'imagename' 'lfsr-seed' 'lfsr-tap'");
			return;
		}
		else {
			filename = args[0];
			seed = args[1];
			tap = Integer.parseInt(args[2]);
		}
		
		LFSR lfsr = new LFSR(seed, tap);
		Picture pic = new Picture(filename);
		pic.show();
		
		Picture encrypted = transform(pic, lfsr);
		encrypted.show();
	}

}