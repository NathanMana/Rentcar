package rentcar.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import rentcar.Entity.Driver;

public class DriverService {
	
	/**
	 * 
	 * @param category
	 * @return
	 */
	public static int addDriver(final Driver driver) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("INSERT INTO driver(id_employee) VALUES (?)");
			stmt.setDouble(1, driver.getEmployee().getIdEmployee());
			int state = stmt.executeUpdate();
			DatabaseService.closeConnection();
			return state;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}	
	}
	
	/**
	 * @param driver
	 */
	public static int removeDriver(final Driver driver) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("DELETE FROM driver WHERE id_driver = ?");
			stmt.setLong(1, driver.getId_driver());
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
	 * Récupère la liste des conducteurs
	 * @return List<Driver>
	 */
	public static List<Driver> getListDrivers() {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("SELECT * FROM driver, employee WHERE driver.id_employee = employee.id_employee");
			ResultSet rs = stmt.executeQuery();
			
			List<Driver> listDrivers = new LinkedList<Driver>();
			while(rs.next()) {
				Driver driver = new Driver(
						rs.getLong("id_category"),
						EmployeeService.convertResultSet(rs));
				listDrivers.add(driver);
			}
			DatabaseService.closeConnection();
			return listDrivers;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Récupère une catégorie spécifique
	 * @param id
	 * @return
	 */
	public static Driver getDriver(final long id) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("SELECT * FROM driver WHERE id_driver = ?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				return null;
			}
			Driver driver = new Driver(
					rs.getLong("id_driver"),
					EmployeeService.convertResultSet(rs));
			DatabaseService.closeConnection();
			return driver;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Regarde si l'emmployé donné est un conducteur
	 * @param id
	 * @return
	 */
	public static boolean isDriver(final long id) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("SELECT id_driver FROM driver WHERE id_employee = ?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				return false;
			}
			DatabaseService.closeConnection();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
}
