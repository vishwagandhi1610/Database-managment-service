import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;

public class userapi {
    // MariaDB Credentials
	private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/hsangha";
	private static final String user = "hsangha";
	private static final String password = "200473048";

	public static Connection connection = null;
    public static PreparedStatement s2 = null;
	public static Statement stmt = null;
	public static ResultSet rs = null;

    /*
	 * API to insert user record in users table.
	 * 
	 * @param uphone: User's phone number
	 * 
     * @param joinDate: User's join date
     * 
     * @param endDate: User's end date
     * 
	 * @param ufName: User's first name
     * 
     * @param ulName: User's last name
     * 
     * @param uEmail: User's email ID
     * 
     * @param uStatus: User's status whether active or inactive
     * 
     * @param subFee: User's subscription fee
	 */
    public static void insertUser(int uphone, String joinDate, String endDate, String ufName, String ulName, String uEmail, String uStatus, int subFee) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);

            String s3 = "INSERT INTO user VALUES (?,?,?,?,?,?,?,?)";
			// Assigning values to the prepared statement
            s2 = connection.prepareStatement(s3);
			// Assigning values to the prepared statement
            s2.setInt(1, uphone);
            s2.setString(2, joinDate);
            if (endDate == "") {
                s2.setNull(3, Types.NULL);
            }
            else{
                s2.setString(3, endDate);
            }
            s2.setString(4, ufName);
            s2.setString(5, ulName);
            if (uEmail == "") {
                s2.setNull(6, Types.NULL);
            }
            else{
                s2.setString(6, uEmail);
            }
            if (uStatus == "") {
                s2.setNull(7, Types.NULL);
            }
            else{
                s2.setString(7, uStatus);
            }
            s2.setInt(8, subFee);
			
            // execute insert query using PreparedStatement object.
            s2.executeUpdate();

			System.out.println("User record has been inserted.");
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
    public static void updateUserFirstName(int uphone, String ufName) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update uf_name for the given uphone.
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
	public static void updateUserLastName(int uphone, String ulName) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update ul_name for the given uphone.
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

    /*
	 * API to update user join date in users table.
	 * 
	 * @param uphone: User's phone number
	 * 
	 * @param joinDate: User's join date
	 */
	public static void updateUserJoinDate(int uphone, String joinDate) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update join_date for the given uphone.
			String updateSql = "UPDATE user SET join_date = '" + joinDate + "' WHERE uphone = '"+ uphone + "'"; 
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("User's join date updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

    /*
	 * API to update user end date in users table.
	 * 
	 * @param uphone: User's phone number
	 * 
	 * @param endDate: User's end date
	 */
	public static void updateUserEndDate(int uphone, String endDate) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update end_date for the given uphone.
			String updateSql = "UPDATE user SET end_date = '" + endDate + "' WHERE uphone = '"+ uphone + "'"; 
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("User's end date updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

	/*
	 * API to update user email id in users table.
	 * 
	 * @param uphone: User's phone number
	 * 
	 * @param uEmail: User's email id
	 */
	public static void updateUserEmail(int uphone, String uEmail) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update uemail for the given uphone.
			String updateSql = "UPDATE user SET uemail = '" + uEmail + "' WHERE uphone = '"+ uphone + "'"; 
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("User's Email ID updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

	/*
	 * API to update user email id in users table.
	 * 
	 * @param uphone: User's phone number
	 * 
	 * @param uStatus: User's status
	 */
	public static void updateUserStatus(int uphone, String uStatus) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update u_status for the given uphone.
			String updateSql = "UPDATE user SET u_status = '" + uStatus + "' WHERE uphone = '"+ uphone + "'"; 
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("User's Status updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

    /*
	 * API to update user email id in users table.
	 * 
	 * @param uphone: User's phone number
	 * 
	 * @param subFee: User's subscription fee
	 */
	public static void updateUserSubFee(int uphone, int subFee) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update subscription fee of user for the given uphone.
			String updateSql = "UPDATE user SET sub_fee = '" + subFee + "' WHERE uphone = '"+ uphone + "'"; 
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("User's subscription fee updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

    /*
	 * API to delete user record from users table.
	 * 
	 * @param uphone: User's phone number
	 */
	public static void deleteUser(int uphone) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// Create Statement Object.
			stmt = connection.createStatement();
			// delete statement to delete the user record with given uphone.
			String deletePubQuery = "DELETE FROM user WHERE uphone = '" + uphone+ "'";
			// execute the delete query using the Statement object.
			stmt.executeUpdate(deletePubQuery);
			System.out.println("User deleted.");
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
