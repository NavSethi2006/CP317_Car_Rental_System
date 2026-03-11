package main.java.com.carrental.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import main.java.com.carrental.gui.Login_RegisterEndpoints.LoginHandler;
import main.java.com.carrental.gui.Login_RegisterEndpoints.RegisterHandler;
import main.java.com.carrental.model.Customer;
import main.java.com.carrental.service.CustomerService;
import main.java.com.carrental.util.SecurePasswordHasher;


/**
 * Handles the frontend to backend endpoints, it spins up a server off of port 8080
 * then listens to call from the web page. If it recieves any GET or POST then
 * the class will handle them all
 */
public class CarRentalServer {

	private static final CustomerService customerService = new CustomerService();
	
	/**
	 * Create Http server to run the web application first directs the Static web page
	 * to login.html that can be accessed in the /public folder
	 * 
	 */
	public CarRentalServer () {
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
			server.createContext("/", new StaticFileHandler("public"));
			server.createContext("/login", new LoginHandler());
			server.createContext("/register", new RegisterHandler());
			server.setExecutor(null);
			server.start();
			System.out.println("Web server started on port http://localhost:8080");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


