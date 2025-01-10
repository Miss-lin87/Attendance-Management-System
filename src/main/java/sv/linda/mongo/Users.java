package sv.linda.mongo;

import com.mongodb.client.*;
import org.bson.Document;
import sv.linda.functions.General;
import java.util.*;

public class Users {
    MongoClient client = MongoClients.create("mongodb://localhost:27017");
    MongoDatabase database = client.getDatabase("Logins");
    MongoCollection<Document> collect = client.getDatabase("Logins").getCollection("Students");
    private String rolls = "students, teacher";

    private Document makeID(String name){
        Utility utility = new Utility();
        return new Document("_id", utility.findID(name.split(" ")[0], name.split(" ")[1]));
    }

    private void addUser(String role) {
        General gen = new General();
        String name = gen.askUser("What is the name of the person? ");
        Document doc1 = new Document("FName", name.split(" ")[0]).append("LName", name.split(" ")[1]);
        database.getCollection(role).insertOne(doc1);
    }

    private void deleteUser(String name, String role) {
        database.getCollection(role).deleteOne(makeID(name));
    }

    private void changeRole(String name, String oldrole, String newrole) {
        String FName = database.getCollection(oldrole).find(makeID(name)).first().get("FName").toString();
        String LName = database.getCollection(oldrole).find(makeID(name)).first().get("LName").toString();
        database.getCollection(newrole).insertOne(new Document("FName", FName).append("LName", LName));
        deleteUser(name, oldrole);
    }

    public List<String> makeStudentList() {
        List<String> studentlist = new ArrayList<>();
        for (Document student : collect.find()) {
            String temp = student.get("FName").toString() + " " + student.get("LName").toString();
            studentlist.add(temp);
        }
        return studentlist;
    }

    private Map<String, Runnable> getStringRunnableMap() {
        General gen = new General();
        return Map.of(
                "add", () -> addUser(gen.askUser(rolls, "What is the roll of person being added? ")),
                "delete", () -> deleteUser(gen.askUser("What is the name of the person being removed? "), gen.askUser(rolls,"Is the person a student or teacher")),
                "change", () -> changeRole(gen.askUser("What is the name of the person being changed? "), gen.askUser(rolls, "What is the old role? "), gen.askUser(rolls, "What is the new role? ")));
    }

    public Runnable users(String input) {
        Map<String, Runnable> options = getStringRunnableMap();
        switch (input) {
            case "add" -> {
                return options.get("add");
            }
            case "delete" -> {
                return options.get("delete");
            }
            case "change" -> {
                return options.get("change");
            }
        }
        return null;
    }


}