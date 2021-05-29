package rentcar.Entity;

import rentcar.Service.AgencyService;
import rentcar.Service.DriverService;
import rentcar.Service.EmployeeService;

/**
 * Représente un employé de l'entreprise
 *
 */
public class Employee extends Person {

	private long id_employee;
	
	/**
	 * Indique si l'utilisateur est un administrateur
	 */
	private boolean role;
	
	/**
	 * Indique si l'utilisateur est un conducteur
	 */
	private boolean isDriver = false;
	
	private String username;
	
	/**
	 * Pour des questions de facilité et rapidité, nous stockons le mdp en clair
	 * Bien évidemment, dans un réel projet professionnel cela ne doit pas arriver
	 */
	private String password;
	
	/**
	 * Association avec Agency
	 */
	private long id_agency;
	
	private String code;
	
	/**
	 * 
	 * @param name
	 * @param firstname
	 * @param street_address
	 * @param city
	 * @param zipcode
	 * @param phone_number
	 * @param role
	 * @param username
	 * @param password
	 * @param id_agency
	 */
	public Employee(final String name, final String firstname, final String street_address, final String city, final String zipcode, final String phone_number, 
			final boolean role, final String username, final String password, final long id_agency) {
		super(name, firstname, street_address, city, zipcode, phone_number);
		this.role = role;
		this.username = username;
		this.password = password;
		this.id_agency = id_agency;
	}
	
	public Employee(final long id_employee, final String name, final String firstname, final String street_address, final String city, final String zipcode, final String phone_number, 
			final boolean role, final String username, final String password, final long id_agency) {
		super(name, firstname, street_address, city, zipcode, phone_number);
		this.id_employee = id_employee;
		this.role = role;
		this.username = username;
		this.password = password;
		this.id_agency = id_agency;
	}
	
	
	public Employee() {
		super();
	}

	public long getIdAgency() {
		return id_agency;
	}
	
	public Agency getAgency() {
		return AgencyService.getAgency(id_agency);
	}

	public void setIdAgency(long id_agency) {
		this.id_agency = id_agency;
	}

	public long getIdEmployee() {
		return this.id_employee;
	}

	public boolean getRole() {
		return role;
	}

	public void setRole(boolean role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Vérifie si l'employé est un conducteur
	 * @return
	 */
	public boolean isDriver() {
		return DriverService.isDriver(id_employee);
	}
	
	public boolean getDriver() {
		return isDriver;
	}

	public void setDriver(boolean isDriver) {
		this.isDriver = isDriver;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (int) (id_agency ^ (id_agency >>> 32));
		result = prime * result + (int) (id_employee ^ (id_employee >>> 32));
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + (role ? 1231 : 1237);
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (id_agency != other.id_agency)
			return false;
		if (id_employee != other.id_employee)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role != other.role)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [id_employee=" + id_employee + ", role=" + role + ", username=" + username + ", password="
				+ password + ", id_agency=" + id_agency + ", codeConnection = " + code + "]";
	}

	

	
	
}
