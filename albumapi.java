import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;

public class albumapi {
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
	 * @param albumid: Album's ID
	 * 
     * @param albumName: Album's name
     * 
     * @param aReleaseYear: Album's release year
     * 
	 * @param edition: Album's edition
     * 
     * @param artistid: Artist's ID associated to the respective album
	 */
    public static void insertAlbum(String albumid, String albumName, int aReleaseYear, String edition, String artistid) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);

            String s3 = "INSERT INTO Album VALUES (?,?,?,?,?)";
			// Assigning values to the prepared statement
            s2 = connection.prepareStatement(s3);
			// Assigning values to the prepared statement
            s2.setString(1, albumid);
            s2.setString(2, albumName);
            s2.setInt(3, aReleaseYear);
            if (edition == "") {
                s2.setNull(4, Types.NULL);
            }
            else{
                s2.setString(4, edition);
            }
            if (artistid == "") {
                s2.setNull(5, Types.NULL);
            }
            else{
                s2.setString(5, artistid);
            }
			
            // execute insert query using PreparedStatement object.
            s2.executeUpdate();

			System.out.println("Album record has been inserted.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
            close(s2);
			close(connection);
		}
	}


	/*
	 * API to insert user record in users table.
	 * 
	 * @param albumid: Album's ID
	 * 
     * @param albumName: Album's name
	 */
    public static void updateAlbumName(String albumid, String albumName) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update album_name for the given albumid.
			String updateSql = "UPDATE Album SET album_name = '" + albumName + "' WHERE albumid = '" + albumid+ "'";
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Album's name updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

	/*
	 * API to insert user record in users table.
	 * 
	 * @param albumid: Album's ID
     * 
     * @param aReleaseYear: Album's release year
	 */
	public static void updateAlbumReleaseYear(String albumid, int aReleaseYear) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update a_release_year for the given albumid.
			String updateSql = "UPDATE Album SET a_release_year = '" + aReleaseYear + "' WHERE albumid = '" + albumid+ "'"; 
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Album's Release Year updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

    /*
	 * API to insert user record in users table.
	 * 
	 * @param albumid: Album's ID
     * 
	 * @param edition: Album's edition
	 */
	public static void updateAlbumEdition(String albumid, String edition) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update edition for the given albumid.
			String updateSql = "UPDATE Album SET edition = '" + edition + "' WHERE albumid = '"+ albumid + "'"; 
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Album's edition updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

    /*
	 * API to insert user record in users table.
	 * 
	 * @param albumid: Album's ID
     * 
     * @param artistid: Artist's ID associated to the respective album
	 */
	public static void assignAlbumArtistID(String albumid, String artistid) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update artistid for the given albumid.
			String updateSql = "UPDATE Album SET artistid = '" + artistid + "' WHERE albumid = '"+ albumid + "'"; 
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Artist's ID associated to the respective album updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

    /*
	 * API to delete album record from Album table.
	 * 
	 * @param albumid: Album's ID
	 */
	public static void deleteAlbum(String albumid) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// Create Statement Object.
			stmt = connection.createStatement();
			// delete statement to delete the album record with given albumid.
			String deletePubQuery = "DELETE FROM Album WHERE albumid = '" + albumid+ "'";
			// execute the delete query using the Statement object.
			stmt.executeUpdate(deletePubQuery);
			System.out.println("Album deleted.");
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