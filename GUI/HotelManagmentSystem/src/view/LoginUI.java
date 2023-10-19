package view;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

import constants.FilePaths;
// custom packages
import view.GuestView.GuestMainView;
import util.ImageManager;
import util.ImageManager.*;

public class LoginUI implements ActionListener {
	JFrame theFrame;
	JPanel loginPanel, FormPanel, guestFormPanel, staffFormPanel, guestSignUpPanel;
	CardLayout cardLayout;
	ImageIcon icon ;
	Image image;
	JLabel greetLabel, nameLabel, pswdLabel, access_levelLabel;
	JButton loginStaffBtn, loginGuestBtn, signupBtn, staffOptionBtn, guestOptionBtn;
	JTextField usernameField , usernameStaffField;
	JPasswordField passwordField, passwordStaffField;
	JComboBox<String> access_level;
	
	
	
	public LoginUI(JFrame f) {
		// passed from container class
		theFrame = f;
		//end
		loginPanel = new JPanel(new BorderLayout());
		FormPanel = new JPanel();
		guestFormPanel = new JPanel(new BorderLayout());
		staffFormPanel = new JPanel(new BorderLayout());
		guestSignUpPanel = new JPanel(new BorderLayout());
		
		cardLayout = new CardLayout();
		FormPanel.setLayout(cardLayout);
		
		// guest and staff are cuplied to other service
		FormPanel.add(guestFormPanel, "guest");
		FormPanel.add(staffFormPanel, "staff");
		FormPanel.add(guestSignUpPanel, "signup");
		
		this.imageManger(loginPanel);
		this.guestForm(guestFormPanel);
		this.staffForm(staffFormPanel);
		
		// formPanel and cardLayout passed for navigation purpose
		new SignUpUI(guestSignUpPanel, FormPanel, cardLayout );
		
		cardLayout.show(FormPanel, "guest");
		loginPanel.add(FormPanel, BorderLayout.WEST);

//		loginPanel.add(staffFormPanel, BorderLayout.WEST);
		theFrame.add(loginPanel);
		
	}
	
	private void guestForm(JPanel p) {
		
		JPanel subPanel = new JPanel(new GridBagLayout());
		GridBagConstraints constrients = new GridBagConstraints();
		constrients.insets = new Insets(5, 10, 5, 5);
        
		greetLabel = new JLabel("Welcome to Hotel X!");
		greetLabel.setFont(new Font("Arial", Font.ITALIC, 30));
//		greetLabel.setBounds(100, 300, 10,50);
		
		nameLabel = new JLabel("username: ");
		pswdLabel = new JLabel("password: ");
		
		usernameField = new JTextField(20);
		usernameField.setPreferredSize(new Dimension(200,30));
		passwordField = new JPasswordField(20);
		passwordField.setPreferredSize(new Dimension(200,30));

		// login Btn
		loginGuestBtn = new JButton("Login");
		loginGuestBtn.setPreferredSize(new Dimension(200,30));
		loginGuestBtn.setBackground(new Color(20, 20, 25));
		loginGuestBtn.setForeground(new Color(255,255,255));
		loginGuestBtn.addActionListener(this);
		
		// signup btn
		signupBtn = new JButton("Sign up");
		signupBtn.setPreferredSize(new Dimension(200,30));
		signupBtn.setBackground(new Color(76, 110, 255));
		signupBtn.setForeground(new Color(255,255,255));
		signupBtn.addActionListener(this);
		
		guestOptionBtn = new JButton("staff?");
		guestOptionBtn.addActionListener(this);
		// greeting label
		constrients.gridx = 1;
		constrients.gridy = 0;
		subPanel.add(greetLabel, constrients);
		
		// username
		constrients.gridx = 0;
		constrients.gridy = 1;
		subPanel.add(nameLabel, constrients);
		
		constrients.gridx = 1;
		constrients.gridy = 1;
		subPanel.add(usernameField, constrients);
		
		// password
		constrients.gridx = 0;
		constrients.gridy = 2;
		subPanel.add(pswdLabel, constrients);
		
		constrients.gridx = 1;
		constrients.gridy = 2;
		subPanel.add(passwordField, constrients);
		
		// login button
		constrients.gridx = 1;
		constrients.gridy = 3;
		subPanel.add(loginGuestBtn, constrients);
		
		constrients.gridx = 1;
		constrients.gridy = 4;
		subPanel.add(signupBtn, constrients);


		p.add(subPanel, BorderLayout.CENTER);
		
		p.add(guestOptionBtn, BorderLayout.SOUTH);
		
	}
	
