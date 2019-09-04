
# Prerrequisitos

* Java
    * Instalar [jdk-8u201](https://www.oracle.com/technetwork/java/javase/downloads/java-archive-javase8-2177648.html)

* IDE
    * [Spring Tool Suit 4](https://spring.io/tools)

* Lombok 
    * [Descargar](https://projectlombok.org/download) la herramienta [Lombok](https://projectlombok.org)  
    * [Configurar](https://projectlombok.org/setup/eclipse) Lombok en Spring Tool 4

    Se utiliza esta librería para evitar la inclusión de los métodos getter y setter en las entidades. Es necesario descargar e instalar el “plugin” en el IDE.


# Instalación & Configuración

##  Spring Tool Suit

En STS importar los siguientes proyectos maven:

* **authorization-server:**
Servidor de autenticación y autorización oauth2.

* **secured-spring-api-rest:**
API Rest Securizado.

* **spring-api-rest:**
API Rest.

Iniciar todos los proyectos como Spring Boot App.

## Testing

* Instalar [Postman](https://www.getpostman.com/)

Se dispone de colecciones para la prueba de los servicios en las rutas 
*/src/test/postman/* de cada proyecto.


