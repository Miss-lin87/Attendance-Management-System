package sv.linda.functions;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import sv.linda.testClass;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

class AttendanceTest {
    Attendance attend = new Attendance();
    testClass mock = new testClass();

    @Disabled
     @Test
    void testIsPresent() {
         Map<String, Boolean> expected = new HashMap<>(Map.of("Linda", true, "Ida", false));
         Map<String, Boolean> actual = new HashMap<>(Map.of("Linda", false, "Ida", false));
         System.setIn(mock.useMock("yes"));
         attend.getAttendance(actual);
         Assertions.assertEquals(expected, actual);
     }

     @Disabled
     @Test
    void testIsnotPresent() {
         Attendance attendance = new Attendance();
    }
}
