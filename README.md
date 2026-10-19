# Online Store Application

## Project description

This project is a simple online store application built with Spring Boot and MariaDB. Users can browse products, filter products by category, place orders, and view order history.

The application uses a REST API and JPA to access the database. The frontend is built with HTML, CSS, and JavaScript.

## Technologies

- Java
- Spring Boot
- Spring Data JPA
- MariaDB
- Maven
- HTML
- CSS
- JavaScript

## Project structure

- `controllers` handle REST API requests.
- `entities` represent database tables.
- `repositories` handle database operations.
- `src/main/resources/static` contains the frontend files.
- `src/main/resources/application.yml` contains the database connection settings.
- `project.sql` contains the database dump.

## Requirements

- Java JDK
- Maven
- MariaDB
- IDE of your choice

## Setup and running the application

1. Clone this repository from GitHub.
2. Start MariaDB and import `project.sql` to create the `project` database.
3. Create a database user named `app_user` and grant it the necessary permissions: `SELECT`, `INSERT`, `UPDATE`, `DELETE`.
4. Open `src/main/resources/application.yml` and replace the `###` placeholders with your local database username and password. The database URL should be `jdbc:mariadb://localhost:3306/project`.
5. Open the project in your preferred IDE and allow Maven to load the dependencies.
6. Run the application from `DatabaseProjectsApplication`.
7. Open `http://localhost:8080` in your browser.

## REST API

The API accepts and returns JSON.

### Products

**GET `/products`**

Retrieves all products.

- Request: No body.
- Response: A JSON array of products.

**POST `/products`**

Creates a new product.

- Request: Product data in JSON format.
- Response: The saved product as JSON.

Example request:

`{"name":"Example product","description":"Product description","price":10.00,"stock_quantity":20,"category":{"id":1}}`

The category ID should refer to an existing category.

**PUT `/products/{id}`**

Updates an existing product's name, description, price, and stock quantity.

- Request: Product data in JSON format.
- Response: The updated product as JSON.

**PUT `/products/increase-prices/{categoryId}`**

Increases the prices of products in the specified category.

- Request: Category ID in the URL. No body.
- Response: An integer returned by the repository method indicating the number of products updated.

**GET `/products/criteria-search?minPrice=10.00&minStock=5`**

Searches for products based on minimum price and minimum stock quantity.

- Request: The `minPrice` and `minStock` query parameters.
- Response: A JSON array of matching products.

This endpoint uses the JPA Criteria API.

### Product categories

**GET `/productcategories`**

Retrieves all product categories.

- Request: No body.
- Response: A JSON array of categories.

**POST `/productcategories`**

Creates a new product category.

- Request: Category data in JSON format.
- Response: The saved category as JSON.

Example request:

`{"name":"Example category","description":"Category description"}`

**POST `/productcategories/{id}/products`**

Adds a product to the specified category.

- Request: Category ID in the URL and product data in JSON format.
- Response: The submitted product as JSON.

### Customer profiles

**GET `/customer-profiles`**

Retrieves all customer profiles.

- Request: No body.
- Response: A JSON array of customer profiles.

**POST `/customer-profiles`**

Creates a customer profile.

- Request: Customer profile data in JSON format.
- Response: The saved profile as JSON.

A customer profile contains address information and a reference to its associated customer.

### Orders

**GET `/orders`**

Retrieves all orders.

- Request: No body.
- Response: A JSON array of orders.

**GET `/orders/customer/{customerId}`**

Retrieves orders belonging to the specified customer.

- Request: Customer ID in the URL.
- Response: A JSON array of the customer's orders.

**POST `/orders`**

Creates an order for a customer with one or more order items.

- Request: Customer ID and a list of products with quantities.
- Response: The created order as JSON.

Example request:

`{"customerId":1,"items":[{"productId":1,"quantity":2},{"productId":3,"quantity":1}]}`

**PUT `/orders/{id}/status`**

Updates the status of an existing order.

- Request: Order ID in the URL and a JSON string containing the new status.
- Response: The updated order as JSON.

Supported status values are `"NEW"`, `"SHIPPED"`, and `"CANCELLED"`.

### Tables and relationships

- `customer_profiles` stores customer address information and is associated with a customer through a one-to-one JPA relationship.
- `productcategories` stores category names and descriptions. A category can contain multiple products.
- `products` stores product names, descriptions, prices, stock quantities, and category references.
- `orders` stores customer references, order dates, and order statuses. A customer can have multiple orders.
- Order items associate products and quantities with an order.
- `order_log` stores log entries created when new orders are inserted.

### JPA features

- Entity IDs are generated automatically by the database (`GenerationType.IDENTITY`).
- Products and product categories are connected through a many-to-one / one-to-many relationship.
- Order items are associated with orders, with cascade operations configured for the relationship.
- Product categories load their product collections lazily.
- The `@Version` field on products supports optimistic locking.
- `OrderStatusConverter` converts order statuses for database storage.
- `@JsonIgnore` is used on selected relationships to prevent those fields from being included in JSON responses.

### Additional database features

- **Index:** An index on `productcategories.name` was created as part of the database performance exercises.
- **View:** The `product_details` view combines product information with the corresponding category name.
- **Trigger:** The `log_new_order` trigger inserts a log entry into `order_log` after a new order is inserted.
- **Scheduled event:** The `clean_old_order_logs` event deletes log entries older than 30 days. It is configured to run every minute.


### Security

The application uses a separate database user, `app_user`, instead of the root account.

### Use of AI
The AI was used in providing guidance in creating the HTML, CSS, and JavaScript files for the frontend. AI was also used for troubleshooting.









