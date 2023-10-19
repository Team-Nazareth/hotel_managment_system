package view.GuestView.profilePanel;

import java.sql.Types;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

import view.CustomException;
import view.FormValidator;


class EditableProfileFields {
	String phone_number;
	String email;
	String password;
}

public class EditProfile {
	String p_num, email, pswd;
	boolean rejected = false;
	
	public EditProfile() {
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
        	
        	p_num = ((JTextField) fields[1]).getText();
            email = ((JTextField) fields[3]).getText();
            char[] pswdChar = ((JPasswordField) fields[5]).getPassword();
            pswd = new String(pswdChar);
       
        } else {
        	rejected = true;
        }
        
	}
	
	public EditableProfileFields getValues() throws CustomException {
        // validate input
        if(rejected) {
        	throw new CustomException("Update is rejected!");
        	
        } else if(p_num.isEmpty() && email.isEmpty() && pswd.isEmpty() ) {
        	System.out.println("No change took place");
        	throw new CustomException("No Update!");
        } else if(!FormValidator.isValidPhoneNumber(p_num, true)) {
        	throw new CustomException("Phone Number is not valid! It Should be 9 char length Integer.");
        } else if(!FormValidator.isValidEmail(email, true)) {
        	throw new CustomException("Not a valid email format!");
        } else {
        	
        	// checks for empty input and sets it to null
        	String[] inputs = {p_num, email, pswd};
        	for(String input: inputs) {
        		if(input.isEmpty()) input = null;
        	}
        }
        
        EditableProfileFields fileds = new EditableProfileFields();
        fileds.phone_number = p_num;
        fileds.email = email;
        fileds.password = pswd;
         
        return fileds;
	}

}
