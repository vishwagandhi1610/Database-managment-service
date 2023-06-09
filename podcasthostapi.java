import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
public class podcasthostapi {
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
	 * API to insert podcast host in Podcast host table.
	 * 
	 * @param creatorsid: Podcast host id 
	 * 
	 * @param cf_name: Podcast host first name
	 * 
	 * @param cl_name: Podcast host last name
	 * 
	 * @param email: Podcast host email
	 * 
	 * @param phone: Podcast host phone.
	 * 
	 * @param city: Podcast host city
	 * 
	 */

	public static void insertPodcastHost(String creatorsid, String cf_name, String cl_name, String email, int phone,
			String city) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);

			String s4 = "INSERT INTO Creators VALUES (?,?,?)";
			// Assigning values to the prepared statement
			s1 = connection.prepareStatement(s4);
			s1.setString(1, creatorsid);
			s1.setString(2, cf_name);
			s1.setString(3, cl_name);

			String s3 = "INSERT INTO podcastHost VALUES (?,?,?,?)";
			// Assigning values to the prepared statement
			s2 = connection.prepareStatement(s3);
			// Assigning values to the prepared statement
			s2.setString(1, creatorsid);
			//s2.setString(2, email);
			if (email.length() == 0) {
				s2.setNull(2, Types.NULL);
			} else {
				s2.setString(2, email);
			}
			s2.setInt(3, phone);
			//s2.setString(4, city);
			if (city.length() == 0) {
				s2.setNull(4, Types.NULL);
			} else {
				s2.setString(4, city);
			}
			// execute insert query using PreparedStatement object.
			s1.executeUpdate();
			s2.executeUpdate();
			System.out.println("Podcast Host record has been inserted.");
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
	 * API to update podcast host first name in Podcasthost table.
	 * 
	 * @param creators_id: podcast host Id
	 * 
	 * @param cf_name: podcast host first name
	 */

	public static void updatePodcastHostFirstName(String creators_id, String cf_name) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update first name of Podcast Host for the given creators
			// id.
			String updateSql = "UPDATE Creators SET cf_name = '" + cf_name + "' WHERE creatorsid = '" + creators_id
					+ "'";
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Podcast Host first name updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

	/*
	 * API to update podcast host last name in podcastHost table.
	 * 
	 * @param creators_id: podcast host Id
	 * 
	 * @param cl_name: podcast host last name
	 */
	public static void updatePodcastHostLastName(String creators_id, String cl_name) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update last name of Podcast Host for the given creators
			// id.
			String updateSql = "UPDATE Creators SET cl_name = '" + cl_name + "' WHERE creatorsid = '" + creators_id
					+ "'";
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Podcast Host last name updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

		/*
	 * API to update podcast host email in podcastHost table.
	 * 
	 * @param creators_id: podcast host Id
	 * 
	 * @param email: podcast host email
	 */

	public static void updatePodcastHostEmail(String creators_id, String email) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update email of Podcast Host for the given creators id.
			String updateSql = "UPDATE podcastHost SET email = '" + email + "' WHERE creatorsid = '" + creators_id
					+ "'";
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Podcast Host email updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

		/*
	 * API to update podcast host city in podcastHost table.
	 * 
	 * @param creators_id: podcast host Id
	 * 
	 * @param city: podcast host city
	 */

	public static void updatePodcastHostCity(String creators_id, String city) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update city of Podcast Host for the given creators id.
			String updateSql = "UPDATE podcastHost SET city = '" + city + "' WHERE creatorsid = '" + creators_id + "'";
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Podcast Host city updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

		/*
	 * API to update podcast host phone in podcastHost table.
	 * 
	 * @param creators_id: podcast host Id
	 * 
	 * @param phone: podcast host phone
	 */

	public static void updatePodcastHostPhone(String creators_id, int phone) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update phone of Podcast Host for the given creators id.
			String updateSql = "UPDATE podcastHost SET phone = '" + phone + "' WHERE creatorsid = '" + creators_id
					+ "'";
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Podcast Host phone updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

		/*
	 * API to delete podcast host in podcastHost table.
	 * 
	 * @param creators_id: Podcast Host Id
	 * 
	 */
	public static void deletePodcastHost(String creators_id) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// Create Statement Object.
			stmt = connection.createStatement();
			// delete statement to delete the Podcast Host with given creators id.
			String deletePubQuery = "DELETE FROM Creators WHERE creatorsid = '" + creators_id + "'";
			// execute the delete query using the Statement object.
			stmt.executeUpdate(deletePubQuery);
			System.out.println("PodcastHost deleted.");
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
