/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.crudnosqlmongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

/**
 *
 * @author javie
 */
public class CRUDNoSQLMongoDB {

    public static void main(String[] args) {
        MongoClient mongo = createConnection();

        //if doesnt exists the database we create it
        if (mongo != null) {
            DB db = mongo.getDB("Test");
            System.out.println("database created");
            //creating collection if it doesnt exists
            //inserting doc to the collection
            insertUser(db,"users","Hansey","Colombia");
            insertUser(db,"users","Hansey2","Colombia2");
            insertUser(db,"users","Hansey3","Colombia3");

        }
    }

    public static MongoClient createConnection() {
        System.out.println("test");
        MongoClient mongo = null;
        mongo = new MongoClient("localhost", 27017);
        return mongo;
    }
    
    public static void insertUser(DB db, String collection,String name,String country){
    
        DBCollection colect = db.getCollection(collection);
        //Create doc and insert received info
        BasicDBObject document = new BasicDBObject();
        document.put("name",name);
         document.put("country",country);
         colect.insert(document);
         
    }
}
