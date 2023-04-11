import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class listenstoapi {
    // MariaDB Credentials
	private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/hsangha";
	private static final String user = "hsangha";
	private static final String password = "200473048";

	public static Connection connection = null;
	public static PreparedStatement s1 = null;
    public static PreparedStatement s2 = null;
	public static Statement stmt = null;
	public static ResultSet rs = null;

	/*
	 * API to enter play count record for songs in listensto table.
	 * Input:
	 * @param userid: User ID  
	 * @param mediaid: Media ID associated to the song
     * @param uplay_date: play date when the user listened to the song
     * @param dplay_count: play count per day
	 * 
	 */

    public static void insertListenRecord(String userid, String mediaid, String uplay_date, int dplay_count) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);

            String s4 = "INSERT INTO listensto VALUES (?,?,?,?)";
			// Assigning values to the prepared statement
            s1 = connection.prepareStatement(s4);
			s1.setString(1, userid);
			s1.setString(2, mediaid);
			s1.setString(3, uplay_date);
			s1.setInt(4, dplay_count);
            
			// execute insert query using PreparedStatement object.
			s1.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(s1);
			close(connection);
		}
	}

    
	/*
	 * API to update play count record for songs in listensto table.
	 * Input:
	 * @param userid: User ID  
	 * @param mediaid: Media ID associated to the song
     * @param uplay_date: play date when the user listened to the song
     * @param dplay_count: play count per day
	 * 
	 */
    public static void updateListenRecord(String userid,String mediaid, String uplay_date, int dplay_count) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			int rowCount = -1;
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// check if the play count record with same timestamp exists. If yes update that if not add new record.
			String getSql = "SELECT COUNT(*) FROM listensto WHERE userid = '" + userid +  "' AND mediaid = '"+ mediaid+ "' AND uplay_date = '"+ uplay_date+ "' "; 
			// update statement to update playcount for the given User ID, Media ID and Play Date.
			String updateSql = "UPDATE listensto SET dplay_count = '" + dplay_count + "' WHERE userid = '" + userid +  "' AND mediaid = '"+ mediaid+ "' AND uplay_date = '"+ uplay_date+ "' ";
			// Create Statement Object.
			stmt = connection.createStatement();
			rs = stmt.executeQuery(getSql);
			rs.next();
			rowCount = rs.getInt(1);
			if (rowCount > 0) {
				// execute update statement using Statement object.
				stmt.execute(updateSql);
			  } else {
				//insert new record with another timestamp.
				listenstoapi.insertListenRecord(userid, mediaid, uplay_date, dplay_count);
			  }
			System.out.println("Song Play Count has been updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(rs);
			close(stmt);
			close(connection);
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

    	// method to close Connection.
	static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
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

    // method to Statement.
	static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (Throwable whatever) {
			}
		}
	}
}
