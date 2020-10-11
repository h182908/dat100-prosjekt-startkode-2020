package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {
	
		double ystep;
		
		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		
		ystep = MAPYSIZE / (Math.abs(maxlat - minlat)); 

		return ystep;
		
	}

	public void showRouteMap(int ybase) {
		
		setColor(0, 255, 0);
		
		for(int i = 0; i < gpspoints.length; i++) {
			
			double xmin = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
			double ymin = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
			double x = (gpspoints[i].getLongitude() - xmin) * xstep();
			double y = ybase - (gpspoints[i].getLatitude() - ymin) * ystep();
			
			if(i < gpspoints.length - 1){
				double x2 = (gpspoints[i+1].getLongitude() - xmin) * xstep();
				double y2 = ybase - (gpspoints[i+1].getLatitude() - ymin) * ystep();
				
				drawLine((int) x, (int) y, (int) x2, (int) y2);
			}
			
			drawCircle((int) x, (int) y, 5);
		}
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;
		int WEIGHT = 80;

		setColor(0,0,0);
		setFont("Courier",12);
		
		String time = String.format("%-15s:%11s", "Total Time", GPSUtils.formatTime(gpscomputer.totalTime()));
		String distance = String.format("%-15s:%11s km", "Total distance", GPSUtils.formatDouble(gpscomputer.totalDistance()/1000));
		String elevation = String.format("%-15s:%11s m", "Total elevation", GPSUtils.formatDouble(gpscomputer.totalElevation()));
		String max_speed = String.format("%-15s:%11s km/t", "Max speed", GPSUtils.formatDouble(gpscomputer.maxSpeed()));
		String average_speed = String.format("%-15s:%11s km/t", "Average speed", GPSUtils.formatDouble(gpscomputer.averageSpeed()));
		String kcal = String.format("%-15s:%11s kcal", "Energy", GPSUtils.formatDouble(gpscomputer.totalKcal(WEIGHT)));
		
		int x = 25; 
		
		drawString(time, x,TEXTDISTANCE);
		drawString(distance, x, 2 * TEXTDISTANCE);
		drawString(elevation, x, 3 * TEXTDISTANCE);
		drawString(max_speed, x, 4 * TEXTDISTANCE);
		drawString(average_speed, x, 5 * TEXTDISTANCE);
		drawString(kcal, x, 6 * TEXTDISTANCE);
		
	}

}
