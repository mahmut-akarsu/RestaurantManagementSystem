package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class database {

	public static Connection connectDb() {
		String jdbcUrl = "jdbc:postgresql://localhost:5432/restaurant";
        String username = "postgres";
        String password = "123";
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection connect=DriverManager.getConnection(jdbcUrl,username,password);
			System.out.println("connected postgressql server");
			
		
			
			return connect;
			
		}
		catch (Exception e) {
			
			e.printStackTrace();
			// TODO: handle exception
			return null;
		}
	}
}
