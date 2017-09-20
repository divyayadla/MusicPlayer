/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author BHAVANA
 */
public class ConnectToMySQL {
    	Connection con=null;
	   
	  public Connection connect (String user, String pass)
	    {
	        try
	        {
	            Class.forName("com.mysql.jdbc.Driver");
	            con=DriverManager.getConnection("jdbc:mysql://localhost:8080",user,pass);
	            if (con!=null)
	                System.out.println("connected succesfully");
	            else
	                System.out.println("could not connect");
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
			return con;
	        
	       
	    }
	 public void close(Connection con)
	 {
	   try {
		   		con.close();
	   		} catch (SQLException e)
	   {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   }
	 }
}
