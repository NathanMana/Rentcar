package rentcar.Entity;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * Agence
 *
 */
public class Agency {
	
	private long id_agency;
	private String name;
	private String phone_number; 
	private String street_address;
	private String city;
	private String zipcode;
	private double gps_longitude;
	private double gps_latitude;
	private Integer maximum_places;
	
	/**
	 * Association avec Employee
	 */
	private LinkedList<Employee> listEmployees;
	
	/**
	 * Association avec Vehicle
	 */
	private LinkedList<Vehicle> listStoredVehicles;
	
	/**
	 * Association avec véhicule et driver
	 */
	private LinkedList<VehicleDivision> listVehiclesDivision;

	/**
	 * Constructeur pour le conteneur de service
	 * @param id_agency
	 * @param name
	 * @param phone_number
	 * @param street_address
	 * @param city
	 * @param zipcode
	 * @param gps_longitude
	 * @param gps_latitude
	 * @param maximum_places
	 * @param listEmployees
	 * @param listStoredVehicles
	 */
	public Agency(final long id_agency, final String name, final String phone_number, final String street_address, final String city, final String zipcode,
			final double gps_longitude, final double gps_latitude, final Integer maximum_places, final List<Employee> listEmployees,
			final List<Vehicle> listStoredVehicles) {
		this.id_agency = id_agency;
		this.name = name;
		this.phone_number = phone_number;
		this.street_address = street_address;
		this.city = city;
		this.zipcode = zipcode;
		this.gps_longitude = gps_longitude;
		this.gps_latitude = gps_latitude;
		this.maximum_places = maximum_places;
		this.listEmployees = new LinkedList<Employee>(listEmployees);
		this.listStoredVehicles = new LinkedList<Vehicle>(listStoredVehicles);
	}
	
	/**
	 * 
	 * @param name
	 * @param phone_number
	 * @param street_address
	 * @param city
	 * @param zipcode
	 * @param gps_longitude
	 * @param gps_latitude
	 * @param maximum_places
	 * @param listEmployees
	 * @param listStoredVehicles
	 */
	public Agency(final String name, final String phone_number, final String street_address, final String city, final String zipcode,
			final double gps_longitude, final double gps_latitude, final Integer maximum_places, final LinkedList<Employee> listEmployees,
			final LinkedList<Vehicle> listStoredVehicles) {
		this.name = name;
		this.phone_number = phone_number;
		this.street_address = street_address;
		this.city = city;
		this.zipcode = zipcode;
		this.gps_longitude = gps_longitude;
		this.gps_latitude = gps_latitude;
		this.maximum_places = maximum_places;
		this.listEmployees = new LinkedList<Employee>(listEmployees);
		this.listStoredVehicles = new LinkedList<Vehicle>(listStoredVehicles);
	}

	public long getId_agency() {
		return id_agency;
	}

	public void setId_agency(long id_agency) {
		this.id_agency = id_agency;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getStreet_address() {
		return street_address;
	}

	public void setStreet_address(String street_address) {
		this.street_address = street_address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public double getGps_longitude() {
		return gps_longitude;
	}

	public void setGps_longitude(double gps_longitude) {
		this.gps_longitude = gps_longitude;
	}

	public double getGps_latitude() {
		return gps_latitude;
	}

	public void setGps_latitude(double gps_latitude) {
		this.gps_latitude = gps_latitude;
	}

	public Integer getMaximum_places() {
		return maximum_places;
	}

	public void setMaximum_places(Integer maximum_places) {
		this.maximum_places = maximum_places;
	}

	public LinkedList<Employee> getListEmployees() {
		return listEmployees;
	}

	public void setListEmployees(final LinkedList<Employee> listEmployees) {
		this.listEmployees = new LinkedList<Employee>(listEmployees);
	}

	public LinkedList<Vehicle> getListStoredVehicles() {
		return listStoredVehicles;
	}

	public void setListStoredVehicles(final LinkedList<Vehicle> listStoredVehicles) {
		this.listStoredVehicles = new LinkedList<Vehicle>(listStoredVehicles);
	}
	
	public LinkedList<VehicleDivision> getListVehiclesDivision() {
		return listVehiclesDivision;
	}

	public void setListVehiclesDivision(LinkedList<VehicleDivision> listVehiclesDivision) {
		this.listVehiclesDivision = listVehiclesDivision;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		long temp;
		temp = Double.doubleToLongBits(gps_latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(gps_longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (id_agency ^ (id_agency >>> 32));
		result = prime * result + ((listEmployees == null) ? 0 : listEmployees.hashCode());
		result = prime * result + ((listStoredVehicles == null) ? 0 : listStoredVehicles.hashCode());
		result = prime * result + ((listVehiclesDivision == null) ? 0 : listVehiclesDivision.hashCode());
		result = prime * result + ((maximum_places == null) ? 0 : maximum_places.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone_number == null) ? 0 : phone_number.hashCode());
		result = prime * result + ((street_address == null) ? 0 : street_address.hashCode());
		result = prime * result + ((zipcode == null) ? 0 : zipcode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agency other = (Agency) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (Double.doubleToLongBits(gps_latitude) != Double.doubleToLongBits(other.gps_latitude))
			return false;
		if (Double.doubleToLongBits(gps_longitude) != Double.doubleToLongBits(other.gps_longitude))
			return false;
		if (id_agency != other.id_agency)
			return false;
		if (listEmployees == null) {
			if (other.listEmployees != null)
				return false;
		} else if (!listEmployees.equals(other.listEmployees))
			return false;
		if (listStoredVehicles == null) {
			if (other.listStoredVehicles != null)
				return false;
		} else if (!listStoredVehicles.equals(other.listStoredVehicles))
			return false;
		if (listVehiclesDivision == null) {
			if (other.listVehiclesDivision != null)
				return false;
		} else if (!listVehiclesDivision.equals(other.listVehiclesDivision))
			return false;
		if (maximum_places == null) {
			if (other.maximum_places != null)
				return false;
		} else if (!maximum_places.equals(other.maximum_places))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone_number == null) {
			if (other.phone_number != null)
				return false;
		} else if (!phone_number.equals(other.phone_number))
			return false;
		if (street_address == null) {
			if (other.street_address != null)
				return false;
		} else if (!street_address.equals(other.street_address))
			return false;
		if (zipcode == null) {
			if (other.zipcode != null)
				return false;
		} else if (!zipcode.equals(other.zipcode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Agency [id_agency=" + id_agency + ", name=" + name + ", phone_number=" + phone_number
				+ ", street_address=" + street_address + ", city=" + city + ", zipcode=" + zipcode + ", gps_longitude="
				+ gps_longitude + ", gps_latitude=" + gps_latitude + ", maximum_places=" + maximum_places
				+ ", listEmployees=" + listEmployees + ", listStoredVehicles=" + listStoredVehicles
				+ ", listVehiclesDivision=" + listVehiclesDivision + "]";
	}


}
