package mph.mongoconnect;




import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.lang.reflect.RecordComponent;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
public class MongoDBHandler {
    private static final String DB_NAME = "FiservDB";

    public static MongoDatabase connect() {
        return MongoClients.create("mongodb://localhost:27017").getDatabase(DB_NAME);
    }

    private static <T> Document convertRecordToDocument(T record) {
        return Arrays.stream(record.getClass().getRecordComponents())
                .collect(Document::new,
                        (doc, component) -> {
                            try {
                                Object value = component.getAccessor().invoke(record);

                                if (value != null && value.getClass().isRecord()) {
                                    value = convertRecordToDocument(value);
                                }

                                doc.append(component.getName(), value);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        },
                        Document::putAll);
    }


    public static <T> void insertRecords(String collectionName, List<T> records) {
        MongoDatabase database = connect();
        MongoCollection<Document> collection = database.getCollection(collectionName);

        List<Document> documents = records.stream()
                .map(MongoDBHandler::convertRecordToDocument)
                .collect(Collectors.toList());

        collection.insertMany(documents);
        System.out.println("Inserted " + documents.size() + " records into " + collectionName);
    }
}

