package com.microshop.stockmanagement.productservice.exception.handler;

import com.microshop.stockmanagement.productservice.exception.enums.FriendlyMessageCodes;
import com.microshop.stockmanagement.productservice.exception.exceptions.ProductAlreadyDeletedException;
import com.microshop.stockmanagement.productservice.exception.exceptions.ProductNotCreatedException;
import com.microshop.stockmanagement.productservice.exception.exceptions.ProductNotFoundException;
import com.microshop.stockmanagement.productservice.exception.utils.FriendlyMessageUtils;
import com.microshop.stockmanagement.productservice.response.FriendlyMessage;
import com.microshop.stockmanagement.productservice.response.InternalApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductNotCreatedException.class)
    public InternalApiResponse<String> handleProductNotCreatedException(ProductNotCreatedException exception){
        return InternalApiResponse.<String>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(), FriendlyMessageCodes.ERROR))
                        .description(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(), exception.getFriendlyMessageCode()))
                        .build())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .hasError(true)
                .errorMessage(Collections.singletonList(exception.getMessage()))
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public InternalApiResponse<String> handleProductNotFoundException(ProductNotFoundException exception){
        return InternalApiResponse.<String>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(),FriendlyMessageCodes.ERROR))
                        .description(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(),exception.getFriendlyMessageCode()))
                        .build())
                .httpStatus(HttpStatus.NOT_FOUND)
                .hasError(true)
                .errorMessage(Collections.singletonList(exception.getMessage()))
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductAlreadyDeletedException.class)
    public InternalApiResponse<String> handleProductAlreadyDeletedException(ProductNotFoundException exception){
        return InternalApiResponse.<String>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(),FriendlyMessageCodes.ERROR))
                        .description(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(),exception.getFriendlyMessageCode()))
                        .build())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .hasError(true)
                .errorMessage(Collections.singletonList(exception.getMessage()))
                .build();
    }
}
