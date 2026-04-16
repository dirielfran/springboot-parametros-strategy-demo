package com.example.parametrosdemo.service;

import com.example.parametrosdemo.model.ParametroTipo;
import com.example.parametrosdemo.service.strategy.ParametroStrategy;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class ParametroStrategyResolver {

    private final Map<ParametroTipo, ParametroStrategy> strategyMap = new EnumMap<>(ParametroTipo.class);

    public ParametroStrategyResolver(List<ParametroStrategy> strategies) {
        for (ParametroStrategy strategy : strategies) {
            strategyMap.put(strategy.supports(), strategy);
        }
    }

    public ParametroStrategy resolve(ParametroTipo tipo) {
        return strategyMap.get(tipo);
    }
}
