package rentcar.Entity;

import java.util.LinkedList;
import java.util.List;

public class Client extends Person {

	private long id_client;
	
	/**
	 * Association avec Loyaltyprogram
	 */
	private List<Subscribe> listSubscription;
	
	/**
	 * Association de location avec véhicule 
	 */
	private List<LocationReservation> listLocationReservations;
	
	/**
	 * Association de retour de la location
	 */
	private List<LocationReservationReturned> listLocationReservationReturned;
	
	/**
	 * Laisser le controleur de Service utiliser ce constructeur
	 * @param id_client
	 * @param name
	 * @param firstname
	 * @param street_address
	 * @param city
	 * @param zipcode
	 * @param phone_number
	 */
	public Client(final long id_client, final String name, final String firstname, final String street_address, final String city, 
			final String zipcode, final String phone_number) {
		super(name, firstname, street_address, city, zipcode, phone_number);
		this.id_client = id_client;
	}
	
	/**
	 * A utiliser quand on veut ajouter une personne en BDD
	 * @param name
	 * @param firstname
	 * @param street_address
	 * @param city
	 * @param zipcode
	 * @param phone_number
	 */
	public Client(final String name, final String firstname, final String street_address, final String city, final String zipcode, final String phone_number) {
		super(name, firstname, street_address, city, zipcode, phone_number);
	}
	
	public long getIdClient() {
		return this.id_client;
	}
	
	
	public List<Subscribe> getListSubscription() {
		return listSubscription;
	}

	public void setListSubscription(List<Subscribe> listSubscription) {
		this.listSubscription = listSubscription;
	}
	
	public List<LocationReservation> getListLocationReservations() {
		return listLocationReservations;
	}

	public void setListLocationReservations(List<LocationReservation> listLocationReservations) {
		this.listLocationReservations = listLocationReservations;
	}

	public List<LocationReservationReturned> getListLocationReservationReturned() {
		return listLocationReservationReturned;
	}

	public void setListLocationReservationReturned(List<LocationReservationReturned> listLocationReservationReturned) {
		this.listLocationReservationReturned = listLocationReservationReturned;
	}

	@Override
	public String toString() {
		return "Client [id_client=" + id_client + ", name=" + name + ", firstname=" + firstname + ", street_address="
				+ street_address + ", city=" + city + ", zipcode=" + zipcode + ", phone_number=" + phone_number + "]";
	}
	
}
