package rentcar.Entity;

public class VehicleDivision {
	
	private Agency agency;
	private Vehicle vehicle;
	private Driver driver;
	
	/**
	 * 
	 * @param agency
	 * @param vehicle
	 * @param driver
	 */
	public VehicleDivision(Agency agency, Vehicle vehicle, Driver driver) {
		this.agency = agency;
		this.vehicle = vehicle;
		this.driver = driver;
	}

	public Agency getAgency() {
		return agency;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public Driver getDriver() {
		return driver;
	}
	

}
