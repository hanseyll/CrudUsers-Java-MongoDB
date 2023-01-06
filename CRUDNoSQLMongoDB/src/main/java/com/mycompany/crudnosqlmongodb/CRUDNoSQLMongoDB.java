/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.crudnosqlmongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
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
            //           insertUser(db, "users", "Hansey", "Colombia");

            //          insertUser(db, "users", "Hansey3", "Colombia3");
            //       insertUser(db, "users", "Hansey4", "Colombia4");
            
            //searchByName(db, "users", "Hansey2");
            System.out.println("before");
             showCollection(db,"users");
            updateDoc(db,"users","Hansey");
            System.out.println("after"); 
            showCollection(db,"users");
        }

    }

    public static MongoClient createConnection() {
        MongoClient mongo = new MongoClient("localhost", 27017);
        return mongo;
    }

    public static void insertUser(DB db, String collection, String name, String country) {
        DBCollection colect = db.getCollection(collection);
        //Create doc and insert received info
        BasicDBObject document = new BasicDBObject();
        document.put("name", name);
        document.put("country", country);
        colect.insert(document);

    }

    //show all docs of user's collection
    public static void showCollection(DB db, String collection) {
        DBCollection collect = db.getCollection(collection);

        DBCursor cursor = collect.find();

        while (cursor.hasNext()) {
            System.out.println("* " + cursor.next().get("name") + " - " + cursor.curr().get("country"));
        }
    }
    //show docs by name

    public static void searchByName(DB db, String collection, String name) {
        DBCollection collect = db.getCollection(collection);

        //creating query with name
        BasicDBObject query = new BasicDBObject();
        query.put("name", name);

        DBCursor cursor = collect.find(query);
        while (cursor.hasNext()) {
            System.out.println("* " + cursor.next().get("name") + " - " + cursor.curr().get("country"));
        }

    }
    public static void updateDoc(DB db, String collection, String name){
        DBCollection collect = db.getCollection(collection);
        
        //information to remplace
        BasicDBObject updateCountry = new BasicDBObject();
        updateCountry.append("$set", new BasicDBObject().append("country", "checoloskavia"));
        
        //search the doc in the collection
        
        BasicDBObject searchByName = new BasicDBObject();
        searchByName.append("name",name);
        
        //make update
        collect.updateMulti(searchByName, updateCountry);
    }
}
