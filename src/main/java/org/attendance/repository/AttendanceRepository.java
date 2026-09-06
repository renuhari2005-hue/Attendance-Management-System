package org.attendance.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.attendance.model.Attendance;
import org.attendance.util.DBConnection;

public class AttendanceRepository {

    // 1. Mark Attendance
    public void markAttendance(Attendance attendance) {

        String sql = "INSERT INTO attendance (student_id, attendance_date, status) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, attendance.getStudentId());
            ps.setDate(2, attendance.getAttendanceDate());
            ps.setString(3, attendance.getStatus());

            ps.executeUpdate();

            System.out.println("Attendance marked successfully!");

        } catch (java.sql.SQLIntegrityConstraintViolationException e) {

            System.out.println("Attendance already marked for this student today!");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // 2. View All Attendance
    public List<Attendance> getAllAttendance() {

        List<Attendance> attendanceList = new ArrayList<>();

        String sql = "SELECT * FROM attendance";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Attendance attendance = new Attendance();

                attendance.setAttendanceId(rs.getInt("attendance_id"));
                attendance.setStudentId(rs.getInt("student_id"));
                attendance.setAttendanceDate(rs.getDate("attendance_date"));
                attendance.setStatus(rs.getString("status"));

                attendanceList.add(attendance);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return attendanceList;
    }

    // 3. Get Total Attendance Records
    public int getTotalAttendance() {

        String sql = "SELECT COUNT(*) FROM attendance";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    // 4. Get Present Count
    public int getPresentCount() {

        String sql = "SELECT COUNT(*) FROM attendance WHERE status = 'Present'";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    // 5. Get Absent Count
    public int getAbsentCount() {

        String sql = "SELECT COUNT(*) FROM attendance WHERE status = 'Absent'";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}