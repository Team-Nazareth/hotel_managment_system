package view.ManagerView;

import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import Model.Connector;
import Model.Users;
import view.GuestView.profilePanel.ProfilePanel;


public class ManagerProfilePanel {
	
	protected JPanel containerPanel;
	protected JPanel wrapperPanel;
	protected JLabel fullNameLabel, usernameLabel,  emailLabel
					, p_numLabel, sex, birth_dateLabel, roleLabel, roleDescLabel;
	protected Connector con;
	protected ResultSet resultSet;
	
	int staffId;
	
	public ManagerProfilePanel(JPanel p, int staff_id) {
		staffId = staff_id;
		
		// make a connection to db
		con = new Connector(Users.getRoot());
		
		containerPanel = new JPanel();
		
		containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
		containerPanel.add(Box.createVerticalStrut(10));
		
		this.fillProfile(containerPanel);
		
		p.add(containerPanel);
		
	}
	
	private void fillProfile(JPanel p) {
		wrapperPanel = new JPanel();
		wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));
		wrapperPanel.add(Box.createVerticalStrut(10));
		
		StaffProfile data = new StaffProfile();
		
		String query = "CALL get_profile(?, 'staff')";
		
		Object[] param = {staffId};
		resultSet = con.getProcedureCallResult(query,param);
		
		try {
			// we are not looping because result is certain to be one item,  based on our db
			if(resultSet.next()) {
				
				data.staff_id = staffId;
				data.username = resultSet.getString("username");
				data.first_name = resultSet.getString("first_name");
				data.last_name = resultSet.getString("last_name");
				data.birth_date = resultSet.getString("birth_date");
				data.email = resultSet.getString("email");
				data.sex = resultSet.getString("sex");
				data.phone_num = resultSet.getString("phone_num");
				data.role = resultSet.getString("name");
				data.role_description = resultSet.getString("description");
			
			}
			
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
		roleLabel = new JLabel("role: " + data.role);
		roleDescLabel = new JLabel("role Description: " + data.role_description);
		
		JLabel[] labelList = {
				fullNameLabel, usernameLabel,  emailLabel
				, p_numLabel, sex, birth_dateLabel, roleLabel, roleDescLabel
		};
		
		for(JLabel l: labelList) {
			l.setFont(new Font("Arial", Font.ITALIC, 15));
			l.setPreferredSize(new Dimension(400 , 100));
			wrapperPanel.add(l);
		}

		
		p.add(wrapperPanel);
	}
}
