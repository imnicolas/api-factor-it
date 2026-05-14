# Cómo levantar el proyecto en local (Windows)

Este proyecto es una aplicación Spring Boot que utiliza PostgreSQL como base de datos.

## Requisitos previos

- **Java 17**: Asegúrate de tener instalado el JDK 17.
- **PostgreSQL**: Debe estar instalado y corriendo.
- **Maven**: El proyecto incluye el wrapper `mvnw`, por lo que no es estrictamente necesario tenerlo instalado globalmente.

## Pasos para la ejecución

### 1. Configurar la Base de Datos

1. Abre tu cliente de PostgreSQL (pgAdmin, psql, etc.).
2. Crea una base de datos llamada `postgres` (o el nombre que prefieras).
   ```sql
   CREATE DATABASE postgres;
   ```
3. Conéctate a la base de datos y ejecuta el script de creación de tablas e inserts que se encuentra en `scripts/scripts.sql`.

### 2. Configurar Variables de Entorno (Opcional)

El proyecto utiliza variables de entorno con valores por defecto para conectarse a la base de datos. Si tu configuración local coincide con los valores por defecto, no necesitas configurar nada:
- `DB_HOST`: `localhost`
- `DB_PORT`: `5432`
- `DB_NAME`: `postgres`
- `DB_USER`: `postgres`
- `DB_PASSWORD`: `postgres`

> [!IMPORTANT]
> Por defecto, el proyecto intenta usar conexión SSL (`sslmode=require`). Si tu base de datos local no tiene SSL habilitado (caso común en instalaciones por defecto), debes setear la variable de entorno:
> `DB_SSL_MODE=disable`
>
> En Windows (CMD), podés hacerlo con:
> ```cmd
> set DB_SSL_MODE=disable
> ```
> O en PowerShell:
> ```powershell
> $env:DB_SSL_MODE="disable"
> ```

### 3. Ejecutar la Aplicación

Abre una terminal en la raíz del proyecto `factorIT` y ejecuta el siguiente comando:

Usando el Maven Wrapper incluido (CMD):
```cmd
mvnw.cmd spring-boot:run
```

O si usas PowerShell:
```powershell
.\mvnw.cmd spring-boot:run
```

La aplicación se levantará en `http://localhost:8080`.

## Documentación API (Swagger)

Una vez levantada la aplicación, podés acceder a la documentación de Swagger en:
`http://localhost:8080/swagger-ui.html`