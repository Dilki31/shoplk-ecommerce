# 🛒 ShopLK — E-Commerce REST API

A production-ready **REST API** built with Spring Boot for managing an e-commerce platform, including product management, shopping cart, and order processing.

## 🚀 Tech Stack
- **Java 17** · **Spring Boot 3.x**
- **Spring Security + JWT** (Role-based access: ADMIN, CUSTOMER)
- **Spring Data JPA** + **PostgreSQL**
- **Maven** build system

## ✨ Key Features
- 🔐 **JWT Authentication**: Secure login and registration for customers.
- 👨‍💼 **Admin-only product creation**: Only ADMIN users can add products (optionally linked to a category).
- 🛍️ **Product Management**: Browse all products (publicly accessible).
- 🛒 **Shopping Cart**: Customers can add items to their cart (stock is validated).
- 📦 **Order Management**: Checkout runs in one transaction: it creates the order, reduces stock and clears the cart. Customers can view their order history.
- ⚡ **Global Exception Handling**: Clean and structured API error responses.

## 🛠️ How to Run Locally
1. Clone the repository:
   ```bash
   git clone https://github.com/Dilki31/shoplk-ecommerce.git

2. Create a PostgreSQL database named shoplk_db.

3. Update src/main/resources/application.properties with your database credentials.

4. Run the application:

bash
./mvnw spring-boot:run

5. Access the API at http://localhost:8082/api/

📂 Project Structure
- **config/ - Security & Data Initialization**

- **controller/ - REST API Endpoints**

- **dto/ - Data Transfer Objects**

- **entity/ - Database Models (User, Product, Cart, Order)**

- **repository/ - Spring Data JPA Interfaces**

- **security/ - JWT Filter & Services**

- **service/ - Business Logic**

## ⚙️ Configuration
Secrets are **not** stored in this repository. Set these environment variables before starting the app:

| Variable | Description |
|---|---|
| `DB_PASSWORD` | Password of your local PostgreSQL user |
| `JWT_SECRET` | Base64-encoded secret key, at least 256 bits (32 bytes) |

Generate a key: `openssl rand -base64 32` (macOS/Linux/Git Bash).

Run on Windows PowerShell:
```powershell
$env:DB_PASSWORD="your-db-password"
$env:JWT_SECRET="your-generated-base64-key"
./mvnw spring-boot:run
```

👨‍💻 Author
**Dilki Nanayakkara**

**GitHub: @Dilki31**
   