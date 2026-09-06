package org.attendance.service;

import java.util.List;

import org.attendance.model.Attendance;
import org.attendance.repository.AttendanceRepository;

public class AttendanceService {

    private AttendanceRepository attendanceRepository =
            new AttendanceRepository();

    // Mark Attendance
    public void markAttendance(Attendance attendance) {
        attendanceRepository.markAttendance(attendance);
    }

    // View All Attendance
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.getAllAttendance();
    }

    // Dashboard - Total Attendance
    public int getTotalAttendance() {
        return attendanceRepository.getTotalAttendance();
    }

    // Dashboard - Present Count
    public int getPresentCount() {
        return attendanceRepository.getPresentCount();
    }

    // Dashboard - Absent Count
    public int getAbsentCount() {
        return attendanceRepository.getAbsentCount();
    }
}