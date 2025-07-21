# API REST Voll Med - Gestión de Consultas Médicas

Proyecto de API REST desarrollada en Java usando **Spring Boot 3**, orientada a la gestión de consultas médicas.  
Incluye autenticación con JWT, manejo de usuarios médicos y pacientes, y gestión de consultas.

## 🚀 Tecnologías Utilizadas
- Java 21+
- Spring Boot 3
- Spring Security (JWT Authentication)
- Spring Data JPA
- Hibernate Validator
- Maven
- MySQL

## 🛠️ Funcionalidades Principales
- ✅ Registro y autenticación de usuarios
- ✅ CRUD completo de médicos y pacientes
- ✅ CRUD completo de gestión de consultas médicas
- ✅ Seguridad basada en JWT
- ✅ Validación de datos con Bean Validation
- ✅ Manejo de excepciones personalizadas
- ✅ Documentación generada con Swagger/OpenAPI
- ✅ Test manuales y automatizados con SpringBoot Test y mockito

## 🗂️ Estructura Básica del Proyecto
<img width="395" height="278" alt="image" src="https://github.com/user-attachments/assets/c918ea86-e8e9-433e-96cc-0fea1edae98f" />

## 🗒️ Requisitos Previos
- JDK 21 o superior
- MySQL Database
- Maven 3.9+

## 🏃‍♂️ Cómo Ejecutar
1. Clonar el repositorio
2. Configurar la conexión a base de datos en `application.properties`
3. Ejecutar:
   ```bash
   ./mvnw spring-boot:run
 
## 🔒 Seguridad
Login con usuario y contraseña → Retorna JWT

JWT incluido en las peticiones a los endpoints protegidos

## 📄 Licencia
Este proyecto está bajo la licencia MIT.

## 📬 Contacto
Creado por @Jumedev [https://jumedev.netlify.app/] — [mesacruzjc@gmail.com] ¡No dudes en contactarme para feedback o colaboraciones!
