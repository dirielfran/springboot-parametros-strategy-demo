package com.example.parametrosdemo.dto.polymorphic;

import com.example.parametrosdemo.model.ParametroTipo;
import jakarta.validation.constraints.NotBlank;

public class ParametroBRequest extends ParametroRequest {

    @NotBlank(message = "El valor es obligatorio para tipo B")
    private String valor;

    @NotBlank(message = "El campo1b es obligatorio para tipo B")
    private String campo1b;

    @NotBlank(message = "El campo2b es obligatorio para tipo B")
    private String campo2b;

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getCampo1b() {
        return campo1b;
    }

    public void setCampo1b(String campo1b) {
        this.campo1b = campo1b;
    }

    public String getCampo2b() {
        return campo2b;
    }

    public void setCampo2b(String campo2b) {
        this.campo2b = campo2b;
    }

    @Override
    public ParametroTipo tipo() {
        return ParametroTipo.PARAMETRO2;
    }
}
