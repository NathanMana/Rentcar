package rentcar.Entity;

import java.util.Date;

/**
 * Association entre véhicule, agence et date
 */
public class LocationReservationReturned {
	
	private long id_agency;
	private long id_client;
	private long id_vehicle;
	private double fuel_amount;
	private int state;
	private Date date;
	
	/**
	 * 
	 * @param id_client
	 * @param id_vehicle
	 * @param fuel_amount
	 * @param state
	 * @param date
	 */
	public LocationReservationReturned(long id_agency, long id_client, long id_vehicle, double fuel_amount, int state, Date date) {
		this.id_agency = id_agency;
		this.id_client = id_client;
		this.id_vehicle = id_vehicle;
		this.fuel_amount = fuel_amount;
		this.state = state;
		this.date = date;
	}

	public double getFuel_amount() {
		return fuel_amount;
	}

	public long getId_agency() {
		return id_agency;
	}

	public void setFuel_amount(double fuel_amount) {
		this.fuel_amount = fuel_amount;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getId_client() {
		return id_client;
	}

	public long getId_vehicle() {
		return id_vehicle;
	}
	
	

}
