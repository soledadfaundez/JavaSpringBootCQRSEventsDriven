package com.dailycodebuffer.ProductService.command.api.controller;

import com.dailycodebuffer.ProductService.command.api.commands.CreateProductCommand;
import com.dailycodebuffer.ProductService.command.api.model.ProductRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequestMapping("/products")
public class ProductCommandController {

    private CommandGateway commandGateway;

    //EFC: Constructor, no es necesario si se coloca @AllArgsConstructor
    public ProductCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    //EFC: Se podría mejorar usando un objeto command y no el objeto de BD directamente.
    @PostMapping
    public String addProduct(@RequestBody ProductRestModel productRestModel) {

        //EFC: Creamos el command, con los datos del restModel.
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                        .productId(UUID.randomUUID().toString())
                        .name(productRestModel.getName())
                        .price(productRestModel.getPrice())
                        .quantity(productRestModel.getQuantity())
                        .build();

        //EFC: Enviamos el command
        String result = commandGateway.sendAndWait(createProductCommand);
        return result;
    }
}
