package sv.linda;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sv.linda.mongo.Mongodb;

class MongodbTest {

    @Test
    void testgetID() {
        Mongodb db = new Mongodb("Logins", "Students,Teacher");
        Assertions.assertEquals("677b2df4609900bf9a518aa3", db.findID("Linda Ek"));
        Assertions.assertEquals("677c6d755af7b20d447f65b8", db.findID("James Gustafson"));
    }
}
