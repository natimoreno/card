# Getting started

Build application

`./gradlew build`

Run application

`./gradlew -PmainClass=CardApplication run`

- Then you have allowed: Open API to [Cards](http://localhost:8080/swagger-ui/index.html) and [consola](http://localhost:8080/h2-console)
- In the Browser configure *DBC URL* with `jdbc:h2:mem:bankdb`

## Example

Desing:
* Microservicio API Rest H2 memo
* The DB is not normalized
* Strategy Pattern:
  * abstract class CreditSegment with 3 concrete classes
  * Card Service appling lamda function with isValid method figure out the segment at the runtime

Example:
- Controlador de Cards o Segmentos - CRUD directo de la entidad Card en la DB
- Controlador Segments¡ - Patrón de diseño Strategy. En tiempo de ejecución determona la Card / Segmento.
  - AMOUNT < 2000 & PESOS : CLASSIC
  - AMOUNT > 2000 & PESOS: PLATINUM
  - AMOUNT > 3000 & DOLAR : GOL
  - Restricción, siempre debe existir 1 solo segmento o tarjeta, en cualquier otro caso el servicio falla
  - No se permite duplicar la tarjeta o segmento para un mismo usuario (name)


Design considerations:
- Se aplica Logger solo en las capas de servicios: Trouble shutting
- Dada la simplicidad no tenemos transaccionalidad, commit o rollback: Integridad de datos
- Dada la simplicidad no se implmentan DTO con el objetivo de abstraer detalles de la entidad de persistencia: Seguridad


## Card Controller
```
curl --location 'http://localhost:8080/bank/card/1'
```

```
curl --location 'http://localhost:8080/bank/card' \
--header 'Content-Type: application/json' \
--data '{
    "name": "USER-NAME",
    "segment": "classic",
    "status": "created"
}'
```

```
curl --location --request PUT 'http://localhost:8080/bank/card/1' \
--header 'Content-Type: application/json' \
--data '{
    "status": "ACTIVE"
}'
```
** status: CREATED / ACTIVE / INACTIVE

```
curl --location --request DELETE 'http://localhost:8080/bank/card/1'
```

## Segment Controller

```
curl --location 'http://localhost:8080/bank/segment' \
--header 'Content-Type: application/json' \
--data '{
    "user": {
        "id": 12346,
        "name": "user-name"
    },
    "currency": "PESOS",
    "amount": 900
}'
```

### Disclaimer: 

Realice dos tipos de implementación para el requerimietno del controlador que se llama a sí mismo (no estoy segura a que referia)

By Segment that call Card controller
```
curl --location 'http://localhost:8080/bank/segment/2'
```
By Card thar call internal method
```
curl --location 'http://localhost:8080/bank/v2/card/1'
```