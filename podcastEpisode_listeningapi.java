import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class podcastEpisode_listeningapi {
    // MariaDB Credentials
	private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/hsangha";
	private static final String user = "hsangha";
	private static final String password = "200473048";

	public static Connection connection = null;
	public static PreparedStatement s1 = null;
	public static Statement stmt = null;
	public static ResultSet rs = null;

	/*
	 * API to enter Podcast Episode Listening details in podcastEpisode_listening table.
	 * 
	 */

    public static void insertPodcastEpL(String podcastid,int episodeno,String pel_date,int listening_count ) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);

            String s4 = "INSERT INTO podcastEpisode_listening VALUES (?,?,?,?)";
			// Assigning values to the prepared statement
            s1 = connection.prepareStatement(s4);
			s1.setString(1, podcastid);
			s1.setInt(2, episodeno);
			s1.setString(3, pel_date);
			s1.setInt(4, listening_count);

            
			// execute insert query using PreparedStatement object.
			s1.executeUpdate();
			System.out.println("Podcast Episode Listening record has been inserted.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(s1);
			close(connection);
		}
	}
    
	/*
	 * API to update Podcast Episode count in Podcast table.
	 * 
	 * @param mediaid: Media ID
	 * 
	 * @param episode_count: Episode Count 
	 */
    public static void updatePodcastEpCount(String podcastid,int episodeno, String pel_date,int listening_count) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update PUB_TITLE for the given publication id.
			String updateSql = "UPDATE podcastEpisode_listening SET listening_count = '" + listening_count + "' WHERE podcastid = '"+ podcastid+ "' AND episodeno = '"+ episodeno+ "'AND pel_date = '"+ pel_date+ "' "; 
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Podcast Episode listening count  updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
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
