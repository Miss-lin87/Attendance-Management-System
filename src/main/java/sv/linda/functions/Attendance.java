package sv.linda.functions;

import java.util.Map;

public class Attendance {

    public void getAttendance(Map<String, Boolean> students) {
        for (Map.Entry<String, Boolean> student : students.entrySet()) {
            student.setValue(isPresent(student.getKey()));
        }
    }

    boolean isPresent(String student) {
        General general = new General();
        return general.askUser("yes,no","Is " + student + " present?").equals("yes");
    }
}