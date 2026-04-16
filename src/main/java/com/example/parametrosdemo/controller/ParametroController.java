package com.example.parametrosdemo.controller;

import com.example.parametrosdemo.dto.polymorphic.ParametroAResponse;
import com.example.parametrosdemo.dto.polymorphic.ParametroBResponse;
import com.example.parametrosdemo.dto.polymorphic.ParametroCResponse;
import com.example.parametrosdemo.dto.polymorphic.ParametroResponse;
import com.example.parametrosdemo.dto.polymorphic.ParametroARequest;
import com.example.parametrosdemo.dto.polymorphic.ParametroBRequest;
import com.example.parametrosdemo.dto.polymorphic.ParametroCRequest;
import com.example.parametrosdemo.dto.polymorphic.ParametroRequest;
import com.example.parametrosdemo.exception.BadRequestException;
import com.example.parametrosdemo.model.ParametroTipo;
import com.example.parametrosdemo.service.ParametroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parametros")
@Tag(name = "Parametros", description = "ABM de parametros por tipo")
public class ParametroController {

    private final ParametroService parametroService;

    public ParametroController(ParametroService parametroService) {
        this.parametroService = parametroService;
    }

    @GetMapping
    @Operation(summary = "Listar parametros por tipo unificado")
    @Schema(oneOf = {ParametroAResponse.class, ParametroBResponse.class, ParametroCResponse.class})
    public List<ParametroResponse> findAll(@RequestParam("tipo") String tipo) {
        return parametroService.findAll(toParametroTipo(tipo));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener parametro por id unificado")
    @Schema(oneOf = {ParametroAResponse.class, ParametroBResponse.class, ParametroCResponse.class})
    public ParametroResponse findById(@PathVariable Long id, @RequestParam("tipo") String tipo) {
        return parametroService.getById(toParametroTipo(tipo), id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Crear parametro por payload polimorfico",
            description = "Recibe tipo=A|B|C y deserializa a ParametroARequest, ParametroBRequest o ParametroCRequest")
    @Schema(oneOf = {ParametroAResponse.class, ParametroBResponse.class, ParametroCResponse.class})
    public ParametroResponse create(
            @Valid @RequestBody
            @Schema(oneOf = {ParametroARequest.class, ParametroBRequest.class, ParametroCRequest.class})
            ParametroRequest request) {
        return parametroService.create(request);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar parametro por payload polimorfico",
            description = "Recibe tipo=A|B|C y actualiza usando la estrategia correspondiente")
    @Schema(oneOf = {ParametroAResponse.class, ParametroBResponse.class, ParametroCResponse.class})
    public ParametroResponse update(
            @PathVariable Long id,
            @Valid @RequestBody
            @Schema(oneOf = {ParametroARequest.class, ParametroBRequest.class, ParametroCRequest.class})
            ParametroRequest request) {
        return parametroService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar parametro por id unificado")
    public void delete(@PathVariable Long id, @RequestParam("tipo") String tipo) {
        parametroService.delete(toParametroTipo(tipo), id);
    }

    private ParametroTipo toParametroTipo(String tipo) {
        return switch (tipo.toUpperCase()) {
            case "A" -> ParametroTipo.PARAMETRO1;
            case "B" -> ParametroTipo.PARAMETRO2;
            case "C" -> ParametroTipo.PARAMETRO3;
            default -> throw new BadRequestException("Tipo invalido. Use A, B o C");
        };
    }
}
