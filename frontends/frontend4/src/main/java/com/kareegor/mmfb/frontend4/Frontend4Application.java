package com.kareegor.mmfb.frontend4;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.kareegar.mmfb.frontend4.conf.ApplicationProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

@EnableConfigurationProperties({ ApplicationProperties.class })
@SpringBootApplication
public class Frontend4Application {
	public static final String SPRING_PROFILE_DEFAULT_KEY = "spring.profiles.default";
	public static final String SPRING_PROFILE_DEFAULT_VALUE = "dev";

	private static final Logger log = LoggerFactory.getLogger(Frontend4Application.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Frontend4Application.class);
		setupDefaultProfile(app);
		Environment env = app.run(args).getEnvironment();		
	}
	private static void setupDefaultProfile(SpringApplication app) {
		Map<String, Object> defProps = new HashMap<>();
		defProps.put(SPRING_PROFILE_DEFAULT_KEY, SPRING_PROFILE_DEFAULT_VALUE);
		app.setDefaultProperties(defProps);
		new SpringBootServletInitializer() {
			@Override
			protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
				application.application().setDefaultProperties(defProps);
				return application.sources(Frontend4Application.class);
			}
		};
	}
	
}
