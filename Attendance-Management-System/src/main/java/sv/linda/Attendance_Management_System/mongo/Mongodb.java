package sv.linda.Attendance_Management_System.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;
import sv.linda.Attendance_Management_System.functions.General;
import org.bson.Document;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Mongodb  {
    private final MongoClient client;
    private final MongoDatabase database;
    private final String rolls;

    public Mongodb(String db, String rolls) {
        this.client = MongoClients.create("mongodb://localhost:27017");
        this.database = client.getDatabase(db);
        this.rolls = rolls;
    }

    public List<String> getStudents(String clazz){
        List<String> students = new ArrayList<>();
        for (Document student : this.getCollection("students").find()){
            if (student.get("Classes").toString().contains(clazz)){
                students.add(student.get("FName") + " " + student.get("LName"));
            }
        }
        return students;
    }

    public Map<ObjectId, String> getAllStudentID(){
        Map<ObjectId, String> AllStudents = new HashMap<>();
        for (Document student : this.getCollection("students").find()) {
            AllStudents.put(student.getObjectId("_id"), student.get("FName").toString() + " " + student.get("LName").toString());
        }
        return AllStudents;
    }

    private Map<String, Boolean> makeStudentmap(String lesson) {
        Map<String, Boolean> studentMap = new HashMap<>();
        for (Document student : this.getCollection("students").find()) {
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

    public String findID(String name) {
        for (String roll : this.rolls.split(",")) {
            for (Document people : this.getCollection(roll).find()){
                if (people.get("FName").equals(name.split(" ")[0]) && people.get("LName").equals(name.split(" ")[1])){
                    return people.get("_id").toString();
                }
            }
        }
        return "not found";
    }

    private void addRecord(String date, String lesson) {
        Document tempDoc = new Document("_id", date + " " + lesson);
        for (Map.Entry<String, Boolean> student : this.makeStudentmap(lesson).entrySet()) {
            tempDoc.append(student.getKey(), student.getValue());
        }
        this.client.getDatabase("Attendance_records").getCollection("records").insertOne(tempDoc);
    }

    private void addUser(String role) {
        General gen = new General();
        String name = gen.askUser("What is the name of the person? ");
        Document doc1 = new Document("FName", name.split(" ")[0]).append("LName", name.split(" ")[1]).append("Classes", " ");
        this.getCollection(role).insertOne(doc1);
    }

    private void deleteUser(String name) {
        for (Document person : this.getCollection(this.getData(this.findID(name), "roll")).find()){
            if (Objects.equals(person.getObjectId("_id").toString(), this.findID(name))){
                this.getCollection(this.getData(this.findID(name), "roll")).deleteOne(person);
            }
        }
    }

    private void Change(String id, String roll, Docdata change){
        for (Document person : this.getCollection(roll).find()){
            if (Objects.equals(person.getObjectId("_id").toString(), id)){
                this.getCollection(roll).replaceOne(person, change.getDoc());
            }
        }
    }

    public void changeData(String name, String data) {
        String change = new General().askUser("What is the new value of " + data + " ? ");
        Docdata doc = new Docdata(this.findID(name));
        switch (data) {
            case "FName" -> doc.setFName(change);
            case "LName" -> doc.setLName(change);
            case "Roll" -> doc.setRoll(change);
            case "Classes" -> doc.setLesions(change);
            case "Rclasses" -> doc.removeLesions(change);
        }
        this.Change(this.findID(name), this.getData(this.findID(name), "roll"), doc);
    }

    public String getData(String id, String outputData) {
        String output = "";
        for (String roll : this.rolls.split(",")) {
            for (Document people : this.getCollection(roll).find()){
                if (Objects.equals(outputData, "roll") && Objects.equals(people.get("_id").toString(), id)){
                    output = roll;
                }
                else if (people.get("_id").toString().equals(id) && people.containsKey(outputData)){
                    output = people.get(outputData).toString();
                }
            }
        }
        return output;
    }

    public List<String> getClazzes(){
        List<String> clazzes = new ArrayList<>();
        for (String roll : this.rolls.split(",")) {
            for (Document people : this.getCollection(roll).find()) {
                for (String clazz : people.get("Classes").toString().split(",")) {
                    if (!clazzes.contains(clazz)){
                        clazzes.add(clazz);
                    }
                }
            }
        }
        return clazzes;
    }

    public Object Use(String action) {
        General gen = new General();
        switch (action) {
            case "add record" -> this.addRecord(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), gen.askUser("What lesson is it?"));
            case "add user" -> this.addUser(gen.askUser(this.rolls, "What is the role of the person being added? "));
            case "delete user" -> this.deleteUser(gen.askUser("What is the name of the person being deleted? "));
            case "get map" -> {
                return this.makeStudentmap(gen.askUser("What class is this? "));
            }
            default -> this.Use(gen.askUser("Please make a valid selection"));
        }
        return null;
    }
}