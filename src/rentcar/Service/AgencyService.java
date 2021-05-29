package rentcar.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import rentcar.Entity.Agency;
import rentcar.Entity.Employee;
import rentcar.Entity.Vehicle;

public class AgencyService {
	
	/**
	 * 
	 * @param agency
	 * @return
	 */
	public static int addAgency(final Agency agency) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("INSERT INTO agency(name, phone_number, streetAddress, city, zipcode, gps_longitude, gps_latitude, maximum_places) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, agency.getName());
			stmt.setString(2, agency.getPhone_number());
			stmt.setString(3, agency.getStreet_address());
			stmt.setString(4, agency.getCity());
			stmt.setString(5, agency.getZipcode());
			stmt.setDouble(6, agency.getGps_longitude());
			stmt.setDouble(7, agency.getGps_latitude());
			int state = stmt.executeUpdate();
			DatabaseService.closeConnection();
			return state;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}	
	}
	
	/**
	 * Modifie les données d'une agence
	 * @param client
	 * @return
	 */
	public static int updateAgency(final Agency agency) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("UPDATE agency SET name = ?, phone_number = ?, streetAddress = ?, city = ?, zipcode = ?, gps_longitude = ?, gps_latitude = ?, maximum_places = ? WHERE id_agency = ?");
			stmt.setString(1, agency.getName());
			stmt.setString(2, agency.getPhone_number());
			stmt.setString(3, agency.getStreet_address());
			stmt.setString(4, agency.getCity());
			stmt.setString(5, agency.getZipcode());
			stmt.setDouble(6, agency.getGps_longitude());
			stmt.setDouble(7, agency.getGps_latitude());
			stmt.setLong(7,  agency.getId_agency());
			int state = stmt.executeUpdate();
			DatabaseService.closeConnection();
			return state;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}	
	}
	
	public static int removeAgency(final Agency agency) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("DELETE FROM agency WHERE id_agency = ?");
			stmt.setLong(1, agency.getId_agency());
			int state = stmt.executeUpdate();
			DatabaseService.closeConnection();
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
	public static List<Agency> getListAgencies() {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("SELECT * FROM agency");
			ResultSet rs = stmt.executeQuery();
			
			List<Agency> listAgencies = new LinkedList<Agency>();
			while(rs.next()) {
				Agency agency = new Agency(
						rs.getLong("id_agency"),
						rs.getString("name"),
						rs.getString("phone_number"),
						rs.getString("streetAddress"),
						rs.getString("city"),
						rs.getString("zipcode"),
						rs.getDouble("gps_longitude"),
						rs.getDouble("gps_latitude"),
						rs.getInt("maximum_places"),
						EmployeeService.getEmployeesOfAgency(rs.getLong("id_agency")),
						VehicleService.getVehiclesOfAgency(rs.getLong("id_agency")));
				listAgencies.add(agency);
			}
			DatabaseService.closeConnection();
			return listAgencies;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Récupère une agence spécifique
	 * @param id
	 * @return
	 */
	public static Agency getAgency(final long id) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("SELECT * FROM agency WHERE id_agency = ?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				return null;
			}
			Agency agency = new Agency(
					rs.getLong("id_agency"),
					rs.getString("name"),
					rs.getString("phone_number"),
					rs.getString("streetAddress"),
					rs.getString("city"),
					rs.getString("zipcode"),
					rs.getDouble("gps_longitude"),
					rs.getDouble("gps_latitude"),
					rs.getInt("maximum_places"),
					EmployeeService.getEmployeesOfAgency(rs.getLong("id_agency")),
					VehicleService.getVehiclesOfAgency(rs.getLong("id_agency")));
			DatabaseService.closeConnection();
			return agency;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	
}
