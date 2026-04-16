package com.example.parametrosdemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "parametro2")
public class Parametro2Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String valor;

    @Column(nullable = false)
    private String campo1b;

    @Column(nullable = false)
    private String campo2b;

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
