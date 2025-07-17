# Java-Based Attendance Management System

A command-line attendance management system built entirely using Java. It enables teachers to securely log in, manage student records, mark and edit attendance, and save attendance in a table format — no databases or frameworks required.

---

## Features

-  Secure Teacher Login & Account Creation
-  Add Students with Roll Numbers and Names
-  Mark Daily Attendance with Custom Date Input
-  View Attendance in Clean Table Format:
-  Dates shown as column headers
-  Rows display student serial number, name, and attendance (P/A)
-  Edit Existing Attendance Records
-  Search by Date, Roll No, or Student Name
-  Save Attendance Permanently to File
-  Logout and Re-login Functionality

---

## Project Structure

AttendanceSystem/
├── AttendanceSystem.java     # Main class (handles UI and flow)
├── AttendanceManager.java    # Core logic for managing attendance
├── Student.java              # Student object structure
├── attendance.txt            # Output file with attendance table
└── accounts.txt              # File storing teacher account credentials

## Sample Table Output
Serial No   Roll No   Name          2025-07-01   2025-07-02   2025-07-03
1           Z1        RAM           P            A            P
2           Z2        LAKSHMAN      A            P            P
3           Z3        BHARAT        P            A            A

---

## How to Run
1. Ensure JDK is installed (Java 8 or above).
2. Compile the files:
javac *.java
3. Run the program:
AttendanceSystem.java

---

👩‍💻 Developed By
👩‍💻 Khushbu Jamliya
🔗 LinkedIn

