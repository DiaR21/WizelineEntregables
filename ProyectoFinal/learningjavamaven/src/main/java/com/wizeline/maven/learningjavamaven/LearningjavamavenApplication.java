package com.wizeline.maven.learningjavamaven;

import java.io.IOException;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearningjavamavenApplication {

	private static final Logger LOGGER = Logger.getLogger(LearningjavamavenApplication.class.getName());

	public static void main(String[] args) throws IOException {
		SpringApplication.run(LearningjavamavenApplication.class, args);
		LOGGER.info("LearningJava - Iniciado servicio REST ...");
	}

}



/**

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import com.wizeline.maven.learningjavamaven.PDISENIO.builder.User;
import com.wizeline.maven.learningjavamaven.PDISENIO.factorymethod.factory.NotificationFactory;
import com.wizeline.maven.learningjavamaven.config.EndpointBean;
import com.wizeline.maven.learningjavamaven.model.BankAccountDTO;
import com.wizeline.maven.learningjavamaven.model.ResponseDTO;
import com.wizeline.maven.learningjavamaven.model.UserDTO;
import com.wizeline.maven.learningjavamaven.service.BankAccountService;
import com.wizeline.maven.learningjavamaven.service.BankAccountServiceImpl;
import com.wizeline.maven.learningjavamaven.service.UserService;
import com.wizeline.maven.learningjavamaven.service.UserServiceImpl;
import com.wizeline.maven.learningjavamaven.utils.exeption.ExcepcionGenerica;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.management.Notification;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.wizeline.maven.learningjavamaven.utils.Utils.isDateFormatValid;
import static com.wizeline.maven.learningjavamaven.utils.Utils.isPasswordValid;

@SpringBootApplication
public class LearningjavamavenApplication extends Thread {

	private static final Logger LOGGER = Logger.getLogger(LearningjavamavenApplication.class.getName());
	private static String SUCCESS_CODE = "OK000";
	private static String responseTextThread = "";
	private ResponseDTO response;
	private static String textThread = "";

	@Autowired
	private EndpointBean endpointBean;

	@Bean
	public static UserService userService() {
		return new UserServiceImpl();
	}



	public static void main(String[] args) throws IOException {
		LOGGER.info("LearningJava - Iniciado servicio REST ...");

		HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
		server.createContext("/api/login", (exchange -> {
			LOGGER.info("LearningJava - Inicia procesamiento de peticion ...");
			ResponseDTO response = new ResponseDTO();
			String responseText = "";
			if ("GET".equals(exchange.getRequestMethod())) {
				LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET");
				UserDTO user =  new UserDTO();
				user = user.getParameters(splitQuery(exchange.getRequestURI()));
				response = login(user.getUser(), user.getPassword());
				JSONObject json = new JSONObject(response);
				responseText = json.toString();
				exchange.getResponseHeaders().set("contentType", "application/json; charset=UTF-8");
				exchange.sendResponseHeaders(200, responseText.getBytes().length);
			} else {

				exchange.sendResponseHeaders(405, -1);
			}
			OutputStream output = exchange.getResponseBody();

			LOGGER.info("LearningJava - Cerrando recursos ...");
			output.write(responseText.getBytes());
			output.flush();
			output.close();
			exchange.close();
		}));


		server.createContext("/api/createUser", (exchange -> {
			LOGGER.info("LearningJava - Inicia procesamiento de peticion ...");
			ResponseDTO response = new ResponseDTO();
			String responseText = "";

			exchange.getRequestBody();
			if ("POST".equals(exchange.getRequestMethod())) {
				LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET");

				UserDTO user =  new UserDTO();
				user = user.getParameters(splitQuery(exchange.getRequestURI()));
				response = createUser(user.getUser(), user.getPassword());

				JSONObject json = new JSONObject(response);
				responseText = json.toString();
				exchange.getResponseHeaders().set("contentType", "application/json; charset=UTF-8");
				exchange.sendResponseHeaders(200, responseText.getBytes().length);

			} else {

				exchange.sendResponseHeaders(405, -1);
			}

			OutputStream output = exchange.getResponseBody();

			LOGGER.info("LearningJava - Cerrando recursos ...");
			output.write(responseText.getBytes());
			output.flush();
			output.close();
			exchange.close();
		}));
*/
		/**Patron de diseño builder para agrgar o no, el numero telefonico */
