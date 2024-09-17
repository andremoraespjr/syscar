# API de Cadastro de Veículos

Este projeto é uma API RESTful desenvolvida com Spring Boot que permite o cadastro, atualização, exclusão e consulta de veículos. A API também fornece relatórios sobre os veículos cadastrados.

## Endpoints da API

### 1. **Listar Todos os Veículos**

- **Método:** GET
- **Endpoint:** `/veiculos`
- **Descrição:** Retorna uma lista de todos os veículos cadastrados.

**Exemplo de cURL:**
```bash
curl -X GET "http://localhost:8080/veiculos" -H "accept: application/json"
```

### 2. **Obter Detalhes de um Veículo por ID**
- **Método:** GET
- **Endpoint:** `/veiculos/{id}`
- **Descrição:** Retorna os detalhes de um veículo específico pelo ID.

**Exemplo de cURL:**
```bash
curl -X GET "http://localhost:8080/veiculos/1" -H "accept: application/json"
```

### 3. **Criar um Novo Veículo**
- **Método:** POST
- **Endpoint:** `/veiculos`
- **Descrição:** Cadastra um novo veículo na base de dados.

**Exemplo de cURL:**
```bash
curl -X POST "http://localhost:8080/veiculos" \
     -H "Content-Type: application/json" \
     -d '{
           "veiculo": "Fusca",
           "marca": "Volkswagen",
           "ano": 1976,
           "descricao": "Carro clássico",
           "vendido": false,
           "created": "2024-09-16T12:00:00",
           "updated": "2024-09-16T12:00:00"
         }'
```

### 4. **Atualizar um Veículo Existente**
- **Método:** PUT
- **Endpoint:** `/veiculos/{id}`
- **Descrição:** Atualiza as informações de um veículo específico pelo ID.

**Exemplo de cURL:**
```bash
curl -X PUT "http://localhost:8080/veiculos/1" \
     -H "Content-Type: application/json" \
     -d '{
           "veiculo": "Fusca",
           "marca": "Volkswagen",
           "ano": 1976,
           "descricao": "Carro clássico atualizado",
           "vendido": true,
           "created": "2024-09-16T12:00:00",
           "updated": "2024-09-16T12:00:00"
         }'

```

### 5. **Excluir um Veículo**
- **Método:** DELETE
- **Endpoint:** `/veiculos/{id}`
- **Descrição:** Exclui um veículo da base de dados pelo ID.

**Exemplo de cURL:**
```bash
curl -X DELETE "http://localhost:8080/veiculos/1" -H "accept: application/json"
```

### 6. **Contar Veículos Não Vendidos**
- **Método:** GET
- **Endpoint:** `/veiculos/nao-vendidos`
- **Descrição:** Retorna a quantidade de veículos que não foram vendidos.

**Exemplo de cURL:**
```bash
curl -X GET "http://localhost:8080/veiculos/nao-vendidos" -H "accept: application/json"
```

### 7. **Contar Veículos por Marca**
- **Método:** GET
- **Endpoint:** `/veiculos/por-marca`
- **Descrição:** Retorna a quantidade de veículos por marca.
- **Parâmetro:** marca (ex: Volkswagen)

**Exemplo de cURL:**
```bash
curl -X GET "http://localhost:8080/veiculos/por-marca?marca=Volkswagen" -H "accept: application/json"
```

### 8. **Obter Veículos Registrados em um Intervalo de Datas**
- **Método:** GET
- **Endpoint:** `/veiculos/por-data`
- **Descrição:** Retorna uma lista de veículos registrados entre duas datas.
- **Parâmetros:**
**startDate** (ex: 2024-09-01)
**endDate** (ex: 2024-09-30)

**Exemplo de cURL:**
```bash
curl -X GET "http://localhost:8080/veiculos/por-data?startDate=2024-09-01&endDate=2024-09-30" -H "accept: application/json"
```


## Documentação Swagger
**Para acessar a documentação Swagger da API:**

**Inicie a Aplicação:** Certifique-se de que sua aplicação Spring Boot está em execução.
**Abra o Navegador:** Acesse a URL:
```bash
http://localhost:8080/swagger-ui.html
```
Aqui você encontrará a interface Swagger que permite explorar e testar os endpoints da API de forma interativa.


## Como Executar a Aplicação

### **Clone o Repositório:**
```bash
git clone https://github.com/username/repository.git
```

### **Compile e Execute a Aplicação:**
```bash
mvn clean install
mvn spring-boot:run
```



