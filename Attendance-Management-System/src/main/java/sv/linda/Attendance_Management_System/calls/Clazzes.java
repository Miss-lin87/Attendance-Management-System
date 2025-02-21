package sv.linda.Attendance_Management_System.calls;

import sv.linda.Attendance_Management_System.mongo.Mongodb;

import java.util.List;

public class Clazzes {
    private List<String> clazzez;
    private String clazz;
    private Mongodb DB = new Mongodb("Logins", "students");

    public Clazzes() {
        this.clazzez = DB.getClazzes();
    }

    public List<String> getClazzez() {
        return this.clazzez;
    }

    public String getClazz() {
        return this.clazz;
    }
}