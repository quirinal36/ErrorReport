package mvn.jcoding.kr;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class AppConfig {
	
	public static Properties getProperty() {
		Properties prop = new Properties();
		URL url = AppConfig.class.getClassLoader().getResource("/db.properties");
        
		try {
			prop.load(url.openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		String jdbcUrl = prop.getProperty("mysql.jdbcUrl");
		String driver = prop.getProperty("mysql.driver");
		String username = prop.getProperty("mysql.username");
		String password = prop.getProperty("mysql.password");
		*/
		return prop;
	}
}
