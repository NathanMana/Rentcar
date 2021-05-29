package rentcar.Entity;

import java.util.LinkedList;

/**
 * 
 * Chauffeur qui déplace les voitures
 *
 */
public class Driver {

	private long id_driver;
	private Employee employee;
	
	/**
	 * Association avec véhicule et driver
	 */
	private LinkedList<VehicleDivision> listVehiclesDivision;
	
	/**
	 * 
	 * @param id_employee
	 */
	public Driver(final Employee employee, final LinkedList<VehicleDivision> listVehiclesDivision) {
		this.employee = employee;
		this.listVehiclesDivision = new LinkedList<VehicleDivision>(listVehiclesDivision);
	}
	
	/**
	 * 
	 * @param id_driver
	 * @param id_employee
	 */
	public Driver(final long id_driver, final Employee employee) {
		this.employee = employee;
		this.id_driver = id_driver;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public long getId_driver() {
		return id_driver;
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
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + (int) (id_driver ^ (id_driver >>> 32));
		result = prime * result + ((listVehiclesDivision == null) ? 0 : listVehiclesDivision.hashCode());
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
		Driver other = (Driver) obj;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (id_driver != other.id_driver)
			return false;
		if (listVehiclesDivision == null) {
			if (other.listVehiclesDivision != null)
				return false;
		} else if (!listVehiclesDivision.equals(other.listVehiclesDivision))
			return false;
		return true;
	}
	
	
	
}
