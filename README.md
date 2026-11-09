# 🛒 ShopLK — E-Commerce REST API

A production-ready **REST API** built with Spring Boot for managing an e-commerce platform, including product management, shopping cart, and order processing.

## 🚀 Tech Stack
- **Java 17** · **Spring Boot 3.x**
- **Spring Security + JWT** (Role-based access: ADMIN, CUSTOMER)
- **Spring Data JPA** + **PostgreSQL**
- **Maven** build system

## ✨ Key Features
- 🔐 **JWT Authentication**: Secure login and registration for customers.
- 👨‍💼 **Admin Panel**: Admins can add products and manage categories.
- 🛍️ **Product Management**: Browse all products (publicly accessible).
- 🛒 **Shopping Cart**: Customers can add items to their cart.
- 📦 **Order Management**: Customers can checkout their cart and view order history.
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

👨‍💻 Author
**Dilki Nanayakkara**

**GitHub: @Diliki31**
   