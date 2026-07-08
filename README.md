# 🛒 Cartify - Full Stack E-Commerce Platform

<p align="center">
  <img src="https://img.shields.io/badge/React-19-blue?logo=react" />
  <img src="https://img.shields.io/badge/Spring_Boot-3.x-green?logo=springboot" />
  <img src="https://img.shields.io/badge/MySQL-Database-blue?logo=mysql" />
  <img src="https://img.shields.io/badge/Azure-Deployed-0078D4?logo=microsoftazure" />
  <img src="https://img.shields.io/badge/Vercel-Frontend-black?logo=vercel" />
  <img src="https://img.shields.io/badge/License-MIT-yellow" />
</p>

## 📌 Overview

**Cartify** is a modern full-stack e-commerce platform built using **Spring Boot** and **React.js**. It provides a seamless shopping experience with secure authentication, product browsing, shopping cart management, and online payment integration.

The application follows a clean client-server architecture, exposing REST APIs from the backend while delivering a responsive frontend optimized for all devices.

---

## 🚀 Live Demo

🌐 **Frontend:** https://cartify-a8pc.vercel.app/

---

## ✨ Features

- 🔐 JWT Authentication & Authorization
- 👤 User Registration & Login
- 🛍 Browse Products
- 🔍 Search Products
- 🛒 Shopping Cart Management
- ❤️ Wishlist Support
- 📦 Order Management
- 💳 Razorpay Payment Integration
- 📱 Responsive UI
- ☁️ Cloud Deployment
- 🔒 Secure REST APIs
- ⚡ Fast API Communication

---

## 🏗 Tech Stack

### Frontend
- React.js
- Tailwind CSS
- Axios
- React Router
- Context API

### Backend
- Spring Boot
- Spring Security
- JWT Authentication
- Hibernate / JPA
- REST APIs

### Database
- MySQL

### Payment Gateway
- Razorpay

### Deployment
- Frontend → Vercel
- Backend → Microsoft Azure
- Docker

---

# 📂 Project Structure

```
CARTIFY
│
├── backend
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   ├── config
│   ├── security
│   └── resources
│
├── frontend
│   ├── components
│   ├── pages
│   ├── services
│   ├── context
│   └── assets
│
└── README.md
```

---

## 🔑 Core Functionalities

### 👤 Authentication
- User Registration
- Secure Login
- JWT Token Authentication
- Protected Routes

### 🛒 Shopping
- Browse Products
- Product Details
- Add to Cart
- Update Quantity
- Remove Items
- Wishlist

### 💳 Checkout
- Secure Checkout
- Razorpay Integration
- Order Confirmation

### 📦 Orders
- View Orders
- Order History
- Order Details

---

## ⚙️ Installation

### Clone Repository

```bash
git clone https://github.com/satyam6203/CARTIFY.git

cd CARTIFY
```

---

## Backend Setup

```bash
cd backend

mvn clean install

mvn spring-boot:run
```

---

## Frontend Setup

```bash
cd frontend

npm install

npm run dev
```

---

## Environment Variables

### Backend (.env or application.properties)

```properties
spring.datasource.url=

spring.datasource.username=

spring.datasource.password=

jwt.secret=

razorpay.key.id=

razorpay.key.secret=
```

---

### Frontend (.env)

```env
VITE_API_BASE_URL=

VITE_RAZORPAY_KEY=
```

---

## 🌐 API Modules

- Authentication API
- User API
- Product API
- Cart API
- Wishlist API
- Order API
- Payment API

---

## 🔒 Security

- JWT Authentication
- Password Encryption
- Spring Security
- Role-Based Authorization
- Protected REST Endpoints

---

## 📸 Screenshots

> Add your application screenshots here.

Example:

```
screenshots/
├── home.png
├── login.png
├── products.png
├── cart.png
└── checkout.png
```

---

## 🚀 Deployment

### Frontend
- Vercel

### Backend
- Microsoft Azure App Service

---

## 🛠 Future Improvements

- Product Reviews
- Product Ratings
- Coupon System
- Email Notifications
- Inventory Management
- Admin Dashboard Analytics
- Order Tracking
- Recommendation System

---

## 👨‍💻 Author

**Satyam Kumar**

GitHub: https://github.com/satyam6203

LinkedIn: *(Add your LinkedIn profile)*

---

## ⭐ Support

If you like this project,

⭐ Star this repository

🍴 Fork it

🛠 Contribute to it

---

## 📄 License

This project is licensed under the **MIT License**.

---

<p align="center">
Made with ❤️ using Spring Boot, React.js, Azure & Vercel
</p>
