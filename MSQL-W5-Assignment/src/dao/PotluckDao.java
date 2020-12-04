package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Potluck;

public class PotluckDao {
	
	private Connection connection;
	private final String GET_POTLUCK_BY_ID_QUERY = "SELECT * FROM potluck_list WHERE id = ?";
	private final String GET_POTLUCK_QUERY = "SELECT * FROM potluck_list";
	private final String INSERT_POTLUCK_ENTRY = "INSERT INTO potluck_list("
			+ "first_name, last_name, phone_number, dish_type) VALUES (?, ?, ?, ?)";
	private final String UPDATE_DISH_ENTRY = "UPDATE potluck_list SET dish_type = ? WHERE ID = ?";
	private final String DELETE_DISH_ENTRY = "DELETE FROM potluck_list WHERE ID = ?";

	
	public PotluckDao()		{
		
		connection = DBConnection.getConnection();
	}
	
	public List<Potluck> getPotluckById(int id) throws SQLException		{

		List<Potluck> potluckInfoById = new ArrayList<Potluck>();
		PreparedStatement ps = connection.prepareStatement(GET_POTLUCK_BY_ID_QUERY);
		ps.setInt(1,  id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		potluckInfoById.add(
				populatePotluck(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getString(5)));
		return potluckInfoById; 
	}
	
	/*
	 * Method below is called by the displayList method in the Menu class.
	 */
	public List<Potluck> getPotluck() throws SQLException		{
		
		ResultSet rs = connection.prepareStatement(GET_POTLUCK_QUERY).executeQuery();
		List<Potluck> potluckInfo = new ArrayList<Potluck>();
				
		while(rs.next())	{
			potluckInfo.add
			(populatePotluck(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
					rs.getString(5)));
		}
		return potluckInfo;
	}
	
	/*
	 * Method below is called by the createList method in the Menu class.
	 */
	public void createPotluck(
			String fname, String lname, String phoneno, String dish) throws SQLException		{
		PreparedStatement ps = connection.prepareStatement(INSERT_POTLUCK_ENTRY);
		ps.setString(1, fname);
		ps.setString(2, lname);
		ps.setString(3, phoneno);
		ps.setString(4, dish);
		ps.executeUpdate();
	}

	/*
	 * Method below is called by the deleteDish method in the Menu class.
	 */
	public List<Potluck> deleteEntry(int id) throws SQLException		{
		PreparedStatement ps = connection.prepareStatement(DELETE_DISH_ENTRY);

		ps.setInt(1, id);

		ps.executeUpdate();
		List<Potluck> potluckInfo = new ArrayList<Potluck>();
		return potluckInfo;
	}

	
	
	/*
	 * Method below is called by the updateDish method in the Menu class.
	 */
	public List<Potluck> updateDish(int id, String dish_type) throws SQLException		{
		PreparedStatement ps = connection.prepareStatement(UPDATE_DISH_ENTRY);
		List<Potluck> potluckInfo = new ArrayList<Potluck>();
		ps.setInt(2, id);
		ps.setString(1, dish_type);

		ps.executeUpdate();

		return potluckInfo;
	}
	
	
	private Potluck populatePotluck(
			int id, String first_name, String last_name, String phone_number, String dish_type) {
		return new Potluck(id, first_name, last_name, phone_number, dish_type);
	}

}