/**
		server.createContext("/api/createUserBuilder", (exchange -> {
			LOGGER.info("LearningJava - Inicia procesamiento de peticion ...");
			ResponseDTO response = new ResponseDTO();
			String responseText = "";

			exchange.getRequestBody();
			if ("POST".equals(exchange.getRequestMethod())) {
				LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET");

				User User =  new User.UserBuilder;
				User = User.UserBuilder(splitQuery(exchange.getRequestURI()));
				response = (User.getUser(), User.getPassword(), User.getPhone());
				JSONObject json = new JSONObject(response);
				responseText = json.toString();
				exchange.getResponseHeaders().set("contentType", "application/json; charset=UTF-8");
				exchange.sendResponseHeaders(200, responseText.getBytes().length);

			} else {

				exchange.sendResponseHeaders(405, -1);
			}
			OutputStream output = exchange.getResponseBody();
			LOGGER.info("LearningJava - Cerrando recursos ...");
			output.write(responseText.getBytes());
			output.flush();
			output.close();
			exchange.close();
		}));
*/
		/***/
/**
		server.createContext("/api/getUserAccount", (exchange -> {
			LOGGER.info("LearningJava - Inicia procesamiento de peticion ...");
			ResponseDTO response = new ResponseDTO();

			String responseText = "";
			if ("GET".equals(exchange.getRequestMethod())) {
				LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET");
				UserDTO user =  new UserDTO();
				Map<String, String> params = splitQuery(exchange.getRequestURI());
				user = user.getParameters(params);
				String lastUsage = params.get("date");
				if (isDateFormatValid(lastUsage)) {
					if (isPasswordValid(user.getPassword())) {
						response = login(user.getUser(), user.getPassword());
						if (response.getCode().equals(SUCCESS_CODE)) {
							BankAccountDTO bankAccountDTO = getAccountDetails(user.getUser(), lastUsage);
							JSONObject json = new JSONObject(bankAccountDTO);
							responseText = json.toString();
							exchange.getResponseHeaders().add("Content-type", "application/json");
							exchange.sendResponseHeaders(200, responseText.getBytes().length);
						}
					} else {
						responseText = "Password Incorrecto";
						exchange.getResponseHeaders().add("Content-type", "application/json");
						exchange.sendResponseHeaders(401, responseText.getBytes().length);
					}
				} else {
					responseText = "Formato de Fecha Incorrecto";
					exchange.getResponseHeaders().add("Content-type", "application/json");
					exchange.sendResponseHeaders(400, responseText.getBytes().length);
				}
			} else {

				exchange.sendResponseHeaders(405, -1);
			}
			OutputStream output = exchange.getResponseBody();
			LOGGER.info("LearningJava - Cerrando recursos ...");
			output.write(responseText.getBytes());
			output.flush();
			output.close();
			exchange.close();
		}));

		server.createContext("/api/getAccounts", (LearningjavamavenApplication::handle));

		server.createContext("/api/getAccounts", (exchange -> {
			LOGGER.info("LearningJava - Inicia procesamiento de peticion ...");
			Instant inicioDeEjecucion = Instant.now();
			BankAccountService bankAccountBO = new BankAccountService() {
				@Override
				public List<BankAccountDTO> getAccounts() {
					return null;
				}

				@Override
				public BankAccountDTO getAccountDetails(String user, String lastUsage) {
					return null;
				}
			}
			String responseText = "";
			if ("GET".equals(exchange.getRequestMethod())) {
				LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET");
				List<BankAccountDTO> accounts = bankAccountBO.getAccounts();
				JSONArray json = new JSONArray(accounts);
				responseText = json.toString();
				exchange.getResponseHeaders().add("Content-type", "application/json");
				exchange.sendResponseHeaders(200, responseText.getBytes().length);
			} else {
				exchange.sendResponseHeaders(405, -1);
			}
			OutputStream output = exchange.getResponseBody();
			Instant finalDeEjecucion = Instant.now();
			LOGGER.info("LearningJava - Cerrando recursos ...");
			String total = new String(String.valueOf(Duration.between(inicioDeEjecucion, finalDeEjecucion).toMillis()).concat(" segundos."));
			LOGGER.info("Tiempo de respuesta: ".concat(total));
			output.write(responseText.getBytes());
			output.flush();
			output.close();
			exchange.close();
		}));

		server.createContext("/api/createUsers", (exchange -> {
			LOGGER.info("LearningJava - Inicia procesamiento de peticion ...");
			ResponseDTO response = new ResponseDTO();
			exchange.getRequestBody();
			if ("POST".equals(exchange.getRequestMethod())) {
				LOGGER.info("LearningJava - Procesando peticion HTTP de tipo POST");

				StringBuilder text = new StringBuilder();
				try (Scanner scanner = new Scanner(exchange.getRequestBody())) {
					while(scanner.hasNext()) {
						text.append(scanner.next());
					}
				} catch (Exception e) {
					LOGGER.severe(e.getMessage());
					throw new ExcepcionGenerica("Fallo al obtener el request del body");
				}
				textThread = text.toString();
				LOGGER.info(textThread);
				LearningjavamavenApplication thread = new LearningjavamavenApplication();
				thread.start();

				while(thread.isAlive());

				exchange.getResponseHeaders().set("contentType", "application/json; charset=UTF-8");
				exchange.sendResponseHeaders(200, responseTextThread.getBytes().length);
			} else {
				exchange.sendResponseHeaders(405, -1);
			}
			OutputStream output = exchange.getResponseBody();
			LOGGER.info("LearningJava - Cerrando recursos ...");
			output.write(responseTextThread.getBytes());
			output.flush();
			output.close();
			exchange.close();
		}));

		server.createContext("/api/getAccountsGroupByType", (exchange -> {
			LOGGER.info("LearningJava - Inicia procesamiento de peticion ...");
			Instant inicioDeEjecucion = Instant.now();
			BankAccountService bankAccountBO = new BankAccountServiceImpl();
			String responseText = "";
			if ("GET".equals(exchange.getRequestMethod())) {
				LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET");
				List<BankAccountDTO> accounts = bankAccountBO.getAccounts();
				Map<String, List<BankAccountDTO>> groupedAccounts;
				Function<BankAccountDTO, String> groupFunction = (account) -> account.getAccountType().toString();
				groupedAccounts = accounts.stream().collect(Collectors.groupingBy(groupFunction));
				JSONObject json = new JSONObject(groupedAccounts);
				responseText = json.toString();
				exchange.getResponseHeaders().add("Content-type", "application/json");
				exchange.sendResponseHeaders(200, responseText.getBytes().length);
			} else {
				exchange.sendResponseHeaders(405, -1);
			}
			OutputStream output = exchange.getResponseBody();
			Instant finalDeEjecucion = Instant.now();

			LOGGER.info("LearningJava - Cerrando recursos ...");
			String total = new String(String.valueOf(Duration.between(inicioDeEjecucion, finalDeEjecucion).toMillis()).concat(" segundos."));
			LOGGER.info("Tiempo de respuesta: ".concat(total));
			output.write(responseText.getBytes());
			output.flush();
			output.close();
			exchange.close();
		}));



		server.setExecutor(null);
		server.start();
		LOGGER.info("LearningJava - Server started on port 8080");
	}

	private static void handle(HttpExchange exchange) {
		LOGGER.info("LearningJava - Inicia procesamiento de peticion ...");
		BankAccountService bankAccountBO = new BankAccountServiceImpl();

		String responseText = "";
		if ("GET".equals(exchange.getRequestMethod())) {
			LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET");
			Set<BankAccountDTO> accounts = (Set<BankAccountDTO>) bankAccountBO.getAccounts();
			Map<String, Set<BankAccountDTO>> groupedAccountsDos;
			Function<BankAccountDTO, String> groupFunctionDos = (account) -> account.getAccountType().toString();
			groupedAccountsDos = accounts.stream().collect(Collectors.toCollection(groupFunctionDos));
			JSONArray json = new JSONArray(groupedAccountsDos);
			responseText = json.toString();
			exchange.getResponseHeaders().add("Content-type", "application/json");
			exchange.sendResponseHeaders(200, responseText.getBytes().length);
		} else {
			exchange.sendResponseHeaders(405, -1);
		}
		OutputStream output = exchange.getResponseBody();

		LOGGER.info("LearningJava - Cerrando recursos ...");
		output.write(responseText.getBytes());
		output.flush();
		output.close();
		exchange.close();
	}

	@Override
	public void run(){
		try {
			crearUsuarios();
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			throw new ExcepcionGenerica(e.getMessage());
		}
	}
	@SuppressWarnings("unused")
	private void crearUsuarios() {
		try {
			String user = "user";
			String pass = "password";
			JSONArray jsonArray = new JSONArray(textThread);
			JSONObject userJson;
			ResponseDTO response = null;
			LOGGER.info("jsonArray.length(): " + jsonArray.length());
			for(int i = 0; i < jsonArray.length(); i++) {
				userJson = new JSONObject(jsonArray.get(i).toString());
				response = createUser(userJson.getString(user), userJson.getString(pass));
				responseTextThread = new JSONObject(response).toString();
				LOGGER.info("Usuario " + (i+1) + ": " + responseTextThread);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}


 */