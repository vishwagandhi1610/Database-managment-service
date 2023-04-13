
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class report {
	// MariaDB Credentials
	private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/hsangha";
	private static final String user = "hsangha";
	private static final String password = "200473048";

	public static Connection connection = null;
	public static PreparedStatement s1 = null;
	public static Statement stmt = null;
	public static ResultSet rs = null;

	/*
	 * API to generate monthly play count of an artist
	 */

	public static void artistMcount(String artistid, int spay_date) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// Create Statement Object.
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"select mediaid,sum(dplay_count) as play_count from listensto where month(uplay_date)='" + spay_date
							+ "' AND mediaid IN(select mediaid from Song join composedby on mediaid=songid join Artist on artistid=creatorsid where creatorsid='"
							+ artistid + "') group by mediaid");
			// execute the delete query using the Statement object.
			// stmt.executeUpdate(deletePubQuery);
			int i = 0;
			while (rs.next()) {
				String mediaid = rs.getString("mediaid");
				int amount = rs.getInt("play_count");
				System.out.println(mediaid + " : " + amount);
				i = i + amount;
			}
			System.out.println("Total Play count for Artist  " + artistid + " :" + i);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(rs);
			close(connection);
		}
	}

	/*
	 * API to generate monthly play count of a song
	 */
	public static void songMcount(String songid, int spay_date) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// Create Statement Object.
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"select mediaid,sum(dplay_count) as play_count from listensto where month(uplay_date)='" + spay_date
							+ "' AND mediaid='" + songid + "'");
			// execute the delete query using the Statement object.
			// stmt.executeUpdate(deletePubQuery);
			while (rs.next()) {
				String mediaid = rs.getString("mediaid");
				int amount = rs.getInt("play_count");
				System.out.println(mediaid + " : " + amount);
			}
			// System.out.println("Total Play count for Artist "+ songid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(rs);
			close(connection);
		}
	}

	/*
	 * API to generate monthly play count of an album
	 */
	public static void albumMcount(String albumid, int spay_date) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// Create Statement Object.
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"select mediaid,sum(dplay_count) as play_count from listensto where month(uplay_date)='" + spay_date
							+ "' AND mediaid IN(select mediaid from Song where albumid='" + albumid
							+ "') group by mediaid");
			// execute the delete query using the Statement object.
			// stmt.executeUpdate(deletePubQuery);
			int i = 0;
			while (rs.next()) {
				String mediaid = rs.getString("mediaid");
				int amount = rs.getInt("play_count");
				System.out.println(mediaid + " : " + amount);
				i = i + amount;
			}
			System.out.println("Total Play count for Album  " + albumid + " :" + i);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(rs);
			close(connection);
		}
	}

	/*
	 * API to generate total payments for an artist accroding to start and end dates
	 */
	public static void totalPayArtist(String artistid, String spay_start, String spay_end) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// Create Statement Object.
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select artistid,sum(artist_payment) as pay from paymentSong where artistid='"
					+ artistid + "' AND spay_date BETWEEN CAST('" + spay_start + "' AS DATE) AND CAST('" + spay_end
					+ "' AS DATE)");
			while (rs.next()) {
				String mediaid = rs.getString("artistid");
				float amount = rs.getFloat("pay");
				System.out.println(mediaid + " : " + amount);
			}
			// System.out.println("Total Play count for Album "+ artistid+ " :");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(rs);
			close(connection);
		}
	}

	/*
	 * API to generate total payments for a podcast host accroding to start and end
	 * dates
	 */
	public static void totalPayHost(String hostid, String spay_start, String spay_end) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// Create Statement Object.
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select hostid,sum(host_amount) as paid from hostedby where hostid='" + hostid
					+ "' AND pay_date BETWEEN CAST('" + spay_start + "' AS DATE) AND CAST('" + spay_end + "' AS DATE)");
			while (rs.next()) {
				String mediaid = rs.getString("hostid");
				float amount = rs.getInt("paid");
				System.out.println(mediaid + " : " + amount);
			}
			// System.out.println("Total Play count for Album "+ artistid+ " :");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(rs);
			close(connection);
		}
	}

	/*
	 * API to generate total payments for a record label accroding to start and end
	 * dates
	 */
	public static void totalPayLabel(String label_id, String spay_start, String spay_end) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// Create Statement Object.
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"select paymentSong.labelname,sum(label_payment)*0.3 as Label_royalty,label_id from paymentSong join recordLabel on paymentSong.labelname=recordLabel.labelname where Main_label='Yes' AND spay_date BETWEEN CAST('"
							+ spay_start + "' AS DATE) AND CAST('" + spay_end + "' AS DATE) AND label_id='" + label_id
							+ "'");
			while (rs.next()) {
				String mediaid = rs.getString("labelname");
				float amount = rs.getFloat("Label_royalty");
				System.out.println(mediaid + " : " + amount);
			}
			// System.out.println("Total Play count for Album "+ artistid+ " :");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(rs);
			close(connection);
		}
	}

	/*
	 * API to generate total payments for an artist accroding to start and end dates
	 */
	public static void songArtist(String artistid) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// Create Statement Object.
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select songid, media_name from Media natural join Song join composedby on songid=mediaid where artistid='" + artistid + "'");
			while (rs.next()) {
				String mediaid = rs.getString("songid");
				String media_name = rs.getString("media_name");
				System.out.println(mediaid + ":" + media_name);
			}
			// System.out.println("Total Play count for Album "+ artistid+ " :");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(rs);
			close(connection);
		}
	}

	public static void songAlbum(String albumid) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// Create Statement Object.
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select mediaid, media_name from Media natural join Song natural join Album where albumid='" + albumid + "'");
			while (rs.next()) {
				String mediaid = rs.getString("mediaid");
				String media_name = rs.getString("media_name");
				System.out.println(mediaid + ":" + media_name);
			}
			// System.out.println("Total Play count for Album "+ artistid+ " :");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(rs);
			close(connection);
		}
	}

	/*
	 * API to generate total revenue for a Song
	 */
	public static void totalRevenue(int month) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// Create Statement Object.
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select sum(label_payment) as revenue from paymentSong where month(spay_date)='"
					+ month + "' AND Main_label='Yes'");
			while (rs.next()) {

				Float revenue = rs.getFloat("revenue");
				System.out.println(revenue);
			}
			// System.out.println("Total Play count for Album "+ artistid+ " :");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(rs);
			close(connection);
		}
	}

	/*
	 * API to generate total revenue for a record label
	 */
	public static void totalRevenueYear(int year) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// Create Statement Object.
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select sum(label_payment) as revenue from paymentSong where year(spay_date)='"
					+ year + "' AND Main_label='Yes'");
			while (rs.next()) {

				Float revenue = rs.getFloat("revenue");
				System.out.println(revenue);
			}
			// System.out.println("Total Play count for Album "+ artistid+ " :");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(rs);
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
