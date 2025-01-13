package sv.linda.mongo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sv.linda.testClass;

class MongodbTest {

    @Test
    void testgetID() {
        Mongodb db = new Mongodb("Logins", "Students,Teacher");
        testClass mock = new testClass();
        System.setIn(mock.useMock("Linda Ek"));
        Assertions.assertEquals("677b2df4609900bf9a518aa3", db.Use("get id"));
        System.setIn(mock.useMock("James Gustafson"));
        Assertions.assertEquals("677c6d755af7b20d447f65b8", db.Use("get id"));
    }
}
