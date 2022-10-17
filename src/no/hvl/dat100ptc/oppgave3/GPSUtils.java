package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;

		// TODO - START 
		
		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		
		return min;

		// TODO - SLUT

	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		// TODO - START
		
		double[] breddegrader = new double[gpspoints.length];
		for(int i = 0;i<gpspoints.length;i++) {
			breddegrader[i] = gpspoints[i].getLatitude();
		}
		return breddegrader;
		// TODO - SLUTT
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		// TODO - START

		double[] lengdegrader = new double[gpspoints.length];
		for(int i = 0;i<gpspoints.length;i++) {
			lengdegrader[i] = gpspoints[i].getLongitude();
		}
		return lengdegrader;
		
		// TODO - SLUTT

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;

		// TODO - START

		latitude1 = gpspoint1.getLatitude();
		longitude1 = gpspoint1.getLongitude();
		latitude2 = gpspoint2.getLatitude();
		longitude2 = gpspoint2.getLongitude();
		
		double lat1 = toRadians(latitude1);
		double long1 = toRadians(longitude1);
		double lat2 = toRadians(latitude2);
		double long2 = toRadians(longitude2);
		
		double deltaLat = lat2 - lat1;
		double deltaLong = long2 - long1;
		
		double a = pow((sin(deltaLat/2)),2)+cos(lat1)*cos(lat2)*pow(sin(deltaLong/2),2);
		double c = 2*atan2(sqrt(a),sqrt(1-a));
		d = R*c;
		return d;
		// TODO - SLUTT

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;

		// TODO - START

		secs = gpspoint2.getTime()-gpspoint1.getTime();
		
		speed = distance(gpspoint1,gpspoint2)/secs;
		speed = speed*3.6;
		
		return speed;

		// TODO - SLUTT

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		// TODO - START

		int sekunder = secs % 60;
		int minutter = secs / 60 % 60;
		int timer = secs / 60 / 60 % 60;

		String sekunderNull = String.format("%02d", sekunder);
		String minutterNull = String.format("%02d", minutter);
		String timerNull = String.format("%02d", timer);

		timestr = "  " + timerNull + TIMESEP + minutterNull + TIMESEP + sekunderNull;

		return timestr;
		// TODO - SLUTT

	}
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;

		// TODO - START

		String tall = String.format("%.2f", d);
		for(int i = tall.length();i<TEXTWIDTH;i++) {
			tall = " "+tall;
		}
		str = tall.replace("," ,".");
		return str;

		// TODO - SLUTT
		
	}
}
