package com.example.productserviceweek11.command.rest;

import com.sop.chapter9.core.command.ReserveProductCommand;
import com.sop.chapter9.core.event.ProductReservedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
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

    public ProductAggregate() {

    }

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

    @CommandHandler
    public void handler(ReserveProductCommand reserveProductCommand) {
        if (quantity < reserveProductCommand.getQuantity()) {
            throw new IllegalArgumentException("Insufficient number of items in stock");
        }
        ProductReservedEvent productReservedEvent = ProductReservedEvent.builder()
                .orderId(reserveProductCommand.getOrderId())
                .productId(reserveProductCommand.getProductId())
                .quantity(reserveProductCommand.getQuantity())
                .userId(reserveProductCommand.getUserId())
                .build();
        AggregateLifecycle.apply(productReservedEvent);
    }

    @EventSourcingHandler
    public void on(ProductReservedEvent productReservedEvent) {
        this.quantity -=productReservedEvent.getQuantity();
    }


}
