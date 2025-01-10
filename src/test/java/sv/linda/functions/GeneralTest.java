package sv.linda.functions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.*;

class GeneralTest {

    @Test
    void testMakeStudentmapEmpty() {
        General general = new General();
        List<String> students = Collections.emptyList();

        Assertions.assertEquals(new HashMap<>(), general.makeStudentmap(students));
        Assertions.assertNotEquals(new HashMap<>(Map.of("ida", true)), general.makeStudentmap(students));
    }

    @Test
    void testMakeStudentmapOne() {
        General general = new General();
        Map<String, Boolean> test = general.makeStudentmap(List.of("linda"));

        Assertions.assertEquals(new HashMap<>(Map.of("linda", false)), test);
        Assertions.assertNotEquals(new HashMap<>(Map.of("ida", true)), test);
    }

    @Test
    void testMakeStudentmapTotal() {
        General general = new General();
        List<String> students = List.of("linda", "ida", "bob");
        Map<String, Boolean> expected = new HashMap<>(Map.of("linda", false, "ida", false, "bob", false));

        Assertions.assertEquals(expected, general.makeStudentmap(students));
        Assertions.assertNotEquals(new HashMap<>(Map.of("linda", true, "ida", false)), general.makeStudentmap(students));
    }

    @Test
    void testMakeStudentmapDups() {
        General general = new General();
        List<String> students = List.of("linda", "linda", "ida");
        Map<String, Boolean> expected = new HashMap<>(Map.of("linda", false, "ida", false));

        Assertions.assertEquals(expected, general.makeStudentmap(students));
        Assertions.assertTrue(general.makeStudentmap(students).containsKey("linda"));
        Assertions.assertFalse(general.makeStudentmap(students).containsKey("bob"));
    }

    @Test
    void testAskUser() {
        General general = new General();
        InputStream mock = new java.io.ByteArrayInputStream("yes".getBytes());
        System.setIn(mock);
        Assertions.assertEquals("yes", general.askUser("yes,no", "Is Linda present?"));
    }
}