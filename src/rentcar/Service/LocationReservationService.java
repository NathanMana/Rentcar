package rentcar.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import rentcar.Entity.Vehicle;

public class LocationReservationService {

	/*
	public static getListLocationReservationForVehicle(final String numberPlate) {
		PreparedStatement stmt =  DatabaseService.prepareStatement("SELECT * FROM location_reservation WHERE number_plate = ?");
		stmt.setString(1, numberPlate);
		ResultSet rsVehicles = stmt.executeQuery();
		List<Vehicle> vehicles = new LinkedList<Vehicle>();
		while(rsVehicles.next()) {
			vehicles.add(VehicleService.convertResultSet(rsVehicles));
		}
		return vehicles;
	}
	*/
	
}
