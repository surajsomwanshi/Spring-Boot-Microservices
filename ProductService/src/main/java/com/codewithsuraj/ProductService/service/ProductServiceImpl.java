package com.codewithsuraj.ProductService.service;

import com.codewithsuraj.ProductService.entity.Product;
import com.codewithsuraj.ProductService.exception.ProductServiceCustomException;
import com.codewithsuraj.ProductService.model.ProductRequest;
import com.codewithsuraj.ProductService.model.ProductResponse;
import com.codewithsuraj.ProductService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Adding product...");

        Product product
                = Product.builder()
                .productName(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);

        log.info("Product created");

        return product.getProductId();
    }

    /**
     * @param productId
     * @return
     */
    @Override
    public ProductResponse getProductById(long productId) {
        log.info("Get product for productId: {}",productId);

        Product product = productRepository.findById(productId)
                .orElseThrow(
                        ()-> new ProductServiceCustomException("Product with given product id not found","PRODUCT_NOT_FOUND")
                );
        ProductResponse productResponse= new ProductResponse();
        BeanUtils.copyProperties(product,productResponse);
        return productResponse;
    }


}
