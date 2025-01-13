package sv.linda.functions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sv.linda.mongo.Mongodb;
import sv.linda.testClass;

import java.io.InputStream;
import java.util.*;

class GeneralTest {
    Mongodb DB = new Mongodb("Logins", "Students,Teacher");
    testClass mock = new testClass();

    @Test
    void testMakeStudentmapEmpty() {
    }

    @Test
    void testMakeStudentmapOne() {
    }

    @Test
    void testMakeStudentmapTotal() {
    }

    @Test
    void testMakeStudentmapDups() {
    }

    @Test
    void testAskUser() {
        General general = new General();
        System.setIn(mock.useMock("yes"));
        Assertions.assertEquals("yes", general.askUser("yes,no", "Is Linda present?"));
    }
}