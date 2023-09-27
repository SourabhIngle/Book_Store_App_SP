package com.bridgelabz.bookstoreapp;

//import io.swagger.annotations.Contact;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableSwagger2
public class BookStoreAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookStoreAppApplication.class, args);
	}
//	@Bean
//	public Docket swaggerconfig() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.select()
//				.apis(RequestHandlerSelectors.basePackage("package name"))
//				.build()
//				.apiInfo(getApiInfo());
//	}
//
//	private ApiInfo getApiInfo() {
//		springfox.documentation.service.Contact contact = new Contact("Demo project API", "http://bridgelabz.com&quot;,
//				"your email id");
//		return new ApiInfoBuilder().title("DEMO Service Swagger API")
//				.description("DEMO Service Swagger API for Learning Swagger").version("0.0.1-SNAPSHOT")
//				.license("Apache 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0").contact(contact)
//				.build();
//	}
}
