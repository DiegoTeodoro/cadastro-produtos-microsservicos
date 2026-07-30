# Cadastro de Produtos com Microsserviços

Projeto didático usando:

- Java 21
- Spring Boot
- APIs REST
- MySQL
- Maven
- Docker e Docker Compose
- JUnit 5 e Mockito
- Git
- Arquitetura de microsserviços

## Arquitetura

- `produto-service`: cadastro e manutenção dos produtos.
- `estoque-service`: controle da quantidade disponível.
- `produto-db`: banco exclusivo do serviço de produtos.
- `estoque-db`: banco exclusivo do serviço de estoque.

Cada microsserviço possui seu próprio banco de dados.

## Executar com Docker

Na raiz do projeto:

```bash
docker compose up --build
```

Serviços:

- Produto API: `http://localhost:8081/produtos`
- Estoque API: `http://localhost:8082/estoques`

## Exemplos

### Cadastrar produto

```http
POST http://localhost:8081/produtos
Content-Type: application/json

{
  "nome": "Notebook",
  "descricao": "Notebook para desenvolvimento",
  "preco": 4500.00
}
```

### Listar produtos

```http
GET http://localhost:8081/produtos
```

### Criar estoque

```http
POST http://localhost:8082/estoques
Content-Type: application/json

{
  "produtoId": 1,
  "quantidade": 10
}
```

### Consultar estoque pelo produto

```http
GET http://localhost:8082/estoques/produto/1
```

## Executar testes

```bash
cd produto-service
./mvnw test
```

No Windows:

```bash
mvnw.cmd test
```

Repita o comando dentro de `estoque-service`.

## Git

```bash
git init
git add .
git commit -m "feat: cria microsservicos de produtos e estoque"
```
