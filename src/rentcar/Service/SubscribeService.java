package rentcar.Service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import rentcar.Entity.Client;
import rentcar.Entity.Loyalty_program;

public class SubscribeService {

	/**
	 * Ajoute une adhésion
	 * @param client
	 * @param loyalty_program
	 * @return
	 */
	public static int addSuscribe(final Client client, final Loyalty_program loyalty_program) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("INSERT INTO subscribe(id_loyaltyprogram, id_client, joining_date) VALUES (?, ?, ?)");
			stmt.setLong(1, loyalty_program.getIdLoyaltyProgram());
			stmt.setLong(2, client.getIdClient());
			Date dateSuscribtion = new Date(System.currentTimeMillis());
			stmt.setDate(3, dateSuscribtion);
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
	 * Modifie la date de l'adhésion à un programme de fidélité
	 * @param client
	 * @return
	 */
	public static int updateSubscribe(final long idClient, final long idLoyaltyProgram, final Date date) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("UPDATE subscribe SET joining_date = ? WHERE id_client = ? AND id_loyaltyprogram = ?");
			stmt.setDate(1, date);
			stmt.setLong(2, idClient);
			stmt.setLong(3, idLoyaltyProgram);
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
	 * Supprime une adhésion
	 * @param client
	 * @return
	 */
	public static int removeSubscribe(final long idClient, final long idLoyaltyProgram) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("DELETE FROM subscribe WHERE id_loyaltyprogram = ? AND id_client = ?");
			stmt.setLong(1, idLoyaltyProgram);
			stmt.setLong(2, idClient);
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
	 * Récupère la liste des adhésions
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
