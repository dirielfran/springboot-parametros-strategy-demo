package com.example.parametrosdemo.dto.polymorphic;

import com.example.parametrosdemo.model.ParametroTipo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotBlank;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ParametroARequest.class, name = "A"),
        @JsonSubTypes.Type(value = ParametroBRequest.class, name = "B"),
        @JsonSubTypes.Type(value = ParametroCRequest.class, name = "C")
})
public abstract class ParametroRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public abstract ParametroTipo tipo();
}
