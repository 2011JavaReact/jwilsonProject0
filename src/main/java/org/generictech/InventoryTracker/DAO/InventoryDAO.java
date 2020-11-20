package org.generictech.InventoryTracker.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.generictech.InventoryTracker.model.Inventory;
import org.generictech.InventoryTracker.utils.DatabaseUtility;

/**
 * Class to handle database access for inventory records. 
 * @author Jaden Wilson
 * @since 1.0
 */
public class InventoryDAO {
	
	public ArrayList<Inventory> getAllInventory() throws SQLException {
		ArrayList<Inventory> inventory = new ArrayList<>();
		
		Connection connection = DatabaseUtility.getConnection();
		String query = "SELECT * FROM Inventory";
		PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet result = stmt.executeQuery();
		
		while (result.next()) {
			//inventory.add(new Inventory(result.getInt(1), result.getInt(2), result.getInt(3), result.getString(4), result.getInt(5)));
		}
			
		return inventory;
	}
}
