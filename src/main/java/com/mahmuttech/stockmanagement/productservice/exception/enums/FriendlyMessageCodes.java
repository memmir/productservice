package com.mahmuttech.stockmanagement.productservice.exception.enums;

public enum FriendlyMessageCodes implements IFriendlyMessageCode {

    OK(1000);
    private final int value;

    FriendlyMessageCodes(int values){
        this.value=values;
    }

    @Override
    public int getFriendlyMessageCode() {
        return value;
    }
}
