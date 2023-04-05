import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class userapi {
    // MariaDB Credentials
	private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/hsangha";
	private static final String user = "hsangha";
	private static final String password = "200473048";

	public static Connection connection = null;
    public static PreparedStatement s2 = null;
	public static Statement stmt = null;
	public static ResultSet rs = null;

    public static void insertUser(String uphone, String joinDate, String endDate, String ufName, String ulName, String uEmail, String uStatus, int subFee) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);

            String s3 = "INSERT INTO user VALUES (?,?,?,?,?,?,?,?)";
			// Assigning values to the prepared statement
            s2 = connection.prepareStatement(s3);
			// Assigning values to the prepared statement
            s2.setString(1, uphone);
            s2.setString(2, joinDate);
            s2.setString(3, endDate);
            s2.setString(4, ufName);
            s2.setString(5, ulName);
            s2.setString(6, uEmail);
            s2.setString(7, uStatus);
            s2.setInt(8, subFee);
			
            // execute insert query using PreparedStatement object.
            s2.executeUpdate();

			System.out.println("Artist record has been inserted.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
            close(s2);
			close(connection);
		}
	}


	/*
	 * API to update user first name in users table.
	 * 
	 * @param uphone: User's phone number
	 * 
	 * @param ufName: User's first name
	 */
    public static void updateUserFirstName(String uphone, String ufName) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update uf_name for the given user.
			System.out.print(uphone);
			String updateSql = "UPDATE user SET uf_name = '" + ufName + "' WHERE uphone = '" + uphone+ "'";
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("User's First name updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

	/*
	 * API to update user last name in users table.
	 * 
	 * @param uphone: User's phone number
	 * 
	 * @param ulName: User's last name
	 */
	public static void updateUserLastName(String uphone, String ulName) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update ul_name for the given user.
			String updateSql = "UPDATE user SET ul_name = '" + ulName + "' WHERE uphone = '" + uphone+ "'"; 
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("User's Last name updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}
	
	public static void updateUserJoinDateName(String uphone, String joinDate) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update PUB_TITLE for the given publication id.
			String updateSql = "UPDATE user SET join_date = '" + joinDate + "' WHERE uphone = '"+ uphone + "'"; 
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Artist label name name updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

	public static void updateArtistStatus(String creators_id, String a_status) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update artist_status for the given creators id.
			String updateSql = "UPDATE Artist SET a_status = '" + a_status + "' WHERE creatorsid = '"+ creators_id+ "'"; 
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

	public static void updateArtistType(String creators_id, String type) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update type of artist for the given creators id.
			String updateSql = "UPDATE Artist SET type = '" + type + "' WHERE creatorsid = '"+ creators_id+ "'"; 
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

	public static void updateArtistPrimaryGenre(String creators_id, String primary_genre) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update primary genre of artist for the given creators id.
			String updateSql = "UPDATE Artist SET primary_genre = '" + primary_genre + "' WHERE creatorsid = '"+ creators_id+ "'"; 
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

	public static void updateArtistCountry(String creators_id, String a_country) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update country of artist for the given creators id.
			String updateSql = "UPDATE Artist SET a_country = '" + a_country + "' WHERE creatorsid = '"+ creators_id+ "'"; 
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

	public static void updateArtistMonthlyListeners(String creators_id, int monthly_listeners) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update number of monthly listeners of artist for the given creators id.
			String updateSql = "UPDATE Artist SET monthly_listeners = '" + monthly_listeners + "' WHERE creatorsid = '"+ creators_id+ "'"; 
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

	public static void deleteArtist(String creators_id) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// set the PUB_ID field in ORDERS table to -1 for the publication that is being deleted.
			// String updateOrdersQuery = "UPDATE ARTIST SET PUB_ID = -1 WHERE PUB_ID = " + creators_id;
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute the update query using Statement Object.
			// stmt.executeUpdate(updateOrdersQuery);
			// delete statement to delete the publication with given PUB_ID.
			String deletePubQuery = "DELETE FROM Creators WHERE creatorsid = '" + creators_id+ "'";
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
