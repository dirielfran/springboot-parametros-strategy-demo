package com.example.parametrosdemo.util;

import com.example.parametrosdemo.exception.BadRequestException;

public final class NumericResourceId {

    private NumericResourceId() {}

    public static long parseLong(String resourceId) {
        try {
            return Long.parseLong(resourceId.trim());
        } catch (NumberFormatException e) {
            throw new BadRequestException("id invalido: se esperaba un numero para este tipo de parametro");
        }
    }
}
