
public class Student {
    private int serialNo;
    private String rollNo;
    private String name;

    public Student(int serialNo, String rollNo, String name) {
        this.serialNo = serialNo;
        this.rollNo = rollNo;
        this.name = name;
    }

    public int getSerialNo() { return serialNo; }
    public String getRollNo() { return rollNo; }
    public String getName() { return name; }
}
