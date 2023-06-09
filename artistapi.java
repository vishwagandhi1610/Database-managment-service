import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
public class artistapi {
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
	 * API to insert artist in Artist table.
	 * 
	 * @param creatorsid: Artist id 
	 * 
	 * @param cf_name: Artist first name
	 * 
	 * @param cl_name: Artist last name
	 * 
	 * @param labelname: label name connected with the artist
	 * 
	 * @param a_status: Artist status.
	 * 
	 * @param type: Artist type
	 * 
	 * @param primary_genre: Artist primary genre
	 * 
	 * @param monthly_listeners: monthly listeners for the art5ist
	 * 
	 * @param a_country: country where artist belongs.
	 * 
	 */

	public static void insertArtist(String creatorsid, String cf_name, String cl_name, String labelname,
			String a_status, String type, String primary_genre, int monthly_listeners, String a_country) {
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

			String s3 = "INSERT INTO Artist VALUES (?,?,?,?,?,?,?)";
			s2 = connection.prepareStatement(s3);
			// Assigning values to the prepared statement
			s2.setString(1, creatorsid);
			
			if (labelname.length()==0) {
                s2.setNull(2, Types.NULL);
            }
            else{
                s2.setString(2, labelname);
            }
			s2.setString(3, a_status);
			s2.setString(4, type);
			s2.setString(5, primary_genre);
			s2.setInt(6, monthly_listeners);
			s2.setString(7, a_country);
			// execute insert query using PreparedStatement object.
			s1.executeUpdate();
			s2.executeUpdate();
			System.out.println("Artist record has been inserted.");
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
	 * API to update artist first name in creators table.
	 * 
	 * @param creators_id: Artist Id
	 * 
	 * @param cf_name: Artist first name
	 */
	public static void updateArtistFirstName(String creators_id, String cf_name) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update Artist's first name for the given creators_id.
			String updateSql = "UPDATE Creators SET cf_name = '" + cf_name + "' WHERE creatorsid = '" + creators_id
					+ "'";
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Artist first name updated.");
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
	public static void updateArtistLastName(String creators_id, String cl_name) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update Artist's last name for the given creators_id.
			String updateSql = "UPDATE Creators SET cl_name = '" + cl_name + "' WHERE creatorsid = '" + creators_id
					+ "'";
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Artist last name updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

	/*
	 * API to update artist label name in Artist table.
	 * 
	 * @param creators_id: Artist Id
	 * 
	 * @param labelnamed: label name
	 */

	public static void assignArtistLabelName(String creators_id, String labelname) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update Artist's label name for the given creators_id.
			String updateSql = "UPDATE Artist SET labelname = '" + labelname + "' WHERE creatorsid = '" + creators_id
					+ "'";
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Artist label name Assigned.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

	/*
	 * API to update artist status in artist table.
	 * 
	 * @param creators_id: Artist Id
	 * 
	 * @param a_status: Artist status
	 */
	public static void updateArtistStatus(String creators_id, String a_status) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update artist_status for the given creators id.
			String updateSql = "UPDATE Artist SET a_status = '" + a_status + "' WHERE creatorsid = '" + creators_id
					+ "'";
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Artist Status updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

	/*
	 * API to update artist type in artist table.
	 * 
	 * @param creators_id: Artist Id
	 * 
	 * @param type: Artist type
	 */
	public static void updateArtistType(String creators_id, String type) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update type of artist for the given creators id.
			String updateSql = "UPDATE Artist SET type = '" + type + "' WHERE creatorsid = '" + creators_id + "'";
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Artist Type updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

		/*
	 * API to update artist primary genre in artist table.
	 * 
	 * @param creators_id: Artist Id
	 * 
	 * @param primary_genre: Artist primary genre
	 */

	public static void updateArtistPrimaryGenre(String creators_id, String primary_genre) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update primary genre of artist for the given creators id.
			String updateSql = "UPDATE Artist SET primary_genre = '" + primary_genre + "' WHERE creatorsid = '"
					+ creators_id + "'";
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Artist's primary genre updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

		/*
	 * API to update artist country in artist table.
	 * 
	 * @param creators_id: Artist Id
	 * 
	 * @param a_country: Artist country 
	 */

	public static void updateArtistCountry(String creators_id, String a_country) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update country of artist for the given creators id.
			String updateSql = "UPDATE Artist SET a_country = '" + a_country + "' WHERE creatorsid = '" + creators_id
					+ "'";
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Artist's country updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

	/*
	 * API to update artist monthly listeners in artist table.
	 * 
	 * @param creators_id: Artist Id
	 * 
	 * @param monthly_listeners: Artist monthly listeners
	 */

	public static void updateArtistMonthlyListeners(String creators_id, int monthly_listeners) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update number of monthly listeners of artist for the
			// given creators id.
			String updateSql = "UPDATE Artist SET monthly_listeners = '" + monthly_listeners + "' WHERE creatorsid = '"
					+ creators_id + "'";
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Artist's monthly listeners updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

		/*
	 * API to delete artist  in artist table.
	 * 
	 * @param creators_id: Artist Id
	 * 
	 */

	public static void deleteArtist(String creators_id) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			stmt = connection.createStatement();
			// delete statement to delete the artist with given creators_id.
			String deletePubQuery = "DELETE FROM Creators WHERE creatorsid = '" + creators_id + "'";
			// execute the delete query using the Statement object.
			stmt.executeUpdate(deletePubQuery);
			System.out.println("Artist deleted.");
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

	// method to close connection
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

	// method to statement
	static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (Throwable whatever) {
			}
		}
	}
}
