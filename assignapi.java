import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class assignapi {
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

     public static void assignhosttopodcast(String hostid, String mediaid) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Get connection object
			connection = DriverManager.getConnection(jdbcURL, user, password);
			// Query to insert the record into CONTR_CHAP table.
			String sql = "INSERT INTO CONTR_CHAP (PUB_ID, CONTR_ID, CHAP_NUM) VALUES (?,?,?)";
			// Create PreparedStatement Object for given insert query.
			ps1 = connection.prepareStatement(sql);
			// Set values with user-passed arguments.
			ps1.setInt(1, pubId);
			ps1.setInt(2, contrId);
			ps1.setInt(3, chapNum);
			ps1.executeUpdate();
			// Query to select the records from CONTRIBUTORS table for a given CONTR_ID.
			String queryCheckEmpType = "SELECT CONTR_EMP_TYPE FROM CONTRIBUTORS WHERE CONTR_ID = " + contrId;
			// Create Statement Object.
			stmt = connection.createStatement();
			rs1 = stmt.executeQuery(queryCheckEmpType);
			while (rs1.next()) {
				String empType = rs1.getString("CONTR_EMP_TYPE");
				if (empType.equals("Visiting")) {
					String query = "SELECT CHAP_CREATION_DATE FROM CHAPTERS CH " + "INNER JOIN CONTR_CHAP CC "
							+ "ON CH.CHAP_NUM = CC.CHAP_NUM AND CH.PUB_ID = CC.PUB_ID "
							+ "INNER JOIN CONTRIBUTORS CT ON CT.CONTR_ID = CC.CONTR_ID "
							+ "WHERE CT.CONTR_EMP_TYPE = \"Visiting\" AND CC.CONTR_ID = ? AND CC.CHAP_NUM = ? AND CC.PUB_ID = ? ";
					// Create PreparedStatement Object for given insert query.
					ps2 = connection.prepareStatement(query);
					// Set values with user-passed arguments.
					ps2.setInt(1, contrId);
					ps2.setInt(2, chapNum);
					ps2.setInt(3, pubId);
					rs2 = ps2.executeQuery();
					while (rs2.next()) {
						Date date = rs2.getDate("CHAP_CREATION_DATE");
						if (date != null) {
							String salaryInsert = "INSERT INTO SALARY (SAL_AMT, SAL_DATE, CONTR_ID) VALUES (?,?,?)";
							// Create PreparedStatement Object for given insert query.
							ps3 = connection.prepareStatement(salaryInsert);
							// Set values with user-passed arguments.
							ps3.setDouble(1, 1000);
							ps3.setDate(2, date);
							ps3.setInt(3, contrId);
							ps3.executeUpdate();
						}
					}
				}
			}
			System.out.println("Contributor Assigned to Chapter.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement, ResultSet and Connection Objects.
			close(rs1);
			close(rs2);
			close(ps1);
			close(ps2);
			close(ps3);
			close(connection);
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