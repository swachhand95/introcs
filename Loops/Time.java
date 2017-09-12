
public class Time {
	public static void main(String[] args) {
		int elapsed = Integer.parseInt(args[0]);
		
		int hr = elapsed / 60;
		int min = elapsed % 60;
				
		String ampm = ((hr / 12) % 2 == 0) ? "pm" : "am";
		hr = hr % 12;
		hr = (hr == 0) ? 12 : hr;
		
		System.out.println(hr + ":" + ((min == 0) ? "00" : min) + ampm);
	}
}
