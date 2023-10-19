package view;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Connector;
import Model.Users;
import constants.DbInfo;

class PasswordValidator {
	static Connector con;
	static String User;
	
	public PasswordValidator(String user) {
		con = new Connector(Users.getRoot());
		User = user;
	}
	
	public static boolean validatePassword(String username, String password ) {
        // Retrieve the hashed password from the database for the given username
        String storedHashedPassword = retrieveHashedPassword(username);

        if (storedHashedPassword == null) {
            // User does not exist in the database
            System.out.println("User not found.");
            return false;
        }

        // Hash the input password using the same algorithm as the stored password
        String hashedPassword = hashPassword(password);

        // Compare the hashed input password with the stored hashed password
        boolean passwordMatch = hashedPassword.equals(storedHashedPassword);

        if (passwordMatch) {
            System.out.println("Password is valid.");
        } else {
            System.out.println("Password is invalid.");
        }

        return passwordMatch;
    }

    private static String retrieveHashedPassword(String username) {
        String hashedPassword = null;

        try  {
        	String query;
        	if (User.equals("guest")) {        		
        		query = "SELECT password FROM guest where username = ? ;";
        	} else {
        		query = "SELECT password FROM staff where username = ? ;";
        	}
       
            
            Object[] param = {username};
        	ResultSet resultSet = con.getQueryResult(query, param);

            if (resultSet.next()) {
                hashedPassword = resultSet.getString("password");
            }
            
            con.closeConnection();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hashedPassword;
    }
    
    private static String hashPassword(String password) {
        try {

            // Create a MessageDigest instance with the specified algorithm
            MessageDigest messageDigest = MessageDigest.getInstance(DbInfo.HASH_ALGORITHM);

            // Hash the salted password
            byte[] hashedPassword = messageDigest.digest(password.getBytes());

            // Convert the hashed password to a hexadecimal string
            return bytesToHexString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();

        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

}

public class Authenticator {
	private boolean result ;
	private String Username;
	
	public Authenticator(String username, String password) {
		
		Username = username;
		
		new PasswordValidator("guest");
		result = PasswordValidator.validatePassword(username, password);
        System.out.println("Password validation result: " + result);
	}
	
	public Authenticator(String username, String password, String user) {
		Username = username;
		new PasswordValidator(user);
		result = PasswordValidator.validatePassword(username, password);
        System.out.println("Password validation result(staff): " + result);
	}
	
	public boolean isAuthenticated() {
		return result;
	}
	
	public int getGuestId() {
		int guest_id = 0;
		
		if(isAuthenticated()) {
			
			try  {
	        	String query = "SELECT guest_id FROM guest where username = ? ;"; 
	       
	            
	            Object[] param = {Username};
	        	Connector con = new Connector(Users.getRoot());
	        	ResultSet resultSet = con.getQueryResult(query, param);

	            if (resultSet.next()) {
	            	guest_id = resultSet.getInt("guest_id");
	            }
	            
	            con.closeConnection();
	            return guest_id;
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		}
		return guest_id;
	}
	
	public int getStaffId() {
		int staff_id = 0;
		
		if(isAuthenticated()) {
			
			try  {
	        	String query = "SELECT staff_id FROM guest where username = ? ;"; 
	       
	            
	            Object[] param = {Username};
	            
	            Connector con = new Connector(Users.getRoot());
	        	ResultSet resultSet = con.getQueryResult(query, param);

	            if (resultSet.next()) {
	            	staff_id = resultSet.getInt("guest_id");
	            }
	            
	            con.closeConnection();

	            return staff_id;
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		}
		return staff_id;
	}
	
	
}
