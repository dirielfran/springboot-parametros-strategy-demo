package com.example.parametrosdemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ParametroConfigId implements Serializable {

    @Column(name = "config_id", nullable = false)
    private Integer id;

    @Column(name = "config_codigo", nullable = false, length = 128)
    private String codigo;

    public ParametroConfigId() {}

    public ParametroConfigId(Integer id, String codigo) {
        this.id = id;
        this.codigo = codigo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ParametroConfigId that = (ParametroConfigId) o;
        return Objects.equals(id, that.id) && Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo);
    }
}
