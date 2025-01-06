package sv.linda.mongo;

import com.mongodb.client.*;

import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

public class MongoFunctions {
        String base = "mongodb://localhost:27017";
        MongoClient client = MongoClients.create(base);
        MongoDatabase database = client.getDatabase("Logins");

        public void addStudent(String name, String password){
            MongoCollection<Document> collection = database.getCollection("Students");
            Document doc = new Document();
            collection.insertOne(doc);
        }

    public static void main(String[] args) {
        String base = "mongodb://localhost:27017";
        MongoClient client = MongoClients.create(base);
        MongoCollection<Document> collect = client.getDatabase("Logins").getCollection("Students");
        // Document doc1 = new Document("Name", "James");
        // MongoDatabase database = client.getDatabase("Logins");
        // database.getCollection("Students").insertOne(doc1);
        Bson filter = Filters.empty();
        MongoCursor<Document> cursor = collect.find(filter).iterator();
        try {
            while (cursor.hasNext()) {
                    System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
    }
}
