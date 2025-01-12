package sv.linda;

import sv.linda.functions.Attendance;
import sv.linda.functions.General;
import sv.linda.mongo.Mongodb;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Main {
    private final Logger logger = Logger.getLogger(getClass().getName());
    private static final Mongodb Database = new Mongodb("Logins", "Students,Teacher");

    private String selection(){
        return null;
    }

    public static void main(String[] args) {
        Database.Use("add user");
        Map<String, Boolean> studentmap = (Map<String, Boolean>) Database.Use("get map");
        System.out.println(studentmap);
        }
    }