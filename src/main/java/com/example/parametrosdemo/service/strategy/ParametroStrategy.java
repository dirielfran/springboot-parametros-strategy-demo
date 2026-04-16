package com.example.parametrosdemo.service.strategy;

import com.example.parametrosdemo.dto.polymorphic.ParametroResponse;
import com.example.parametrosdemo.dto.polymorphic.ParametroRequest;
import com.example.parametrosdemo.model.ParametroTipo;
import java.util.List;

public interface ParametroStrategy {

    ParametroTipo supports();

    ParametroResponse create(ParametroRequest request);

    ParametroResponse update(Long id, ParametroRequest request);

    ParametroResponse getById(Long id);

    List<ParametroResponse> findAll();

    void delete(Long id);
}
