package view.GuestView.profilePanel;

import java.sql.Types;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

class CustomException extends Exception {
	
	public CustomException() {
		super();
	}
	
	public CustomException(String message) {
		super(message);
	}
	
	public CustomException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public CustomException(Throwable cause) {
		super(cause);
	}
}

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
        } else if(!isValidPhoneNumber(p_num)) {
        	throw new CustomException("Phone Number is not valid! It Should be 9 char length Integer.");
        } else if(!isValidEmail(email)) {
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
	
    public static boolean isValidEmail(String email) {
      	if(email.isEmpty()) {
    		// this means the user is not updating email
    		return true;
    	} 
        // regular expression pattern for a valid email address
        String regexPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        Pattern pattern = Pattern.compile(regexPattern);

        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
    
    public static boolean isValidPhoneNumber(String phoneNumber) {
    	
    	if(phoneNumber.isEmpty()) {
    		// this means the user is not updating p_num
    		return true;
    	} else if( phoneNumber.length() != 9) {
    		return false;
    	}
    	
        String regexPattern = "^[0-9]+$";

        Pattern pattern = Pattern.compile(regexPattern);

        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }
}
