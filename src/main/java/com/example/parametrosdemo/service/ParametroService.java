package com.example.parametrosdemo.service;

import com.example.parametrosdemo.dto.polymorphic.ParametroResponse;
import com.example.parametrosdemo.dto.polymorphic.ParametroRequest;
import com.example.parametrosdemo.exception.BadRequestException;
import com.example.parametrosdemo.model.ParametroTipo;
import com.example.parametrosdemo.service.strategy.ParametroStrategy;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ParametroService {

    private final ParametroStrategyResolver strategyResolver;

    public ParametroService(ParametroStrategyResolver strategyResolver) {
        this.strategyResolver = strategyResolver;
    }

    public ParametroResponse create(ParametroRequest request) {
        return getStrategy(request.tipo()).create(request);
    }

    public ParametroResponse update(String resourceId, ParametroRequest request) {
        return getStrategy(request.tipo()).update(resourceId, request);
    }

    public ParametroResponse getById(ParametroTipo tipo, String resourceId) {
        return getStrategy(tipo).getById(resourceId);
    }

    public List<ParametroResponse> findAll(ParametroTipo tipo) {
        return getStrategy(tipo).findAll();
    }

    public void delete(ParametroTipo tipo, String resourceId) {
        getStrategy(tipo).delete(resourceId);
    }

    private ParametroStrategy getStrategy(ParametroTipo tipo) {
        ParametroStrategy strategy = strategyResolver.resolve(tipo);
        if (strategy == null) {
            throw new BadRequestException("No existe una estrategia registrada para el tipo " + tipo);
        }
        return strategy;
    }
}
