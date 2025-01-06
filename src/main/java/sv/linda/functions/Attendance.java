package sv.linda.functions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Attendance {

    public List<String> getAttendance(Map<String, Boolean> Students){
        List<String> attending = new ArrayList<>(Students.size());
        for (Map.Entry<String, Boolean> student : Students.entrySet()) {
        }
        return attending;
    }

    public boolean isPresent(String student){
        General general = new General();
        return true;
    }
}
