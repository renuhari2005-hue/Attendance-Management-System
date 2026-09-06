# Attendance Management System

## 📌 Project Overview

The Attendance Management System is a console-based Java application developed to manage student information and attendance records efficiently.

The application uses JDBC to connect with a MySQL database and follows a layered architecture to separate the application logic into different components.

## 🛠️ Technologies Used

- Java 21
- JDBC
- MySQL
- Maven
- Eclipse IDE
- MySQL Connector/J

## ✨ Features
👨‍🎓 Student Management – Add, view, search, update, and delete students.
📅 Attendance Management – Mark and manage student attendance.
📊 Attendance Percentage – Calculate individual student attendance percentage.
📆 Attendance Reports – View date-wise and monthly attendance reports.
📈 Dashboard – View important student and attendance statistics.
🔍 Search – Search students by ID or name.
🗄️ MySQL Database – Store and manage student and attendance data using JDBC.
🏗️ Layered Architecture – Controller, Service, Repository, Model, and Utility layers.

### Student Management

- Add Student
- View All Students
- Search Student 
- Update Student
- Delete Student

### Attendance Management

- Mark Attendance
- View All Attendance Records
- Delete Attendance
- Prevent duplicate attendance records for the same student and date

### Dashboard

The dashboard displays:

- Total Students
- Total Attendance Records
- Present Records
- Absent Records

## 🏗️ Project Architecture

The project follows a layered architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
