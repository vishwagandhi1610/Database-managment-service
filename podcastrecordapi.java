import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class podcastrecordapi {
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

    public static void insertPodcastRecord(String podcastid,String pd_date, int rating,int total_subscribers ) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);

            String s4 = "INSERT INTO podcast_record VALUES (?,?,?,?)";
			// Assigning values to the prepared statement
            s1 = connection.prepareStatement(s4);
			s1.setString(1, podcastid);
			s1.setString(2, pd_date);
			s1.setInt(3, rating);
			s1.setInt(4, total_subscribers);

            
			// execute insert query using PreparedStatement object.
			s1.executeUpdate();
			System.out.println("Podcast Record has been inserted.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(s1);
			close(connection);
		}
	}
    
	/*
	 * API to update Podcast Record in Podcast Record table.
	 * Input:
	 * @param podcastid: Podcast ID  
	 * @param pd_date: Episode Count 
     * @param rating: Podcast Episode  
     * @param total_subscribers: Episode Count 
	 */
    public static void updatePodcastRating(String podcastid,String pd_date, int rating) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update PUB_TITLE for the given publication id.
			String updateSql = "UPDATE podcast_record SET rating = '" + rating + "' WHERE podcastid = '"+ podcastid+ "' AND pd_date = '"+ pd_date+ "' "; 
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Podcast Record Rating has been updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

    public static void updatePodcastSub(String podcastid,String pd_date, int total_subscribers) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update PUB_TITLE for the given publication id.
			String updateSql = "UPDATE podcast_record SET total_subscribers = '" + total_subscribers + "' WHERE podcastid = '"+ podcastid+ "' AND pd_date = '"+ pd_date+ "' "; 
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Podcast Record total subscribers has been updated.");
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
