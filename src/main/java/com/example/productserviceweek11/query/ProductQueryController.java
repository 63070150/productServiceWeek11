package com.example.productserviceweek11.query;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.productserviceweek11.query.rest.ProductRestModel;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    @Autowired
    QueryGateway queryGateway;


    @GetMapping
    public List<ProductRestModel> getProducts() {
        FindProductQuery query = new FindProductsQuery();
        queryGateway.query(query, ResponseTypes.multipleInstancesOf(ProductRestModel.class)).join();

    }
}
