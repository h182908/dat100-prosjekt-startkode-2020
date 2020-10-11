package no.hvl.dat100ptc.oppgave3;

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
		
		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		
		return min;
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		double[] latitudes = new double[gpspoints.length];
		
		for(int i = 0; i < gpspoints.length; i++) {
			latitudes[i] = gpspoints[i].getLatitude();
		}
		
		return latitudes;
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		double[] longitudes = new double[gpspoints.length];
		
		for(int i = 0; i < gpspoints.length; i++) {
			longitudes[i] = gpspoints[i].getLongitude();
		}
		
		return longitudes;

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;

		latitude1 = Math.toRadians(gpspoint1.getLatitude());
		latitude2 = Math.toRadians(gpspoint2.getLatitude());
		longitude1 = Math.toRadians(gpspoint1.getLongitude());
		longitude2 = Math.toRadians(gpspoint2.getLongitude());
		
		double delta_phi = latitude2 - latitude1;
		
		double delta_lambda = longitude2 - longitude1;
		
		double a = Math.pow(Math.sin(delta_phi/2), 2) + Math.cos(latitude1) * Math.cos(latitude2) * Math.pow(Math.sin(delta_lambda/2), 2);

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		
		d = R * c;
		
		return d;
	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;

		secs = gpspoint2.getTime() - gpspoint1.getTime();

		speed = distance(gpspoint1, gpspoint2)/secs*3.6;

		return speed;
	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";
		
		int hours = secs/60/60;
		int minutes = secs/60 % 60;
		int seconds = secs % 60;
		
		timestr = String.format("%02d:%02d:%02d", hours, minutes, seconds);
		
		return String.format("%10s", timestr);
	}
	
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;
		
		str = String.format("%10.2f", d);
		
		return str;
		
	}
}
