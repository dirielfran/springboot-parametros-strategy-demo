package com.example.parametrosdemo.dto.parametro3;

import jakarta.validation.constraints.NotBlank;

public class Parametro3UpsertRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El campo1c es obligatorio")
    private String campo1c;

    @NotBlank(message = "El campo2c es obligatorio")
    private String campo2c;

    @NotBlank(message = "El campo3c es obligatorio")
    private String campo3c;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

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
}
