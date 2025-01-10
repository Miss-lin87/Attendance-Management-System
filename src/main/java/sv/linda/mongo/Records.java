package sv.linda.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import sv.linda.functions.General;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Records {
    MongoClient client = MongoClients.create("mongodb://localhost:27017");
    MongoCollection<Document> collect = client.getDatabase("Attendance_records").getCollection("records");

    private void updateRecord(String date, String lesson, Map<String, Boolean> students) {
        Document tempDoc = new Document("_id", date + " " + lesson);
        for (Map.Entry<String, Boolean> student : students.entrySet()) {
            tempDoc.append(student.getKey(), student.getValue());
        }
        collect.replaceOne(tempDoc, tempDoc);
    }

    private void addRecord(String date, String lesson, Map<String, Boolean> students) {
        Document tempDoc = new Document("_id", date + " " + lesson);
        for (Map.Entry<String, Boolean> student : students.entrySet()) {
            tempDoc.append(student.getKey(), student.getValue());
        }
        collect.insertOne(tempDoc);
    }

    public void Record(String updateORadd, Map<String, Boolean> students) {
        switch (updateORadd) {
            case "update" -> updateRecord(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), new General().askUser("What lesson is it?"), students);
            case "add" -> addRecord(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), new General().askUser("What lesson is it?"), students);
        }
    }
}
