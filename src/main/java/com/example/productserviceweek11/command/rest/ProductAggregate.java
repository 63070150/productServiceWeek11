package com.example.productserviceweek11.command.rest;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

@Aggregate
public class ProductAggregate {

    //State
    @AggregateIdentifier
    private String productId;
    private String title;
    private BigDecimal price;
    private Integer quantity;

    //Command Handlers
    @CommandHandler
    public ProductAggregate(CreateProductCommand command){
        //Business logic
        if (command.getPrice().compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Price cannot be less than or equal to zero ja");
        }
        if (command.getTitle().isBlank()){
            throw new IllegalArgumentException("Title cannot be blank ja");
        }
//        if (command.getQuantity()<=0){
//            throw new IllegalArgumentException("Quantity cannont be less than or equal to zero ja");
//        }
    }
}
