package drugstore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBWork {

	private static String SELECT_ALL = "SELECT * FROM `medicine`;";
	private static String INSERT_LOG = "INSERT INTO `log`(`PHARMACIST_NAME`, `CLIENT_NAME`, `CLIENT_LIST`, `TIME_OF_SERVE`) VALUES (?,?,?,?)";
	private List<Meds> listOfAllMeds;

	public DBWork() {
		listOfAllMeds = new ArrayList<Meds>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/pharmacy?" + "user=root&password=");
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return conn;
	}

	public List<Meds> getFullListOfMeds() {
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SELECT_ALL);
			while (rs.next()) {
				Meds meds = new Meds();
				meds.setName(rs.getString("NAME"));
				meds.setTime(rs.getInt("TIME"));
				listOfAllMeds.add(meds);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listOfAllMeds;
	}

	public synchronized void insertLog(Pharmacist pharmacist, Client client) {
		Connection con = getConnection();

		try {
			PreparedStatement stmt = con.prepareStatement(INSERT_LOG);
			stmt.setString(1, pharmacist.getName());
			stmt.setString(2, client.getName());
			stmt.setString(3, client.getListOfMedsForPrint());
			stmt.setInt(4, pharmacist.getTimeOfServe());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
