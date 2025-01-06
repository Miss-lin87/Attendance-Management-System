package sv.linda.mongo;

import com.mongodb.client.*;

import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class Users {
    String base = "mongodb://localhost:27017";
    MongoClient client = MongoClients.create(base);
    MongoDatabase database = client.getDatabase("Logins");
    MongoCollection<Document> collect = client.getDatabase("Logins").getCollection("Students");

    public void addUser(String Fname, String Lname, String role){
        Document doc1 = new Document("FName", Fname).append("Lname", Lname);
        database.getCollection(role).insertOne(doc1);
    }

    public void deleteUser(String ID, String role){
        database.getCollection(role).deleteOne(new Document("ID", ID));
    }

    public void changeRole(String ID, String oldrole, String newrole){
        String Fname = database.getCollection(oldrole).find(new Document("ID", ID)).first().get("FName").toString();
        String Lname = database.getCollection(oldrole).find(new Document("ID", ID)).first().get("LName").toString();
        database.getCollection(newrole).insertOne(new Document("Fname", Fname).append("Lname", Lname));
        database.getCollection(oldrole).deleteOne(new Document("ID", ID));
    }

    public List<String> makeStudentList(){
        List<String> studentlist = new ArrayList<>();
        Bson filter = Filters.empty();
        try (MongoCursor<Document> students = collect.find(filter).iterator()) {
            while (students.hasNext()) {
                studentlist.add(students.next().toJson());
            }
        }
        return studentlist;
    }
}
