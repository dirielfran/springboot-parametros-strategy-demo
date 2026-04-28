package com.example.parametrosdemo.service.strategy;

import com.example.parametrosdemo.dto.polymorphic.ParametroActividadRequest;
import com.example.parametrosdemo.dto.polymorphic.ParametroActividadResponse;
import com.example.parametrosdemo.dto.polymorphic.ParametroRequest;
import com.example.parametrosdemo.dto.polymorphic.ParametroResponse;
import com.example.parametrosdemo.entity.ParametroActividadEntity;
import com.example.parametrosdemo.entity.ParametroConfigId;
import com.example.parametrosdemo.exception.BadRequestException;
import com.example.parametrosdemo.exception.NotFoundException;
import com.example.parametrosdemo.model.ParametroTipo;
import com.example.parametrosdemo.repository.ParametroActividadRepository;
import com.example.parametrosdemo.util.ActividadResourceId;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ParametroActividadStrategy implements ParametroStrategy {

    private final ParametroActividadRepository repository;

    public ParametroActividadStrategy(ParametroActividadRepository repository) {
        this.repository = repository;
    }

    @Override
    public ParametroTipo supports() {
        return ParametroTipo.PARAMETRO_ACTIVIDAD;
    }

    @Override
    public ParametroResponse create(ParametroRequest request) {
        ParametroActividadRequest typed = requireTipo(request);
        validate(typed);
        ParametroConfigId newId = new ParametroConfigId(typed.getConfigId(), typed.getCodigo());
        if (repository.existsById(newId)) {
            throw new BadRequestException("Ya existe ParametroActividad con la clave " + ActividadResourceId.encode(newId));
        }
        ParametroActividadEntity entity = new ParametroActividadEntity();
        entity.setId(newId);
        map(typed, entity);
        return toResponse(repository.save(entity));
    }

    @Override
    public ParametroResponse update(String resourceId, ParametroRequest request) {
        ParametroActividadRequest typed = requireTipo(request);
        validate(typed);
        ParametroConfigId pathId = ActividadResourceId.decode(resourceId);
        ParametroConfigId bodyId = new ParametroConfigId(typed.getConfigId(), typed.getCodigo());
        if (!pathId.equals(bodyId)) {
            throw new BadRequestException("La clave del path debe coincidir con configId y codigo del body");
        }
        ParametroActividadEntity entity = repository.findById(pathId)
                .orElseThrow(() -> new NotFoundException("No existe ParametroActividad con id " + resourceId));
        map(typed, entity);
        return toResponse(repository.save(entity));
    }

    @Override
    public ParametroResponse getById(String resourceId) {
        ParametroConfigId id = ActividadResourceId.decode(resourceId);
        return toResponse(repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No existe ParametroActividad con id " + resourceId)));
    }

    @Override
    public List<ParametroResponse> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public void delete(String resourceId) {
        ParametroConfigId id = ActividadResourceId.decode(resourceId);
        if (!repository.existsById(id)) {
            throw new NotFoundException("No existe ParametroActividad con id " + resourceId);
        }
        repository.deleteById(id);
    }

    private void validate(ParametroActividadRequest request) {
        if (isBlank(request.getNombre())) {
            throw new BadRequestException("El nombre (descripcion visible en API) es obligatorio para ParametroActividad");
        }
    }

    private void map(ParametroActividadRequest request, ParametroActividadEntity entity) {
        entity.setDescripcion(request.getNombre());
        entity.setTipoPersona(request.getTipoPersona());
        entity.setTipoParametro(request.getTipoParametro());
        entity.setTooltipMessage(request.getTooltipMessage());
        entity.setVisible(request.getVisible());
        entity.setValidarOficialQr(request.getValidarOficialQr());
        entity.setTemplateOfertaId(request.getTemplateOfertaId());
    }

    private ParametroActividadRequest requireTipo(ParametroRequest request) {
        if (request instanceof ParametroActividadRequest actividadRequest) {
            return actividadRequest;
        }
        throw new BadRequestException("Se esperaba un request ACTIVIDAD para PARAMETRO_ACTIVIDAD");
    }

    private ParametroResponse toResponse(ParametroActividadEntity entity) {
        ParametroActividadResponse r = new ParametroActividadResponse();
        r.setId(null);
        r.setNombre(entity.getDescripcion());
        r.setConfigId(entity.getId().getId());
        r.setCodigo(entity.getId().getCodigo());
        r.setDescripcion(entity.getDescripcion());
        r.setTipoPersona(entity.getTipoPersona());
        r.setTipoParametro(entity.getTipoParametro());
        r.setTooltipMessage(entity.getTooltipMessage());
        r.setVisible(entity.getVisible());
        r.setValidarOficialQr(entity.getValidarOficialQr());
        r.setTemplateOfertaId(entity.getTemplateOfertaId());
        return r;
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
