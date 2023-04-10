import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class podcastEpisodeapi {
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
	 * API to insert podcast episode in Podcast episode table.
	 * 
	 * @param podcastid: Podcast id in which podcast episode is released
	 * 
	 * @param episodeid: Podcast episode id
	 * 
	 * @param title: Podcast episode title
	 * 
	 * @param p_duration: Podcast episode duration
	 * 
	 * @param p_release_date: Podcast episode release date
     * 	
	 * @param flat_fee: Flat fee per released episode
     * 
     * @param ad_count : Advertisment count
	 */

     public static void insertPodcastEpisode(String podcastid, String episodeid,String title,float p_duration,String p_release_date, int flat_fee,
     int ad_count) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);

			String s4 = "INSERT INTO podcastEpisode VALUES (?,?,?,?,?,?,?,?)";
			// Assigning values to the prepared statement
			s1 = connection.prepareStatement(s4);
			s1.setString(1, podcastid);
			s1.setString(2, episodeid);
			s1.setString(3, title);
			s1.setFloat(4, p_duration);
			s1.setString(5, p_release_date);
            s1.setInt(6, flat_fee);
            s1.setInt(7, ad_count);
            int temp = ad_count * 2;
            s1.setInt(8,temp);

			// execute insert query using PreparedStatement object.
			s1.executeUpdate();

			System.out.println("Podcast episode has been inserted.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(s1);
			close(connection);
		}
	}

	/*
	 * API to update podcast episode title in podcastEpisode table.
	 * 
	 * @param episodeid: episode Id
	 * 
	
	 * 
	 * @param title: Podcast Episode title
	 */
	public static void updatePodcastEpisodeTitle(String episodeid, String title) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update title for the given podcast episode id.
			String updateSql = "UPDATE podcastEpisode SET title = '" + title + "' WHERE episodeid = '"+ episodeid+ "' "; 
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Podcast episode Title updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

	/*
	 * API to update podcast episode duration in podcastEpisode table.
	 * 
	 * @param podcastid: Podcast Id
	 * 
	 * @param episodeno: Podcast Episode No
	 * 
	 * @param p_duration: Podcast Episode duration
	 */
	public static void updatePodcastEpisodeDuration(String episodeid ,  float p_duration) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update title for the given podcast episode id.
			String updateSql = "UPDATE podcastEpisode SET p_duration = '" + p_duration + "' WHERE episodeid = '"+ episodeid+ "' "; 
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Podcast episode duration updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

	/*
	 * API to update podcast episode release date in podcastEpisode table.
	 * 
	 * @param podcastid: Podcast Id
	 * 
	 * @param episodeno: Podcast Episode No
	 * 
	 * @param p_release_date: Podcast Episode release date
	 */
	public static void updatePodcastEpisodeReleasedate(String episodeid, String p_release_date) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update title for the given podcast episode id.
			String updateSql = "UPDATE podcastEpisode SET p_release_date = '" + p_release_date + "' WHERE episodeid = '"+ episodeid+ "' "; 
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Podcast episode release date updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}
		/*
	 * API to update podcast episode ad count in podcastEpisode table.
	 * 
	 * @param podcastid: Podcast Id
	 * 
	 * @param episodeno: Podcast Episode No
	 * 
	 * @param ad_count: Podcast Episode advertisment count
	 */
	public static void updatePodcastEpisodeAdcount(String episodeid,  int ad_count) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			int temp1 = ad_count * 2;
			// update statement to update title for the given podcast id and podcast episode no.
			String updateSql = "UPDATE podcastEpisode SET ad_count = '" + ad_count + "' WHERE episodeid = '"+ episodeid+ "' "; 
			// Create Statement Object.
			String updateSql2 = "UPDATE podcastEpisode SET bonus = '" + temp1 + "' WHERE episodeid = '"+ episodeid+ "' ";
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			stmt.execute(updateSql2);
			System.out.println("Podcast episode ad count updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

			/*
	 * API to update podcast episode flat fee in podcastEpisode table.
	 * 
	 * @param podcastid: Podcast Id
	 * 
	 * @param episodeno: Podcast Episode No
	 * 
	 * @param flat_fee: Podcast Episode flat fee
	 */
	public static void updatePodcastEpisodeFlatfee(String episodeid, int flat_fee) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update title for the given podcast episode id.
			String updateSql = "UPDATE podcastEpisode SET flat_fee = '" + flat_fee  + "' WHERE episodeid = '"+ episodeid+ "' "; 
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Podcast episode flat fee updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

	/*
	 * API to delete podcast episode from podcastEpisode table.
	 * 
	 * @param podcastid: podcast Id
	 * 
	 * @param episdoeno: Podcast Episode Number
	 */
	public static void deletePodcastEpisode(String episodeid) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);

			String deleteSql = "DELETE FROM podcastEpisode WHERE episodeid = '" + episodeid +  "' ";
			// Create Statement Object.
			stmt = connection.createStatement();
			stmt.executeUpdate(deleteSql);
			System.out.println("podcast episode deleted.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

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