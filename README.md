# PruebaTecSupermercado

[![Java CI](https://github.com/Selcky/PruebaTecnica/actions/workflows/maven.yml/badge.svg)](https://github.com/Selcky/PruebaTecnica/actions/workflows/maven.yml)

Proyecto Spring Boot de prueba técnica para gestión de productos, sucursales y ventas.

Cómo ejecutar localmente:

- Requisitos: JDK 17+, Maven wrapper incluido
- Compilar:

```
.\mvnw.cmd -DskipTests=true package
```

- Ejecutar (puerto por defecto 8080):

```
.\mvnw.cmd spring-boot:run
```

Pruebas:

```
.\mvnw.cmd test
```

API - ejemplos de endpoints

Nota: ajusta el puerto si cambias `server.port`.

- Listar productos (GET):

```
curl -s http://localhost:8080/api/productos
```

- Crear producto (POST):

```
curl -X POST -H "Content-Type: application/json" -d '{"nombre":"Leche","categoria":"Lacteos","precio":2.5,"cantidad":10}' http://localhost:8080/api/productos
```

- Listar sucursales (GET):

```
curl -s http://localhost:8080/api/sucursales
```

- Crear venta (POST): ejemplo mínimo (ajusta ids y campos):

```
curl -X POST -H "Content-Type: application/json" -d '{"idSucursal":1,"detalle":[{"nombreProd":"Leche","cantProd":2,"precio":2.5}]}' http://localhost:8080/api/ventas
```

Contribuciones

Si quieres mejorar el proyecto, crea un fork, haz cambios y abre un Pull Request.
