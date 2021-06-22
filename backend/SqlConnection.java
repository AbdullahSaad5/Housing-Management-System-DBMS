package backend;

import java.sql.*;

public class SqlConnection {

	public static Connection connectToDatabase() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "project1", "project1");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return connection;
	}

	public static ResultSet findResult(PreparedStatement query) {
		ResultSet result = null;
		try {
			result = query.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public static int alterResults(PreparedStatement query) {
		int count = -1;
		try {
			count = query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

}
