package com.dailycodebuffer.ProductService.command.api.events;

import com.dailycodebuffer.ProductService.command.api.data.Product;
import com.dailycodebuffer.ProductService.command.api.data.ProductRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product")
public class ProductEventsHandler {

    //EFC: Inyectamos el repositorio para BD
    private ProductRepository productRepository;

    //EFC: Constructor
    public ProductEventsHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //EFC: El evento que se dispara en ocurrencia de ProductCreatedEvent
    @EventHandler
    public void on(ProductCreatedEvent event) throws Exception {

        Product product = new Product();
        BeanUtils.copyProperties(event,product); //EFC: Copiamos los valores al objeto producto
        productRepository.save(product); //EFC: Se guarda a nivel persistencia de BD

    }

    @ExceptionHandler
    public void handle(Exception exception) throws Exception {
        throw exception;
    }
}
