import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class findapi {
    // MariaDB Credentials
    private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/hsangha";
    private static final String user = "hsangha";
    private static final String password = "200473048";

    public static Connection connection = null;
    public static Statement stmt = null;
    public static PreparedStatement ps1 = null;
    public static PreparedStatement ps2 = null;
    public static PreparedStatement ps3 = null;
    public static ResultSet rs1 = null;
    public static ResultSet rs2 = null;

    /*
     * API to assign podcast hosts to podcast table.
     * 
     * @param hostid: Podcast host Id
     * 
     * @param mediaid: Podcast Id
     */

    public static void findpodcastepisode(String podcastid) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            // Get connection object
            connection = DriverManager.getConnection(jdbcURL, user, password);
            // update statement to update album the song belongs to for the given mediaid.
            // System.out.print(mediaid);
            String selectsql = "Select episodeid, title from podcastEpisode WHERE podcastid = '" + podcastid
                    + "'";
            // Create Statement Object.
            stmt = connection.createStatement();
            // execute update statement using Statement object.
            rs1 = stmt.executeQuery(selectsql);

            while (rs1.next()) {
                String episodeid = rs1.getString("episodeid");
                String title = rs1.getString("title");
                System.out.println(episodeid + " : " + title);
            }
            // System.out.println("information selected");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close PreparedStatement and Connection Objects.
            close(stmt);
            close(rs1);
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

    // method to close PreparedStatement.
    static void close(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
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
}