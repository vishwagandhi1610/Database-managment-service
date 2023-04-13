import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;
import java.sql.SQLException;

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

	public static void insertSong(String mediaid, String media_name, String genre, String language, String m_country,
			int duration, String s_release_date, float royalty_rate, int royalty_paid, String albumid, int track_no) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);

			// set Auto-Commit to false for transaction handling.
			connection.setAutoCommit(false);

			String s4 = "INSERT INTO Media VALUES (?,?,?,?,?)";
			// Assigning values to the prepared statement
			s1 = connection.prepareStatement(s4);
			s1.setString(1, mediaid);
			s1.setString(2, media_name);
			s1.setString(3, genre);
			s1.setString(4, language);
			s1.setString(5, m_country);

			// execute insert query using PreparedStatement object.
			s1.executeUpdate();

			String s3 = "INSERT INTO Song VALUES (?,?,?,?,?,?,?,0)";
			// Assigning values to the prepared statement
			s2 = connection.prepareStatement(s3);
			// Assigning values to the prepared statement
			s2.setString(1, mediaid);
			s2.setInt(2, duration);
			s2.setString(3, s_release_date);
			s2.setFloat(4, royalty_rate);
			s2.setInt(5, royalty_paid);
			// s2.setString(6, albumid);
			if (albumid.length() == 0) {
				s2.setNull(6, Types.NULL);
			} else {
				s2.setString(6, albumid);
			}
			s2.setInt(7, track_no);
			
			// execute insert query using PreparedStatement object.
			
			s2.executeUpdate();
			
			System.out.println("Song record has been inserted.");

			// Over successful execution of all operations of this task, commit the transaction.
			connection.commit();

		} catch (Exception e) {
			try {
				// If an exception has occurred, roll back the transaction.
				connection.rollback();
			} catch (SQLException e1) {
				try {
					// set AutoCommit to True at the end of transaction.
					connection.setAutoCommit(true);
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				e1.printStackTrace();
			}
			e.printStackTrace();
		} 
		
		finally {
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
			// update statement to update media name for the given mediaid.
			System.out.print(mediaid);
			String updateSql = "UPDATE Media SET media_name = '" + media_name + "' WHERE mediaid = '" + mediaid + "'";
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Song name updated.");
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
			// update statement to update genre of a song for the given mediaid.
			System.out.print(mediaid);
			String updateSql = "UPDATE Media SET genre = '" + genre + "' WHERE mediaid = '" + mediaid + "'";
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Song Genre updated.");
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
			// update statement to update language of a song for the given mediaid.
			System.out.print(mediaid);
			String updateSql = "UPDATE Media SET language = '" + language + "' WHERE mediaid = '" + mediaid + "'";
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Song Language updated.");
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
			// update statement to update country the song belongs to for the given mediaid.
			System.out.print(mediaid);
			String updateSql = "UPDATE Media SET m_country = '" + m_country + "' WHERE mediaid = '" + mediaid + "'";
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Song Country updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

	public static void updateMediaDuration(String mediaid, int duration) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update duration of a song for the given mediaid.
			System.out.print(mediaid);
			String updateSql = "UPDATE Song SET duration = '" + duration + "' WHERE mediaid = '" + mediaid + "'";
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Song Duration updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

	public static void updateMediaDate(String mediaid, String s_release_date) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update release date of a song for the given mediaid.
			System.out.print(mediaid);
			String updateSql = "UPDATE Song SET s_release_date = '" + s_release_date + "' WHERE mediaid = '" + mediaid
					+ "'";
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Song Release Date updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

	public static void updateMediaRate(String mediaid, Float royalty_rate) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update royalty rate of a song for the given mediaid.
			System.out.print(mediaid);
			String updateSql = "UPDATE Song SET royalty_rate_USD = '" + royalty_rate + "' WHERE mediaid = '" + mediaid
					+ "'";
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Song Royalty rate updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

	public static void updateMediaPaid(String mediaid, int royalty_paid) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update royalty to be paid for a song for the given
			// mediaid
			System.out.print(mediaid);
			String updateSql = "UPDATE Song SET royalty_paid = '" + royalty_paid + "' WHERE mediaid = '" + mediaid
					+ "'";
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Song Royalty paid updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

	public static void assignMediaAlbum(String mediaid, String albumid) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update album the song belongs to for the given mediaid.
			System.out.print(mediaid);
			String updateSql = "UPDATE Song SET albumid = '" + albumid + "' WHERE mediaid = '" + mediaid + "'";
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Song Album ID Assigned.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

	public static void updateMediaTrack(String mediaid, int track_no) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update track number of a song for the given mediaid.
			System.out.print(mediaid);
			String updateSql = "UPDATE Song SET track_no = '" + track_no + "' WHERE mediaid = '" + mediaid + "'";
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);
			System.out.println("Song Track number updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

	public static void deleteSong(String mediaid) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// Create Statement Object.
			stmt = connection.createStatement();
			// delete statement to delete the Song with given mediaid.
			String deletePubQuery = "DELETE FROM Media WHERE mediaid = '" + mediaid + "'";
			// execute the delete query using the Statement object.
			stmt.executeUpdate(deletePubQuery);
			System.out.println("Song deleted.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

	public static void updatePlaycountSong(int month) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// Create Statement Object.
			stmt = connection.createStatement();
			String abc = "Update Song SET splay_count = 0";
			// delete statement to delete the Song with given mediaid.
			stmt.executeUpdate(abc);
			rs = stmt.executeQuery(
					"select mediaid,sum(dplay_count)as dplay_count from listensto where month(uplay_date)='" + month + "' group by mediaid");
			// execute the delete query using the Statement object.
			// stmt.executeUpdate(deletePubQuery);
			while (rs.next()) {
				String mediaid = rs.getString("mediaid");
				int playcount = rs.getInt("dplay_count");
				System.out.println(mediaid + "  " + playcount);
				stmt.executeUpdate(
						"Update Song set splay_count = '" + playcount + "'where mediaid = '" + mediaid + "'");
			}

			System.out.println("Song Play count updated.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(rs);
			close(connection);
		}
	}

	public static void insertArtist(String songid, String artistid) {
		try {
			Scanner scanner = new Scanner(System.in);
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);

			String s4 = "INSERT INTO composedby VALUES (?,?,'Yes')";
			// Assigning values to the prepared statement
			s1 = connection.prepareStatement(s4);
			s1.setString(1, songid);
			s1.setString(2, artistid);
			s1.executeUpdate();
			System.out.println("Enter the number of guest artist");
			int numb = Integer.parseInt(scanner.nextLine());
			String s3;
			String guest;
			for (int i = 1; i <= numb; i++) {
				System.out.print("Enter Song guest artist");
				guest = scanner.nextLine();
				s3 = "INSERT INTO composedby VALUES (?,?,'No')";
				s2 = connection.prepareStatement(s3);
				// Assigning values to the prepared statement
				s2.setString(1, songid);
				s2.setString(2, guest);
				s2.executeUpdate();
			}
			// scanner.close();

			// execute insert query using PreparedStatement object.
			System.out.println("Song artist record has been inserted.");
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
