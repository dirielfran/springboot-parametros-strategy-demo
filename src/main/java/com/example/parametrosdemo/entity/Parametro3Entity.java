package com.example.parametrosdemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "parametro3")
public class Parametro3Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String campo1c;

    @Column(nullable = false)
    private String campo2c;

    @Column(nullable = false)
    private String campo3c;

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
