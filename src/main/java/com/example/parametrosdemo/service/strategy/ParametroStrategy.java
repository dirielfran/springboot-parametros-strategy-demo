package com.example.parametrosdemo.service.strategy;

import com.example.parametrosdemo.dto.polymorphic.ParametroResponse;
import com.example.parametrosdemo.dto.polymorphic.ParametroRequest;
import com.example.parametrosdemo.model.ParametroTipo;
import java.util.List;

public interface ParametroStrategy {

    ParametroTipo supports();

    ParametroResponse create(ParametroRequest request);

    ParametroResponse update(String resourceId, ParametroRequest request);

    ParametroResponse getById(String resourceId);

    List<ParametroResponse> findAll();

    void delete(String resourceId);
}
