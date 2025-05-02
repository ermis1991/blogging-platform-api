https://roadmap.sh/projects/blogging-platform-api

# 📝 Blog API

A simple RESTful API for managing blog posts built with Spring Boot and PostgreSQL.

---

## 📦 Features

- Create, read, update, and delete blog posts
- Search posts by title, content, or category
- Clean RESTful endpoints
- Proper error handling and status codes

---

## 🚀 API Endpoints

### 📌 Create a Post

POST /posts

**Request Body (PostRequest):**
```json
{
  "title": "Learning Spring Boot",
  "content": "Spring Boot simplifies backend development.",
  "category": "Technology",
  "tags": ["java", "spring"]
}

```

### ✏️ Update a Post

PUT /posts/{id}

**Request Body (PostRequest):**  
Same structure as the "Create a Post" request.

---

### 🗑️ Delete a Post

DELETE /posts/{id}

- Returns `204 No Content` if successfully deleted  
- Returns `404 Not Found` if the post doesn’t exist

---

### 🔍 Get Post by ID

GET /posts/{id}

---

### 📥 Get All Posts

**GET /posts**  
Returns all blog posts.

---

### 🔍 Search Posts by Term

**GET /posts?term=java**  
Filters posts by title, content, or category using case-insensitive partial match.

---

## ⚠️ Error Handling

- `200 OK` – Post fetched or updated successfully
- `201 Created` – Post successfully created
- `204 No Content` – Post successfully deleted
- `400 Bad Request` – Invalid input
- `404 Not Found` – Post not found

---

## 🛠 Getting Started

1. **Clone the repository**
```bash
git clone https://github.com/ermis1991/blogging-platform-api.git
cd blogging-platform-api
```

2. **Set up PostgreSQL**

- Create a database (e.g., `blogdb`)
- Update `application.properties` or `application.yml` with DB credentials

3. **Run the application**
```bash
./mvnw spring-boot:run
```

---

## 👤 Author

**Kadir Eren Tüter**  
Backend Developer | Java Enthusiast  
[LinkedIn](https://www.linkedin.com/in/kadir-eren-t%C3%BCter-84a3451a4/) • [GitHub](https://github.com/ermis1991)
