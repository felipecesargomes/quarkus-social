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

## Requisitos

- **Java**
- **Maven Compiler 3.13+**
- **Quarkus 3.14.4+**

## Como Executar o Projeto

1. Clone o repositório:
    ```bash
    git clone https://github.com/seu-usuario/seu-repositorio.git
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
./mvnw test
```
