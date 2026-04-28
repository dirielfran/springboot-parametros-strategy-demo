package com.example.parametrosdemo.dto.polymorphic;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ParametroAResponse.class, name = "A"),
        @JsonSubTypes.Type(value = ParametroBResponse.class, name = "B"),
        @JsonSubTypes.Type(value = ParametroCResponse.class, name = "C"),
        @JsonSubTypes.Type(value = ParametroActividadResponse.class, name = "ACTIVIDAD")
})
public abstract class ParametroResponse {

    private Long id;
    private String nombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
