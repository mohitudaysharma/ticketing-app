package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import com.example.demo.filter.JWTFilter;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import org.apache.kafka.clients.admin.NewTopic;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title= "MovieBookingApp", version="1.0",description="Book your next film here"))
public class MovieBookingAppApplication {

	@Bean
	public FilterRegistrationBean jwtFilter() {
		FilterRegistrationBean fb = new FilterRegistrationBean();
		fb.setFilter(new JWTFilter());
		fb.addUrlPatterns("/movie/v1/*","/theatre/v1/*","/ticket/v1/*","/show/v1/*");
		return fb;
	}
	
	@Configuration
	class OpenApiConfig {
		@Bean
		public OpenAPI customConfig() {
			final String securitySchemeName = "bearerAuth";
			
			return new OpenAPI()
				.addSecurityItem(new SecurityRequirement()
						.addList(securitySchemeName))
					.components(new Components()
						.addSecuritySchemes(securitySchemeName, new SecurityScheme()
						.name(securitySchemeName).type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
		}
	}

	@Value("${spring.kafka.topic.name}")
	private String topicName;

	@Configuration
	class KafkaTopicConfig {
	    @Bean
	    public NewTopic topic(){
	        return TopicBuilder.name(topicName).build();
	    }
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MovieBookingAppApplication.class, args);
	}

}
