package Model;

import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import constants.DbInfo;

public class Connector {
	String url, username, password;
	Connection con;
	PreparedStatement preparedStatement;
	CallableStatement callableStatement ; // for procedures
	ResultSet resultSet;
	
	public Connector(Crediential crediential)  {
		url = DbInfo.URL;
		username = crediential.username;
		password = crediential.password;
		
		
		try {
			
			con = DriverManager.getConnection(url, username,password);

		}  catch(SQLException  e) {
			e.printStackTrace();
		}
		
	}

	
	public ResultSet getQueryResult(String query) {
		
		try {
			preparedStatement = con.prepareStatement(query);
			
			resultSet = preparedStatement.executeQuery();
			
			return resultSet;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return resultSet;
	} 
	
	public ResultSet getQueryResult(String query, Object[] param) {
		
		try {
			
			preparedStatement = con.prepareStatement(query);
			
			int count = 1;
			for(Object item: param) {	
				if(item instanceof String) {
					preparedStatement.setString(count, (String) item);
				} else if(item instanceof Integer) {
				    preparedStatement.setInt(count, (Integer) item);
				} 
//				else if(item == null) {
//					preparedStatement.setNull(count,(int) item);
//				}
				count++;
			}
			
			
			resultSet = preparedStatement.executeQuery();
			
			return resultSet;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return resultSet;
	} 
	
	public ResultSet getProcedureCallResult(String procedureCall, Object[] param) {

		try {
		    callableStatement = con.prepareCall(procedureCall);
		    // Set input parameters
			int count = 1;
			for(Object item: param) {	
				if(item instanceof String) {
					callableStatement.setString(count, (String) item);
				} else if(item instanceof Integer) {
					callableStatement.setInt(count, (Integer) item);
				} else if(item == null) {
					// only for editing profile (problematic)
					callableStatement.setNull(count, Types.VARCHAR);
				}
				count++;
			}
			
		    
		    // Execute the stored procedure
			boolean hasResults = callableStatement.execute();
		    
		    // Process the result set if available
		    if (hasResults) {
		        resultSet = callableStatement.getResultSet();
		        return resultSet;
		    }
		    
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		
		return resultSet;
	}
	
	public void closeResultSet() {
    	try {
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closePreparedStatement()  {
	    try {
			preparedStatement.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void closeCallableStatement()  {
	    try {
			callableStatement.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	
//	public ResultSet getUpdateResult(String query) {
//		
//		return resultSet;
//	} 

 }
