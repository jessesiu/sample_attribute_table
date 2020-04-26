package sample_attribute_update;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class sample_attributes_table {
	
	 public static void main(String[] args) throws SQLException
	 {
	BufferedReader reader;
	try {
		reader = new BufferedReader(new FileReader(
				"/Users/xiaosizhe/Downloads/sample_attribute_Mar2020-v2-fix1.txt"));
		String line = reader.readLine();
		database db= new database();
		while (line != null) {
			System.out.println(line);			
			String[] aa= line.split("\\|");
			int id= Integer.valueOf(aa[0]);
			int sample_id= Integer.valueOf(aa[1]);
			int attribute_id= Integer.valueOf(aa[3]);
			String value=null;
			if(aa.length<6)
			{
				line = reader.readLine();
				continue;
			}
			if(aa[5] == null||aa[5].isEmpty()==true){
			 line = reader.readLine();
			 continue;
			}else{
			value=aa[5];	
			System.out.println("id: "+id+" sample_id: "+sample_id+" attribute_id: "+attribute_id+" value: "+value);
			db.updatesampleattributeall(id, sample_id, attribute_id, value);
			line = reader.readLine();
			}
		}
		reader.close();
		db.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
	 }
}