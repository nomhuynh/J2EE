# J2EE Exercise Repository

This repository contains exercises and projects for learning **J2EE (Java 2 Platform, Enterprise Edition)**, now known as **Jakarta EE**.

## 📚 About J2EE

J2EE is a platform-independent, Java-centric environment from Oracle for developing, building, and deploying Web-based enterprise applications online. The J2EE platform consists of a set of services, APIs, and protocols that provide the functionality for developing multi-tiered, web-based applications.

### Key Technologies Covered

- **Servlets**: Java programs that run on a web server and handle client requests
- **JSP (JavaServer Pages)**: Technology for developing web pages that support dynamic content
- **EJB (Enterprise JavaBeans)**: Server-side components for modular construction of enterprise applications
- **JPA (Java Persistence API)**: Specification for accessing, persisting, and managing data between Java objects and relational databases
- **JMS (Java Message Service)**: API for sending messages between components
- **Web Services**: SOAP and RESTful services
- **JDBC (Java Database Connectivity)**: API for database access
- **JNDI (Java Naming and Directory Interface)**: API for directory services

## 🎯 Purpose

This repository serves as a learning hub for J2EE concepts through hands-on exercises. Each exercise focuses on specific aspects of enterprise Java development, helping to build practical skills in:

- Building web applications
- Database connectivity and ORM
- RESTful and SOAP web services
- Enterprise application architecture
- Security and transaction management
- Messaging and asynchronous processing

## 📋 Prerequisites

Before starting with the exercises, ensure you have the following installed:

- **Java Development Kit (JDK)**: Version 8 or higher
  ```bash
  java -version
  ```

- **Application Server**: One of the following:
  - Apache Tomcat (9.x or higher)
  - WildFly (formerly JBoss)
  - GlassFish
  - Payara Server

- **Build Tool**: 
  - Maven 3.x or higher
  - or Gradle 6.x or higher

- **IDE** (Recommended):
  - IntelliJ IDEA
  - Eclipse IDE for Enterprise Java Developers
  - NetBeans

- **Database** (for exercises involving persistence):
  - MySQL, PostgreSQL, or H2 (in-memory database)

## 🗂️ Project Structure

```
J2EE/
├── README.md
├── exercises/
│   ├── 01-servlets/
│   ├── 02-jsp/
│   ├── 03-jdbc/
│   ├── 04-jpa/
│   ├── 05-ejb/
│   ├── 06-web-services/
│   └── ...
├── projects/
│   └── final-project/
└── resources/
    ├── docs/
    └── scripts/
```

## 🚀 Getting Started

1. **Clone the repository**
   ```bash
   git clone https://github.com/nomhuynh/J2EE.git
   cd J2EE
   ```

2. **Set up your development environment**
   - Install JDK and configure JAVA_HOME
   - Install and configure your chosen application server
   - Install Maven or Gradle

3. **Navigate to an exercise**
   ```bash
   cd exercises/01-servlets
   ```

4. **Build and deploy**
   ```bash
   # Using Maven
   mvn clean install
   mvn tomcat7:run
   
   # Or deploy the WAR file to your application server
   ```

5. **Access the application**
   - Open your browser and navigate to `http://localhost:8080/[app-name]`

## 📝 Exercises

Exercises will be organized by topic. Each exercise directory will contain:
- Source code
- README with specific instructions
- Sample data or configuration files
- Unit tests

### Planned Exercise Topics

1. **Servlets Fundamentals**
   - HTTP request handling
   - Session management
   - Filters and listeners

2. **JSP Development**
   - JSP syntax and directives
   - Expression Language (EL)
   - JSTL (JavaServer Pages Standard Tag Library)
   - Custom tags

3. **Database Connectivity**
   - JDBC basics
   - Connection pooling
   - Prepared statements
   - Transaction management

4. **Java Persistence API (JPA)**
   - Entity mapping
   - EntityManager
   - JPQL queries
   - Relationships

5. **Enterprise JavaBeans (EJB)**
   - Session beans
   - Message-driven beans
   - Transactions and security

6. **Web Services**
   - RESTful services with JAX-RS
   - SOAP services with JAX-WS
   - JSON and XML processing

7. **MVC Architecture**
   - Model-View-Controller pattern
   - Front Controller pattern
   - Data Access Object (DAO) pattern

8. **Security**
   - Authentication and authorization
   - JAAS (Java Authentication and Authorization Service)
   - Secure communication

## 🔧 Common Maven Dependencies

### Jakarta EE (Modern)
```xml
<!-- Servlet API -->
<dependency>
    <groupId>jakarta.servlet</groupId>
    <artifactId>jakarta.servlet-api</artifactId>
    <version>5.0.0</version>
    <scope>provided</scope>
</dependency>

<!-- JSP API -->
<dependency>
    <groupId>jakarta.servlet.jsp</groupId>
    <artifactId>jakarta.servlet.jsp-api</artifactId>
    <version>3.0.0</version>
    <scope>provided</scope>
</dependency>

<!-- JSTL -->
<dependency>
    <groupId>jakarta.servlet.jsp.jstl</groupId>
    <artifactId>jakarta.servlet.jsp.jstl-api</artifactId>
    <version>2.0.0</version>
</dependency>

<!-- JPA -->
<dependency>
    <groupId>jakarta.persistence</groupId>
    <artifactId>jakarta.persistence-api</artifactId>
    <version>3.0.0</version>
</dependency>
```

### Legacy Java EE (javax.*)
For older projects or compatibility with Java EE 8 and earlier:
```xml
<!-- Servlet API -->
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>4.0.1</version>
    <scope>provided</scope>
</dependency>

<!-- JPA -->
<dependency>
    <groupId>javax.persistence</groupId>
    <artifactId>javax.persistence-api</artifactId>
    <version>2.2</version>
</dependency>
```

## 📚 Resources

### Official Documentation
- [Jakarta EE Documentation](https://jakarta.ee/specifications/)
- [Oracle Java EE Tutorial](https://docs.oracle.com/javaee/7/tutorial/)
- [Apache Tomcat Documentation](https://tomcat.apache.org/tomcat-9.0-doc/)

### Books
- "Head First Servlets and JSP" by Bryan Basham, Kathy Sierra, and Bert Bates
- "Java EE 8 Application Development" by David R. Heffelfinger
- "Pro JPA 2 in Java EE 8" by Mike Keith and Merrick Schincariol

### Online Courses and Tutorials
- [Java EE Tutorials on Oracle](https://docs.oracle.com/javaee/)
- [Baeldung Java EE](https://www.baeldung.com/java-ee)
- [JavaTpoint J2EE Tutorial](https://www.javatpoint.com/j2ee)

## 🤝 Contributing

This is a personal learning repository. However, suggestions and improvements are welcome:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/improvement`)
3. Commit your changes (`git commit -am 'Add some improvement'`)
4. Push to the branch (`git push origin feature/improvement`)
5. Create a Pull Request

## 📄 License

This project is for educational purposes.

## 👤 Author

**Huynh Quoc Nam** ([@nomhuynh](https://github.com/nomhuynh))

---

*Happy Learning! 🎓*