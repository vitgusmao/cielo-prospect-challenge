# Cielo Prospect Challenge

Este repositório contém dois projetos separados: um projeto Spring Boot em Java 17 na pasta `prospect` e um projeto web usando Node.js v18.18.0 na pasta `prospect-web`.

# Configuração de ambiente

## Configuração para o Projeto Spring Boot (prospect)

### Pré-requisitos

- [Java Development Kit (JDK) 17](https://openjdk.java.net/projects/jdk/17/)
- [Maven](https://maven.apache.org/)

### Passos de Configuração

1. Clone este repositório

2. Navegue até a pasta `prospect`:

   ```bash
   cd prospect
   ```

3. Defina o .env:
   copie o conteúdo do `sample.env` par aum arquivo `.env` e altere de acordo com suas necessidades

4. Compile o projeto e empacote-o com o Maven:

   ```bash
   mvn clean install
   ```

5. Execute o aplicativo Spring Boot:

   ```bash
   mvn spring-boot:run
   ```

   O aplicativo estará acessível em http://localhost:8080.

## Configuração para o Projeto Web (prospect-web)

### Pré-requisitos

- [Node.js v18.18.0](https://nodejs.org/)

### Passos de Configuração

1. Navegue até a pasta `prospect-web`:

   ```bash
   cd prospect-web
   ```

2. Defina o .env:
   copie o conteúdo do `sample.env` par aum arquivo `.env` e altere de acordo com suas necessidades

3. Instale as dependências do projeto Node.js:

   ```bash
   npm install
   ```

4. Inicie o servidor de desenvolvimento:

   ```bash
   npm run dev
   ```

   O aplicativo web estará acessível na porta definida no arquivo `prospect-web/vite.config.ts`.

### Atenção

Se atentar às origens permitidas pela política do cors e a url sendo utilizada para acessar a web, o cors identifica de form diferente `localhost` e `127.0.0.1`

## Licença

Este projeto não possui uma licença para uso livre.
