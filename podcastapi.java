import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class podcastapi {
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
	 * API to enter Podcast details in Media and Podcast table.
	 * 
	 */

    public static void insertPodcast(String mediaid,String media_name,String genre,String language,String m_country,int episode_count, String hostid ) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);

            String s4 = "INSERT INTO Media VALUES (?,?,?,?,?)";
			// Assigning values to the prepared statement
            s1 = connection.prepareStatement(s4);
			s1.setString(1, mediaid);
			s1.setString(2, media_name);
			s1.setString(3, genre);
			s1.setString(4, language);
			s1.setString(5, m_country);

            String s3= "INSERT INTO Podcast VALUES (?,?,?)";
			// Assigning values to the prepared statement
            s2 = connection.prepareStatement(s3);
			// Assigning values to the prepared statement
            s2.setString(1, mediaid);
            s2.setInt(2, episode_count);
            s2.setString(3, hostid);
			// execute insert query using PreparedStatement object.
			s1.executeUpdate();
            s2.executeUpdate();
			System.out.println("Song record has been inserted.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(s1);
            close(s2);
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
    public static void updatePodcastEpCount(String mediaid, int episode_count) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update PUB_TITLE for the given publication id.
			System.out.print(mediaid);
			String updateSql = "UPDATE Podcast SET episode_count = '" + episode_count + "' WHERE mediaid = '"+ mediaid+ "'"; 
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Podcast episode_count updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

	/*
	 * API to update artist last name in creators table.
	 * 
	 * @param creators_id: Artist Id
	 * 
	 * @param cl_name: Artist last name
	 */
	public static void updatePodcastHostID(String mediaid, String hostid) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update PUB_TITLE for the given publication id.
			System.out.print(mediaid);
			String updateSql = "UPDATE Podcast SET hostid = '" + hostid + "' WHERE mediaid = '"+ mediaid+ "'"; 
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Podcast Host ID updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

	/*
	 * API For updating rest all values are already present in songapi.java
	 * API For delete Podcast is already present in songapi.java
	
	 */

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
