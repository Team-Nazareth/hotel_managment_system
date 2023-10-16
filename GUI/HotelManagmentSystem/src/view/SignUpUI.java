package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class SignUpUI implements ActionListener { 
	JPanel formPanel, subFormPanel, parentForm;
	JLabel titleLabel, firstNameLabel, lastNameLabel, emailLabel,
			b_dateLabel, sexLabel, p_numLabel, userNameLabel, pswdLabel, padding;
	JTextField firstName, lastName, email, b_date, p_num, userName ;
	JPasswordField pswd;
	JComboBox<String> sex;
	JButton signupBtn, backBtn;
	
	CardLayout p_cardLayout;
	
	public SignUpUI(JPanel p, JPanel ParentForm, CardLayout cardLayout ) {
		
		// passed from container login class
		p_cardLayout = cardLayout;
		parentForm = ParentForm;
		// end
		
		formPanel = new JPanel(new GridBagLayout());
		
		GridBagConstraints constrients = new GridBagConstraints();
		constrients.insets = new Insets(5, 10, 5, 5);
		
		// labels
		titleLabel = new JLabel("Sign Up Form!");
		titleLabel.setFont(new Font("Arial", Font.ITALIC, 30));
		firstNameLabel = new JLabel("First Name(*): ");
		lastNameLabel = new JLabel("Last Name(*): ");
		emailLabel = new JLabel("Email: ");
		b_dateLabel = new JLabel("Birth Date: ");
		sexLabel = new JLabel("Sex(*): ");
		p_numLabel = new JLabel("Phone number(*): ");
		padding = new JLabel("");
		padding.setPreferredSize(new Dimension(100,50));
		
		userNameLabel = new JLabel("Username(*): ");
		pswdLabel = new JLabel("Password(*): ");
		
		//text fields
		
		firstName = new JTextField(20);
		firstName.setPreferredSize(new Dimension(100,30));
		
		lastName = new JTextField(20);
		lastName.setPreferredSize(new Dimension(100,30));
		
		email = new JTextField(20);
		email.setPreferredSize(new Dimension(100,30));
		
		b_date = new JTextField(20);
		b_date.setPreferredSize(new Dimension(100,30));
		
		p_num = new JTextField(20);
		p_num.setPreferredSize(new Dimension(100,30));
		
		userName = new JTextField(20);
		userName.setPreferredSize(new Dimension(100,30));
		
		pswd = new JPasswordField(20);
		pswd.setPreferredSize(new Dimension(100,30));
		
		
		String[] options = {"Male", "Female"};
		sex = new JComboBox<>(options);
		sex.setPreferredSize(new Dimension(200,30));
		
		signupBtn = new JButton("Sign Up");
		signupBtn.setPreferredSize(new Dimension(100,40));
		signupBtn.setBackground(new Color(200, 200, 120));
		signupBtn.setForeground(new Color(255,255,255));
		signupBtn.addActionListener(this);
		
		backBtn = new JButton("Back");
		backBtn.setPreferredSize(new Dimension(50,40));
		backBtn.setBackground(new Color(192, 192, 192));
		backBtn.addActionListener(this);
		
		
		constrients.gridx = 1;
		constrients.gridy = 0;
		formPanel.add(titleLabel, constrients);
		
		// first name
		constrients.gridx = 0;
		constrients.gridy = 1;
		formPanel.add(firstNameLabel, constrients);
		
		constrients.gridx = 1;
		constrients.gridy = 1;
		formPanel.add(firstName, constrients);
		
		// last name
		
		constrients.gridx = 0;
		constrients.gridy = 2;
		formPanel.add(lastNameLabel, constrients);
		
		constrients.gridx = 1;
		constrients.gridy = 2;
		formPanel.add(lastName, constrients);
		
		// email
		constrients.gridx = 0;
		constrients.gridy = 3;
		formPanel.add(emailLabel, constrients);
		
		constrients.gridx = 1;
		constrients.gridy = 3;
		formPanel.add(email, constrients);
		
		// b_date
		constrients.gridx = 0;
		constrients.gridy = 4;
		formPanel.add(b_dateLabel, constrients);
		
		constrients.gridx = 1;
		constrients.gridy = 4;
		formPanel.add(b_date, constrients);
		
		// p_num
		constrients.gridx = 0;
		constrients.gridy = 5;
		formPanel.add(p_numLabel, constrients);
		
		constrients.gridx = 1;
		constrients.gridy = 5;
		formPanel.add(p_num, constrients);
		
		// sex
		constrients.gridx = 0;
		constrients.gridy = 6;
		formPanel.add(sexLabel, constrients);
		
		constrients.gridx = 1;
		constrients.gridy = 6;
		formPanel.add(sex, constrients);
		
		// padding
		constrients.gridx = 0;
		constrients.gridy = 7;
		formPanel.add(padding, constrients);
		
		// username
		constrients.gridx = 0;
		constrients.gridy = 8;
		formPanel.add(userNameLabel, constrients);
		
		constrients.gridx = 1;
		constrients.gridy = 8;
		formPanel.add(userName, constrients);
		
		// pswd
		constrients.gridx = 0;
		constrients.gridy = 9;
		formPanel.add(pswdLabel, constrients);
		
		constrients.gridx = 1;
		constrients.gridy = 9;
		formPanel.add(pswd, constrients);
		
		// sign up btn
		constrients.gridx = 1;
		constrients.gridy = 10;
		formPanel.add(signupBtn, constrients);
		
		
		p.add(formPanel, BorderLayout.CENTER);
		p.add(backBtn, BorderLayout.SOUTH);
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == signupBtn) {
			System.out.println("register a user");
			p_cardLayout.show(parentForm, "guest");
		} else if(e.getSource() == backBtn) {
			System.out.println("back");
			p_cardLayout.show(parentForm, "guest");
		}
	}
}
