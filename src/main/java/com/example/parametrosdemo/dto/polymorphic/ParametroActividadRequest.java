package com.example.parametrosdemo.dto.polymorphic;

import com.example.parametrosdemo.model.ParametroTipo;
import com.example.parametrosdemo.model.TipoParametro;
import com.example.parametrosdemo.model.TipoPersona;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ParametroActividadRequest extends ParametroRequest {

    @NotNull(message = "configId es obligatorio para ParametroActividad")
    private Integer configId;

    @NotBlank(message = "codigo es obligatorio para ParametroActividad")
    private String codigo;

    @NotNull(message = "tipoPersona es obligatorio")
    private TipoPersona tipoPersona;

    @NotNull(message = "tipoParametro es obligatorio")
    private TipoParametro tipoParametro;

    private String tooltipMessage;

    private Boolean visible;

    private Boolean validarOficialQr;

    private Long templateOfertaId;

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    @Override
    public ParametroTipo tipo() {
        return ParametroTipo.PARAMETRO_ACTIVIDAD;
    }
}
