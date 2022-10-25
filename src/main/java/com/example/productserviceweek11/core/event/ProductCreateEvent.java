package com.example.productserviceweek11.core.event;

import com.example.productserviceweek11.command.rest.CreateProductCommand;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Data
public class ProductCreateEvent {
    @AggregateIdentifier
    private String productId;
    private String title;
    private BigDecimal price;
    private Integer quantity;
    @CommandHandler
    public ProductAggregate(CreateProductCommand command){//error ja rai mai ru

        //Business logic
        if (command.getPrice().compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Price cannot be less than or equal to zero ja");
        }
        if (command.getTitle().isBlank()){
            throw new IllegalArgumentException("Title cannot be blank ja");
        }

        //Event
        ProductCreateEvent event = new ProductCreateEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(ProductCreateEvent event) {
        // Update state
        this.productId = event.getProductId();
        this.title = event.getTitle();
        this.price = event.getPrice();
        this.quantity = event.getQuantity();
        System.out.println("on product created ja: " + this.productId);
    }
}
