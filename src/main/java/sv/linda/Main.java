package sv.linda;

import sv.linda.mongo.MongoFunctions;
import sv.linda.mongo.Users;

import java.util.ArrayList;
import java.util.List;

public class Main {

    void printStudents(List<String> students){
        for (String student : students) {
            System.out.println(student);
        }
    }

    public static void main(String[] args) {
        MongoFunctions mongo = new MongoFunctions();
        Users user = new Users();
        Main main = new Main();
        List<String> students = new ArrayList<>(user.makeStudentList());
        main.printStudents(students);
        user.addUser("James", "Gustafson", "Students");
        main.printStudents(students);
    }
}