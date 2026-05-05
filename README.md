
# 🚀 Compliance Training Manager

AI-powered web application for managing compliance training with automated insights and recommendations.

---

## 🧱 Tech Stack

- Backend: Spring Boot (Java 17)
- AI Service: Flask (Python)
- Frontend: React + Vite
- Database: PostgreSQL
- Cache: Redis
- AI: Groq (LLaMA 3.3)
- Containerization: Docker + Docker Compose

---

## ⚙️ Setup Instructions

### 1. Clone Repository
git clone <https://github.com/darshanhkdarshan223-create/compliance-training-manager.git>
cd your-project-folder

### 2. Setup Environment
cp .env.example .env

Add your GROQ_API_KEY inside `.env`

---

### 3. Run Project
docker-compose up --build

---

## 🌐 Access Services

| Service       | URL                         |
|--------------|----------------------------|
| Frontend     | http://localhost:3000      |
| Backend API  | http://localhost:8080      |
| Swagger      | http://localhost:8080/swagger-ui.html |
| AI Service   | http://localhost:5000/health |

---

## 🔄 Architecture Flow

Frontend (React)
↓
Backend (Spring Boot)
↓
AI Service (Flask)
↓
Groq API + ChromaDB

---

## 🔐 Features

- JWT Authentication
- Role-Based Access (ADMIN / MANAGER / VIEWER)
- AI Description Generation
- AI Recommendations
- Dashboard Analytics
- Email Notifications
- Redis Caching
- Scheduled Jobs

---

## 🐳 Run Commands

Start:
docker-compose up --build

Stop:
docker-compose down

Reset DB:
docker-compose down -v

---

## ⚠️ Important Notes

- Frontend NEVER calls AI directly
- Backend handles all AI communication
- Environment variables must not be hardcoded
- Use Docker for consistent environment

---

## 🎯 Demo Script (Demo Day)

**Role**: Presenter / Team Lead

**Step 1: Introduction & Login**
- "Welcome to the Compliance Training Manager. Today I'll show you how we've streamlined training tracking and leveraging AI to simplify compliance."
- Navigate to `http://localhost:3000/login`
- Click **Sign In** (demonstrates sleek, glassmorphic login interface).

**Step 2: The Dashboard Overview**
- "Upon logging in, managers land on the Analytics Dashboard. Here we see real-time aggregated metrics pulled directly from our Redis-cached Spring Boot backend."
- Point out the Total, Pending, and Completed counters.

**Step 3: Creating a Training**
- "Let's create a new training module."
- Click **+ New** in the Navbar.
- Fill out the form (e.g., "GDPR 2026 Refresh", "Annual European Data Privacy Update") and click **Create Training**.

**Step 4: AI Insights**
- "Now, the real power of our tool: AI generation."
- Navigate to the newly created training's Detail page.
- Click **Generate with AI**.
- "Our React frontend proxies this request securely through the Spring Boot API, which communicates with our Python Flask Microservice. The LLaMA model processes the context and returns an executive description and tailored recommendations instantly."

**Step 5: General AI Assistant**
- Go back to the **Dashboard**.
- Enter a scenario in the AI Assistant box (e.g., "We are expanding to California and need CCPA training strategies").
- Click **Generate AI Insights** to show the generative capabilities for unstructured queries.

---
**Thank you for watching!**
