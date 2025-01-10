package sv.linda;

import sv.linda.functions.Attendance;
import sv.linda.functions.General;
import sv.linda.mongo.Records;
import sv.linda.mongo.Users;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Main {
    private static Map<String, Boolean> students = new HashMap<>(new General().makeStudentmap(new Users().makeStudentList()));
    private Map<String, Runnable> menu = new HashMap<>(Map.of(
            "attendance", () -> new Attendance().getAttendance(students),
            "add", () -> new Records().Record("add", students),
            "update", () -> new Records().Record("update", students)
    ));
    private final String options = "get attendance,add record,update record";
    private final Logger logger = Logger.getLogger(getClass().getName());

    private void selector(String selection) {
        switch (selection) {
            case "get attendance" -> new Attendance().getAttendance(students);
            case "add record" -> new Records().Record("add", students);
            case "update record" -> new Records().Record("update", students);
            default -> logger.info("Invalid input try again");
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        General gen = new General();
        main.logger.info("Get Attendance\nAdd Record\nUpdate Record");
        main.selector(gen.askUser(main.options, "Please make a selection: "));
        for (String student : students.keySet()){
            System.out.println(student + " | " + students.get(student));
        }
    }
}