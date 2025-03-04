package sv.linda.Attendance_Management_System;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sv.linda.Attendance_Management_System.calls.Student;
import sv.linda.Attendance_Management_System.calls.Students;
import sv.linda.Attendance_Management_System.calls.StudentsDOA;

import java.net.URI;

@RestController
@RequestMapping("/students")
public class RestAPI {

    @Autowired
    private final StudentsDOA studentsDOA = new StudentsDOA();

    @GetMapping("/S")
    public Students getStudents() {
        return studentsDOA.getStudents();
    }

    @PostMapping("/S")
    public ResponseEntity<Object> addStudent(@RequestBody Student student) {
        String id = new ObjectId().toString();
        student.setId(id);

        studentsDOA.addStudent(student);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(student.getId())
                    .toUri();

        return ResponseEntity.created(location).build();
    }
}