package view.GuestView;

import java.awt.event.ActionEvent;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

// local package
import Model.menuData;
import util.FileHandler;
import constants.FilePaths;


public class MenuCard extends Card {
	menuData Data;

	public MenuCard(JPanel p, menuData data) {
		super(p);
		Data = data;
		this.cardBodyWrapper(cardBodyPanel, data);
	}
	
	public void cardBodyWrapper(JPanel p, menuData data) {
		
		NameLabel = new JLabel(data.Name);
		RateLabel = new JLabel((data.Price).toString());
		JLabel categoryLabel = new JLabel(data.category); 
		
		p.add(NameLabel);
		p.add(RateLabel);
		p.add(categoryLabel);
	}
	
	private boolean doesRoomExist() {
		fileManager = new FileHandler(FilePaths.menuFile);
		ArrayList<String[]> menuData =  fileManager.readMenu();
		
		for(String[] row: menuData) {
			// we just check the first array list
			if(row[0].equals(Data.menu_id.toString())) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String input = JOptionPane.showInputDialog("Add Quantity for " + Data.Name ,1);
		
		if(input != null && !input.isEmpty() && !input.equals("0")) {
			if(doesRoomExist()) {
				System.out.println("menu exists");
			} else {
				fileManager = new FileHandler(FilePaths.menuFile);
				fileManager.write((Data.menu_id).toString(), Integer.parseInt(input));
			}
			
		} else {
			System.out.println("item cancled");
		}
	}
	
}