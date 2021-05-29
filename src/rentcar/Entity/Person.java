package rentcar.Entity;

/**
 * 
 * Classe mère personne
 *
 */
public class Person {

	protected String name;
	protected String firstname;
	protected String street_address;
	protected String city;
	protected String zipcode;
	protected String phone_number;
	
	/**
	 * Constructeur de la classe mère Personne
	 * @param name
	 * @param firstname
	 * @param street_address
	 * @param city
	 * @param zipcode
	 * @param phone_number
	 */
	public Person(final String name, final String firstname, final String street_address, final String city, final String zipcode, final String phone_number) {
		this.firstname = firstname;
		this.name = name;
		this.street_address = street_address;
		this.city = city;
		this.zipcode = zipcode;
		this.phone_number =  phone_number;
	}
	
	/**
	 * Constructeur simple
	 */
	public Person() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
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

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
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
		Person other = (Person) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
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
		return "Person [name=" + name + ", firstname=" + firstname + ", street_address=" + street_address + ", city="
				+ city + ", zipcode=" + zipcode + ", phone_number=" + phone_number + "]";
	}
	
	
	
}
