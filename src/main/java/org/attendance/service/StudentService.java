package org.attendance.service;

import java.util.List;

import org.attendance.model.Student;
import org.attendance.repository.StudentRepository;

public class StudentService {

    private StudentRepository studentRepository = new StudentRepository();

    // Add Student
    public void addStudent(Student student) {
        studentRepository.addStudent(student);
    }

    // View All Students
    public List<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    // Search Student
    public Student getStudentById(int studentId) {
        return studentRepository.getStudentById(studentId);
    }

    // Update Student
    public void updateStudent(Student student) {
        studentRepository.updateStudent(student);
    }

    // Delete Student
    public void deleteStudent(int studentId) {
        studentRepository.deleteStudent(studentId);
    }
 // Get Total Students
    public int getTotalStudents() {
        return studentRepository.getTotalStudents();
    }
    public boolean studentExistsByEmail(String email) {
        return studentRepository.studentExistsByEmail(email);
    }
}