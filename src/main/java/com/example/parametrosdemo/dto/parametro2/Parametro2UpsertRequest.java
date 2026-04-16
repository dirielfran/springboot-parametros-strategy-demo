package com.example.parametrosdemo.dto.parametro2;

import jakarta.validation.constraints.NotBlank;

public class Parametro2UpsertRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El valor es obligatorio")
    private String valor;

    @NotBlank(message = "El campo1b es obligatorio")
    private String campo1b;

    @NotBlank(message = "El campo2b es obligatorio")
    private String campo2b;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

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
}
