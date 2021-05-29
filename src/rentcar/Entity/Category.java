package rentcar.Entity;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * Catégorie d'une voiture
 *
 */
public class Category {

	@Override
	public String toString() {
		return "Category [id_category=" + id_category + ", price=" + price + ", bail=" + bail + ", name=" + name
				+ ", listVehicles=" + listVehicles + "]";
	}

	private long id_category;
	private double price;
	private double bail;
	private String name;
	
	/**
	 * Association avec les véhicules
	 */
	private List<Vehicle> listVehicles;
	 
	/**
	 * 
	 * @param id_category
	 * @param price
	 * @param bail
	 */
	public Category(final long id_category, final String name, final double price, final double bail) {
		this.id_category = id_category;
		this.name = name;
		this.price = price;
		this.bail = bail;
	}
	
	public List<Vehicle> getListVehicles() {
		return listVehicles;
	}

	public void setListVehicles(List<Vehicle> listVehicles) {
		this.listVehicles = new LinkedList<Vehicle>(listVehicles);
	}

	public long getIdCategory() {
		return this.id_category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getBail() {
		return bail;
	}

	public void setBail(double bail) {
		this.bail = bail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(bail);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (id_category ^ (id_category >>> 32));
		result = prime * result + ((listVehicles == null) ? 0 : listVehicles.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Category other = (Category) obj;
		if (Double.doubleToLongBits(bail) != Double.doubleToLongBits(other.bail))
			return false;
		if (id_category != other.id_category)
			return false;
		if (listVehicles == null) {
			if (other.listVehicles != null)
				return false;
		} else if (!listVehicles.equals(other.listVehicles))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		return true;
	}
	
	
	
}
