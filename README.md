Card Example

Open API to [Cards](http://localhost:8080/swagger-ui/index.html)

Objetivo:
* Creación de un microservicio API Rest con un CRUD para una entidad de Dummy (Card). Persistencia con una DB en memoria.

Consideraciones en el Diseño:
* Se aplica Logger solo en la capa de servicios: trouble shutting
* Dada la simplicidad no tenemos transaccionalidad, commit o rollback: integridad de datos
* Dada la simplicidad no se implmentan DTO con el objetivo de abstraer detalles de la entidad de persistencia: seguridad
* Cuando inicia genera un registro chequeo de la DB y acceso a la [consola](http://localhost:8080/h2-console): en el campo JDBC URL configurar `jdbc:h2:mem:bankdb`
