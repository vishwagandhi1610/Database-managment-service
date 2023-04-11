import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class recordLabelapi {
    // MariaDB Credentials
    private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/hsangha";
    private static final String user = "hsangha";
    private static final String password = "200473048";

    public static Connection connection = null;
    public static PreparedStatement s1 = null;
    public static Statement stmt = null;
    public static ResultSet rs = null;

    // API to enter & update Record Label details in recordLabel table.

    public static void insertLabelName(String label_id, String labelname) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            // Get connection object
            connection = DriverManager.getConnection(jdbcURL, user, password);
            String s4 = "INSERT INTO recordLabel VALUES (?,?)";
            // Assigning values to the prepared statement
            s1 = connection.prepareStatement(s4);
            s1.setString(1, labelname);
            s1.setString(2, label_id);
            // execute insert query using PreparedStatement object.
            s1.executeUpdate();
            System.out.println("Record Labelname has been inserted.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close PreparedStatement and Connection Objects.
            close(s1);
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
