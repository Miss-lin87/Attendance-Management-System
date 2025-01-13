package sv.linda;

import org.bson.Document;
import sv.linda.functions.Attendance;
import sv.linda.functions.General;
import sv.linda.mongo.Docdata;
import sv.linda.mongo.Mongodb;

import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Main {
    private final Logger logger = Logger.getLogger(getClass().getName());
    private static Mongodb Database = new Mongodb("Logins", "students,teacher");
    /*private static Map<String, Object> menu = new HashMap<>(Map.of(
            "Add user", Database.Use("add user"),
            "Delete user", Database.Use("delete user"),
            "Add record", Database.Use("add record"),
            "Get student map", Database.Use("get map"),
            "Get ID", Database.Use("get id"),
            "Get data", Database.Use("get data")));*/

    public static void main(String[] args) {
        }
    }