# Tickets API

API para gestionar tickets via API REST Endpoints / GraphQL Endpoint

## Prerequsitos
- Java 17
- Gradle
- Docker

## Ejecución 
Desde el directorio raíz del proyecto :<br/>
`./gradlew build` <br/>
`./gradlew bootRun`

### Colección REST
* `src/main/resources/[Tickets REST].postman_collection.json`

### GraphQL
Acceder a http://localhost:8080/graphiql?path=/graphql

####  Ejemplo de Query
* `src/main/resources/graphql/sampleGraphQLQuery`

#### Ejemplos de Mutation
* `src/main/resources/graphql/sampleGraphQLMutation`