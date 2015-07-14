package ntut.csie.ezScrum.issue.sql.service.tool.internal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import ntut.csie.ezScrum.issue.sql.service.core.Configuration;


public class PostgreSQLControl {
	String mHost;
	String mPort;
	String mDbName;
	String mUser;
	String mPassword;
	Configuration mConfig = null;
	
	Connection mConnection = null;
	DataSource mDataSource = null;
	
	String[] mKeys;
	
	public PostgreSQLControl(Configuration config) {
		mConfig = config;
		mHost = config.getServerUrl();
		mPort = "5432";
		mUser = config.getDBAccount();
		mPassword = config.getDBPassword();
		mDbName = config.getDBName();
		loadDriver();
	}
	
	public PostgreSQLControl(String host, String port, String dbName) {
		mHost = host;
		mPort = port;
		mDbName = dbName;
		loadDriver();
	}

	private void loadDriver() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void connect() {
		try {
			if (mDataSource == null) {
				mDataSource = ConnectionPoolManager.getInstance().getConnectionPool("org.postgresql.Driver", getURL(), mUser, mPassword);
			}

			// 只有在Connection為null或者是Connection已經Close的情況下才進行Connection
			if (mConnection == null || mConnection.isClosed())
				mConnection = mDataSource.getConnection();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			mDataSource = null;
			ConnectionPoolManager.getInstance().RemoveConnectionPool(getURL());
		}	
	}
	
	public void reconnect() {
		try {
			mDataSource = ConnectionPoolManager.getInstance().getConnectionPool("com.postgresql.Driver", getURL(), mUser, mPassword);
			mConnection = mDataSource.getConnection();
		} catch(SQLException e) {
			mDataSource = null;
			ConnectionPoolManager.getInstance().RemoveConnectionPool(getURL());
		}
	}
	
	public Connection getconnection() {
		return mConnection;
	}
	
	public void close() {
		try {
			mConnection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Long executeInsert(String query) {
		execute(query, true);
		return Long.parseLong(mKeys[0]);
	}
	
	public boolean execute(String query, boolean returnKeys) {
		try {
			Statement statement = mConnection.createStatement();
			if (returnKeys) {
				setKeys(statement, query);
			} else {
				return statement.execute(query);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	/**
	 * for insert, update, delete
	 */
	public boolean executeUpdate(String query) {
		return executeUpdate(query, false);
	}
	
	/**
	 * for insert, update, delete
	 */
	public boolean executeUpdate(String query, boolean returnKeys) {
		try {
			Statement statement = mConnection.createStatement();
			if (returnKeys) {
				setKeys(statement, query);
			} else {
				if (statement.executeUpdate(query) > 0) {
					return true;
				}
				return false;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public ResultSet executeQuery(String query) {
		ResultSet result = null;
		
		// 進行 MySQL connection 測試
		try {
			Statement statement = mConnection.createStatement();
			statement.execute("Select 1;");
			statement.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			reconnect();
		}
		
		try {
			Statement statement = mConnection.createStatement();
			result = statement.executeQuery(query);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
		return result;
	}
	
	private void setKeys(Statement statement, String query) throws SQLException {
		statement.execute(query, Statement.RETURN_GENERATED_KEYS);
		ResultSet keys = statement.getGeneratedKeys();

		if (keys.next()) {
			ResultSetMetaData _metadata = keys.getMetaData();
			int columnCount = _metadata.getColumnCount();
			mKeys = new String[columnCount];
			for (int i = 1; i <= columnCount; i++) {
				mKeys[i - 1] = keys.getString(i);
			}
		} else {
			mKeys = new String[0];
		}
	}
	
	private String getURL() {
		return "jdbc:postgresql://" + mHost + ":" + mPort + "/" + mDbName + "?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true";
	}
	
	public String[] getKeys() {
		return mKeys;
	}
}
