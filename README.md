# Spring Boot Junior Case

Este projeto é uma API RESTful desenvolvida com Spring Boot para gerenciar livros, autores e categorias. Ele serve como um caso de estudo para demonstrar boas práticas de desenvolvimento backend, incluindo CRUD, paginação, filtragem, validação de dados e documentação OpenAPI.

## Sumário

1.  [Tecnologias Utilizadas](#1-tecnologias-utilizadas)
2.  [Como Executar o Projeto](#2-como-executar-o-projeto)
    * [Pré-requisitos](#pré-requisitos)
    * [Configuração do Ambiente](#configuração-do-ambiente)
    * [Executando a Aplicação](#executando-a-aplicação)
3.  [Documentação da API (Swagger UI)](#3-documentação-da-api-swagger-ui)
4.  [Endpoints Disponíveis](#4-endpoints-disponíveis)
    * [Autor (Author)](#autor-author)
    * [Categoria (Category)](#categoria-category)
    * [Livro (Book)](#livro-book)
5.  [Exemplos de Requisições](#5-exemplo-de-requisições)
    * [Criar Autor](#criar-autor)
    * [Listar Autores Paginados](#listar-autores-paginados)
    * [Buscar Autor por ID](#buscar-autor-por-id)
    * [Criar Categoria](#criar-categoria)
    * [Listar Categorias Paginadas](#listar-categorias-paginadas)
    * [Buscar Categoria por ID](#buscar-categoria-por-id)
    * [Criar Livro](#criar-livro)
    * [Listar Livros Paginados e Filtrados](#listar-livros-paginados-e-filtrados)
    * [Buscar Livro por ID](#buscar-livro-por-id)
    * [Listar Livros de um Autor Específico](#listar-livros-de-um-autor-específico)

---

## 1. Tecnologias Utilizadas

* **Java 17:** Linguagem de programação.
* **Spring Boot 3.5.3:** Framework para desenvolvimento rápido de APIs.
* **Spring Data JPA:** Abstração para persistência de dados.
* **H2 Database:** Banco de dados em memória (para desenvolvimento e testes).
* **Lombok:** Geração automática de boilerplate code (getters, setters, construtores).
* **ModelMapper 3.2.0:** Mapeamento entre entidades e DTOs.
* **Spring Boot Starter Validation:** Implementação de validação de beans (Jakarta Bean Validation).
* **SpringDoc OpenAPI Starter WebMVC UI 2.8.9:** Geração automática de documentação da API (Swagger UI).
* **Maven:** Gerenciamento de dependências e construção do projeto.

---

## 2. Como Executar o Projeto

### Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

* **JDK 17** ou superior.
* **Maven 3.x** ou superior.
* Um IDE de sua preferência (IntelliJ IDEA, VS Code, Eclipse, etc.).

### Configuração do Ambiente

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/guilhermeramos31/spring-boot-junior-case.git
    ```
    ```bash
        cd spring-boot-junior-case
    ```

2.  **Configuração do `application.properties`:**
    O projeto utiliza o banco de dados H2 em memória por padrão, o que significa que os dados são perdidos a cada reinício da aplicação. Os dados iniciais são populados através do script `src/main/resources/data.sql`.

    Verifique o arquivo `src/main/resources/application.properties` e certifique-se de que as seguintes configurações estão presentes para o ambiente de desenvolvimento:

    ```properties
    # Nome da aplicação Spring Boot
    spring.application.name=spring-boot-junior-case

    # H2 Console (acessível em http://localhost:8080/h2-console)
    spring.h2.console.enabled=true
    spring.h2.console.path=/h2-console
    
    # H2 Database Configuration
    spring.datasource.url=jdbc:h2:mem:digital_library;
    spring.datasource.driverClassName=org.h2.Driver
    spring.datasource.username=sa
    spring.datasource.password=password

    # JPA/Hibernate Configuration
    spring.jpa.hibernate.ddl-auto=update # Ou 'create-drop' para limpar o schema a cada restart (útil em dev)
    spring.jpa.defer-datasource-initialization=true # Essencial: Garante que o data.sql seja executado APÓS o DDL

    # Cabeçalhos Customizados para Paginação (utilizados na classe HttpHeadersUtil)
    my-app.header.page-number=X-Page-Number
    my-app.header.page-size=X-Page-Size
    my-app.header.total-elements=X-Total-Elements
    my-app.header.total-pages=X-Total-Pages
    my-app.header.is-last=X-Is-Last
    my-app.header.is-first=X-Is-First
    my-app.header.number-of-elements=X-Number-Of-Elements

    # Configurações de Logging (Opcional, para depuração)
    # logging.level.org.springframework.web=DEBUG
    # logging.level.org.hibernate.SQL=DEBUG
    
    spring.mvc.servlet.path=/api
    ```

### Executando a Aplicação

1.  **Construa o projeto com Maven:**
    Abra seu terminal na raiz do projeto e execute:
    ```bash
    mvn clean install
    ```
    Isso compilará o código, executará os testes e empacotará a aplicação.

2.  **Execute a aplicação:**
    Após a construção, você pode executar a aplicação a partir do terminal:
    ```bash
    mvn spring-boot:run
    ```
    Ou, se estiver usando um IDE, execute a classe `SpringBootJuniorCaseApplication.java` como uma aplicação Spring Boot.

A aplicação será iniciada e estará acessível em `http://localhost:8080/api`.

---

## 3. Documentação da API (Swagger UI)

Após iniciar a aplicação, você pode acessar a documentação interativa da API através do Swagger UI.

* **URL do Swagger UI:** `http://localhost:8080/api/swagger-ui.html`

---

## 4. Endpoints Disponíveis

A API expõe os seguintes endpoints sob a URL base `http://localhost:8080/api` (assumindo que você tem `authors`, `categories`, `books` mapeados diretamente.

### Autor (Author)
* `POST api/authors`: Cria um novo autor.
* `GET api/authors`: Lista todos os autores com paginação e ordenação.
* `GET api/authors/{id}`: Busca um autor por ID.
* `GET api/authors/{id}/books`: Lista os livros de um autor específico, com paginação e ordenação.
* `PUT api/authors/{id}`: Atualiza um autor existente por ID.
* `DELETE api/authors/{id}`: Exclui um autor por ID.

### Categoria (Category)
* `POST api/categories`: Cria uma nova categoria.
* `GET api/categories`: Lista todas as categorias com paginação e ordenação.
* `GET api/categories/{id}`: Busca uma categoria por ID.
* `PUT api/categories/{id}`: Atualiza uma categoria existente por ID.
* `DELETE api/categories/{id}`: Exclui uma categoria por ID.

### Livro (Book)
* `POST api/books`: Cria um novo livro.
* `GET api/books`: Lista todos os livros com paginação, ordenação e filtros por categoria, ano, autor e título (parcial).
* `GET api/books/{id}`: Busca um livro por ID.
* `GET api/books/search?title={title}`: Busca um livro por título exato.
* `PUT api/books/{id}`: Atualiza um livro existente por ID.
* `DELETE api/books/{id}`: Exclui um livro por ID.

---

## 5. Exemplos de Requisições

A base da URL para os exemplos é `http://localhost:8080/` (ajuste se necessário).

### Criar Autor
* **Endpoint:** `POST /authors`
* **Headers:**
    ```
    Content-Type: application/json
    ```
* **Body (JSON):**
    ```json
    {
      "name": "João Guimarães Rosa",
      "birthDate": "1908-06-27",
      "email": "joao.rosa@example.com"
    }
    ```

### Listar Autores Paginados
* **Endpoint:** `GET /authors?page=1&limit=5&sortBy=name&direction=ASC`
* **Headers de Resposta de Paginação (Exemplo):**
    ```
    X-Page-Number: 1
    X-Page-Size: 5
    X-Total-Elements: 7
    X-Total-Pages: 2
    X-Is-Last: false
    X-Is-First: true
    X-Number-Of-Elements: 5
    ```
* **Exemplo de Resposta (200 OK - Body):**
    ```json
    {
      "content": [
        { "id": 4, "name": "Agatha Christie", "birthDate": "1890-09-15", "email": "agatha.christie@example.com" },
        { "id": 6, "name": "Gabriel Garcia Marquez", "birthDate": "1927-03-06", "email": "gabriel.marquez@example.com" },
        { "id": 2, "name": "George R.R. Martin", "birthDate": "1948-09-20", "email": "grrm@example.com" },
        { "id": 1, "name": "J.K. Rowling", "birthDate": "1965-07-31", "email": "jk.rowling@example.com" },
        { "id": 7, "name": "Machado de Assis", "birthDate": "1839-06-21", "email": "machado.assis@example.com" }
      ],
      "page": 1,
      "limit": 5,
      "totalElements": 7,
      "totalPages": 2,
      "last": false,
      "numberOfElements": 5,
      "first": true
    }
    ```

### Buscar Autor por ID
* **Endpoint:** `GET /authors/1`
* **Exemplo de Resposta (200 OK):**
    ```json
    {
      "id": 1,
      "name": "J.K. Rowling",
      "birthDate": "1965-07-31",
      "email": "jk.rowling@example.com"
    }
    ```

### Criar Categoria
* **Endpoint:** `POST /categories`
* **Headers:**
    ```
    Content-Type: application/json
    ```
* **Body (JSON):**
    ```json
    {
      "name": "Biografia",
      "description": "Livros que narram a vida de uma pessoa."
    }
    ```

### Listar Categorias Paginadas
* **Endpoint:** `GET /categories?page=1&limit=3&sortBy=name&direction=DESC`

### Buscar Categoria por ID
* **Endpoint:** `GET /categories/1`

### Criar Livro
* **Endpoint:** `POST /books`
* **Headers:**
    ```
    Content-Type: application/json
    ```
* **Body (JSON):
    ```json
    {
      "title": "Novo Livro Incrível",
      "isbn": "9781234567890",
      "yearPublished": 2024,
      "price": 45.50,
      "author": 1,   // ID de um autor existente (ex: J.K. Rowling)
      "category": 1  // ID de uma categoria existente (ex: Fantasia)
    }
    ```

### Listar Livros Paginados e Filtrados
* **Endpoint:** `GET /books?page=1&limit=5&sortBy=title&direction=ASC&categoryName=Fantasia&yearPublished=1997&authorName=J.K.%20Rowling&title=Harry`
* **Parâmetros de Query (Query Params):**
    * `page`: Número da página (começa em 1).
    * `limit`: Quantidade de itens por página.
    * `direction`: Direção da ordenação (`ASC` ou `DESC`).
    * `categoryName`: Nome da categoria para filtrar.
    * `yearPublished`: Ano de publicação para filtrar.
    * `authorName`: Nome do autor para filtrar.
    * `title`: Parte do título para busca parcial (case-insensitive).

### Buscar Livro por ID
* **Endpoint:** `GET /books/1`
