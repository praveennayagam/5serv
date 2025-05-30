
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
import org.junit.jupiter.api.Test;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import mongoconnect.MongoDBHandler;
public class TestClass {
      public static HashMap<String,Integer> recordsCount = MongoDBHandler.methodcall();
    MongoDatabase db = MongoDBHandler.getDatabase();
      @Test
    public void abc(){
          for(Map.Entry<String,Integer> map: recordsCount.entrySet()){
              String key=map.getKey();
              long value=map.getValue();
              MongoCollection<Document> collection = db.getCollection(key);
              long DbCount = collection.countDocuments();
              assertEquals(value,DbCount);
          }
      }
}