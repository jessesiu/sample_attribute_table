package sample_update;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.util.IOUtils;
import org.xml.sax.SAXException;


public class database_v1 {

	Connection con;
	String url ;
	String password ;
	String user ;
	String doi;
	static Statement stmt;
	Statement stmt1;
	PreparedStatement prepforall = null;

	public database_v1() throws ParserConfigurationException, SAXException, IOException {
		
		Setting.Loadsetting();
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			url = Setting.databaseUrl;
			user = Setting.databaseUserName;
			password = Setting.databasePassword;
			doi = Setting.doi;
			con = DriverManager.getConnection(url, user, password);
			con.setAutoCommit(true);
			stmt = con.createStatement();
			stmt1 = con.createStatement();



		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	public void updatesampleattributeall(int id, int sample_id, int attribute_id, String value) throws SQLException
	{
		String query="update sample_attribute set sample_id="+sample_id+", attribute_id="+attribute_id+", value="+"'"+value.replace("'", "''")+"'"+" where id="+id+";";
		System.out.println(query);
		PreparedStatement prep = con.prepareStatement(query);
		prep.executeUpdate();
	}
	public void close() throws SQLException {
		con.close();

	}

	public static void main(String[] args) throws Exception {
		database_v1 db = new database_v1();
	}

}
