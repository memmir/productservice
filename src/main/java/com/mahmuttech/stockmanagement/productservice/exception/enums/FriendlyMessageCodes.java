package com.mahmuttech.stockmanagement.productservice.exception.enums;

public enum FriendlyMessageCodes implements IFriendlyMessageCode {

    OK(1000),
    ERROR(1001),
    SUCCES(1002),
    PRODUCT_NOT_CREATED_EXCEPTION(1500),
    PRODUCT_SUCCESFULLY_CREATED(1501),
    PRODUCT_NOT_FOUND_EXCEPTION(1502);
    private final int value;

    FriendlyMessageCodes(int values){
        this.value=values;
    }

    @Override
    public int getFriendlyMessageCode() {
        return value;
    }
}
