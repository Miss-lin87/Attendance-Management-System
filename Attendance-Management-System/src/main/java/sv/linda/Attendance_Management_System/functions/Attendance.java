package sv.linda.Attendance_Management_System.functions;

import sv.linda.Attendance_Management_System.mongo.Mongodb;

import java.util.List;
import java.util.Map;

public class Attendance {
    private String clazz;
    private List<String> students;
    private Mongodb DB = new Mongodb("Logins", "students,teacher");

    public Attendance(String clazz){
        this.clazz = clazz;
        this.students = DB.getStudents(clazz);
    }

    public List<String> getStudents(){
        return this.students;
    }

    public String getClazz(){
        return this.clazz;
    }

    public void getAttendance() {
        Map<String, Boolean> studentmap = (Map<String, Boolean>) DB.Use("get map");
        for (Map.Entry<String, Boolean> student : studentmap.entrySet()) {
            student.setValue(isPresent(student.getKey()));
        }
    }

    private boolean isPresent(String student) {
        General gen = new General();
        return gen.askUser("yes,no", "Is " + student + " present?").equals("yes");
    }
}