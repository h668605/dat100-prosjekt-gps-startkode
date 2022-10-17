package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 600;
	private static int MAPYSIZE = 600;

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
		
		// TODO - START
		
		double maxlon = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		ystep = MAPYSIZE / (Math.abs(maxlon - minlon)); 

		return ystep;

		// TODO - SLUTT
		
	}

	public void showRouteMap(int ybase) {

		// TODO - START
		double startx = 0;
		double starty = 0;
		double ystep = ystep();
		double xstep = xstep();
		
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		
		starty = ybase - (Math.abs(gpspoints[0].getLatitude() - minlat)*ystep);
		startx = MARGIN + (Math.abs(gpspoints[0].getLongitude() - minlon)*xstep);
		
		for(int i = 1;i<gpspoints.length;i++) {
			double endy = ybase - (Math.abs(gpspoints[i].getLatitude() - minlat)*ystep);
			double endx = MARGIN + (Math.abs(gpspoints[i].getLongitude()-minlon)*xstep);
			
			setColor(0,255,0);
			drawLine((int)startx,(int)starty,(int)endx,(int)endy);
			fillCircle((int)startx,(int)starty,3);
			
			startx  = endx;
			starty = endy;
			if (i == gpspoints.length-1) {
				setColor(0,0,255);
				fillCircle((int)startx,(int)starty,5);
			}
			
		}
		
		// TODO - SLUTT
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Courier",12);
		
		// TODO - START
		
		String time = ("Total time: "+GPSUtils.formatTime(gpscomputer.totalTime()));
		drawString(time,TEXTDISTANCE,TEXTDISTANCE);
		
		String distance = ("Total distance:"+GPSUtils.formatDouble((gpscomputer.totalDistance())/1000)+" km");
		drawString(distance,TEXTDISTANCE,TEXTDISTANCE*2);
		
		String totalElevation = ("Total elevation: "+GPSUtils.formatDouble(gpscomputer.totalElevation())+" m");
		drawString(totalElevation,TEXTDISTANCE,TEXTDISTANCE*3);
		
		String maxSpeed = ("MAX Speed: "+GPSUtils.formatDouble(gpscomputer.maxSpeed())+" km/t");
		drawString(maxSpeed,TEXTDISTANCE,TEXTDISTANCE*4);
		
		String averageSpeed = ("Average Speed: "+GPSUtils.formatDouble(gpscomputer.averageSpeed())+" km/t");
		drawString(averageSpeed,TEXTDISTANCE,TEXTDISTANCE*5);
		
		String energy = ("Energy: "+gpscomputer.totalKcal(80)+" kcal");
		drawString(energy,TEXTDISTANCE,TEXTDISTANCE*6);
		
		// TODO - SLUTT;
	}

}
