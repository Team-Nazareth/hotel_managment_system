package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;


public class FetchTotalOrderPrice {
	Double price_total = 0.0;
//	JLabel priceTotalLabel;
	Connector con;
	ResultSet rs;
	
	
	public FetchTotalOrderPrice(JLabel l, ArrayList<String> roomIds, ArrayList<String[]> menuIds) {
//		priceTotalLabel = l;
		con = new Connector(Users.getRoot());
		
//		for rooms
		for(String id: roomIds) {
			String query = "{CALL get_room_by_id(?)}";
			
			int parsedID = Integer.parseInt(id);
			Object[] param = {parsedID};
			
			rs = con.getProcedureCallResult(query, param);
			
			try {
				while (rs.next()) {
					price_total += rs.getDouble("rate");
				}
			} catch (SQLException e) {
				System.out.println("order reading from db failed");
				e.printStackTrace();
			}
			
		}
		
		// for menus
		for(String[] row: menuIds) {
			
			String query = "{CALL get_menu_by_id(?)}";
			
			// row[0] -> menu_id
			int parsedID = Integer.parseInt(row[0]); 
			int parsedQty = Integer.parseInt(row[1]);
			Object[] param = {parsedID};
			
			rs = con.getProcedureCallResult(query, param);
			
			try {
				while (rs.next()) {	
					price_total += (rs.getDouble("price") * parsedQty);
				}
			} catch (SQLException e) {
				System.out.println("order reading from db failed");
				e.printStackTrace();
			}
		}
		

		con.closeConnection();
		
		l.setText(price_total.toString());
		l.revalidate();
		l.repaint();

	}
	
	
//	public void total_price(ArrayList<String> roomIds, ArrayList<String[]> menuIds) {
//		
//
//	}

}
