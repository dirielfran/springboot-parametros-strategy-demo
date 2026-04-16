package com.example.parametrosdemo.dto.polymorphic;

import com.example.parametrosdemo.model.ParametroTipo;
import jakarta.validation.constraints.NotBlank;

public class ParametroCRequest extends ParametroRequest {

    @NotBlank(message = "El campo1c es obligatorio para tipo C")
    private String campo1c;

    @NotBlank(message = "El campo2c es obligatorio para tipo C")
    private String campo2c;

    @NotBlank(message = "El campo3c es obligatorio para tipo C")
    private String campo3c;

    public String getCampo1c() {
        return campo1c;
    }

    public void setCampo1c(String campo1c) {
        this.campo1c = campo1c;
    }

    public String getCampo2c() {
        return campo2c;
    }

    public void setCampo2c(String campo2c) {
        this.campo2c = campo2c;
    }

    public String getCampo3c() {
        return campo3c;
    }

    public void setCampo3c(String campo3c) {
        this.campo3c = campo3c;
    }

    @Override
    public ParametroTipo tipo() {
        return ParametroTipo.PARAMETRO3;
    }
}
