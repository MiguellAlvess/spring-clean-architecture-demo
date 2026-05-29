# Person Management Challenge API

API REST desenvolvida como desafio técnico utilizando Spring Boot, com foco na aplicação de boas práticas de arquitetura de software, organização de código e implementação de testes automatizados.

O projeto realiza o gerenciamento de Pessoas e Endereços, seguindo um relacionamento 1 (uma pessoa pode possuir múltiplos endereços), utilizando princípios RESTful e arquitetura baseada em Clean Architecture.

Além da implementação do CRUD completo, o principal objetivo do projeto foi praticar:

- Separação de responsabilidades;
- Organização em camadas;
- Boas práticas de desenvolvimento backend;
- Tratamento de exceções;
- Validação de dados;
- Testes de integração automatizados com Rest Assured.

## Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Bean Validation
- H2 Database
- Maven
- JUnit 5
- Rest Assured

## Funcionalidades

- Criar pessoas e endereços;
- Listar pessoas;
- Buscar pessoa por ID;
- Atualizar dados da pessoa e endereços;
- Remover pessoa e seus respectivos endereços;
- Validação de CPF único;
- Cálculo automático da idade da pessoa.

## Testes automatizados

O projeto possui testes de integração utilizando Rest Assured, cobrindo cenários de:

- Criação de pessoa;
- Atualização;
- Remoção;
- Busca;
- Validações;
- Regras de negócio;
- Tratamento de erros.

## Executando o projeto

```bash
mvn spring-boot:run
```

## Executando os testes

```bash
mvn clean test
```
