// AttendanceSystem.java
import java.util.*;
import java.io.*;

public class AttendanceSystem {
    private static final String ACCOUNT_FILE = "accounts.txt";

    private static boolean login(Scanner sc) {
        System.out.print("Enter Teacher ID: ");
        String teacherId = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(ACCOUNT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equals(teacherId) && parts[2].equals(password)) {
                    System.out.println("✅ Login successful.");
                    System.out.println("Teacher ID : " + parts[0]);
                    System.out.println("Name       : " + parts[1]);
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error reading accounts file.");
        }
        System.out.println("❌ Invalid credentials.");
        return false;
    }

    private static void createAccount(Scanner sc) {
        System.out.print("Enter Teacher ID: ");
        String teacherId = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ACCOUNT_FILE, true))) {
            bw.write(teacherId + "," + name + "," + password);
            bw.newLine();
            System.out.println("✅ Account created successfully.");
            System.out.println("Stored Details:");
            System.out.printf("%-15s %-20s %-15s%n", "Teacher ID", "Name", "Password");
            System.out.printf("%-15s %-20s %-15s%n", teacherId, name, password);
        } catch (IOException e) {
            System.out.println("❌ Error writing account file.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AttendanceManager manager = new AttendanceManager();

        while (true) {
            System.out.println("\n==== Welcome to Attendance Management System ====");
            System.out.println("1. Login");
            System.out.println("2. Create Account");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            String option = sc.nextLine();

            if (option.equals("1")) {
                if (!login(sc)) continue;
                break;
            } else if (option.equals("2")) {
                createAccount(sc);
            } else if (option.equals("3")) {
                System.out.println("Goodbye!");
                return;
            } else {
                System.out.println("❌ Invalid choice.");
            }
        }

        while (true) {
            System.out.println("\n===== Attendance Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. Mark Attendance");
            System.out.println("3. View Attendance Table");
            System.out.println("4. Edit Attendance");
            System.out.println("5. Search Attendance");
            System.out.println("6. Save Attendance");
            System.out.println("7. Logout");
            System.out.print("Choose an option: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter Roll No: ");
                    String roll = sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    manager.addStudent(roll, name);
                    break;

                case "2":
                    System.out.print("Enter Date (yyyy-MM-dd): ");
                    String date = sc.nextLine();
                    Map<String, String> attendanceMap = new HashMap<>();
                    List<Student> students = manager.getStudents();
                    if (students.isEmpty()) {
                        System.out.println("⚠️ No students found. Please add students first.");
                        break;
                    }
                    for (Student s : students) {
                        System.out.print("Enter status for " + s.getName() + " (Roll: " + s.getRollNo() + ") [Present/Absent]: ");
                        String status = sc.nextLine();
                        attendanceMap.put(s.getRollNo(), status);
                    }
                    manager.markAttendance(date, attendanceMap);
                    break;

                case "3":
                    manager.viewAttendance();
                    break;

                case "4":
                    System.out.print("Enter Date (yyyy-MM-dd): ");
                    String editDate = sc.nextLine();
                    System.out.print("Enter Roll No: ");
                    String editRoll = sc.nextLine();
                    System.out.print("Enter new status (Present/Absent): ");
                    String newStatus = sc.nextLine();
                    manager.editAttendance(editDate, editRoll, newStatus);
                    break;

                case "5":
                    System.out.print("Enter keyword to search (date, roll no, or name): ");
                    String keyword = sc.nextLine();
                    manager.searchByRollOrDate(keyword);
                    break;

                case "6":
                    manager.saveAttendance();
                    break;

                case "7":
                    System.out.println("🔒 Logging out...");
                    main(null);
                    return;

                default:
                    System.out.println("❌ Invalid option. Please try again.");
            }
        }
    }
}

