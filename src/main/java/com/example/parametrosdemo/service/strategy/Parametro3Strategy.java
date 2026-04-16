package com.example.parametrosdemo.service.strategy;

import com.example.parametrosdemo.dto.polymorphic.ParametroCResponse;
import com.example.parametrosdemo.dto.polymorphic.ParametroResponse;
import com.example.parametrosdemo.dto.polymorphic.ParametroCRequest;
import com.example.parametrosdemo.dto.polymorphic.ParametroRequest;
import com.example.parametrosdemo.entity.Parametro3Entity;
import com.example.parametrosdemo.exception.BadRequestException;
import com.example.parametrosdemo.exception.NotFoundException;
import com.example.parametrosdemo.model.ParametroTipo;
import com.example.parametrosdemo.repository.Parametro3Repository;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class Parametro3Strategy implements ParametroStrategy {

    private final Parametro3Repository repository;

    public Parametro3Strategy(Parametro3Repository repository) {
        this.repository = repository;
    }

    @Override
    public ParametroTipo supports() {
        return ParametroTipo.PARAMETRO3;
    }

    @Override
    public ParametroResponse create(ParametroRequest request) {
        ParametroCRequest typed = requireTipoC(request);
        validate(typed);
        Parametro3Entity entity = new Parametro3Entity();
        map(typed, entity);
        return toResponse(repository.save(entity));
    }

    @Override
    public ParametroResponse update(Long id, ParametroRequest request) {
        ParametroCRequest typed = requireTipoC(request);
        validate(typed);
        Parametro3Entity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No existe Parametro3 con id " + id));
        map(typed, entity);
        return toResponse(repository.save(entity));
    }

    @Override
    public ParametroResponse getById(Long id) {
        return toResponse(repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No existe Parametro3 con id " + id)));
    }

    @Override
    public List<ParametroResponse> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("No existe Parametro3 con id " + id);
        }
        repository.deleteById(id);
    }

    private void validate(ParametroCRequest request) {
        if (isBlank(request.getCampo1c()) || isBlank(request.getCampo2c()) || isBlank(request.getCampo3c())) {
            throw new BadRequestException("Los campos campo1c, campo2c y campo3c son obligatorios para PARAMETRO3");
        }
    }

    private void map(ParametroCRequest request, Parametro3Entity entity) {
        entity.setNombre(request.getNombre());
        entity.setCampo1c(request.getCampo1c());
        entity.setCampo2c(request.getCampo2c());
        entity.setCampo3c(request.getCampo3c());
    }

    private ParametroCRequest requireTipoC(ParametroRequest request) {
        if (request instanceof ParametroCRequest parametroCRequest) {
            return parametroCRequest;
        }
        throw new BadRequestException("Se esperaba un request de tipo C para PARAMETRO3");
    }

    private ParametroResponse toResponse(Parametro3Entity entity) {
        ParametroCResponse response = new ParametroCResponse();
        response.setId(entity.getId());
        response.setNombre(entity.getNombre());
        response.setCampo1c(entity.getCampo1c());
        response.setCampo2c(entity.getCampo2c());
        response.setCampo3c(entity.getCampo3c());
        return response;
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
