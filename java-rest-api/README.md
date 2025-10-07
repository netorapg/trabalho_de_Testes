# Java REST API

Este projeto é uma API REST simples desenvolvida em Java utilizando Spring Boot. A API gerencia usuários e fornece operações básicas de CRUD (Criar, Ler, Atualizar e Deletar).

## Estrutura do Projeto

O projeto possui a seguinte estrutura de diretórios:

```
java-rest-api
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── api
│   │   │               ├── Application.java
│   │   │               ├── controller
│   │   │               │   └── UserController.java
│   │   │               ├── model
│   │   │               │   └── User.java
│   │   │               ├── service
│   │   │               │   └── UserService.java
│   │   │               └── repository
│   │   │                   └── UserRepository.java
│   │   └── resources
│   │       └── application.properties
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── api
│                       ├── controller
│                       │   └── UserControllerTest.java
│                       └── service
│                           └── UserServiceTest.java
├── pom.xml
└── README.md
```

## Dependências

O projeto utiliza Maven como gerenciador de dependências. As principais dependências incluem:

- Spring Boot
- Spring Data JPA
- H2 Database (para testes)
- JUnit
- Mockito

## Como Executar

1. Clone o repositório:
   ```
   git clone <URL_DO_REPOSITORIO>
   ```

2. Navegue até o diretório do projeto:
   ```
   cd java-rest-api
   ```

3. Execute o projeto utilizando Maven:
   ```
   mvn spring-boot:run
   ```

A aplicação estará disponível em `http://localhost:8080`.

## Endpoints da API

A API possui os seguintes endpoints para manipulação de usuários:

- `GET /users`: Retorna todos os usuários.
- `GET /users/{id}`: Retorna um usuário específico pelo ID.
- `POST /users`: Cria um novo usuário.
- `PUT /users/{id}`: Atualiza um usuário existente.
- `DELETE /users/{id}`: Deleta um usuário pelo ID.

## Testes

Os testes unitários estão localizados na pasta `src/test/java/com/example/api`. Utilize o seguinte comando para executar os testes:

```
mvn test
```

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou pull requests.