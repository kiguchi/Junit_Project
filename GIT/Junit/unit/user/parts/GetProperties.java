package unit.user.parts;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
				 
public class GetProperties {
	
	public static String getPro(String key){
	FileInputStream fi = null;
	Properties prop = new Properties();
	try {
		fi=new FileInputStream("C:\\tools\\gitTest\\GIT\\WebContent\\sql.properties");
		prop.load(fi);
	} catch (IOException e) {
		e.printStackTrace();
	}finally{
		try {
			fi.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	String sql = prop.getProperty(key);
	
	return sql;
	}
}
