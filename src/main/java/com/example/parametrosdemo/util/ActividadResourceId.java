package com.example.parametrosdemo.util;

import com.example.parametrosdemo.entity.ParametroConfigId;
import com.example.parametrosdemo.exception.BadRequestException;

public final class ActividadResourceId {

    public static final String SEP = "@@";

    private ActividadResourceId() {}

    public static String encode(ParametroConfigId id) {
        return id.getId() + SEP + id.getCodigo();
    }

    public static ParametroConfigId decode(String resourceId) {
        if (resourceId == null || resourceId.isBlank()) {
            throw new BadRequestException("id requerido para ParametroActividad");
        }
        int sep = resourceId.indexOf(SEP);
        if (sep < 1 || sep + SEP.length() >= resourceId.length()) {
            throw new BadRequestException(
                    "id ParametroActividad invalido: formato esperado <idNumerico>@@<codigo> (URL-encoded si hace falta)");
        }
        try {
            int idNum = Integer.parseInt(resourceId.substring(0, sep));
            String codigo = resourceId.substring(sep + SEP.length());
            if (codigo.isBlank()) {
                throw new BadRequestException("codigo vacio en id compuesto ParametroActividad");
            }
            return new ParametroConfigId(idNum, codigo);
        } catch (NumberFormatException e) {
            throw new BadRequestException("parte numerica del id ParametroActividad invalida");
        }
    }
}
