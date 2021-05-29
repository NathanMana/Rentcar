package rentcar.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import rentcar.Entity.Agency;
import rentcar.Entity.Employee;

public class EmployeeService extends DatabaseService {

	/**
	 * Permet de convertir en employé
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public static Employee convertResultSet(ResultSet rs) throws SQLException {
		Employee employee = new Employee(
				rs.getString("name"),
				rs.getString("firstname"),
				rs.getString("streetAddress"),
				rs.getString("city"),
				rs.getString("zipcode"),
				rs.getString("phone_number"),
				rs.getBoolean("role"),
				rs.getString("username"),
				rs.getString("password"),
				rs.getLong("id_agency")
		);		
		return employee;
	}
	
	/**
	 * Récupère la liste des employés d'une agence
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public static List<Employee> getEmployeesOfAgency(final long id) throws SQLException {
		PreparedStatement stmtEmployees =  con.prepareStatement("SELECT * FROM employee WHERE id_agency = ?");
		stmtEmployees.setLong(1, id);
		ResultSet rsEmployees = stmtEmployees.executeQuery();
		List<Employee> employees = new LinkedList<Employee>();
		while(rsEmployees.next()) {
			employees.add(EmployeeService.convertResultSet(rsEmployees));
		}
		return employees;
	}
	
	/**
	 * 
	 * @param agency
	 * @return
	 */
	public static int addEmployee(final Employee employee) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("INSERT INTO employee(name, firstname, city, zipcode, streetAddress, phone_number, codeConnection,  role, username, password, id_agency) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, employee.getName());
			stmt.setString(2, employee.getFirstname());
			stmt.setString(3, employee.getCity());
			stmt.setString(4, employee.getZipcode());
			stmt.setString(5, employee.getStreet_address());
			stmt.setString(6, employee.getPhone_number());
			stmt.setString(7, employee.getCode());
			stmt.setBoolean(8, employee.getRole());
			stmt.setString(9, employee.getUsername());
			stmt.setString(10, employee.getPassword());
			stmt.setLong(11, employee.getIdAgency());
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
	public static int updateEmployee(final Employee employee) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("UPDATE employee SET name = ?, firstname = ?, streetAddress = ?, city = ?, zipcode = ?, phone_number = ?, role = ?, username = ?, password = ?, id_agency = ? WHERE id_employee = ?");
			stmt.setString(1, employee.getName());
			stmt.setString(2, employee.getFirstname());
			stmt.setString(3, employee.getStreet_address());
			stmt.setString(4, employee.getCity());
			stmt.setString(5, employee.getZipcode());
			stmt.setString(6, employee.getPhone_number());
			stmt.setBoolean(7, employee.getRole());
			stmt.setString(8, employee.getUsername());
			stmt.setString(9, employee.getPassword());
			stmt.setLong(10,  employee.getIdAgency());
			stmt.setLong(11, employee.getIdEmployee());
			int state = stmt.executeUpdate();
			DatabaseService.closeConnection();
			return state;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}	
	}
	
	public static int removeEmployee(final Employee employee) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("DELETE FROM employee WHERE id_employee = ?");
			stmt.setLong(1, employee.getIdEmployee());
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
	public static List<Employee> getListEmployees() {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("SELECT * FROM employee");
			ResultSet rs = stmt.executeQuery();
			
			List<Employee> listEmployees = new LinkedList<Employee>();
			while(rs.next()) {
				Employee employee = new Employee(
						rs.getLong("id_employee"),
						rs.getString("name"),
						rs.getString("firstname"),
						rs.getString("streetAddress"),
						rs.getString("city"),
						rs.getString("zipcode"),
						rs.getString("phone_number"),
						rs.getBoolean("role"),
						rs.getString("username"),
						rs.getString("password"),
						rs.getLong("id_agency")
				);		
				listEmployees.add(employee);
			}
			DatabaseService.closeConnection();
			return listEmployees;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Récupère un client par le biais de son nom
	 * @return Client
	 */
	public static Employee getEmployeeByUsername(final String username) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("SELECT * FROM employee WHERE username = ?");
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();

			if (!rs.next()) {
				return null;
			}
			
			Employee employee = new Employee(
					rs.getLong("id_employee"),
					rs.getString("name"),
					rs.getString("firstname"),
					rs.getString("streetAddress"),
					rs.getString("city"),
					rs.getString("zipcode"),
					rs.getString("phone_number"),
					rs.getBoolean("role"),
					rs.getString("username"),
					rs.getString("password"),
					rs.getLong("id_agency")
			);	
			DatabaseService.closeConnection();
			return employee;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Récupère un employé spécifique
	 * @param id
	 * @return
	 */
	public static Employee getEmployee(final long id) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("SELECT * FROM employee WHERE id_employee = ?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				return null;
			}
			
			Employee employee = new Employee(
					rs.getLong("id_employee"),
					rs.getString("name"),
					rs.getString("firstname"),
					rs.getString("streetAddress"),
					rs.getString("city"),
					rs.getString("zipcode"),
					rs.getString("phone_number"),
					rs.getBoolean("role"),
					rs.getString("username"),
					rs.getString("password"),
					rs.getLong("id_agency")
			);		
			DatabaseService.closeConnection();
			return employee;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Récupère un employé spécifique par le code
	 * @param id
	 * @return
	 */
	public static Employee getEmployeeByCode(final String code) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("SELECT * FROM employee WHERE codeConnection = ? AND password IS NULL");
			stmt.setString(1, code);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				return null;
			}
			
			Employee employee = new Employee(
					rs.getLong("id_employee"),
					rs.getString("name"),
					rs.getString("firstname"),
					rs.getString("streetAddress"),
					rs.getString("city"),
					rs.getString("zipcode"),
					rs.getString("phone_number"),
					rs.getBoolean("role"),
					rs.getString("username"),
					rs.getString("password"),
					rs.getLong("id_agency")
			);		
			DatabaseService.closeConnection();
			return employee;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	
}
