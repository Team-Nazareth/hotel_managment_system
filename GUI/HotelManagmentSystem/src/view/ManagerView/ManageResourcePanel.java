package view.ManagerView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Model.Connector;
import Model.Users;
import Model.menuData;
import view.GuestView.invoicePanel.CustomTableCellRenderer;


// room info: total room , available room, rooms list
// menu info : total menu, menus list
// add menu
// make rooms available


class TableInfo {
	String[] colNames ;
	String[][] data;
}

class MenuCard {
//	menuData Data;
	JTable menuTable;
	DefaultTableModel model;
	JScrollPane scrollMenuPane;
	Connector con;
	ResultSet rs;
	
	public MenuCard(JPanel p, menuData data) {
//		Data = data;
		this.cardBodyWrapper(p, data);
	}
	
	public void cardBodyWrapper(JPanel p, menuData data) {
		// scroll pane
		scrollMenuPane = new JScrollPane(this.renderTable());
		scrollMenuPane.setPreferredSize(new Dimension(850,700));
	}
	
	public JTable renderTable() {
		TableInfo tableInfo = this.getMenus();
		
		model = new DefaultTableModel(tableInfo.data , tableInfo.colNames ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non editable
            }
        };
        
        menuTable = new JTable(model);
		
		// table styling
		JTableHeader tableHeader = menuTable.getTableHeader();
		tableHeader.setFont(new Font("Arial", Font.BOLD, 20));
		tableHeader.setBackground(Color.GRAY);
		tableHeader.setForeground(Color.ORANGE);
		menuTable.setFont(new Font("Arial", Font.TYPE1_FONT, 15));
		
		CustomTableCellRenderer customRenderer = new CustomTableCellRenderer();
		menuTable.setDefaultRenderer(Object.class, customRenderer);
		
		return menuTable;
	}
	
	public TableInfo getMenus() {
		// fetch menus
				String query = "{CALL get_menu()}";
				
				ArrayList<String> colNames = 
						new ArrayList<>(Arrays.asList("menu_id","name", "description","price", "category"));
				int colSize = colNames.size();
				ArrayList<String[]> data = new ArrayList<>();
				
				Object[] param = {};
				Connector con = new Connector(Users.getRoot());
				rs = con.getProcedureCallResult(query, param);
				
				try {
					while(rs.next()) {
						String[] rowValues = new String[colSize];
						
						int count = 0;
						for(String col: colNames) {
							if(col.equals("menu_id") || col.equals("price")) {						
								Integer val = rs.getInt(col);
								rowValues[count] = val.toString();
							} else {
								rowValues[count] = rs.getString(col);
							}
							count++;
						}
						
						data.add(rowValues);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
				TableInfo tableInfo = new TableInfo();
				// convert back to String
				tableInfo.colNames = colNames.toArray(new String[(colNames.size())]);
				tableInfo.data = data.toArray(new String[(data.size())][(colNames.size())]);
				
				return tableInfo;
	}
		
}

public class ManageResourcePanel {
	JPanel roomPanel, menuPanel, roomCardWrapperPanel, menuCardWrapperPanel;
	JTabbedPane ManageResourcePanel;
	JScrollPane scrollRoomPane, scrollMenuPane;
	
	public ManageResourcePanel(JPanel p) {
		ManageResourcePanel = new JTabbedPane();
		roomPanel = new JPanel(new BorderLayout());
		menuPanel = new JPanel(new BorderLayout());
		
		this.menuWrapper(menuPanel);
		this.roomWrapper(roomPanel);
		
		ManageResourcePanel.addTab("Rooms", roomPanel);
		ManageResourcePanel.addTab("Menu", menuPanel);
		
//		exploreWrapperPanel.add(exploreTab);
		
		p.add(ManageResourcePanel);
	}
	
	public void menuWrapper(JPanel p) {
		menuCardWrapperPanel = new JPanel();
		
		String query = "SELECT * FROM menu;";
		Connector con = new Connector(Users.getRoot());
		ResultSet rs = con.getQueryResult(query);
		
		try {
			// menu data
			
			menuData data = new menuData();
			while(rs.next()) {
				data.Name = rs.getString("name");
				data.Price = rs.getDouble("price");
				data.Desc = rs.getString("description");
				data.menu_id = rs.getInt("menu_id");
				data.category = rs.getString("category");
			}
			new MenuCard(menuCardWrapperPanel,data);
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
	
	public void roomWrapper(JPanel p) {
		p.add(new JLabel("rooms"));
	}
}
