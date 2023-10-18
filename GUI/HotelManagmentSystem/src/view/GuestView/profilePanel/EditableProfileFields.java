package view.GuestView.profilePanel;

import javax.swing.*;

public class EditableProfileFields {
	
	public EditableProfileFields() {
		// Create an array of components for input fields
        Object[] fields = {
                "Phone number:", new JTextField(),
                "Email:", new JTextField(),
                "New Password:", new JPasswordField()
        };

        // Show the dialog and get the user input
        int result = JOptionPane.showConfirmDialog(null, fields, "Edit your profile (leave blank if no update needed)", JOptionPane.OK_CANCEL_OPTION);

        // Process the user input
        if (result == JOptionPane.OK_OPTION) {
            String p_num = ((JTextField) fields[1]).getText();
            String email = ((JTextField) fields[3]).getText();
            char[] pswdChar = ((JPasswordField) fields[5]).getPassword();
            String pswd = new String(pswdChar);
            
            // Do something with the input values
//            if(p_num.equals("") && email.equals("") && pswd.equals("")) {
//            	System.out.println("phone is null");
//            }
            
            System.out.println("Phone number: " + p_num);
            System.out.println("Email: " + email);
            System.out.println("New Password: " + pswd);
        }
	}
}
