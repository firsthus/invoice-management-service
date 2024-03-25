Online Invoice and Payment Link Generator System

**Overall Description and Goals:**
The system aims to provide a comprehensive solution for generating, managing, and paying detailed invoices containing multiple items or services. It facilitates the creation of unique, accessible transaction links for each invoice, streamlining the payment process for customers and enhancing transaction management for companies.

**Functional Specification:**

1. **User Authentication for Companies:**
    - Companies register and log in to access the system. Authentication is required for generating invoice links.
    - The invoice links themselves are accessible without authentication, ensuring ease of access for customers.

2. **Invoice Generation and Management:**
    - Companies can create invoices detailing multiple items or services, including quantities and unit prices.
    - The system calculates the total invoice amount automatically.
    - Invoices are editable and deletable by the company until payment is made.

3. **Unique Link Generation and Expiry:**
    - Each invoice is associated with a unique link for viewing details and making payments.
    - Links expire after a configurable period. Expired links display an EXPIRED status but still show invoice details.

4. **Payment Processing:**
    - Payment is facilitated through integrated third-party gateways via the unique link.
    - The system is adaptable to include new payment methods over time.
    - Payment details are displayed once the invoice is paid. Paid links reflect the payment status instead of expiring.

5. **Invoice Status Tracking:**
    - Invoices display statuses (Unpaid, Paid, Expired) accessible through the unique link by both the company and the customer.

**Technical Specification:**

- **Application Type:** Web application.
- **Platform/Technologies:** Backend developed in Java with Spring Boot, Maven for dependency management, Liquibase for database migration, MySQL as the database, Querydsl for database queries, and Swagger for API documentation.

- **Architecture:**
    - **Backend:** Manages authentication, invoice creation and management, unique link generation with expiry logic, and payment gateway integration.
    - **Database:** Stores user (company) profiles, detailed invoices (items/services, quantities, unit prices), and payment statuses.

**Integration Specification:**

- **Third-Party Payment Gateways:** Integrates with multiple payment gateways, flexible to include new methods as needed. Specific gateway APIs will be integrated based on requirements.
- **Invoice and Payment API:** Provides RESTful APIs for invoice management, unique payment link generation, payment processing, and status queries. Documented with Swagger for clarity and integration ease.

**Conclusion:**
This detailed specification outlines the development of an invoice and payment link generator system leveraging Java and Spring Boot. The system is designed to efficiently manage detailed invoices, facilitate easy payments through unique transaction links, and adapt to varying payment gateway integrations, providing a comprehensive billing and payment solution for companies and their customers.



------------------------------------------------------------------------------------------------------------------------------------------------------------


## Running the Application

1. Ensure you have Java 21, Maven, and MySQL installed on your system. You can check this by running the following commands in your terminal:

```bash
java -version
mvn -version
mysql --version
```

2. Clone the repository to your local machine:

```bash
git clone https://github.com/firsthus/invoice-management-service
```

3. Navigate into the project directory:

```bash
cd invoice-management-service
```

4. Build the project using Maven:

```bash
mvn clean install
```

5. Before running the application, ensure that MySQL is running and accessible. The application expects a MySQL server running on localhost with the default port (3306). If your MySQL server is running on a different host or port, you can pass these as environment variables:

```bash
export OXYGEN_DATASOURCE_HOST=<your-mysql-host>
export OXYGEN_DATASOURCE_PORT=<your-mysql-port>
export OXYGEN_SCHEMA_NAME=<your-schema-name>
export OXYGEN_DATASOURCE_USERNAME=<your-mysql-username>
export OXYGEN_DATASOURCE_PASSWORD=<your-mysql-password>
```

Replace `<your-mysql-host>`, `<your-mysql-port>`, `<your-mysql-username>`, and `<your-mysql-password>` with your actual MySQL host, port, username, and password respectively.

6. Run the application using the Spring Boot Maven Plugin:


```bash
mvn spring-boot:run
```

The application should now be running and accessible on `localhost:4001` (or whatever port you have configured).

7. You can access the Swagger UI to interact with the API by navigating to http://localhost:4001/swagger-ui/index.html#/ in your browser.