package com.example.parametrosdemo.dto.polymorphic;

import com.example.parametrosdemo.model.ParametroTipo;
import jakarta.validation.constraints.NotBlank;

public class ParametroARequest extends ParametroRequest {

    @NotBlank(message = "El valor es obligatorio para tipo A")
    private String valor;

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public ParametroTipo tipo() {
        return ParametroTipo.PARAMETRO1;
    }
}
