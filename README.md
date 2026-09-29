# ⚙️ ZITAPP-API — API REST de agenda de citas

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

Backend de **Zitapp**, plataforma para agendar citas en negocios. Expone una **API REST documentada con Swagger** y notificaciones en **tiempo real por WebSockets**.

🔗 **Frontend (React):** [Zitapp](https://github.com/jovapg/Zitapp)

---

## ✨ Funcionalidades

- Registro e inicio de sesión de usuarios
- CRUD de negocios, servicios y disponibilidad horaria
- Creación, edición y cambio de estado de citas
- Notificaciones en tiempo real a clientes y negocios (WebSockets)
- Documentación interactiva de endpoints con **Swagger / OpenAPI**

## 🏗️ Arquitectura por capas

```
src/main/java/com/example/Zitapp/
├── Controladores/   # Endpoints REST
├── Servicios/       # Lógica de negocio
├── Repositorios/    # Acceso a datos (Spring Data JPA)
├── Modelos/         # Entidades JPA
├── DTO/             # Objetos de transferencia
├── Mappers/         # Conversión entidad ↔ DTO (MapStruct)
└── Configuracion/   # CORS, Swagger, Jackson
```

## 🛠️ Tecnologías

Java · Spring Boot · Spring Web · Spring Data JPA / Hibernate · MySQL · WebSockets · MapStruct · Lombok · Springdoc OpenAPI · Maven

## 🚀 Instalación local

1. Crea la base de datos en MySQL e importa `zitapp.sql`.
2. Configura usuario y contraseña de MySQL en `src/main/resources/application.properties`.
3. Ejecuta:

```bash
./mvnw spring-boot:run
```

- API: `http://localhost:8081`
- Swagger: `http://localhost:8081/swagger-ui.html`

---

👤 Desarrollado por **Jovany Posada** · [GitHub](https://github.com/jovapg)
