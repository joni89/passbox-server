package com.jonatancm.passbox.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;

@Configuration
@EnableSwagger2
public class AppConfiguration {

	private static final String DB_URL = "jdbc:mysql://nestorrente.duckdns.org:6034/passbox?autoReconnect=true&useSSL=false";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "p45580x";

	@ConfigurationProperties(prefix = "spring.datasource")
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(DB_URL);
		dataSource.setUsername(DB_USERNAME);
		dataSource.setPassword(DB_PASSWORD);
		return dataSource;
		// return DataSourceBuilder.create()
		// 		.url(DB_URL)
		// 		.username(DB_USERNAME)
		// 		.password(DB_PASSWORD)
		// 		.build();
	}

	// @Bean
	// NamedParameterJdbcOperations operations() {
	// 	return new NamedParameterJdbcTemplate(dataSource());
	// }
	//
	// @Bean
	// PlatformTransactionManager transactionManager() {
	// 	return new DataSourceTransactionManager(dataSource());
	// }

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

}
