package rentcar.Service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import rentcar.Entity.Client;
import rentcar.Entity.Loyalty_program;

public class LoyaltyProgramService {

	public static int addLoyaltyProgram(final Loyalty_program loyalty_program) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("INSERT INTO loyaltyprogram(duration, description, price, reduction_rate, expiration_date) VALUES (?, ?, ?, ?, ?)");
			stmt.setInt(1, loyalty_program.getDuration());
			stmt.setString(2, loyalty_program.getDescription());
			stmt.setDouble(3, loyalty_program.getPrice());
			stmt.setDouble(4, loyalty_program.getReduction_rate());
			stmt.setDate(5, new Date(loyalty_program.getExpiration_date().getTime()));
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
	 * Modifie les données d'un programme de fidélité
	 * @param client
	 * @return
	 */
	public static int updateLoyaltyProgram(final Loyalty_program loyalty_program) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("UPDATE loyaltyprogram SET duration = ?, description = ?, price = ?, reduction_rate = ?, expiration_date = ? WHERE id_loyaltyprogram = ?");
			stmt.setInt(1, loyalty_program.getDuration());
			stmt.setString(2, loyalty_program.getDescription());
			stmt.setDouble(3, loyalty_program.getPrice());
			stmt.setDouble(4, loyalty_program.getReduction_rate());
			stmt.setDate(5, new Date(loyalty_program.getExpiration_date().getTime()));
			stmt.setLong(6, loyalty_program.getIdLoyaltyProgram());
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
	 * Supprime un programme de fidélité
	 * @param client
	 * @return
	 */
	public static int removeLoyaltyProgram(final Loyalty_program loyalty_program) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("DELETE FROM loyaltyprogram WHERE id_loyaltyprogram = ?");
			stmt.setLong(1, loyalty_program.getIdLoyaltyProgram());
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
	public static List<Loyalty_program> getListLoyaltyPrograms() {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("SELECT * FROM loyaltyprogram");
			ResultSet rs = stmt.executeQuery();
			List<Loyalty_program> listLoyaltyPrograms = new LinkedList<Loyalty_program>();
			while(rs.next()) {
				Loyalty_program loyalty_program = new Loyalty_program(
						rs.getLong("id_loyaltyprogram"),
						rs.getInt("duration"),
						rs.getString("description"),
						rs.getDouble("price"),
						rs.getDouble("reduction_rate"),
						rs.getDate("expiration_date"));
				listLoyaltyPrograms.add(loyalty_program);
			}
			DatabaseService.closeConnection();
			return listLoyaltyPrograms;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Récupère un programme spécifique
	 * @param id
	 * @return
	 */
	public static Loyalty_program getLoyaltyProgram(final long id) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("SELECT * FROM loyaltyprogram WHERE id_loyaltyprogram = ?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				return null;
			}
			Loyalty_program loyalty_program = new Loyalty_program(
					rs.getLong("id_loyaltyprogram"),
					rs.getInt("duration"),
					rs.getString("description"),
					rs.getDouble("price"),
					rs.getDouble("reduction_rate"),
					rs.getDate("expiration_date"));
			DatabaseService.closeConnection();
			return loyalty_program;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
