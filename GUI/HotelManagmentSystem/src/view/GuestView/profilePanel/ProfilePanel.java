package view.GuestView.profilePanel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;

class ProfileFields {
	public String username;
	public String first_name;
	public String last_name;
	public String email;
	public String birth_date;
	public String sex ;
	public String phone_num;
}

class GuestProfile extends ProfileFields {
	public Integer guest_id;
}

class StaffProfile extends ProfileFields {
	public Integer staff_id;
	public Integer role_id;
	public Integer reports_to; 
	public access_level accessLevel;
	public enum access_level {
		MANAGER,
		RECEPTION,
		REGULAR_STAFF
	};
}

public class ProfilePanel implements ActionListener {
	// can edit profile
	// can see profile info
	JPanel containerPanel;
	JLabel usernameLabel, fullNameLabel, emailLabel, birth_dateLabel,sex, p_numLabel;
	JButton editProfileBtn ;
	
	public ProfilePanel(JPanel p) {
		
		containerPanel = new JPanel();
		
		containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
		containerPanel.add(Box.createVerticalStrut(10));
		
		this.fillProfile(containerPanel);
		
		editProfileBtn = new JButton("edit profile");
		editProfileBtn.addActionListener(this);
		
		containerPanel.add(editProfileBtn);
		p.add(containerPanel);
	}
	
	private void fillProfile(JPanel p) {
		GuestProfile data = new GuestProfile();
		data.username = "user1";
		data.first_name = "abe";
		data.last_name = "kebe";
		data.birth_date = "2002-12-12";
		data.sex = "M";
		data.phone_num = "912345678";
		
		
		
		usernameLabel = new JLabel("Username: "+"@"+data.username);
		fullNameLabel = new JLabel("Name: "+data.first_name + " " + data.last_name);
		emailLabel = new JLabel("Email: "+data.email);
		birth_dateLabel = new JLabel("Birth Date: "+data.birth_date);
		sex = new JLabel("Sex: "+data.sex);
		p_numLabel = new JLabel("Phone number: "+"+251"+data.phone_num);
		
		JLabel[] labelList = {
				fullNameLabel, usernameLabel,  emailLabel
				, p_numLabel, sex, birth_dateLabel
		};
		
		for(JLabel l: labelList) {
			l.setFont(new Font("Arial", Font.ROMAN_BASELINE, 20));
			l.setPreferredSize(new Dimension(400 , 100));
			p.add(l);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		new EditableProfileFields();
	}
}
