package org.attendance.controller;
import org.attendance.model.Attendance;
import org.attendance.model.Student;
import org.attendance.service.AttendanceService;
import org.attendance.service.StudentService;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AttendanceController {

    private Scanner scanner = new Scanner(System.in);

    private StudentService studentService = new StudentService();
    private AttendanceService attendanceService = new AttendanceService();

    public void start() {

        while (true) {

            System.out.println("\n===== ATTENDANCE MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Mark Attendance");
            System.out.println("7. View Attendance");
            System.out.println("8. Dashboard");
            System.out.println("9. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    updateStudent();
                    break;

                case 5:
                    deleteStudent();
                    break;

                case 6:
                    markAttendance();
                    break;

                case 7:
                    viewAttendance();
                    break;

                case 8:
                    showDashboard();
                    break;

                case 9:
                    System.out.println(
                            "Thank you for using Attendance Management System!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

 // View Attendance
    private void viewAttendance() {

        List<Attendance> attendanceList =
                attendanceService.getAllAttendance();

        if (attendanceList.isEmpty()) {
            System.out.println("No attendance records found.");
            return;
        }

        System.out.println("\n------------- ATTENDANCE RECORDS -------------");

        for (Attendance attendance : attendanceList) {

            System.out.println(
                    "Attendance ID: " + attendance.getAttendanceId()
                    + ", Student ID: " + attendance.getStudentId()
                    + ", Date: " + attendance.getAttendanceDate()
                    + ", Status: " + attendance.getStatus()
            );
        }

        System.out.println("----------------------------------------------");
    }
    
	private void showDashboard() {

    	    int totalStudents = studentService.getTotalStudents();
    	    int totalAttendance = attendanceService.getTotalAttendance();
    	    int presentCount = attendanceService.getPresentCount();
    	    int absentCount = attendanceService.getAbsentCount();

    	    System.out.println("\n========== DASHBOARD ==========");
    	    System.out.println("Total Students     : " + totalStudents);
    	    System.out.println("Total Attendance   : " + totalAttendance);
    	    System.out.println("Present Records    : " + presentCount);
    	    System.out.println("Absent Records     : " + absentCount);
    	    System.out.println("===============================");
  	}

	// Add Student
    private void addStudent() {

        scanner.nextLine();

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter department: ");
        String department = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        if (studentService.studentExistsByEmail(email)) {
            System.out.println("Student already exists!");
            return;
        }

        Student student = new Student(name, department, email, phone);

        studentService.addStudent(student);
    }

 // View Students
    private void viewStudents() {

        List<Student> students = studentService.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("\n---------------- STUDENT LIST ----------------");
        System.out.printf("%-5s %-15s %-12s %-25s %-12s%n",
                "ID", "Name", "Department", "Email", "Phone");
        System.out.println("---------------------------------------------------------------");

        for (Student student : students) {

            System.out.printf("%-5d %-15s %-12s %-25s %-12s%n",
                    student.getStudentId(),
                    student.getName(),
                    student.getDepartment(),
                    student.getEmail(),
                    student.getPhone());
        }

        System.out.println("---------------------------------------------------------------");
    }

    // Search Student
    private void searchStudent() {

        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();

        Student student = studentService.getStudentById(studentId);

        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found.");
        }
    }

    // Update Student
    private void updateStudent() {

        System.out.print("Enter student ID to update: ");
        int studentId = scanner.nextInt();

        scanner.nextLine();

        Student student = studentService.getStudentById(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new department: ");
        String department = scanner.nextLine();

        System.out.print("Enter new email: ");
        String email = scanner.nextLine();

        System.out.print("Enter new phone: ");
        String phone = scanner.nextLine();

        Student updatedStudent =
                new Student(studentId, name, department, email, phone);

        studentService.updateStudent(updatedStudent);
    }

    // Delete Student
    private void deleteStudent() {

        System.out.print("Enter student ID to delete: ");
        int studentId = scanner.nextInt();

        Student student = studentService.getStudentById(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        studentService.deleteStudent(studentId);
    }

    // Mark Attendance
    private void markAttendance() {

        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();

        Student student = studentService.getStudentById(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter status (Present/Absent): ");
        String status = scanner.next();

        if (!status.equalsIgnoreCase("Present")
                && !status.equalsIgnoreCase("Absent")) {

            System.out.println("Invalid status. Enter Present or Absent.");
            return;
        }

        Date date = Date.valueOf(LocalDate.now());

        Attendance attendance =
                new Attendance(studentId, date,
                        status.substring(0, 1).toUpperCase()
                        + status.substring(1).toLowerCase());

        attendanceService.markAttendance(attendance);
    }
}
 
 