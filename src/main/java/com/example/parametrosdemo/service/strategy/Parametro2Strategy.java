package com.example.parametrosdemo.service.strategy;

import com.example.parametrosdemo.dto.polymorphic.ParametroBResponse;
import com.example.parametrosdemo.dto.polymorphic.ParametroResponse;
import com.example.parametrosdemo.dto.polymorphic.ParametroBRequest;
import com.example.parametrosdemo.dto.polymorphic.ParametroRequest;
import com.example.parametrosdemo.entity.Parametro2Entity;
import com.example.parametrosdemo.exception.BadRequestException;
import com.example.parametrosdemo.exception.NotFoundException;
import com.example.parametrosdemo.model.ParametroTipo;
import com.example.parametrosdemo.repository.Parametro2Repository;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class Parametro2Strategy implements ParametroStrategy {

    private final Parametro2Repository repository;

    public Parametro2Strategy(Parametro2Repository repository) {
        this.repository = repository;
    }

    @Override
    public ParametroTipo supports() {
        return ParametroTipo.PARAMETRO2;
    }

    @Override
    public ParametroResponse create(ParametroRequest request) {
        ParametroBRequest typed = requireTipoB(request);
        validate(typed);
        Parametro2Entity entity = new Parametro2Entity();
        map(typed, entity);
        return toResponse(repository.save(entity));
    }

    @Override
    public ParametroResponse update(Long id, ParametroRequest request) {
        ParametroBRequest typed = requireTipoB(request);
        validate(typed);
        Parametro2Entity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No existe Parametro2 con id " + id));
        map(typed, entity);
        return toResponse(repository.save(entity));
    }

    @Override
    public ParametroResponse getById(Long id) {
        return toResponse(repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No existe Parametro2 con id " + id)));
    }

    @Override
    public List<ParametroResponse> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("No existe Parametro2 con id " + id);
        }
        repository.deleteById(id);
    }

    private void validate(ParametroBRequest request) {
        if (isBlank(request.getValor()) || isBlank(request.getCampo1b()) || isBlank(request.getCampo2b())) {
            throw new BadRequestException("Los campos valor, campo1b y campo2b son obligatorios para PARAMETRO2");
        }
    }

    private void map(ParametroBRequest request, Parametro2Entity entity) {
        entity.setNombre(request.getNombre());
        entity.setValor(request.getValor());
        entity.setCampo1b(request.getCampo1b());
        entity.setCampo2b(request.getCampo2b());
    }

    private ParametroBRequest requireTipoB(ParametroRequest request) {
        if (request instanceof ParametroBRequest parametroBRequest) {
            return parametroBRequest;
        }
        throw new BadRequestException("Se esperaba un request de tipo B para PARAMETRO2");
    }

    private ParametroResponse toResponse(Parametro2Entity entity) {
        ParametroBResponse response = new ParametroBResponse();
        response.setId(entity.getId());
        response.setNombre(entity.getNombre());
        response.setValor(entity.getValor());
        response.setCampo1b(entity.getCampo1b());
        response.setCampo2b(entity.getCampo2b());
        return response;
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
