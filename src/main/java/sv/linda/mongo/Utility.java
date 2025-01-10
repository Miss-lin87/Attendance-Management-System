package sv.linda.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Utility {
    MongoClient client = MongoClients.create("mongodb://localhost:27017");
    MongoDatabase database = client.getDatabase("Logins");

    public String findID (String FName, String LName) {
        MongoCollection<Document> collect = database.getCollection("Students");
        for (Document people : collect.find()) {
            if (people.get("FName").equals(FName) && people.get("LName").equals(LName)){
                return people.get("_id").toString();
            }
        }
        return "Not fund";
    }
}