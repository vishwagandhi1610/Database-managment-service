import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class assignapi {
	// MariaDB Credentials
	private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/hsangha";
	private static final String user = "hsangha";
	private static final String password = "200473048";

	public static Connection connection = null;
	public static Statement stmt = null;
	public static PreparedStatement ps1 = null;
	public static PreparedStatement ps2 = null;
	public static PreparedStatement ps3 = null;
	public static ResultSet rs1 = null;
	public static ResultSet rs2 = null;

	/*
	 * API to assign podcast hosts to podcast table.
	 * 
	 * @param hostid: Podcast host Id
	 * 
	 * @param mediaid: Podcast Id
	 */

     public static void assignhosttoepisode(String mediaid, String hostid) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update album the song belongs to for the given mediaid.
			System.out.print(mediaid);
			String updateSql = "UPDATE Podcast SET hostid = '" + hostid + "' WHERE mediaid = '" + mediaid + "'";
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("podcast host Assigned.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

     // method to close Connection.
	static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (Throwable whatever) {
			}
		}
	}

	// method to Statement.
	static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (Throwable whatever) {
			}
		}
	}

	// method to close PreparedStatement.
	static void close(PreparedStatement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (Throwable whatever) {
			}
		}
	}

	// method to close ResultSet
	static void close(ResultSet result) {
		if (result != null) {
			try {
				result.close();
			} catch (Throwable whatever) {
			}
		}
	}
}