package test.ds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import test.BaseTest;

public class datasourceTest extends BaseTest {

	protected DataSource datasource;

	protected void setUp() throws Exception {
		super.setUp();
		
		datasource = application.getDatasource();		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testDsCommit() throws Exception {
		Connection connection = datasource.getConnection();
		int id = getMaxCabin(connection);
		System.out.println("Max id found on Cabin table: " + id);
		
		createNewCabin(connection, id+1);

		connection.commit();
		connection.close();
		
		Connection newConnection = datasource.getConnection();
		int newid = getMaxCabin(newConnection);
		connection.close();
		System.out.println("Executed Statement, now Max id is: " + (newid));

		assertEquals(id+1, newid);
	}
	
	
	public void testDsNoCommit() throws Exception {
		Connection connection = datasource.getConnection();
		int id = getMaxCabin(connection);
		System.out.println("Max id found on Cabin table: " + id);
		
		createNewCabin(connection, id+1);

//		connection.commit();
		connection.close();
		
		Connection newConnection = datasource.getConnection();
		int newid = getMaxCabin(newConnection);
		connection.close();
		System.out.println("Executed Statement, now Max id is: " + (newid));
		assertEquals(id, newid);
	}

	
	int getMaxCabin(Connection connection) throws Exception {
		String idQuery = "select max(id) from cabin";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(idQuery);
		resultSet.next();
		return resultSet.getInt(1);
	}
	
	void createNewCabin(Connection connection, int id) throws Exception {
		String insertQuery = "insert into cabin values ( ? , ?, ?, ? , ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
		preparedStatement.setInt(1, id);
		preparedStatement.setInt(2, id);
		preparedStatement.setInt(3, id);
		preparedStatement.setString(4, "UsingDataSource Servlet " + id);
		preparedStatement.setInt(5, id);
		preparedStatement.execute();
	}
}
