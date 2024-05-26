# reto-global-hitss

Crear un solo servicio REST POST que inserte 1 producto el cual será enviado en request y como response muestre una lista de los productos registrados del día [YYYYMMDD].
## Contenido

- [Instalación](#instalación)
- [Configuración](#configuración)
- [Pruebas](#pruebas)
- [Autor](#Autor)
## Instalación

Descarga el proyecto y ejecutele el el IDE de su preferencia.
```bash
git clone https://github.com/alexDelacruz/reto-global-hitss.git
```
## Configuración de la base de datos
### Script
Ejecuta el script.sql que se encuentra dentro de la carpeta recursos en su base de datos
### Cadena de conexión
Actualize el archivo properties con los datos de conexión
```bash
jdbc.url=jdbc:oracle:thin:@localhost:1521:bdhitss
jdbc.username=admin
jdbc.password=@Admin24
```

## Pruebas 
### Con swagger
En el navegador ingresa a la ruta del swagger
```bash
http://localhost:8100/swagger-ui/index.html
```
### Junit
En el paquete test se encuentra las pruebas unitarias con Junit, clase ProjectApplicationTests
## Autor
Alex De la Cruz Guillermo