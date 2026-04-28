package com.example.parametrosdemo.controller;

import com.example.parametrosdemo.dto.polymorphic.ParametroAResponse;
import com.example.parametrosdemo.dto.polymorphic.ParametroBResponse;
import com.example.parametrosdemo.dto.polymorphic.ParametroActividadResponse;
import com.example.parametrosdemo.dto.polymorphic.ParametroCResponse;
import com.example.parametrosdemo.dto.polymorphic.ParametroResponse;
import com.example.parametrosdemo.dto.polymorphic.ParametroARequest;
import com.example.parametrosdemo.dto.polymorphic.ParametroBRequest;
import com.example.parametrosdemo.dto.polymorphic.ParametroActividadRequest;
import com.example.parametrosdemo.dto.polymorphic.ParametroCRequest;
import com.example.parametrosdemo.dto.polymorphic.ParametroRequest;
import com.example.parametrosdemo.exception.BadRequestException;
import com.example.parametrosdemo.model.ParametroTipo;
import com.example.parametrosdemo.service.ParametroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    @Schema(oneOf = {ParametroAResponse.class, ParametroBResponse.class, ParametroCResponse.class, ParametroActividadResponse.class})
    public List<ParametroResponse> findAll(@RequestParam("tipo") String tipo) {
        return parametroService.findAll(toParametroTipo(tipo));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener parametro por id unificado (ACTIVIDAD: id compuesto <configId>@@<codigo>, URL-encoded)")
    @Schema(oneOf = {ParametroAResponse.class, ParametroBResponse.class, ParametroCResponse.class, ParametroActividadResponse.class})
    public ParametroResponse findById(@PathVariable String id, @RequestParam("tipo") String tipo) {
        return parametroService.getById(toParametroTipo(tipo), id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Crear parametro por payload polimorfico",
            description = "Recibe tipo=A|B|C|ACTIVIDAD y deserializa al request correspondiente",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Incluye siempre la propiedad discriminadora \"tipo\" (A, B, C o ACTIVIDAD).",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema =
                                    @Schema(
                                            oneOf = {
                                                ParametroARequest.class,
                                                ParametroBRequest.class,
                                                ParametroCRequest.class,
                                                ParametroActividadRequest.class
                                            }),
                            examples = {
                                @ExampleObject(
                                        name = "Tipo A",
                                        summary = "PARAMETRO1",
                                        value =
                                                "{\"tipo\":\"A\",\"nombre\":\"Pais\",\"valor\":\"Argentina\"}"),
                                @ExampleObject(
                                        name = "Tipo B",
                                        summary = "PARAMETRO2",
                                        value =
                                                "{\"tipo\":\"B\",\"nombre\":\"Sucursal\",\"valor\":\"CENTRAL\",\"campo1b\":\"Habilitada\",\"campo2b\":\"Interior\"}"),
                                @ExampleObject(
                                        name = "Tipo C",
                                        summary = "PARAMETRO3",
                                        value =
                                                "{\"tipo\":\"C\",\"nombre\":\"Perfil\",\"campo1c\":\"ADMIN\",\"campo2c\":\"SEGURIDAD\",\"campo3c\":\"ALTO\"}"),
                                @ExampleObject(
                                        name = "ACTIVIDAD",
                                        summary = "PARAMETRO_ACTIVIDAD",
                                        value =
                                                "{\"tipo\":\"ACTIVIDAD\",\"nombre\":\"Actividad comercial\",\"configId\":1,\"codigo\":\"ACT-01\",\"tipoPersona\":\"FISICA\",\"tipoParametro\":\"TEXTO\",\"tooltipMessage\":\"Ayuda\",\"visible\":true,\"validarOficialQr\":false,\"templateOfertaId\":null}")
                            })))
    @Schema(oneOf = {ParametroAResponse.class, ParametroBResponse.class, ParametroCResponse.class, ParametroActividadResponse.class})
    public ParametroResponse create(@Valid @RequestBody ParametroRequest request) {
        return parametroService.create(request);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar parametro por payload polimorfico",
            description = "Recibe tipo=A|B|C|ACTIVIDAD y actualiza usando la estrategia correspondiente",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Incluye siempre la propiedad discriminadora \"tipo\" (A, B, C o ACTIVIDAD).",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema =
                                    @Schema(
                                            oneOf = {
                                                ParametroARequest.class,
                                                ParametroBRequest.class,
                                                ParametroCRequest.class,
                                                ParametroActividadRequest.class
                                            }),
                            examples = {
                                @ExampleObject(
                                        name = "Tipo A",
                                        summary = "PARAMETRO1",
                                        value =
                                                "{\"tipo\":\"A\",\"nombre\":\"Pais\",\"valor\":\"Argentina\"}"),
                                @ExampleObject(
                                        name = "Tipo B",
                                        summary = "PARAMETRO2",
                                        value =
                                                "{\"tipo\":\"B\",\"nombre\":\"Sucursal\",\"valor\":\"CENTRAL\",\"campo1b\":\"Habilitada\",\"campo2b\":\"Interior\"}"),
                                @ExampleObject(
                                        name = "Tipo C",
                                        summary = "PARAMETRO3",
                                        value =
                                                "{\"tipo\":\"C\",\"nombre\":\"Perfil\",\"campo1c\":\"ADMIN\",\"campo2c\":\"SEGURIDAD\",\"campo3c\":\"ALTO\"}"),
                                @ExampleObject(
                                        name = "ACTIVIDAD",
                                        summary = "PARAMETRO_ACTIVIDAD",
                                        value =
                                                "{\"tipo\":\"ACTIVIDAD\",\"nombre\":\"Actividad comercial\",\"configId\":1,\"codigo\":\"ACT-01\",\"tipoPersona\":\"FISICA\",\"tipoParametro\":\"TEXTO\",\"tooltipMessage\":\"Ayuda\",\"visible\":true,\"validarOficialQr\":false,\"templateOfertaId\":null}")
                            })))
    @Schema(oneOf = {ParametroAResponse.class, ParametroBResponse.class, ParametroCResponse.class, ParametroActividadResponse.class})
    public ParametroResponse update(
            @PathVariable String id,
            @Valid @RequestBody ParametroRequest request) {
        return parametroService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar parametro por id unificado (ACTIVIDAD: id compuesto)")
    public void delete(@PathVariable String id, @RequestParam("tipo") String tipo) {
        parametroService.delete(toParametroTipo(tipo), id);
    }

    private ParametroTipo toParametroTipo(String tipo) {
        return switch (tipo.toUpperCase()) {
            case "A" -> ParametroTipo.PARAMETRO1;
            case "B" -> ParametroTipo.PARAMETRO2;
            case "C" -> ParametroTipo.PARAMETRO3;
            case "ACTIVIDAD" -> ParametroTipo.PARAMETRO_ACTIVIDAD;
            default -> throw new BadRequestException("Tipo invalido. Use A, B, C o ACTIVIDAD");
        };
    }
}
