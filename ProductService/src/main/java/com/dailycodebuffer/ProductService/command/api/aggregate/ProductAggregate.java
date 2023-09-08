package com.dailycodebuffer.ProductService.command.api.aggregate;

import com.dailycodebuffer.ProductService.command.api.commands.CreateProductCommand;
import com.dailycodebuffer.ProductService.command.api.events.ProductCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class ProductAggregate {

    @AggregateIdentifier
    private String productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;

    //EFC: Definimos un command handler
    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {

        //You can perform all the validations

        //EFC: Creamos el objeto de evento
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();

        //EFC: BeanUtils.copyProperties copia valores de propiedades entre diferentes objetos.
        BeanUtils.copyProperties(createProductCommand,productCreatedEvent);

        //EFC: Se publica el evento.
        AggregateLifecycle.apply(productCreatedEvent);
    }

    //EFC: Constructor
    public ProductAggregate() {
    }

    //EFC: El destino del apply
    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent) {

        //EFC: Pasamos los datos al aggregate.
        this.quantity = productCreatedEvent.getQuantity();
        this.productId = productCreatedEvent.getProductId();
        this.price = productCreatedEvent.getPrice();
        this.name = productCreatedEvent.getName();

    }
}
