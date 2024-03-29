package com.mahmuttech.stockmanagement.productservice.controller;

import com.mahmuttech.stockmanagement.productservice.enums.Language;
import com.mahmuttech.stockmanagement.productservice.exception.enums.FriendlyMessageCodes;
import com.mahmuttech.stockmanagement.productservice.exception.utils.FriendlyMessageUtils;
import com.mahmuttech.stockmanagement.productservice.repository.entity.Product;
import com.mahmuttech.stockmanagement.productservice.request.ProductCreateRequest;
import com.mahmuttech.stockmanagement.productservice.request.ProductUpdateRequest;
import com.mahmuttech.stockmanagement.productservice.response.FriendlyMessage;
import com.mahmuttech.stockmanagement.productservice.response.InternalApiResponse;
import com.mahmuttech.stockmanagement.productservice.response.ProductResponse;
import com.mahmuttech.stockmanagement.productservice.service.IProductRepositoryService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/api/1.0/product")
@RequiredArgsConstructor
public class ProductController {

    private final IProductRepositoryService iProductRepositoryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{language}/create")
    public InternalApiResponse<ProductResponse> createProduct(@PathVariable("language") Language language, @RequestBody ProductCreateRequest productCreateRequest){

        log.debug("[{}][create product] -> request: {}", this.getClass().getSimpleName(), productCreateRequest);
        Product product = iProductRepositoryService.createProduct(language, productCreateRequest);
        ProductResponse productResponse = convertProductResponse(product);
        log.debug("[{}][createProduct] -> response: {}",this.getClass().getSimpleName(),productResponse);


        return InternalApiResponse.<ProductResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCES))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language,FriendlyMessageCodes.PRODUCT_SUCCESFULLY_CREATED))
                        .build())
                .httpStatus(HttpStatus.CREATED)
                .hasError(false)
                .payload(productResponse)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{language}/get/{productId}")
    public InternalApiResponse<ProductResponse> getProduct(@PathVariable("language")Language language,
                                                           @PathVariable("productId") Long productId){
        log.debug("[{}][getProduct] -> request: {}", this.getClass().getSimpleName(), productId);
        Product product = iProductRepositoryService.getProduct(language,productId);
        ProductResponse productResponse = convertProductResponse(product);
        log.debug("[{}][getProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
        return InternalApiResponse.<ProductResponse>builder()
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponse)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{language}/update/{productId}")
    public InternalApiResponse<ProductResponse> updateProduct(@PathVariable("language") Language language,
                                                              @PathVariable("productId") Long productId,
                                                              @RequestBody ProductUpdateRequest productUpdateRequest){
        log.debug("[{}][updateProduct] -> request: {}", this.getClass().getSimpleName(),productId, productUpdateRequest);
        Product product= iProductRepositoryService.updateProduct(language, productId,productUpdateRequest);
        ProductResponse productResponse = convertProductResponse(product);
        log.debug("[{}][updateProduct] -> request: {}", this.getClass().getSimpleName(), productResponse);

        return InternalApiResponse.<ProductResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCES))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.PRODUCT_SUCCESFULLY_UPDATED))
                        .build())
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponse)
                .build();
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value= "/{language}/products")
    public InternalApiResponse<List<ProductResponse>> getAllProducts(@PathVariable("language") Language language){
        log.debug("[{}][getAllProducts] -> request: {}", this.getClass().getSimpleName());
        List<Product> products = iProductRepositoryService.getProducts(language);
        List<ProductResponse> productResponses = convertProductResponseList(products);
        log.debug("[{}][getAllProducts] -> response: {}", this.getClass().getSimpleName(), productResponses);
        return InternalApiResponse.<List<ProductResponse>>builder()
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponses)
                .build();
    }

    private List<ProductResponse> convertProductResponseList (List<Product> productList){
        return productList.stream()
                .map(arg -> ProductResponse.builder()
                        .productId(arg.getProductId())
                        .productName(arg.getProductName())
                        .quantity(arg.getQuantity())
                        .price(arg.getPrice())
                        .productCreatedDate(arg.getProductCreatedDate().getTime())
                        .productUpdatedDate(arg.getProductUpdatedDate().getTime())
                        .build())
                .collect(Collectors.toList());

    }

    //Setleme işlemini birden fazla kez yapacağımız için setleme işlemlerinin gerçekleştiği bir methoda dönüştürüyoruz.
    private ProductResponse convertProductResponse(Product product) {
         return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .productCreatedDate(product.getProductCreatedDate().getTime())
                .productUpdatedDate(product.getProductCreatedDate().getTime())
                .build();
    }

}
