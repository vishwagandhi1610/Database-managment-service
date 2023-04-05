import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class songapi {
    // MariaDB Credentials
	private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/hsangha";
	private static final String user = "hsangha";
	private static final String password = "200473048";

	public static Connection connection = null;
	public static PreparedStatement s1 = null;
    public static PreparedStatement s2 = null;
	public static Statement stmt = null;
	public static ResultSet rs = null;

    public static void insertSong(String mediaid,String media_name,String genre,String language,String m_country, int duration,String s_release_date,int royalty_rate,int royalty_paid, String albumid , int track_no ) {
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

            String s3= "INSERT INTO Song VALUES (?,?,?,?,?,?,?,0)";
			// Assigning values to the prepared statement
            s2 = connection.prepareStatement(s3);
			// Assigning values to the prepared statement
            s2.setString(1, mediaid);
            s2.setInt(2, duration);
            s2.setString(3, s_release_date);
            s2.setInt(4, royalty_rate);
            s2.setInt(5, royalty_paid);
            s2.setString(6, albumid);
            s2.setInt(7, track_no);
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
    
    public static void updateMediaName(String mediaid, String media_name) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update PUB_TITLE for the given publication id.
			System.out.print(mediaid);
			String updateSql = "UPDATE Media SET media_name = '" + media_name + "' WHERE mediaid = '"+ mediaid+ "'"; 
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Media name updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}
    
    public static void updateMediaGenre(String mediaid, String genre) {
 		try {
 			Class.forName("org.mariadb.jdbc.Driver");
 			// Get connection object
 			connection = DriverManager.getConnection(jdbcURL, user, password);
 			// update statement to update PUB_TITLE for the given publication id.
 			System.out.print(mediaid);
 			String updateSql = "UPDATE Media SET genre = '" + genre + "' WHERE mediaid = '"+ mediaid+ "'"; 
 			// Create Statement Object.
 			stmt = connection.createStatement();
 			// execute update statement using Statement object.
 			stmt.execute(updateSql);
 			System.out.println("Genre updated.");
 		} catch (Exception e) {
 			e.printStackTrace();
 		} finally {
 			// Close PreparedStatement and Connection Objects.
 			close(stmt);
 			close(connection);
 		}
 	}
    
    public static void updateMediaLanguage(String mediaid, String language) {
 		try {
 			Class.forName("org.mariadb.jdbc.Driver");
 			// Get connection object
 			connection = DriverManager.getConnection(jdbcURL, user, password);
 			// update statement to update PUB_TITLE for the given publication id.
 			System.out.print(mediaid);
 			String updateSql = "UPDATE Media SET language = '" + language + "' WHERE mediaid = '"+ mediaid+ "'"; 
 			// Create Statement Object.
 			stmt = connection.createStatement();
 			// execute update statement using Statement object.
 			stmt.execute(updateSql);
 			System.out.println("Language updated.");
 		} catch (Exception e) {
 			e.printStackTrace();
 		} finally {
 			// Close PreparedStatement and Connection Objects.
 			close(stmt);
 			close(connection);
 		}
 	}
    
    
    public static void updateMediaCountry(String mediaid, String m_country) {
 		try {
 			Class.forName("org.mariadb.jdbc.Driver");
 			// Get connection object
 			connection = DriverManager.getConnection(jdbcURL, user, password);
 			// update statement to update PUB_TITLE for the given publication id.
 			System.out.print(mediaid);
 			String updateSql = "UPDATE Media SET m_country = '" + m_country + "' WHERE mediaid = '"+ mediaid+ "'"; 
 			// Create Statement Object.
 			stmt = connection.createStatement();
 			// execute update statement using Statement object.
 			stmt.execute(updateSql);
 			System.out.println("Media Country updated.");
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
