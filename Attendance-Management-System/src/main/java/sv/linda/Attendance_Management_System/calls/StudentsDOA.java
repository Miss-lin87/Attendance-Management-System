package sv.linda.Attendance_Management_System.calls;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;
import sv.linda.Attendance_Management_System.mongo.Mongodb;

@Repository
public class StudentsDOA {
    @Setter
    private static Students students = new Students();
    private static final Mongodb db = new Mongodb("Logins", "students");

    static {
        students.getStudentList().add(new Student(new ObjectId().toString(),"Linda", "Ek", "Math"));
    }

    public Students getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.getStudentList().add(student);
    }
}