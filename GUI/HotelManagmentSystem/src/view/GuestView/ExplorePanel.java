package view.GuestView;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import Model.Connector;
import Model.Users;
import Model.menuData;
import Model.roomData;
// custom packages
import util.ImageManager;
import util.ImageManager.*;


public class ExplorePanel {
	JPanel roomPanel, menuPanel, roomCardWrapperPanel, menuCardWrapperPanel;
	JTabbedPane exploreTab;
	JScrollPane scrollRoomPane, scrollMenuPane;
	
	public ExplorePanel(JPanel p) {
		exploreTab = new JTabbedPane();
		roomPanel = new JPanel(new BorderLayout());
		menuPanel = new JPanel(new BorderLayout());
		
		this.menuWrapper(menuPanel);
		this.roomWrapper(roomPanel);
		
		exploreTab.addTab("Rooms", roomPanel);
		exploreTab.addTab("Menu", menuPanel);
		
//		exploreWrapperPanel.add(exploreTab);
		
		p.add(exploreTab);
	}
	
	private void menuWrapper(JPanel p) {
		menuCardWrapperPanel = new JPanel(new GridLayout(0, 3, 5, 10));
		
			String query = "SELECT * FROM menu;";
			Connector con = new Connector(Users.getRoot());
			ResultSet rs = con.getQueryResult(query);
			
			try {
				// menu data
				
				while(rs.next()) {
					menuData data = new menuData();
					data.Name = rs.getString("name");
					data.Price = rs.getDouble("price");
					data.Desc = rs.getString("description");
					data.menu_id = rs.getInt("menu_id");
					data.category = rs.getString("category");
					new MenuCard(menuCardWrapperPanel,data);
				}
			} catch (SQLException e) {
				// fetch failed
				e.printStackTrace();
			}
			
			con.closePreparedStatement();
			con.closeConnection();

		
		scrollMenuPane = new JScrollPane(menuCardWrapperPanel);
		scrollMenuPane.setPreferredSize(new Dimension(920,670));
		
		p.add(scrollMenuPane, BorderLayout.WEST);
	}
	
	private void roomWrapper(JPanel p) {
		roomCardWrapperPanel = new JPanel(new GridLayout(0, 3, 5, 10));

		String query = "SELECT * FROM available_rooms";
		Connector con = new Connector(Users.getRoot());
		ResultSet rs = con.getQueryResult(query);
		
		try {
			while(rs.next()) {
				roomData data = new roomData();
				data.Name = rs.getString("type_name");
				data.Price = rs.getDouble("rate");
				data.Desc = rs.getString("description");
				data.room_id = rs.getInt("room_id");
				data.capacity = rs.getInt("capacity");
				new RoomCard(roomCardWrapperPanel,data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		con.closePreparedStatement();
		con.closeConnection();
		
		
		scrollRoomPane = new JScrollPane(roomCardWrapperPanel);
		scrollRoomPane.setPreferredSize(new Dimension(920,670));
		
		
		p.add(scrollRoomPane , BorderLayout.WEST);
	}
}
