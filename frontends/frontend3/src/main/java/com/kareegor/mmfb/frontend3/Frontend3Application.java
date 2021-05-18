package com.kareegor.mmfb.frontend3;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.kareegar.mmfb.frontend3.conf.ApplicationProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

@SpringBootApplication
public class Frontend3Application {

	public static final String SPRING_PROFILE_DEFAULT_KEY = "spring.profiles.default";
	public static final String SPRING_PROFILE_DEFAULT_VALUE = "dev";

	private static final Logger log = LoggerFactory.getLogger(Frontend3Application.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Frontend3Application.class);
		setupDefaultProfile(app);
		Environment env = app.run(args).getEnvironment();
		logStartup(env);
	}

	private static void setupDefaultProfile(SpringApplication app) {
		Map<String, Object> defProps = new HashMap<>();
		defProps.put(SPRING_PROFILE_DEFAULT_KEY, SPRING_PROFILE_DEFAULT_VALUE);
		app.setDefaultProperties(defProps);
		new SpringBootServletInitializer() {
			@Override
			protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
				application.application().setDefaultProperties(defProps);
				return application.sources(Frontend3Application.class);
			}
		};
	}

	private static void logStartup(Environment env) {
		String protocol = "http";
		if (env.getProperty("server.ssl.key-store") != null) {
			protocol = "https";
		}
		String serverPort = env.getProperty("server.port");
		String contextPath = env.getProperty("server.servlet.context-path");
		if (!StringUtils.hasLength(contextPath)) {
			contextPath = "/";
		}
		String hostAddress = "localhost";
		try {
			hostAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			log.warn("The host name could not be determined, using `localhost` as fallback");
		}
		log.info(
				"\n----------------------------------------------------------\n\t"
						+ "Application '{}' is running! Access URLs:\n\t" + "Local: \t\t{}://localhost:{}{}\n\t"
						+ "External: \t{}://{}:{}{}\n\t"
						+ "Profile(s): \t{}\n----------------------------------------------------------",
				env.getProperty("spring.application.name"), protocol, serverPort, contextPath, protocol, hostAddress,
				serverPort, contextPath, env.getActiveProfiles());

		String configServerStatus = env.getProperty("configserver.status");
		if (configServerStatus == null) {
			configServerStatus = "Not found or not setup for this application";
		}
		log.info(
				"\n----------------------------------------------------------\n\t"
						+ "Config Server: \t{}\n----------------------------------------------------------",
				configServerStatus);
	}
}
