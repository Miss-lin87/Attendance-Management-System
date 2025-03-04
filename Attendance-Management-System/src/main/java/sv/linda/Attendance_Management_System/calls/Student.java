package sv.linda.Attendance_Management_System.calls;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import sv.linda.Attendance_Management_System.mongo.Mongodb;

@Data
public class Student {
    private String id;
    private String FName;
    private String LName;
    private String clazzes;
    private Mongodb db = new Mongodb("Logins", "students");

    public Student() {
    }

    public Student (String studentId, String FName, String LName, String clazzes) {
        this.id = studentId;
        this.FName = FName;
        this.LName = LName;
        this.clazzes = clazzes;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getFName() {
        return this.FName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getLName() {
        return this.LName;
    }

    public void setClazzes(String clazzes) {
        this.clazzes = clazzes;
    }

    public String getClazzes(){
        return this.clazzes;
    }

    @Override
    public String toString() {
        return "Student: [id= " + this.id +
                ", Name= " + this.FName + " " + this.LName +
                ", Classes= " + this.clazzes + "]";
    }
}