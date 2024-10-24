# Hering Challenge API

API RESTful desenvolvida para o desafio técnico da Hering.

## Sumário

- [Funcionalidades](#funcionalidades)
- [Requisitos](#requisitos)
- [Configuração](#configuração)
- [Executando a Aplicação com Maven](#executando-a-aplicação)
- [Acesso à Documentação](#acesso-à-documentação)
- [Acesso ao Banco de Dados](#acesso-ao-banco-de-dados)
    - [Credenciais](#credenciais)

## Funcionalidades

#### Obras: 

- Criar obra
- Buscar obra por ID
- Atualizar obra por ID
- Deletar obra por ID
- Listar todas as obras
- Listar todos os autores de uma obra

#### Autores:

- Criar autor
- Buscar autor por ID
- Deletar autor por ID
- Listar todos os autores
- Listar todas as obras de um autor
- Atualizar autor por ID

#### Outros:
- Reverter número
- Verificar se uma palavra é palíndromo
- Buscar país por termo


## Requisitos

- JDK 21
- Maven 3.6+

## Configuração

**Instalação do JDK, Maven e Docker:**

- [Instruções para instalação do JDK](https://docs.oracle.com/en/java/javase/21/install/overview-jdk-installation.html)
- [Instruções para instalação do Maven](https://maven.apache.org/install.html)

### Executando a Aplicação

```bash
mvn spring-boot:run
```

### Acesso à Documentação

#### Postman (preferência)

Ambas as collections estão no diretório `collections`:
[collections](collections)

Importe ambas as collections para o Postman e teste os serviços

#### OpenAPI

- **Core OpenApi UI:** [http://localhost:8080](http://localhost:8080)

## Acesso ao Banco de Dados

Para acessar o banco de dados H2 utilizado pelo projeto, utilize a seguintes URL:

- **Console H2 (local):** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

|                           **URL**                           | **Username** | **Password** |
|:-----------------------------------------------------------:|:------------:|:------------:|
| `jdbc:h2:mem:hrg_db;DB_CLOSE_ON_EXIT=FALSE;MODE=PostgreSQL` |   `hrg_db`   |   `hrg_db`   |
