## Estructura del Proyecto

Este proyecto está organizado en diferentes capas y componentes, cada uno de los cuales cumple un rol específico dentro del sistema de control de asistencia a eventos. A continuación, se describe cómo se relacionan las tecnologías utilizadas.

### Capa de Presentación (Front-End)
- **Angular (u otro framework front-end)**
  - **Descripción**: La interfaz de usuario que permite a los asistentes registrarse, ver eventos, y más. Este componente se comunica con el back-end a través de APIs REST.

### Capa de Aplicación (Back-End)
- **Spring Boot**
  - **Descripción**: El núcleo de la aplicación back-end. Aquí se gestionan las rutas, servicios, y controladores que manejan las peticiones y respuestas. Spring Boot se encarga de la configuración automática y la gestión de dependencias.

- **MapStruct**
  - **Descripción**: Se utiliza para mapear entre DTOs y entidades JPA. Este componente está en la capa de servicio y es fundamental para la transformación de datos.

- **Lombok**
  - **Descripción**: Integra de forma transversal para reducir el código repetitivo en las entidades, servicios, y otros componentes de la capa de aplicación.

### Capa de Persistencia (Data Layer)
- **JPA (Java Persistence API)**
  - **Descripción**: Proporciona la interfaz para interactuar con la base de datos relacional. Aquí es donde se definen las entidades que representan las tablas de la base de datos.

- **Hibernate**
  - **Descripción**: La implementación de JPA utilizada para gestionar la comunicación con la base de datos MySQL. Hibernate realiza el mapeo ORM y facilita las operaciones CRUD.

- **MySQL**
  - **Descripción**: Base de datos relacional donde se almacenan los datos de los eventos, asistentes, y otras entidades relevantes. Es el almacén de datos central.

### Capa de Gestión de Dependencias y Construcción
- **Maven 3.9.8**
  - **Descripción**: Se encarga de gestionar las dependencias del proyecto, asegurando que todas las bibliotecas y plugins necesarios estén presentes. También facilita el proceso de construcción y empaquetado de la aplicación.

## Relación entre Componentes

1. **Angular (Front-End)** se comunica con **Spring Boot** a través de **APIs REST** para enviar y recibir datos.

2. **Spring Boot** maneja las solicitudes entrantes, delegando la lógica de negocio a **Servicios** que pueden utilizar **MapStruct** para mapear entre DTOs y entidades.

3. **Spring Boot** interactúa con la **Capa de Persistencia (JPA/Hibernate)** para realizar operaciones de lectura y escritura en **MySQL**.

4. **Lombok** es utilizado en todas las capas del back-end para simplificar el código.

5. **Maven** asegura que todas las dependencias estén disponibles y listas para ser usadas, además de gestionar el ciclo de vida del proyecto.
