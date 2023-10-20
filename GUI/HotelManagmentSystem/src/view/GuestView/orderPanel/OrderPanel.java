package view.GuestView.orderPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

import Model.Connector;
// local package
import Model.OrderData;
import Model.Users;
import constants.FilePaths;
import util.FileHandler;


public class OrderPanel implements ActionListener {
	JLabel itemNameLabel, categoryLabel , qtyLabel , unit_priceLabel, price_totalLabel, price_totalValueLabel;
	JPanel wrapperPanel, itemWrapperPanel, itemPanel, colNamePanel, priceTotalPanel;
	JButton removeBtn, confirmOrderBtn ;
	JScrollPane scrollOrderPane;
	
	int guestId;
	Double price_total = 0.0;
	
	public OrderPanel(JPanel p, int guest_id)  {
		guestId = guest_id;
		
		wrapperPanel = new JPanel(new BorderLayout());
		colNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 100, 0));
		itemWrapperPanel = new JPanel();
		priceTotalPanel = new JPanel(new BorderLayout());
		
		wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));
		wrapperPanel.add(Box.createVerticalStrut(10));
		
		itemWrapperPanel.setLayout(new BoxLayout(itemWrapperPanel, BoxLayout.Y_AXIS));
		itemWrapperPanel.add(Box.createVerticalStrut(10));
		
		// col name
		itemNameLabel = new JLabel("Name");
		categoryLabel = new JLabel("Category");
		qtyLabel = new JLabel("Quantity");
		unit_priceLabel = new JLabel("Unit Price");
		
		colNamePanel.add(itemNameLabel);
		colNamePanel.add(categoryLabel);
		colNamePanel.add(qtyLabel);
		colNamePanel.add(unit_priceLabel);
		
		// orders
		this.itemWrapper(itemWrapperPanel);
		scrollOrderPane = new JScrollPane(itemWrapperPanel);
		scrollOrderPane.setPreferredSize(new Dimension(850,500));
		
		// price total
		price_totalLabel = new JLabel("Total Price: ");
		price_totalValueLabel = new JLabel(price_total.toString());
		price_totalLabel.setFont(new Font("Arial", Font.ITALIC, 20));
		price_totalValueLabel.setFont(new Font("Arial", Font.BOLD, 20));
		
		priceTotalPanel.add(price_totalLabel, BorderLayout.CENTER);
		priceTotalPanel.add(price_totalValueLabel, BorderLayout.EAST);
		
		// confirm btn
		confirmOrderBtn = new JButton("confirm Order");
		confirmOrderBtn.setPreferredSize(new Dimension(100,40));
		confirmOrderBtn.setBackground(new Color(173, 216, 230));
		confirmOrderBtn.addActionListener(this);
		
		wrapperPanel.add(colNamePanel);
		wrapperPanel.add(scrollOrderPane);
		wrapperPanel.add(priceTotalPanel);
		wrapperPanel.add(confirmOrderBtn);
		
		p.add(wrapperPanel);
		
	}
	
	public void itemWrapper(JPanel p) {
		// fetch from db
		// given item id from file
		
		ArrayList<String> roomIds;
		ArrayList<String[]> menuIds;
		
		// read from room file 
		roomIds  = new FileHandler(FilePaths.roomFile).readRoom();
		
		// read from menu file 
		menuIds  = new FileHandler(FilePaths.menuFile).readMenu();
		
		Connector con = new Connector(Users.getRoot());
		ResultSet rs;
		
//		for rooms
		for(String id: roomIds) {
			String query = "{CALL get_room_by_id(?)}";
			
			int parsedID = Integer.parseInt(id);
			Object[] param = {parsedID};
			
			rs = con.getProcedureCallResult(query, param);
			
			try {
				while (rs.next()) {
					price_total += rs.getDouble("rate");
					OrderData order = itemFill(rs.getString("type_name"), 1,rs.getDouble("rate"),OrderData.Category.ROOM, parsedID);
					new OrderItem(p, order, price_totalValueLabel);
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
					OrderData order = itemFill(rs.getString("name"), parsedQty ,rs.getDouble("price"),OrderData.Category.MENU, parsedID);
					new OrderItem(p, order, price_totalValueLabel);
				}
			} catch (SQLException e) {
				System.out.println("order reading from db failed");
				e.printStackTrace();
			}
		}
		

//		con.closeCallableStatement();
		con.closeConnection();
	}
	 
	
	public OrderData itemFill(String item_name, int qty, double unit_price, OrderData.Category category, int id) {
		OrderData order = new OrderData();
		
		// dummy data
		order.item_name = item_name;
        order.qty = qty;
        order.unit_price = unit_price;
        order.id = id;

        // Set the Category enum
        if(category == OrderData.Category.ROOM) {	        	
        	order.category = OrderData.Category.ROOM;
        } else order.category = OrderData.Category.MENU;
        
		if(!itemWrapperPanel.isEnabled()) {
			itemWrapperPanel.setEnabled(true);;
		}
        
        return order;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// make generate invoice using reserve_room() and order_menu()
		// remove rooms and menus from file
		// show message invoice generated
		
		if(!itemWrapperPanel.isEnabled()) {
			this.confirmOrderBtn.removeActionListener(this);;
		}
		
		int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to Proceed with payment?",
				"Payment Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if(choice == JOptionPane.YES_OPTION) {
		
			ArrayList<String> roomIds;
			ArrayList<String[]> menuIds;
			
			// read from room file 
			FileHandler roomFile = new FileHandler(FilePaths.roomFile);
			roomIds  = roomFile.readRoom();
			
			// read from menu file 
			FileHandler menuFile = new FileHandler(FilePaths.menuFile);
			menuIds  = menuFile.readMenu();
			
			Connector con = new Connector(Users.getRoot());
	//		ResultSet rs;
			
	//		for rooms
			for(String id: roomIds) {
				String query = "{CALL reserve_room(?, ?, ?)}";
				
				int parsedID = Integer.parseInt(id);
				Integer guest_id = guestId;
				String checkout_date = "2023-10-25";
				Object[] param = {guest_id , parsedID, checkout_date};
				
				con.getProcedureCallResult(query, param);
				
				// clear file
				
	        	String line = id;
	        	roomFile.deleteLine(line);
	
			}
			
			// for menus
	
			for(String[] row: menuIds) {
				
				String query = "{CALL order_menu(?, ?, ?, ?)}";
				
				// row[0] -> menu_id
				int parsedID = Integer.parseInt(row[0]); 
				int parsedQty = Integer.parseInt(row[1]);
				Integer guest_id = guestId;
				Integer table_id = 1;
				Object[] param = {guest_id,table_id, parsedQty, parsedID};
				
				con.getProcedureCallResult(query, param);
				
				//			clear from file
	  
	        	String line = row[0] +","+ row[1];
	        	menuFile.deleteLine(line);
			}
			
			JOptionPane.showMessageDialog(null, "Order is successfully recorded. Please head over to invoice", "Success Message", JOptionPane.INFORMATION_MESSAGE);

			// remove from gui
			itemWrapperPanel.removeAll();
			itemWrapperPanel.revalidate();
			itemWrapperPanel.repaint();
			itemWrapperPanel.add(new JLabel("no new order!"));
			itemWrapperPanel.setEnabled(false);
			
			System.out.println("Order is Confirmed");
			
			con.closeConnection();
		}
		//con.closeCallableStatement();
		
		
	}
	
}
