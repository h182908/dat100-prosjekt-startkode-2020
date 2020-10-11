package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int n) {

		this.gpspoints = new GPSPoint[n];
		this.antall = 0;
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	protected boolean insertGPS(GPSPoint gpspoint) {

		boolean inserted = false;

		if(this.antall < gpspoints.length) {
			this.gpspoints[antall] = gpspoint;
			antall += 1;
			inserted = true;
		}
		
		return inserted;
	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {

		GPSPoint gpspoint;

		gpspoint = GPSDataConverter.convert(time, latitude, longitude, elevation);
		
		return insertGPS(gpspoint);
	}

	public void print() {

		System.out.println("====== Konvertert GPS Data - START ======");

		for(GPSPoint point : this.gpspoints) {
			System.out.print(point.toString());
		}
		
		System.out.println("====== Konvertert GPS Data - SLUTT ======");

	}
}
