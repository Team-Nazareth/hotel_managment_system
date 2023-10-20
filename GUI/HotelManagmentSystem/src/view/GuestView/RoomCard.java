package view.GuestView;

import java.awt.event.ActionEvent;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

// custom package
import Model.roomData;
import constants.FilePaths;
import util.FileHandler;

public class RoomCard extends Card {
	roomData Data;
	public RoomCard(JPanel p,  roomData data) {
		super(p);
		Data = data;
		this.cardBodyWrapper(cardBodyPanel, data);
	}
	
	public void cardBodyWrapper(JPanel p, roomData data) {
		
		NameLabel = new JLabel(data.Name);
		RateLabel = new JLabel((data.Price).toString());
		DescLabel = new JLabel(data.Desc);
		JLabel category = new JLabel((data.capacity).toString());
		
		
		p.add(NameLabel);
		p.add(RateLabel);
		p.add(DescLabel);
		p.add(category);
	}
	
	private boolean doesRoomExist() {
		fileManager = new FileHandler(FilePaths.roomFile);
		ArrayList<String> roomData =  fileManager.readRoom();
		
		for(String room_id: roomData) {
			if(room_id.equals(Data.room_id.toString())) {
				return true;
			}
		}
		
		return false;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(doesRoomExist()) {
			System.out.println("room exists");
		} else {
			fileManager = new FileHandler(FilePaths.roomFile);
			fileManager.write((Data.room_id).toString());
		}
	}
	
}
