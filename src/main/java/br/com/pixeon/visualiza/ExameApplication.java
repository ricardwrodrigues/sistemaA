package br.com.pixeon.visualiza;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import javax.inject.Inject;
import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class ExameApplication {

	private static final Logger log = LoggerFactory.getLogger(ExameApplication.class);

	@Inject
	private Environment env;

	public static void main(String[] args) throws UnknownHostException {
		SpringApplication app = new SpringApplication(ExameApplication.class);
		Environment env = app.run(args).getEnvironment();
		log.info("\n----------------------------------------------------------\n\t" +
						"Application '{}' is running! Access URLs:\n\t" +
						"Local: \t\thttp://127.0.0.1:{}\n\t" +
						"External: \thttp://{}:{}\n----------------------------------------------------------\n\t",
				env.getProperty("spring.application.name"),
				env.getProperty("server.port"),
				InetAddress.getLocalHost().getHostAddress(),
				env.getProperty("server.port"));

	}
}
