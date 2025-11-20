package com.codewithsuraj.ProductService.service;

import com.codewithsuraj.ProductService.model.ProductRequest;
import com.codewithsuraj.ProductService.model.ProductResponse;

public interface ProductService {
    long addProduct(ProductRequest productRequest);


    ProductResponse getProductById(long productId);
}
