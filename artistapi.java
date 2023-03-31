import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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

    public static void insertArtist(String creatorsid, String cf_name, String cl_name, String labelname, String a_status, String type, String primary_genre, String a_country ) {
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

            String s3= "INSERT INTO Artist VALUES (?,?,?,?,?,?,?)";
			// Assigning values to the prepared statement
            s2 = connection.prepareStatement(s3);
			// Assigning values to the prepared statement
            s2.setString(1, creatorsid);
            s2.setString(2, labelname);
            s2.setString(3, a_status);
            s2.setString(4, type);
            s2.setString(5, primary_genre);
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
}
