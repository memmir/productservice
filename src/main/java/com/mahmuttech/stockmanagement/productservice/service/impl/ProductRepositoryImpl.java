package com.mahmuttech.stockmanagement.productservice.service.impl;

import com.mahmuttech.stockmanagement.productservice.enums.Language;
import com.mahmuttech.stockmanagement.productservice.repository.entity.Product;
import com.mahmuttech.stockmanagement.productservice.repository.entity.ProductRepository;
import com.mahmuttech.stockmanagement.productservice.request.ProductCreateRequest;
import com.mahmuttech.stockmanagement.productservice.request.ProductUpdateRequest;
import com.mahmuttech.stockmanagement.productservice.service.IProductRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductRepositoryImpl implements IProductRepositoryService {

    private final ProductRepository productRepository;

    @Override
    public Product createProduct(Language language, ProductCreateRequest productCreateRequest) {
        log.debug("[{}][create product] -> request: {}", this.getClass().getSimpleName(), productCreateRequest);
        try{
            Product product= Product.builder()
                    .productName(productCreateRequest.getProductName())
                    .quantity(productCreateRequest.getQuantity())
                    .price(productCreateRequest.getPrice())
                    .deleted(false)
                    .build();
        }catch (Exception e){}
        return null;
    }

    @Override
    public Product getProduct(Language language, Long productId) {
        return null;
    }

    @Override
    public List<Product> getProducts(Language language) {
        return null;
    }

    @Override
    public Product updateProduct(Language language, Long productId, ProductUpdateRequest productUpdateRequest) {
        return null;
    }

    @Override
    public Product deleteProduct(Language language, Long productId) {
        return null;
    }
}
