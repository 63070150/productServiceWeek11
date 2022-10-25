package com.example.productserviceweek11.command.rest;

import lombok.Data;

import java.math.BigDecimal;

@Data//lombok auto generate getter setter
public class CreateProductRestModel {
    private String title;
    private BigDecimal price;
    private Integer quantity;


}
