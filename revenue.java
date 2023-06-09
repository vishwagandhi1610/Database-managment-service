
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


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
/* 
     public static void totalRevenueYearComment(int month, int year) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// Create Statement Object.
            stmt = connection.createStatement();
            
			rs1 = stmt.executeQuery("select sum(label_payment) as revenue from paymentSong where month(spay_date)='" + month + "' AND Main_label='Yes'");
			float sp = 0f ;
            while (rs1.next()) {
				Float srevenue = 0f  ;
				srevenue = rs1.getFloat("revenue");
                sp = sp + srevenue;
				System.out.println("Song royalties : "+srevenue);
			}


            rs2 = stmt.executeQuery("select sum(host_amount) as revenue from hostedby where month(pay_date)='" + month + "' ");
            while (rs2.next()) {
                int prevenue = 0;
				prevenue = rs2.getInt("revenue");
                sp = sp + (float)prevenue;
				System.out.println("Payment to Host : " + prevenue);
			}

            rs3 = stmt.executeQuery("select count(uid) * sub_fee as revenue from user_payment join user on user_payment.userid= user.userid where month(ups_date)='" + month + "'  ");
            while (rs3.next()) {
                Float urevenue = 0f ;
				urevenue = rs3.getFloat("revenue");
                sp = urevenue + sp;
				System.out.println("User Payemnet: " + urevenue);
			}
            
            String s3;
            s3 = "INSERT INTO totalRevenue VALUES (?,?,?)";
				s2 = connection.prepareStatement(s3);
				// Assigning values to the prepared statement
				s2.setInt(1, month);
				s2.setInt(2, year);
                s2.setFloat(3, sp);
				s2.executeUpdate();
            System.out.println("Total Revenue of Streaming Service  for "+ month + " : "+ sp + " ");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection Objects.
			close(stmt);
			close(rs1);
            close(rs2);
            close(rs3);
			close(connection);
            close(s2);
		}
	}
*/

public static void totalRevenueYear(int month, int year) {
    try {
        Class.forName("org.mariadb.jdbc.Driver");
        // Get connection object
        connection = DriverManager.getConnection(jdbcURL, user, password);
        // Create Statement Object.
        stmt = connection.createStatement();
        float sp = 0f ;
        
        rs2 = stmt.executeQuery("select sum(bonus) as revenue from podcastEpisode where month(p_release_date)='" + month + "' ");
        while (rs2.next()) {
            int prevenue = 0;
            prevenue = rs2.getInt("revenue");
            sp = sp + (float)prevenue;
            System.out.println("Payment Ad Count : " + prevenue);
        }

        rs3 = stmt.executeQuery("select count(uid) * sub_fee as revenue from user_payment join user on user_payment.userid= user.userid where month(ups_date)='" + month + "'  ");
        while (rs3.next()) {
            Float urevenue = 0f ;
            urevenue = rs3.getFloat("revenue");
            sp = urevenue + sp;
            System.out.println("User Payemnet: " + urevenue);
        }
        rs1 = stmt.executeQuery("delete from totalRevenue where month='" + month + "' AND year='" + year + "' ");
        String s3;
        s3 = "INSERT INTO totalRevenue VALUES (?,?,?)";
            s2 = connection.prepareStatement(s3);
            // Assigning values to the prepared statement
            s2.setInt(1, month);
            s2.setInt(2, year);
            s2.setFloat(3, sp);
            s2.executeUpdate();
        System.out.println("Total Revenue of Streaming Service  for "+ month + " : "+ sp + " ");
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Close PreparedStatement and Connection Objects.
        close(stmt);
        close(rs1);
        close(rs2);
        close(rs3);
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

