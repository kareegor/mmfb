package com.kareegar.mmfb.service1;

import java.util.HashMap;
import java.util.Map;

import com.kareegar.mmfb.service1.conf.ApplicationProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.env.Environment;

@EnableConfigurationProperties({ ApplicationProperties.class })
@SpringBootApplication
public class Service1 {
	public static final String SPRING_PROFILE_DEFAULT_KEY = "spring.profiles.default";
	public static final String SPRING_PROFILE_DEFAULT_VALUE = "dev";

	private static final Logger log = LoggerFactory.getLogger(Service1.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Service1.class);
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
				return application.sources(Service1.class);
			}
		};
	}
	
}
