import java.util.*;
import java.io.*;

public class AttendanceManager {
    private List<Student> students = new ArrayList<>();
    private Map<String, Map<String, String>> attendanceTable = new TreeMap<>();

    private static final String ATTENDANCE_FILE = "attendance_table.txt";

    public void addStudent(String rollNo, String name) {
        int serial = students.size() + 1;
        students.add(new Student(serial, rollNo, name));
        System.out.println("✅ Student added.");
    }

    public List<Student> getStudents() {
        return students;
    }

    public void markAttendance(String date, Map<String, String> attendanceMap) {
        attendanceTable.put(date, new HashMap<>(attendanceMap));
        System.out.println("✅ Attendance marked.");
    }

    public void editAttendance(String date, String rollNo, String newStatus) {
        if (attendanceTable.containsKey(date) && attendanceTable.get(date).containsKey(rollNo)) {
            attendanceTable.get(date).put(rollNo, newStatus);
            System.out.println("✅ Attendance updated.");
        } else {
            System.out.println("⚠️ Record not found.");
        }
    }

    public void searchByRollOrDate(String keyword) {
        boolean found = false;
        for (String date : attendanceTable.keySet()) {
            for (Student s : students) {
                if (s.getRollNo().equalsIgnoreCase(keyword) || s.getName().equalsIgnoreCase(keyword) || date.contains(keyword)) {
                    String status = attendanceTable.getOrDefault(date, new HashMap<>()).getOrDefault(s.getRollNo(), "-");
                    System.out.println(date + " → " + s.getName() + " (" + s.getRollNo() + "): " + status);
                    found = true;
                }
            }
        }
        if (!found) System.out.println("⚠️ No records matched.");
    }

    public void viewAttendance() {
        List<String> dates = new ArrayList<>(attendanceTable.keySet());
        Collections.sort(dates);

        System.out.printf("%-5s%-10s%-15s", "S.No", "Roll No", "Name");
        for (String date : dates) {
            System.out.printf("%-12s", date);
        }
        System.out.println();

        for (Student s : students) {
            System.out.printf("%-5d%-10s%-15s", s.getSerialNo(), s.getRollNo(), s.getName());
            for (String date : dates) {
                String status = attendanceTable.get(date).getOrDefault(s.getRollNo(), "-");
                System.out.printf("%-12s", status);
            }
            System.out.println();
        }
    }

    public void saveAttendance() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ATTENDANCE_FILE))) {
            List<String> dates = new ArrayList<>(attendanceTable.keySet());
            Collections.sort(dates);

            writer.write(String.format("%-5s%-10s%-15s", "S.No", "Roll No", "Name"));
            for (String date : dates) {
                writer.write(String.format("%-12s", date));
            }
            writer.newLine();

            for (Student s : students) {
                writer.write(String.format("%-5d%-10s%-15s", s.getSerialNo(), s.getRollNo(), s.getName()));
                for (String date : dates) {
                    String status = attendanceTable.get(date).getOrDefault(s.getRollNo(), "-");
                    writer.write(String.format("%-12s", status));
                }
                writer.newLine();
            }
            System.out.println("✅ Attendance saved to file.");
        } catch (IOException e) {
            System.out.println("❌ Error saving attendance.");
        }
    }
}