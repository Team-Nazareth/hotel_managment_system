package view.GuestView;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

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
		
		for(int i =0; i < 12; i++ ) {	
			menuData data = new menuData();
			data.Name = "Menu Name";
			data.Price = 50.0;
			data.Desc = "Lorem ipsum dolor sit amet, consectetur adipiscing.";
			data.menu_id = i+1;
			new MenuCard(menuCardWrapperPanel,data);
		}
		
		scrollMenuPane = new JScrollPane(menuCardWrapperPanel);
		scrollMenuPane.setPreferredSize(new Dimension(920,670));
		
		p.add(scrollMenuPane, BorderLayout.WEST);
	}
	
	private void roomWrapper(JPanel p) {
		roomCardWrapperPanel = new JPanel(new GridLayout(0, 3, 5, 10));
		
		for(int i =0; i < 12; i++ ) {		
			
			roomData data = new roomData();
			data.Name = "Room Name";
			data.Price = 70.0;
			data.Desc = "Lorem ipsum dolor sit amet, consectetur adipiscing.";
			data.room_id = i+1;
			new RoomCard(roomCardWrapperPanel, data);
		}
		
		
		scrollRoomPane = new JScrollPane(roomCardWrapperPanel);
		scrollRoomPane.setPreferredSize(new Dimension(920,670));
		
		
		p.add(scrollRoomPane , BorderLayout.WEST);
	}
}
