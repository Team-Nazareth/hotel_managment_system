package view.GuestView.profilePanel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.*;

import Model.Connector;
import Model.Users;
import view.CustomException;

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
	JPanel containerPanel, wrapperPanel;
	JLabel usernameLabel, fullNameLabel, emailLabel, birth_dateLabel,sex, p_numLabel;
	JButton editProfileBtn, refreshProfileBtn ;
	Connector con;
	ResultSet resultSet;
	int guest_id ;
	
	public ProfilePanel(JPanel p, int id) {
		
		// make a connection to db
		con = new Connector(Users.getRoot());
		
		guest_id = id;
		
		containerPanel = new JPanel();
		
		containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
		containerPanel.add(Box.createVerticalStrut(10));
		
		this.fillProfile(containerPanel);
		
		editProfileBtn = new JButton("edit profile");
		editProfileBtn.addActionListener(this);
		
		refreshProfileBtn = new JButton("refresh");
		refreshProfileBtn.addActionListener(this);
		
		containerPanel.add(editProfileBtn);
		containerPanel.add(refreshProfileBtn);
		p.add(containerPanel);
	}
	
	private void fillProfile(JPanel p) {
		wrapperPanel = new JPanel();
		wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));
		wrapperPanel.add(Box.createVerticalStrut(10));
		
		GuestProfile data = new GuestProfile();
		
		String query = "CALL get_profile(?, 'guest')";
		
		Object[] param = {guest_id};
		resultSet = con.getProcedureCallResult(query,param );
		
		try {
			// we are not looping because result is certain to be one item,  based on our db
			resultSet.next();
			
			data.guest_id = guest_id;
			data.username = resultSet.getString("username");
			data.first_name = resultSet.getString("first_name");
			data.last_name = resultSet.getString("last_name");
			data.birth_date = resultSet.getString("birth_date");
			data.email = resultSet.getString("email");
			data.sex = resultSet.getString("sex");
			data.phone_num = resultSet.getString("phone_num");
			
			con.closeResultSet();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
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
			wrapperPanel.add(l);
		}
		
		p.add(wrapperPanel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == refreshProfileBtn) {
			wrapperPanel.removeAll();
			this.fillProfile(containerPanel);
			wrapperPanel.revalidate();
			wrapperPanel.repaint();
			
		} else if(e.getSource() == editProfileBtn) {
			
			try {
				String query = "CALL edit_guest_profile(?, ?, ?, ?);";
				EditableProfileFields fields = new EditProfile().getValues();
				
				int id = 3;
				// check for nullable fields and set the sqlTyoe null
				String pswd = fields.password ;
				String email = fields.email;
				String p_num = fields.phone_number ;
				
				Object[] param = {id, p_num, pswd, email};
				
				// now write to db
				Connector con = new Connector(Users.getRoot());
				con.getProcedureCallResult(query, param);
				
				
				con.closeConnection();
				
				JOptionPane.showMessageDialog(null, "Succsessfuly Updated!", "Update Success", JOptionPane.INFORMATION_MESSAGE);
				
			} catch (CustomException e1) {
				e1.printStackTrace();
				e1.getMessage();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Update Failed", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
