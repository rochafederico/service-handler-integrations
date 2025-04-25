# API Integraciones

Este proyecto es un API Gateway desarrollado con Spring Boot que utiliza `RestTemplate` para realizar integraciones con servicios externos.

## Características

- **Integraciones configurables**: Utiliza un registro de handlers para manejar diferentes servicios.
- **Propiedades configurables**: Configuración de URLs y rutas a través de `application.properties`.

## Requisitos previos

- Java 17 o superior
- Maven 3.8 o superior

## Ejemplo de uso

Puedes probar el servicio utilizando el siguiente comando `curl`:

```bash
curl -X POST http://localhost:8080/integrador \
-H "Content-Type: application/json" \
-d '{
   "service_handler": "typicode.posts",
   "params": {
      "user_id": "1"
   }
}'
```
