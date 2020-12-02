package mvn.jcoding.kr;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeController
 */
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Properties prop = new Properties();
		URL url = HomeController.class.getClassLoader().getResource("/db.properties");
        
		prop.load(url.openStream());
		String jdbcUrl = prop.getProperty("mysql.jdbcUrl");
		String driver = prop.getProperty("mysql.driver");
		String username = prop.getProperty("mysql.username");
		String password = prop.getProperty("mysql.password");
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
