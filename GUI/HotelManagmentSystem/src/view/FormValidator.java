package view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormValidator {
	
    public static boolean isValidEmail(String email, boolean isUpdate) {
      	if(isUpdate && email.isEmpty()) {
    		// this means the user is not updating email
    		return true;
    	} 
        // regular expression pattern for a valid email address
        String regexPattern = "^[A-Za-z0-9+_.-].@[A-Za-z0-9.-]+$";

        Pattern pattern = Pattern.compile(regexPattern);

        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
    
    public static boolean isValidPhoneNumber(String phoneNumber, boolean isUpdate) {
    	
    	if(isUpdate && phoneNumber.isEmpty()) {
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
    
	public static boolean isValidateDate(String date) {
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);

        return matcher.matches();
    }
	
}
