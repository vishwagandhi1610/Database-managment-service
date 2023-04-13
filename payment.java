
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class payment {
	// MariaDB Credentials
	private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/hsangha";
	private static final String user = "hsangha";
	private static final String password = "200473048";

	public static Connection connection = null;
	public static PreparedStatement s1 = null;
	public static Statement stmt = null;
	public static ResultSet rs = null;
	public static ResultSet rs1 = null;
	/*
	 * API to enter Podcast Episode Listening details in podcastEpisode_listening
	 * table.
	 * 
	 */

	public static void generatePaymentAll(String Date) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// update statement to update artist_status for the given creators id.
			String updateSql = "insert into paymentSong(songid,artistid,labelname,label_payment,artist_payment,spay_date,Main_label)select abc.mediaid,creatorsid,labelname,sum(abc.total_amount) as label_pay , sum(abc.total_amount*0.7/def.counta) as artist_pay,'2023-04-01',abc.lead as Main_label from (select creatorsid,cf_name, mediaid,labelname, royalty_rate_USD*splay_count AS total_amount, lead from Media natural join Song join composedby on mediaid = songid join Artist on artistid=creatorsid natural join Creators where royalty_paid=0) as abc join (select mediaid,count(artistid) as counta, lead from Song join composedby on mediaid=songid group by mediaid) as def on abc.mediaid = def.mediaid group by creatorsid,mediaid;";
			// Create Statement Object.
			stmt = connection.createStatement();
			// execute update statement using Statement object.
			stmt.execute(updateSql);

			String updateRoyalty = "UPDATE Song SET royalty_paid = 1 ";
			// Create Statement Object.
			// execute update statement using Statement object.
			stmt.execute(updateRoyalty);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(connection);
		}
	}

	/*
	 * API to for payment date of song .
	 */
	public static void songRoyalty(String songid, String spay_date) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// Create Statement Object.
			stmt = connection.createStatement();
			// System.out.println(spay_date);
			String updateSql = "insert into paymentSong(songid,artistid,labelname,label_payment,artist_payment,spay_date,Main_label)select abc.mediaid,creatorsid,labelname,sum(abc.total_amount) as label_pay , sum(abc.total_amount*0.7/def.counta) as artist_pay,'"
					+ spay_date
					+ "',abc.lead as Main_label from (select creatorsid,cf_name, mediaid,labelname, royalty_rate_USD*splay_count AS total_amount, lead from Media natural join Song join composedby on mediaid = songid join Artist on artistid=creatorsid natural join Creators where royalty_paid=0 AND mediaid='"
					+ songid
					+ "') as abc join (select mediaid,count(artistid) as counta, lead from Song join composedby on mediaid=songid group by mediaid) as def on abc.mediaid = def.mediaid group by creatorsid,mediaid";
			stmt.execute(updateSql);

			String updateRoyalty = "UPDATE Song SET royalty_paid = 1 where mediaid='" + songid + "'";
			stmt.execute(updateRoyalty);

			rs = stmt.executeQuery("select songid, label_payment as royalty_generated from paymentSong where songid='"
					+ songid + "' AND spay_date= '" + spay_date + "' AND Main_label='Yes'");
			// execute the delete query using the Statement object.
			// stmt.executeUpdate(deletePubQuery);
			while (rs.next()) {
				String mediaid = rs.getString("songid");
				float amount = rs.getFloat("royalty_generated");
				System.out.println(mediaid + " : " + amount);
			}
			// System.out.println("Song Play count updated.");
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
	 * API to for payment date of record label .
	 */
	public static void labelPay(String label_id, int spay_date) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// Create Statement Object.
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"select paymentSong.labelname,sum(label_payment)*0.3 as Label_royalty,label_id from paymentSong join recordLabel on paymentSong.labelname=recordLabel.labelname where Main_label='Yes' AND month(spay_date)='"
							+ spay_date + "' AND label_id='" + label_id + "'");
			// execute the delete query using the Statement object.
			// stmt.executeUpdate(deletePubQuery);
			while (rs.next()) {
				String mediaid = rs.getString("labelname");
				float amount = rs.getFloat("Label_royalty");
				System.out.println(mediaid + " : " + amount);
			}

			// System.out.println("Song Play count updated.");
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
	 * API to for payment date of artist .
	 */
	public static void artistPay(String artistid, int spay_date) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// Create Statement Object.
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"select artistid, sum(artist_payment) as artist_payment from paymentSong where month(spay_date)='"
							+ spay_date + "' AND artistid='" + artistid + "'");
			// execute the delete query using the Statement object.
			while (rs.next()) {
				String mediaid = rs.getString("artistid");
				float amount = rs.getFloat("artist_payment");
				System.out.println(mediaid + " : " + amount);
			}
			// System.out.println("Song Play count updated.");
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
	 * API to for payment to podcast host .
	 */
	public static void paytohost(String episodeid, String podcastid, String pay_date) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			int am = 0;
			String temp = "";
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// Create Statement Object.
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select sum(flat_fee+bonus) as total from podcastEpisode where podcastid = '"
					+ podcastid + "' AND episodeid = '" + episodeid + "'");
			while (rs.next()) {
				am = rs.getInt("total");
			}

			// System.out.println(am);
			rs1 = stmt.executeQuery("select hostid from Podcast where mediaid = '" + podcastid + "'");
			while (rs1.next()) {
				temp = rs1.getString(1);
			}
			String s4 = "INSERT INTO hostedby VALUES (?,?,?)";
			s1 = connection.prepareStatement(s4);
			s1.setString(1, temp);
			s1.setString(2, pay_date);
			s1.setInt(3, am);
			s1.executeUpdate();
			System.out.println(" Payment for host added");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(rs);
			close(rs1);
			close(s1);
			close(connection);
		}
	}

	/*
	 * API to for user subscription fee .
	 */
	public static void paytoUser(String userid, String uid, String ups_date, float sub_fee) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String tem = "Active";
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// Create Statement Object.
			String s3 = "INSERT INTO user_payment VALUES (?,?,?)";
			s1 = connection.prepareStatement(s3);
			s1.setString(1, uid);
			s1.setString(2, userid);
			s1.setString(3, ups_date);

			s1.executeUpdate();

			String updateSql = "UPDATE user SET u_status = '" + tem + "' WHERE userid = '" + userid + "'";
			String updateSql1 = "UPDATE user SET sub_fee = '" + sub_fee + "' WHERE userid = '" + userid + "'";
			stmt = connection.createStatement();
			stmt.execute(updateSql);
			stmt.execute(updateSql1);
			System.out.println("User's payment updated.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
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
