package sv.linda.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import sv.linda.functions.General;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Mongodb  {
    private final MongoClient client;
    private final MongoDatabase database;
    private final String[] rolls;

    public Mongodb(String db, String rolls){
        this.client = MongoClients.create("mongodb://localhost:27017");
        this.database = client.getDatabase(db);
        this.rolls = rolls.split(",");
    }

    private Map<String, Boolean> makeStudentmap(String lesson) {
        Map<String, Boolean> studentMap = new HashMap<>();
        for (Document student : this.getCollection("Students").find()) {
            if (student.get("Classes").toString().contains(lesson)) {
                String temp = student.get("FName").toString() + " " + student.get("LName").toString();
                studentMap.put(temp, false);
            }
        }
        return studentMap;
    }

    private MongoCollection<Document> getCollection(String roll){
        return database.getCollection(roll);
    }

    private String findID(String name) {
        for (String roll : this.rolls) {
            for (Document people : this.getCollection(roll).find()){
                if (people.get("FName").equals(name.split(" ")[0]) && people.get("LName").equals(name.split(" ")[1])){
                    return people.get("_id").toString();
                }
            }
        }
        return "not found";
    }

    private String findRoll(String name) {
        for (String roll : this.rolls) {
            for (Document people : this.getCollection(roll).find()) {
                if (people.get("FName").equals(name.split(" ")[0]) && people.get("LName").equals(name.split(" ")[1])){
                    return roll;
                }
            }
        }
        return "not found";
    }

    /*private void updateRecord(String date, String lesson) {
        Document tempDoc = new Document("_id", date + " " + lesson);
        for (Map.Entry<String, Boolean> student : this.studentMap.entrySet()) {
            tempDoc.append(student.getKey(), student.getValue());
        }
        this.getCollection("Students").replaceOne(tempDoc, tempDoc);
    } Not working needs fixing later */

    private void addRecord(String date, String lesson) {
        Document tempDoc = new Document("_id", date + " " + lesson);
        for (Map.Entry<String, Boolean> student : this.makeStudentmap(lesson).entrySet()) {
            tempDoc.append(student.getKey(), student.getValue());
        }
        this.client.getDatabase("Attendance_records").getCollection("records").insertOne(tempDoc);
    }

    private void addUser(String role) {
        String name = new General().askUser("What is the name of the person? ");
        Document doc1 = new Document("FName", name.split(" ")[0]).append("LName", name.split(" ")[1]).append("Classes", " ");
        this.getCollection(role).insertOne(doc1);
    }

    private void deleteUser(String name) {
        database.getCollection(this.findRoll(name)).deleteOne(new Document("_id", this.findID(name)));
    }

    public Object Use(String action) {
        switch(action){
            case "add record" -> this.addRecord(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), new General().askUser("What lesson is it?"));
            case "add user" -> this.addUser(new General().askUser(Arrays.toString(this.rolls), "What is the role of the person being added? "));
            case "delete user" -> this.deleteUser(new General().askUser("What is the name of the person being deleted? "));
            case "get map" -> {
                return this.makeStudentmap(new General().askUser("What class is this? "));
            }
            case "get id" -> {
                return this.findID(new General().askUser("What is the name of the person? "));
            }
        }
        return this.Use(new General().askUser("Please make a valid selection. "));
    }
}