	private void staffForm(JPanel p) {
		
		JPanel subPanel = new JPanel(new GridBagLayout());
		GridBagConstraints constrients = new GridBagConstraints();
		constrients.insets = new Insets(5, 10, 5, 5);
        
		greetLabel = new JLabel("Welcome to Hotel XB!");
		greetLabel.setFont(new Font("Arial", Font.ITALIC, 30));
//		greetLabel.setBounds(100, 300, 10,50);
		
		access_levelLabel = new JLabel("role: ");
		nameLabel = new JLabel("username: ");
		pswdLabel = new JLabel("password: ");
		
		usernameStaffField = new JTextField(20);
		usernameStaffField.setPreferredSize(new Dimension(300,30));
		passwordStaffField = new JPasswordField(20);
		passwordStaffField.setPreferredSize(new Dimension(300,30));

		
		loginStaffBtn = new JButton("Login");
		loginStaffBtn.setPreferredSize(new Dimension(200,30));
		loginStaffBtn.setBackground(new Color(20, 20, 25));
		loginStaffBtn.setForeground(new Color(255,255,255));
		loginStaffBtn.addActionListener(this);
		
		String[] options = {"Manager", "Reception", "Regular staff"};
		
		access_level = new JComboBox<>(options);
		access_level.setPreferredSize(new Dimension(200,35));
		access_level.setBackground(new Color(250,255,255));
		
		staffOptionBtn = new JButton("guest?");
		staffOptionBtn.addActionListener(this);
		// greeting label
		constrients.gridx = 1;
		constrients.gridy = 0;
		subPanel.add(greetLabel, constrients);
		
		// username
		constrients.gridx = 0;
		constrients.gridy = 1;
		subPanel.add(nameLabel, constrients);
		
		constrients.gridx = 1;
		constrients.gridy = 1;
		subPanel.add(usernameStaffField, constrients);
		
		// password
		constrients.gridx = 0;
		constrients.gridy = 2;
		subPanel.add(pswdLabel, constrients);
		
		constrients.gridx = 1;
		constrients.gridy = 2;
		subPanel.add(passwordStaffField, constrients);
		
		// access_level
		constrients.gridx = 0;
		constrients.gridy = 3;
		subPanel.add(access_levelLabel, constrients);
		
		constrients.gridx = 1;
		constrients.gridy = 3;
		subPanel.add(access_level, constrients);
		
		// login button
		constrients.gridx = 1;
		constrients.gridy = 4;
		subPanel.add(loginStaffBtn, constrients);


		p.add(subPanel, BorderLayout.CENTER);
		
		p.add(staffOptionBtn, BorderLayout.SOUTH);
		
	}
	
	public void imageManger(JPanel p) {
        
		ImageManager imageManger = new ImageManager();
		JLabel label = imageManger.imageFiller(FilePaths.loginImage, null);
        
        
        p.add(label, BorderLayout.EAST);
	}
	
	private void loginHandler(String username, String password) {
		// for guest
		Authenticator auth = new Authenticator(username, password);
		boolean result = auth.isAuthenticated();
		
		// if authorized
		
		if(result) {					
			MainView.panelRemover(theFrame, loginPanel);
			new GuestMainView(theFrame, auth.getGuestId());		
			MainView.repainter(theFrame);
		} else {
			JOptionPane.showMessageDialog(theFrame, "Invalid credientail!", "login failed", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void loginHandler(String username, String password, String access_level) {
		// for staff
		Authenticator auth = new Authenticator(username, password, access_level);
		boolean result = auth.isAuthenticated();
		
		// if authorized
		
		if(result) {					
			MainView.panelRemover(theFrame, loginPanel);		
			MainView.repainter(theFrame);
		} else {
			JOptionPane.showMessageDialog(theFrame, "Invalid credientail! try again", "login failed", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	

	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == staffOptionBtn) {
			cardLayout.show(FormPanel, "guest");
			
		} else if(e.getSource() == guestOptionBtn) {
			cardLayout.show(FormPanel, "staff");
			
		} else if(e.getSource() == loginGuestBtn ) {
			System.out.println("guest login pressed");
			
			String username = usernameField.getText();
			char[] pswdChar = passwordField.getPassword();
            String pswd = new String(pswdChar);
            
			loginHandler(username, pswd);
			
		} else if(e.getSource() == loginStaffBtn) {
			System.out.println("staff login pressed");
			
			String username = usernameStaffField.getText();
			char[] pswdChar = passwordStaffField.getPassword();
            String pswd = new String(pswdChar);
            
			loginHandler(username, pswd, "staff");
			
		} else if(e.getSource() == signupBtn ) {
			cardLayout.show(FormPanel, "signup");
			
		}
	}
}