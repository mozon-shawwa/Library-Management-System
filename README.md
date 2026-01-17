# 📚 Library Management System (JavaFX)

A comprehensive **Library Management System** desktop application developed using **Java**, **JavaFX**, and **Scene Builder**.  
The system supports **role-based access control** and provides full management of users, books, and borrowing operations through an interactive and user-friendly GUI.

---

## 👥 User Roles
- **Admin**
- **Librarian**
- **User**

Each role has its own dashboard, permissions, and sidebar navigation.

---

## 🔐 Authentication System

### 🔑 Login
- Login interface with full input validation
- Error messages displayed for invalid or empty inputs
- User credentials verification
- System contains at least one **default Admin** account

### 📝 Register
- New user registration with validation
- Error handling for incorrect inputs
- Registration is available **only for Users**

---

## 🛠 Admin Dashboard

### 👤 User Management
- Display all users in a table
- Filter users by **Role**
- Add, update, delete, and cancel user operations
- Sidebar includes:
  - Logged-in user profile (image + full name)
  - Clickable profile to view and edit personal data
- Any profile update is reflected immediately across the system

### 📚 Book Management
- Display all books in a table
- Filter books by **Category**
- Add, update, delete, and cancel book operations
- Add new book categories
- Newly added categories update automatically in:
  - Filters
  - Book forms

---

## 📊 Admin Statistics
Accessible from the sidebar:
- Total number of users
- Number of Admin users
- Number of Librarian users
- Number of regular Users

Each statistic is displayed with:
- Fixed image
- Title
- Dynamic counter

Clicking any statistic opens a detailed table with:
- Search functionality
- Clear search button

---

## 📘 Librarian Dashboard

### 📊 Book & Borrowing Statistics
- Total books count
- Total borrowed books
- Pending borrow requests
- Approved borrow requests

### 📑 Borrowing Management
- View all borrowing records
- Borrowing status handling:
  - **Pending**
  - **Approved**
- Filter borrowing records by status:
  - All
  - Pending
  - Approved
- Search and clear search functionality

### 📦 Borrow Approval Logic
- Before approving a borrow request:
  - System checks available book copies
- If copies are sufficient:
  - Borrow is approved
  - Book copies are reduced
- If copies are insufficient:
  - Error message is displayed
- Librarian can:
  - Approve a request
  - Reject a request (removes it from the system)

---

## 👤 User Dashboard

### 🏠 Home
- Welcome screen for the logged-in user
- Sidebar with profile image and full name
- Profile editing with live updates across the system

### 📖 Borrow Book
- Search for books by **Category**
- After selecting a category:
  - All related books are displayed
- Full book details shown after search
- Validation ensures:
  - Category and book selection
  - Error messages for missing selections

### 🔄 Borrowing States
- **First-time Borrow**
  - Button enabled
  - Status: Borrow
- **Pending**
  - Button disabled
  - Status: Pending
- **Approved**
  - Button disabled
  - Status: Approved

### 🔁 Return Book
- Available only if borrow status is **Approved**
- Returning a book:
  - Removes the borrowing record
  - Increases the number of available copies

---

## 🧠 Technologies Used
- **Java**
- **JavaFX**
- **Scene Builder**
- **FXML**
- **CSS**
- **MVC Architecture**

---

## 🎯 Project Highlights
- Role-based access control
- Fully validated forms
- Interactive dashboards
- Statistics and filtering
- Real-time UI updates
- Clean and structured code

---

## 🎥 Project Demo
📺 [Watch the demo video on YouTube](https://www.youtube.com/watch?v=-ezj81EHYic)

