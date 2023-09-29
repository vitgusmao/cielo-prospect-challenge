# Cielo Prospect Challenge

Este repositório contém dois projetos separados: um projeto Spring Boot em Java 17 na pasta `prospect` e um projeto web usando Node.js v18.18.0 na pasta `prospect-web`.

# Configuração de ambiente

## Configuração para o Projeto Spring Boot (prospect)

### Pré-requisitos

- [Java Development Kit (JDK) 17](https://openjdk.java.net/projects/jdk/17/)
- [Maven](https://maven.apache.org/)

### Passos de Configuração

1. Clone este repositório:

   ```bash
   git clone https://github.com/seu-usuario/repo.git
   ```

2. Navegue até a pasta `prospect`:

   ```bash
   cd prospect
   ```

3. Compile o projeto e empacote-o com o Maven:

   ```bash
   mvn clean install
   ```

4. Execute o aplicativo Spring Boot:

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

2. Instale as dependências do projeto Node.js:

   ```bash
   npm install
   ```

3. Inicie o servidor de desenvolvimento:

   ```bash
   npm start
   ```

   O aplicativo web estará acessível em http://localhost:3000.

## Licença

Este projeto não possui uma licença para uso livre.
