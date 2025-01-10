package sv.linda.functions;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

class AttendanceTest {

    @Disabled
     @Test
    void testIsPresent() {
         Attendance attendance = new Attendance();
         Map<String, Boolean> expected = new HashMap<>(Map.of("Linda", true, "Ida", false));
         Map<String, Boolean> actual = new HashMap<>(Map.of("Linda", false, "Ida", false));
         InputStream mock = new java.io.ByteArrayInputStream("Yes".getBytes());
         System.setIn(mock);
         attendance.getAttendance(actual);
         Assertions.assertEquals(expected, actual);
     }

     @Disabled
     @Test
    void testIsnotPresent() {
         Attendance attendance = new Attendance();
    }
}
