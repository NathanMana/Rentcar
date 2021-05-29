package rentcar.Entity;

import java.util.LinkedList;
import java.util.List;

import rentcar.Enum.FuelType_Enum;
import rentcar.Enum.VehicleState_Enum;

/**
 * Classe véhicule
 *
 */
public class Vehicle {

	private String number_plate;
	private double mileage;
	private boolean type_of_gearbox;
	private boolean is_air_conditionned;
	private FuelType_Enum fuel_type;
	private VehicleState_Enum state;
	private String brand;
	private String model;
	
	/**
	 * Association avec Agency
	 */
	private long id_agency;
	
	/**
	 * Association avec category
	 */
	private long id_category;
	
	/**
	 * Association avec le client correspondant à la location
	 */
	private List<LocationReservation> listLocationReservation;
	
	/**
	 * Association avec le client correspondant au retour de la location
	 */
	private List<LocationReservationReturned> listLocationReservationReturned;
	
	/**
	 * Association avec agence et driver
	 */
	private LinkedList<VehicleDivision> listVehiclesDivision;

	/**
	 * 
	 * @param number_plate
	 * @param mileage
	 * @param type_of_gearbox
	 * @param is_air_conditionned
	 * @param fuel_type
	 * @param state
	 * @param brand
	 * @param model
	 * @param id_agency
	 */
	public Vehicle(final String number_plate, final double mileage, final boolean type_of_gearbox, final boolean is_air_conditionned,
			final FuelType_Enum fuel_type,final VehicleState_Enum state, final String brand, final String model, final long id_agency,
			final long id_category) {
		this.number_plate = number_plate;
		this.mileage = mileage;
		this.type_of_gearbox = type_of_gearbox;
		this.is_air_conditionned = is_air_conditionned;
		this.fuel_type = fuel_type;
		this.state = state;
		this.brand = brand;
		this.model = model;
		this.id_agency = id_agency;
		this.id_category = id_category;
	}

	public Vehicle() {
		// TODO Auto-generated constructor stub
	}

	public String getNumber_plate() {
		return number_plate;
	}

	public void setNumber_plate(String number_plate) {
		this.number_plate = number_plate;
	}

	public double getMileage() {
		return mileage;
	}

	public void setMileage(double mileage) {
		this.mileage = mileage;
	}

	public boolean isType_of_gearbox() {
		return type_of_gearbox;
	}

	public void setType_of_gearbox(boolean type_of_gearbox) {
		this.type_of_gearbox = type_of_gearbox;
	}

	public boolean isIs_air_conditionned() {
		return is_air_conditionned;
	}

	public void setIs_air_conditionned(boolean is_air_conditionned) {
		this.is_air_conditionned = is_air_conditionned;
	}

	public FuelType_Enum getFuel_type() {
		return fuel_type;
	}

	public void setFuel_type(FuelType_Enum fuel_type) {
		this.fuel_type = fuel_type;
	}

	public VehicleState_Enum getState() {
		return state;
	}

	public void setState(VehicleState_Enum state) {
		this.state = state;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public List<LocationReservation> getListLocationReservation() {
		return listLocationReservation;
	}

	public void setListLocationReservation(List<LocationReservation> listLocationReservation) {
		this.listLocationReservation = listLocationReservation;
	}

	public List<LocationReservationReturned> getListLocationReservationReturned() {
		return listLocationReservationReturned;
	}

	public void setListLocationReservationReturned(List<LocationReservationReturned> listLocationReservationReturned) {
		this.listLocationReservationReturned = listLocationReservationReturned;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public long getIdAgency() {
		return id_agency;
	}

	public void setAgency(long agency) {
		this.id_agency = agency;
	}

	public long getIdCategory() {
		return id_category;
	}

	public void setCategory(long category) {
		this.id_category = category;
	}

	public LinkedList<VehicleDivision> getListVehiclesDivision() {
		return listVehiclesDivision;
	}

	public void setListVehiclesDivision(LinkedList<VehicleDivision> listVehiclesDivision) {
		this.listVehiclesDivision = listVehiclesDivision;
	}

	@Override
	public String toString() {
		return "Vehicle [number_plate=" + number_plate + ", mileage=" + mileage + ", type_of_gearbox=" + type_of_gearbox
				+ ", is_air_conditionned=" + is_air_conditionned + ", fuel_type=" + fuel_type + ", state=" + state
				+ ", brand=" + brand + ", model=" + model + ", agency=" + id_agency + ", category=" + id_category
				+ ", listLocationReservation=" + listLocationReservation + ", listLocationReservationReturned="
				+ listLocationReservationReturned + ", listVehiclesDivision=" + listVehiclesDivision + "]";
	}

	
	
}
