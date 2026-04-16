package com.example.parametrosdemo.dto.polymorphic;

public class ParametroBResponse extends ParametroResponse {

    private String valor;
    private String campo1b;
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
}
