![Quarkus](https://img.shields.io/badge/Quarkus-4695EB?style=for-the-badge&logo=quarkus&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white)
![JUnit5](https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

# Quarkus Rede Social API Project

Este é um projeto de API desenvolvido com Quarkus, utilizando o Panache ORM para interações com o banco de dados, JUnit 5 para testes e Maven como ferramenta de build.

<div align="center">
    <img src="https://github.com/user-attachments/assets/f78cb769-fcba-4808-a007-e0a045c09f5d" alt="image" />
</div>

## Tecnologias Utilizadas

- **[Quarkus](https://quarkus.io/):** Framework para desenvolvimento de microservices e aplicações nativas em nuvem.
- **[Panache ORM](https://quarkus.io/guides/hibernate-orm-panache):** Extensão do Hibernate ORM, simplificando o uso de ORM com Quarkus.
- **[JUnit 5](https://junit.org/junit5/):** Framework de testes para Java, utilizado para escrever e executar testes unitários.
- **[Maven](https://maven.apache.org/):** Ferramenta de build e gerenciamento de dependências.

## Recursos da API

- **/users**
  - `POST` (consumes: `application/json`, produces: `application/json`)
  - `GET` (consumes: `application/json`, produces: `application/json`)
  
- **/users/{id}**
  - `PUT` (consumes: `application/json`, produces: `application/json`)
  - `DELETE` (consumes: `application/json`, produces: `application/json`)
  
- **/users/{userId}/followers**
  - `PUT` (consumes: `application/json`, produces: `application/json`)
  - `DELETE` (consumes: `application/json`, produces: `application/json`)
  - `GET` (consumes: `application/json`, produces: `application/json`)
  
- **/users/{userId}/posts**
  - `GET` (consumes: `application/json`, produces: `application/json`)
  - `POST` (consumes: `application/json`, produces: `application/json`)

- **/hello**
  - `GET` (produces: `text/plain`)

## Bibliotecas Utilizadas

- **[ArC](https://quarkus.io/guides/cdi):** Suporte ao Contextos e Injeção de Dependências (CDI) no Quarkus.
- **[Hibernate Validator](https://quarkus.io/guides/hibernate-validator):** Validação de dados utilizando as anotações do Hibernate Validator.
- **[Hibernate ORM with Panache](https://quarkus.io/guides/hibernate-orm-panache):** Facilita o uso de Hibernate ORM com Quarkus.
- **[Hibernate ORM](https://quarkus.io/guides/hibernate-orm):** ORM robusto e flexível para mapeamento de objetos em banco de dados relacionais.
- **[JDBC Driver - H2](https://quarkus.io/guides/datasource):** Driver JDBC para o banco de dados H2, utilizado em testes.
- **[RESTEasy Classic JSON-B](https://quarkus.io/guides/rest-json):** Suporte à serialização/deserialização JSON com JSON-B.
- **[RESTEasy Classic Jackson](https://quarkus.io/guides/rest-json):** Integração com a biblioteca Jackson para trabalhar com JSON.
- **[JDBC Driver - PostgreSQL](https://quarkus.io/guides/datasource):** Driver JDBC para conexão com banco de dados PostgreSQL.

## Requisitos

- **Java 22**
- **Maven Compiler 3.13+**
- **Quarkus 3.14.4+**

## Como Executar o Projeto

1. Clone o repositório:
    ```bash
    git clone https://github.com/felipecesargomes/quarkus-social.git
    ```

2. Navegue até o diretório do projeto:
    ```bash
    cd seu-repositorio
    ```

3. Execute o projeto em modo de desenvolvimento:
    ```bash
    ./mvnw compile quarkus:dev
    ```

    O projeto estará disponível em: `http://localhost:8080`

## Testes

Para rodar os testes, execute:
```bash
./mvnw test -Dquarkus.profile=test -X
```
