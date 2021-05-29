package rentcar.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import rentcar.Entity.Client;

public class ClientService {
	
	public static int addClient(final Client client) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("INSERT INTO client(name, firstname, streetAddress, city, zipcode, phone_number) VALUES (?, ?, ?, ?, ?, ?)");
			stmt.setString(1, client.getName());
			stmt.setString(2, client.getFirstname());
			stmt.setString(3, client.getStreet_address());
			stmt.setString(4, client.getCity());
			stmt.setString(5, client.getZipcode());
			stmt.setString(6, client.getPhone_number());
			int state = stmt.executeUpdate();
			return state;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}	
	}
	
	/**
	 * Modifie les données d'un client
	 * @param client
	 * @return
	 */
	public static int updateClient(final Client client) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("UPDATE client SET name = ?, firstname = ?, streetAddress = ?, city = ?, zipcode = ?, phone_number = ? WHERE id_client = ?");
			stmt.setString(1, client.getName());
			stmt.setString(2, client.getFirstname());
			stmt.setString(3, client.getStreet_address());
			stmt.setString(4, client.getCity());
			stmt.setString(5, client.getZipcode());
			stmt.setString(6, client.getPhone_number());
			stmt.setLong(7,  client.getIdClient());
			int state = stmt.executeUpdate();
			return state;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}	
	}
	
	public static int removeClient(final Client client) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("DELETE FROM client WHERE id_client = ?");
			stmt.setLong(1, client.getIdClient());
			int state = stmt.executeUpdate();
			return state;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}
	
	/**
	 * Récupère la liste des clients
	 * @return List<Client>
	 */
	public static List<Client> getListClients() {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("SELECT * FROM client");
			ResultSet rs = stmt.executeQuery();
			List<Client> listClients = new LinkedList<Client>();
			while(rs.next()) {
				Client client = new Client(
						rs.getLong("id_client"),
						rs.getString("name"),
						rs.getString("firstname"),
						rs.getString("streetAddress"),
						rs.getString("city"),
						rs.getString("zipcode"),
						rs.getString("phone_number"));
				listClients.add(client);
			}
			DatabaseService.closeConnection();
			return listClients;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Récupère un client spécifique
	 * @param id
	 * @return
	 */
	public static Client getClient(final long id) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("SELECT * FROM client WHERE id_client = ?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				return null;
			}
			Client client = new Client(rs.getLong("id_client"),
					rs.getString("name"),
					rs.getString("firstname"),
					rs.getString("streetAddress"),
					rs.getString("city"),
					rs.getString("zipcode"),
					rs.getString("phone_number"));
			DatabaseService.closeConnection();
			return client;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
}
