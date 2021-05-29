package rentcar.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import rentcar.Entity.Agency;
import rentcar.Entity.Vehicle;
import rentcar.Enum.FuelType_Enum;
import rentcar.Enum.VehicleState_Enum;

public class VehicleService {

	/**
	 * Permet de convertir
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public static Vehicle convertResultSet(ResultSet rs) throws SQLException {
		Vehicle vehicle = new Vehicle(
				rs.getString("numberplate"),
				rs.getDouble("mileage"),
				rs.getBoolean("type_of_gearbox"),
				rs.getBoolean("is_air_conditioned"),
				FuelType_Enum.valueOf(rs.getString("fuel_type")),
				VehicleState_Enum.valueOf(rs.getString("state")),
				rs.getString("brand"),
				rs.getString("model"),
				rs.getLong("id_agency"),
				rs.getLong("id_category")
		);		
		return vehicle;
	}
	
	/**
	 * Récupère la liste des véhciles de l'agence
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public static List<Vehicle> getVehiclesOfAgency(final long id) throws SQLException {
		PreparedStatement stmtVehicles =  DatabaseService.prepareStatement("SELECT * FROM vehicle WHERE id_agency = ?");
		stmtVehicles.setLong(1, id);
		ResultSet rsVehicles = stmtVehicles.executeQuery();
		List<Vehicle> vehicles = new LinkedList<Vehicle>();
		while(rsVehicles.next()) {
			vehicles.add(VehicleService.convertResultSet(rsVehicles));
		}
		return vehicles;
	}
	
	/**
	 * 
	 * @param vehicle
	 * @return
	 */
	public static int addVehicle(final Vehicle vehicle) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("INSERT INTO vehicle(numberplate, mileage, type_of_gearbox, is_air_conditioned, fuel_type, state, brand, model, id_agency, id_category) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, vehicle.getNumber_plate());
			stmt.setDouble(2, vehicle.getMileage());
			stmt.setBoolean(3, vehicle.isType_of_gearbox());
			stmt.setBoolean(4, vehicle.isIs_air_conditionned());
			stmt.setString(5, vehicle.getFuel_type().toString());
			stmt.setString(6, vehicle.getState().toString());
			stmt.setString(7, vehicle.getBrand());
			stmt.setString(8, vehicle.getModel());
			stmt.setLong(9, vehicle.getIdAgency());
			stmt.setLong(10, vehicle.getIdCategory());
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
	 * Modifie les données d'un véhicule
	 * @param client
	 * @return
	 */
	public static int updateVehicle(final Vehicle vehicle) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("UPDATE vehicle SET numberplate = ?, mileage = ?, type_of_gearbox = ?, is_air_conditioned = ?, fuel_type = ?, state = ?, brand = ?, model = ?, id_agency = ?, id_category = ? WHERE numberplate = ?");
			stmt.setString(1, vehicle.getNumber_plate());
			stmt.setDouble(2, vehicle.getMileage());
			stmt.setBoolean(3, vehicle.isType_of_gearbox());
			stmt.setBoolean(4, vehicle.isIs_air_conditionned());
			stmt.setString(5, vehicle.getFuel_type().toString());
			stmt.setString(6, vehicle.getState().toString());
			stmt.setString(7, vehicle.getBrand());
			stmt.setString(8, vehicle.getModel());
			stmt.setLong(9, vehicle.getIdAgency());
			stmt.setLong(10, vehicle.getIdCategory());
			stmt.setString(11, vehicle.getNumber_plate());
			System.out.println(vehicle.getNumber_plate());
			int state = stmt.executeUpdate();
			DatabaseService.closeConnection();
			return state;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}	
	}
	
	public static List<Vehicle> getListVehiclesForSearch(String cat, String brand, boolean isRented) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			if (isRented) {
				stmt = DatabaseService.prepareStatement("SELECT * FROM vehicle NATURAL JOIN category LEFT JOIN location_reservation ON vehicle.numberplate = location_reservation.numberplate WHERE category.name = ? AND vehicle.brand LIKE ?");
			} else {
				stmt = DatabaseService.prepareStatement("SELECT * FROM vehicle NATURAL JOIN category WHERE category.name LIKE ? AND vehicle.brand LIKE ?");	
			}
			stmt.setString(1, ("%" + cat));
			stmt.setString(2, ("%" + brand));
			
			ResultSet rs = stmt.executeQuery();
			List<Vehicle> listVehicles = new LinkedList<Vehicle>();
			while(rs.next()) {
				Vehicle vehicle = new Vehicle(
						rs.getString("numberplate"),
						rs.getDouble("mileage"),
						rs.getBoolean("type_of_gearbox"),
						rs.getBoolean("is_air_conditioned"),
						FuelType_Enum.valueOf(rs.getString("fuel_type")),
						VehicleState_Enum.valueOf(rs.getString("state")),
						rs.getString("brand"),
						rs.getString("model"),
						rs.getLong("id_agency"),
						rs.getLong("id_category")
						);
				
				listVehicles.add(vehicle);
			}
			DatabaseService.closeConnection();
			return listVehicles;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static int removeVehicle(final Vehicle vehicle) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("DELETE FROM vehicle WHERE numberplate = ?");
			stmt.setString(1, vehicle.getNumber_plate());
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
	 * Récupère la liste des voitures
	 * @return List<Vehicle>
	 */
	public static List<Vehicle> getListVehicles() {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("SELECT * FROM vehicle");
			ResultSet rs = stmt.executeQuery();
			
			List<Vehicle> listVehicles = new LinkedList<Vehicle>();
			while(rs.next()) {
				Vehicle vehicle = new Vehicle(
						rs.getString("numberplate"),
						rs.getDouble("mileage"),
						rs.getBoolean("type_of_gearbox"),
						rs.getBoolean("is_air_conditioned"),
						FuelType_Enum.valueOf(rs.getString("fuel_type")),
						VehicleState_Enum.valueOf(rs.getString("state")),
						rs.getString("brand"),
						rs.getString("model"),
						rs.getLong("id_agency"),
						rs.getLong("id_category")
						);
				
				listVehicles.add(vehicle);
			}
			DatabaseService.closeConnection();
			return listVehicles;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Récupère un véhicule spécifique
	 * @param id
	 * @return
	 */
	public static Vehicle getVehicle(final long id) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("SELECT * FROM vehicle WHERE numberplate = ?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				return null;
			}
			Vehicle vehicle = new Vehicle(
					rs.getString("numberplate"),
					rs.getDouble("mileage"),
					rs.getBoolean("type_of_gearbox"),
					rs.getBoolean("is_air_conditioned"),
					FuelType_Enum.valueOf(rs.getString("fuel_type")),
					VehicleState_Enum.valueOf(rs.getString("state")),
					rs.getString("brand"),
					rs.getString("model"),
					rs.getLong("id_agency"),
					rs.getLong("id_category")
					);
			DatabaseService.closeConnection();
			return vehicle;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
