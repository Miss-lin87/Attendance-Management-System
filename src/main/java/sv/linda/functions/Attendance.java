package sv.linda.functions;

import java.util.Map;

public class Attendance {

    public void getAttendance(Map<String, Boolean> students) {
        for (Map.Entry<String, Boolean> student : students.entrySet()) {
            student.setValue(isPresent(student.getKey()));
        }
    }

    boolean isPresent(String student) {
        General gen = new General();
        return gen.askUser("yes,no", "Is " + student + " present?").equals("yes");
    }
}