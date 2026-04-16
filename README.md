# Spring Boot Parametros Strategy Demo

Ejemplo de API REST con Spring Boot para un ABM de parametros donde cada tipo persiste en una tabla distinta:

- `parametro1`: `id`, `nombre`, `valor`
- `parametro2`: `id`, `nombre`, `valor`, `campo1b`, `campo2b`
- `parametro3`: `id`, `nombre`, `campo1c`, `campo2c`, `campo3c`

## Patron aplicado

El ejemplo usa:

- `Strategy` para encapsular la logica de cada tipo de parametro
- `Resolver/Factory` para seleccionar la estrategia correcta segun el tipo del payload
- `Facade Service` para exponer un servicio comun al controlador
- `@JsonTypeInfo/@JsonSubTypes` para deserializacion polimorfica de requests

## Estructura principal

- `controller`: consultas por tipo y ABM de escritura/actualizacion polimorfico
- `service.strategy`: implementaciones por tipo
- `repository`: repositorios separados por tabla
- `entity`: entidades JPA separadas
- `dto`: request polimorfico (`ParametroRequest` + subtipos) y responses por tipo para consultas

## Endpoints

Base URL: `http://localhost:8080/api/parametros`

### ABM polimorfico (creacion/actualizacion)

- `POST /`
- `PUT /{id}`

Request base:

```json
{
  "tipo": "A | B | C",
  "nombre": "..."
}
```

Ejemplo `POST /api/parametros` tipo A:

```json
{
  "tipo": "A",
  "nombre": "Pais",
  "valor": "Argentina"
}
```

Ejemplo `POST /api/parametros` tipo B:

```json
{
  "tipo": "B",
  "nombre": "Sucursal",
  "valor": "CENTRAL",
  "campo1b": "Habilitada",
  "campo2b": "Interior"
}
```

Ejemplo `POST /api/parametros` tipo C:

```json
{
  "tipo": "C",
  "nombre": "Perfil",
  "campo1c": "ADMIN",
  "campo2c": "SEGURIDAD",
  "campo3c": "ALTO"
}
```

### Consultas y baja unificadas

- `GET /?tipo=A|B|C`
- `GET /{id}?tipo=A|B|C`
- `DELETE /{id}?tipo=A|B|C`

Ejemplo listado tipo B:

`GET /api/parametros?tipo=B`

Ejemplo detalle tipo C:

`GET /api/parametros/1?tipo=C`

Ejemplo baja tipo A:

`DELETE /api/parametros/1?tipo=A`

## Ejecutar

```bash
mvn spring-boot:run
```

## Base H2

- Consola: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:parametrosdb`
- User: `sa`
- Password: vacio

## Swagger / OpenAPI

- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## Coleccion Postman

- Archivo listo para importar: `postman/springboot-parametros-strategy-demo.postman_collection.json`
- Variable incluida: `baseUrl = http://localhost:8080`

## Observacion de diseno

La API publica acepta un request polimorfico con `tipo` (`A/B/C`). Internamente, se mantiene `Strategy` para ejecutar la logica especifica por cada tabla.
