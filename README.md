## JWT Auth - Pacientes e Usuários

API REST desenvolvida para gerenciamento de usuários e pacientes com autenticação baseada em JWT.

##Tecnologias

- Java 17
- Spring Boot
- Spring Security
- JPA / Hibernate
- MySQL 
- Maven

## 🔐 Autenticação

A aplicação utiliza autenticação via JWT. Após o login, o token deve ser enviado no cabeçalho das requisições protegidas:

> ⚠️ **Importante:** Para que um paciente consiga realizar login e receber o token JWT, é necessário que:
> - O paciente esteja previamente cadastrado na tabela `pacientes`.
> - Seja criado um `usuario` associado ao ID do paciente (campo `pacienteId` na entidade `Usuario`).



## 📦 Como rodar o projeto

1. Clone o repositório:
git clone https://github.com/Rodrigo285/jwt-auth-pacientes-usuarois.git
  
2. Configure o banco de dados no `application.properties`:
spring.application.name=Sistema_hospitalar

spring.datasource.url= jdbc:mysql://localhost:3306/sistema_hospitalar
spring.datasource.username= root
spring.datasource.password= root

#JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql= true
spring.jpa.open-in-view= true
api.security.token.secret=${JWT_SECRET:my-secret-key}


##  Endpoints principais

| Método | Endpoint              | Acesso     | Descrição                        |
|--------|-----------------------|------------|----------------------------------|
| POST   | /registro/login       | Público    | Autenticação e geração de token  |
| POST   | /registro/cadastro    | Público    | Cadastro de usuarios             |
| CRUD   | /pacientes            | ADMIN      | Autenticação e geração de token  |
| POST   | /consultas            | PACIENTE   | Agendamento de consultas         |
| DELETE | /consultas/{id}       | PACIENTE   | Cancelamento de consultas        |


## 👤 Autor

Rodrigo Borges  
[GitHub](https://github.com/Rodrigo285)





