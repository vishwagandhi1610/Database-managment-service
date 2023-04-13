
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class revenue {
	// MariaDB Credentials
	private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/hsangha";
	private static final String user = "hsangha";
	private static final String password = "200473048";

	public static Connection connection = null;
	//public static PreparedStatement s1 = null;
    public static PreparedStatement s2 = null;
	public static Statement stmt = null;
	public static ResultSet rs = null;
    public static ResultSet rs1 = null;
    public static ResultSet rs2 = null;
    public static ResultSet rs3 = null;

	/*
	 * API to update Podcast Episode count in Podcast table.
	 * Input:
	 * 
	 * @param podcastid: Podcast ID
	 * 
	 * @param episode_count: Episode Count
	 * 
	 * @param pel_date: Podcast Episode
	 * 
	 * Update:
	 * 
	 * @param episode_count: Episode Count
	 * 
	 * Output : Message
	 */

     public static void totalRevenueYear(int month, int year) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// Create Statement Object.
            stmt = connection.createStatement();
			rs1 = stmt.executeQuery("select sum(label_payment) as revenue from paymentSong where month(spay_date)='" + month + "' AND year(pay_date)='" + year + "' AND Main_label='Yes'");
			float sp =0 ;
            while (rs1.next()) {
				
				Float srevenue = rs.getFloat("revenue");
                sp = sp + srevenue;
				System.out.println(srevenue);
			}

            rs2 = stmt.executeQuery("select sum(host_amount) as revenue from hostedby where month(pay_date)='" + month + "' AND year(pay_date)='" + year + "' AND Main_label='Yes'");
            while (rs2.next()) {
				Float prevenue = rs.getFloat("revenue");
                sp = sp + prevenue;
				System.out.println(prevenue);
			}

            rs3 = stmt.executeQuery("select count(*) * 10 as revenue from hostedby where month(ups_date)='" + month + "' AND year(ups_date)='" + year + "' AND Main_label='Yes'");
            while (rs2.next()) {
				Float urevenue = rs.getFloat("revenue");
                sp = sp + urevenue;
				System.out.println(urevenue);
			}

            String s3;
            s3 = "INSERT INTO totalRevenue VALUES (?,?,?)";
				s2 = connection.prepareStatement(s3);
				// Assigning values to the prepared statement
				s2.setInt(1, month);
				s2.setInt(2, year);
                s2.setFloat(3, sp);
				s2.executeUpdate();
            System.out.println("Total Revenue of Streaming Service  for '"+ month + "' : '"+ sp + "'");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(rs);
			close(connection);
            close(s2);
		}
	}


    public static void FindtotalRevenue(int month, int year) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// Create Statement Object.
            stmt = connection.createStatement();
			rs = stmt.executeQuery("select balance as revenue from totalRevenue where year='" + year + "' AND month='" + month + "'");
			while (rs.next()) {
				
				Float revenue = rs.getFloat("revenue");
				System.out.println(revenue);
			}
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

