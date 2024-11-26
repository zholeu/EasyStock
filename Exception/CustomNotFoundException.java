package com.springeasystock.easystock.Exception;

import lombok.Getter;

@Getter
public class CustomNotFoundException extends RuntimeException {
    private final Integer id;

    public CustomNotFoundException(Integer id) {
        super("ID of the missing object: " + id);
        this.id = id;
    }

}

