package org.attendance.main;

import org.attendance.controller.AttendanceController;

public class AttendanceApp {

    public static void main(String[] args) {

        AttendanceController controller =
                new AttendanceController();

        controller.start();
    }
}
