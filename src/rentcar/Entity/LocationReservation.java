package rentcar.Entity;

import java.util.Date;

public class LocationReservation {

	private long id_vehicle;
	private long id_client;
	private Date date_location;
	private int duration;
	private boolean isAssured;
	
	/**
	 * 
	 * @param id_vehicle
	 * @param id_client
	 * @param date_location
	 * @param duration
	 * @param isAssured
	 */
	public LocationReservation(long id_vehicle, long id_client, Date date_location, int duration, boolean isAssured) {
		this.id_vehicle = id_vehicle;
		this.id_client = id_client;
		this.date_location = date_location;
		this.duration = duration;
		this.isAssured = isAssured;
	}

	public long getId_vehicle() {
		return id_vehicle;
	}

	public long getId_client() {
		return id_client;
	}

	public Date getDate_location() {
		return date_location;
	}

	public void setDate_location(Date date_location) {
		this.date_location = date_location;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public boolean isAssured() {
		return isAssured;
	}

	public void setAssured(boolean isAssured) {
		this.isAssured = isAssured;
	}
	

	
	
	
	
}
