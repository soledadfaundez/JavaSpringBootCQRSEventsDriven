package com.dailycodebuffer.ProductService;

import com.dailycodebuffer.ProductService.command.api.exception.ProductServiceEventsErrorHandler;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	//EFC: Esta configuración permite abortar el proceso de registro en cualquier momento de la llamada al command.
	// el "product" hace referencia al grupo que está en ProductEventsHandler.

	@Autowired
	public void configure(EventProcessingConfigurer configurer) {
		configurer.registerListenerInvocationErrorHandler(
				"product",
				configuration -> new ProductServiceEventsErrorHandler()
		);
	}
}
