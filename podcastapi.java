import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.sql.SQLException;

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

	public static void insertPodcast(String mediaid, String media_name, String genre, String language, String m_country,
			int episode_count, String hostid, String sponsor) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);

			// set Auto-Commit to false for transaction handling.
			connection.setAutoCommit(false);

			String s4 = "INSERT INTO Media VALUES (?,?,?,?,?)";
			// Assigning values to the prepared statement
			s1 = connection.prepareStatement(s4);
			s1.setString(1, mediaid);
			s1.setString(2, media_name);
			s1.setString(3, genre);
			s1.setString(4, language);
			s1.setString(5, m_country);

			// execute insert query using PreparedStatement object.
			s1.executeUpdate();

			String s3 = "INSERT INTO Podcast VALUES (?,?,?,?)";
			// Assigning values to the prepared statement
			s2 = connection.prepareStatement(s3);
			// Assigning values to the prepared statement
			s2.setString(1, mediaid);
			s2.setInt(2, episode_count);
			if (hostid.length()==0) {
                s2.setNull(3, Types.NULL);
            }
            else{
                s2.setString(3, hostid);
            }
			// execute insert query using PreparedStatement object.
			s2.setString(4, sponsor);
			s2.executeUpdate();
			
			System.out.println("Podcast record has been inserted.");

			// Over successful execution of all operations of this task, commit the transaction.
			connection.commit();

		} catch (Exception e) {
			try {
				// If an exception has occurred, roll back the transaction.
				connection.rollback();
			} catch (SQLException e1) {
				try {
					// set AutoCommit to True at the end of transaction.
					connection.setAutoCommit(true);
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				e1.printStackTrace();
			}
			e.printStackTrace();
		} 
		
		finally {
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
			// update statement to update episode count for the given mediaid.
			System.out.print(mediaid);
			String updateSql = "UPDATE Podcast SET episode_count = '" + episode_count + "' WHERE mediaid = '" + mediaid
					+ "'";
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

	public static void updatePodcastHostID(String mediaid, String hostid) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update podcast host id for the given mediaid.
			System.out.print(mediaid);
			String updateSql = "UPDATE Podcast SET hostid = '" + hostid + "' WHERE mediaid = '" + mediaid + "'";
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

	public static void updatePodcastSponsor(String mediaid, String sponsor) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update podcast host id for the given mediaid.
			System.out.print(mediaid);
			String updateSql = "UPDATE Podcast SET sponsor = '" + sponsor + "' WHERE mediaid = '" + mediaid + "'";
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

	public static void deletePodcast(String mediaid) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// Create Statement Object.
			stmt = connection.createStatement();
			// delete statement to delete the Song with given mediaid.
			String deletePubQuery = "DELETE FROM Media WHERE mediaid = '" + mediaid + "'";
			// execute the delete query using the Statement object.
			stmt.executeUpdate(deletePubQuery);
			System.out.println("Podcast deleted.");
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
