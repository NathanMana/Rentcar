package rentcar.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import rentcar.Entity.Category;

public class CategoryService {
	
	/**
	 * 
	 * @param category
	 * @return
	 */
	public static int addCategory(final Category category) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("INSERT INTO category(price, bail, name) VALUES (?, ?, ?)");
			stmt.setDouble(1, category.getPrice());
			stmt.setDouble(2, category.getBail());
			stmt.setString(3, category.getName());
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
	public static int updateCategory(final Category category) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("UPDATE category SET name = ?, price = ?, bail = ? WHERE id_category = ?");
			stmt.setString(1, category.getName());
			stmt.setDouble(2, category.getPrice());
			stmt.setDouble(3, category.getBail());
			stmt.setLong(4, category.getIdCategory());
			int state = stmt.executeUpdate();
			DatabaseService.closeConnection();
			return state;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}	
	}
	
	public static int removeCategory(final Category category) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("DELETE FROM category WHERE id_category = ?");
			stmt.setLong(1, category.getIdCategory());
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
	 * Récupère la liste des catégories
	 * @return List<Category>
	 */
	public static List<Category> getListCategories() {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("SELECT * FROM category");
			ResultSet rs = stmt.executeQuery();
			
			List<Category> listCategories = new LinkedList<Category>();
			while(rs.next()) {
				Category category = new Category(
						rs.getLong("id_category"),
						rs.getString("name"),
						rs.getDouble("price"),
						rs.getDouble("bail"));
				listCategories.add(category);
			}
			DatabaseService.closeConnection();
			return listCategories;
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
	public static Category getCategory(final long id) {
		PreparedStatement stmt;
		try {
			DatabaseService.initConnection();
			stmt = DatabaseService.prepareStatement("SELECT * FROM category WHERE id_category = ?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				return null;
			}
			Category category = new Category(
					rs.getLong("id_category"),
					rs.getString("name"),
					rs.getDouble("price"),
					rs.getDouble("bail"));
			DatabaseService.closeConnection();
			return category;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	
}
