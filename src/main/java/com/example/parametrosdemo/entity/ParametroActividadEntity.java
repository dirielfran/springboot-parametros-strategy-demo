package com.example.parametrosdemo.entity;

import com.example.parametrosdemo.model.TipoParametro;
import com.example.parametrosdemo.model.TipoPersona;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "parametro_actividad")
public class ParametroActividadEntity {

    @EmbeddedId
    private ParametroConfigId id;

    @Column(nullable = false, length = 512)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private TipoPersona tipoPersona;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private TipoParametro tipoParametro;

    @Column(length = 1024)
    private String tooltipMessage;

    private Boolean visible;

    private Boolean validarOficialQr;

    @Column(name = "template_oferta_id")
    private Long templateOfertaId;

    public ParametroConfigId getId() {
        return id;
    }

    public void setId(ParametroConfigId id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public TipoParametro getTipoParametro() {
        return tipoParametro;
    }

    public void setTipoParametro(TipoParametro tipoParametro) {
        this.tipoParametro = tipoParametro;
    }

    public String getTooltipMessage() {
        return tooltipMessage;
    }

    public void setTooltipMessage(String tooltipMessage) {
        this.tooltipMessage = tooltipMessage;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean getValidarOficialQr() {
        return validarOficialQr;
    }

    public void setValidarOficialQr(Boolean validarOficialQr) {
        this.validarOficialQr = validarOficialQr;
    }

    public Long getTemplateOfertaId() {
        return templateOfertaId;
    }

    public void setTemplateOfertaId(Long templateOfertaId) {
        this.templateOfertaId = templateOfertaId;
    }
}
