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
	
	/**
	 * Class to handle what happens when the user wants to register, calls methods from
	 * CustomerDAO in order to insert records in the database
	 */
    static class RegisterHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (!"POST".equals(exchange.getRequestMethod())) {
                sendResponse(exchange, 405, "{\"error\":\"Method not allowed\"}");
                return;
            }

            String body = readBody(exchange);
            Customer customer = parseCustomerFromJson(body);

            if (customer == null) {
                sendResponse(exchange, 400, "{\"error\":\"Invalid JSON\"}");
                return;
            }

            try {
                Customer registered = customerService.Register(customer);
                String jsonResponse = customerToJson(registered);
                sendResponse(exchange, 201, jsonResponse);
            } catch (Exception e) {
                sendResponse(exchange, 400, "{\"error\":\"" + escapeJson(e.getMessage()) + "\"}");
            }
        }
    }

    /**
     * Class to handle a client logging in, will pull records from the MySQL database
     * in order to verify the client
     */
    static class LoginHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (!"POST".equals(exchange.getRequestMethod())) {
                sendResponse(exchange, 405, "{\"error\":\"Method not allowed\"}");
                return;
            }

            String body = readBody(exchange);
            String email = extractField(body, "email");
            String password = extractField(body, "password");

            if (email == null || password == null) {
                sendResponse(exchange, 400, "{\"error\":\"Missing email or password\"}");
                return;
            }

            try {
                String hashedpass = SecurePasswordHasher.hashPassword(password);
                Customer customer = customerService.Login(email, hashedpass);
                String jsonResponse = customerToJson(customer);
                sendResponse(exchange, 200, jsonResponse);
            } catch (Exception e) {
                sendResponse(exchange, 401, "{\"error\":\"" + escapeJson(e.getMessage()) + "\"}");
            }
        }
    }

    // ----- Helper methods -----

    /**
     * Helper method to 'decrypt' the text body sent by the web server client
     * @param HttpExchange usally found in classes
     * @return String to extract client information from
     */
    private static String readBody(HttpExchange exchange) {
        StringBuilder sb = new StringBuilder();
        try (InputStream is = exchange.getRequestBody();
             BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            System.out.println("GET "+ sb.toString());
        } catch (IOException e) {
			e.printStackTrace();
		}
        return sb.toString();
    }
    /**
     * Sends a response to the web server client, self explanintory
     * @param HttpExchange usally found in classes
     * @param StatusCode so the client knows what happend in its send process 
     * @param String the reponse you want to Send
     * @throws IOException
     */
    private static void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(statusCode, response.getBytes(StandardCharsets.UTF_8).length);
        try (OutputStream os = exchange.getResponseBody()) {
            System.out.println("POST "+ response.getBytes(StandardCharsets.UTF_8));
            os.write(response.getBytes(StandardCharsets.UTF_8));
        }
    }

    /**
     * Extracts a field from a json string
     * @param String entire json string
     * @param fieldName
     * @return the information taken from said field
     */
    private static String extractField(String json, String fieldName) {
        String pattern = "\"" + fieldName + "\"\\s*:\\s*\"([^\"]*)\"";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
        java.util.regex.Matcher m = p.matcher(json);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }

    /**
     * Parses a Customer instance from the json sent by the web server client
     * @param String json that was sent
     * @return new instance of a Customer
     */
    private static Customer parseCustomerFromJson(String json) {
        String name = extractField(json, "name");
        String email = extractField(json, "email");
        String password = extractField(json, "password");

        if (name == null || email == null || password == null) {
            return null;
        }

        Customer customer = new Customer();
        customer.setCustomerName(name);
        customer.setEmail(email);
        customer.setPassword(SecurePasswordHasher.hashPassword(password));
        return customer;
    }

    /**
     * return a customer to its json string format
     * @param Customer to translate
     * @return String of json
     */
    private static String customerToJson(Customer c) {
        return String.format(
            "{\"id\":%d,\"name\":\"%s\",\"email\":\"%s\",\"phone\":\"%s\",\"licenseNumber\":\"%s\"}",
            c.getCustomerID(), escapeJson(c.getCustomerName()), escapeJson(c.getEmail()),
            escapeJson(c.getPhone()), escapeJson(c.getLicenseNumber())
        );
    }

    /**
     * escapes special characters in a string
     * so that it can be safely embedded inside a JSON string 
     * @param String to escape
     * @return escaped string
     */
    private static String escapeJson(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}


