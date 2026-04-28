package com.example.parametrosdemo.service.strategy;

import com.example.parametrosdemo.dto.polymorphic.ParametroAResponse;
import com.example.parametrosdemo.dto.polymorphic.ParametroResponse;
import com.example.parametrosdemo.dto.polymorphic.ParametroARequest;
import com.example.parametrosdemo.dto.polymorphic.ParametroRequest;
import com.example.parametrosdemo.entity.Parametro1Entity;
import com.example.parametrosdemo.exception.BadRequestException;
import com.example.parametrosdemo.exception.NotFoundException;
import com.example.parametrosdemo.model.ParametroTipo;
import com.example.parametrosdemo.repository.Parametro1Repository;
import com.example.parametrosdemo.util.NumericResourceId;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class Parametro1Strategy implements ParametroStrategy {

    private final Parametro1Repository repository;

    public Parametro1Strategy(Parametro1Repository repository) {
        this.repository = repository;
    }

    @Override
    public ParametroTipo supports() {
        return ParametroTipo.PARAMETRO1;
    }

    @Override
    public ParametroResponse create(ParametroRequest request) {
        ParametroARequest typed = requireTipoA(request);
        validate(typed);
        Parametro1Entity entity = new Parametro1Entity();
        entity.setNombre(typed.getNombre());
        entity.setValor(typed.getValor());
        return toResponse(repository.save(entity));
    }

    @Override
    public ParametroResponse update(String resourceId, ParametroRequest request) {
        long id = NumericResourceId.parseLong(resourceId);
        ParametroARequest typed = requireTipoA(request);
        validate(typed);
        Parametro1Entity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No existe Parametro1 con id " + id));
        entity.setNombre(typed.getNombre());
        entity.setValor(typed.getValor());
        return toResponse(repository.save(entity));
    }

    @Override
    public ParametroResponse getById(String resourceId) {
        long id = NumericResourceId.parseLong(resourceId);
        return toResponse(repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No existe Parametro1 con id " + id)));
    }

    @Override
    public List<ParametroResponse> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public void delete(String resourceId) {
        long id = NumericResourceId.parseLong(resourceId);
        if (!repository.existsById(id)) {
            throw new NotFoundException("No existe Parametro1 con id " + id);
        }
        repository.deleteById(id);
    }

    private void validate(ParametroARequest request) {
        if (isBlank(request.getValor())) {
            throw new BadRequestException("El campo valor es obligatorio para PARAMETRO1");
        }
    }

    private ParametroARequest requireTipoA(ParametroRequest request) {
        if (request instanceof ParametroARequest parametroARequest) {
            return parametroARequest;
        }
        throw new BadRequestException("Se esperaba un request de tipo A para PARAMETRO1");
    }

    private ParametroResponse toResponse(Parametro1Entity entity) {
        ParametroAResponse response = new ParametroAResponse();
        response.setId(entity.getId());
        response.setNombre(entity.getNombre());
        response.setValor(entity.getValor());
        return response;
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
