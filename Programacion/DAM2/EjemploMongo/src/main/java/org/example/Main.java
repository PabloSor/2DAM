package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Main {
    public static void main(String[] args) {

        // Conectarse a mongo
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("miBaseDeDatos");

        // Añadir colección
        MongoCollection<Document> collection = database.getCollection("miColeccion");

        // Crear documento
        Document doc = new Document("nombre", "Juan")
                .append("edad", 30)
                .append("ciudad", "Madrid");

        // Isertar
        //collection.insertOne(doc);

        // Actualizar
        //collection.updateOne(new Document("nombre", "Juan"), new Document("$set", new Document("nombre", "Pedro").append("edad", 21)));

        // Eliminar
        //collection.deleteOne(new Document("nombre", "Juan"));

        // Leer
        for (Document document : collection.find()){
            System.out.println(document.toJson());
        }

        // Cerrar conexión
        mongoClient.close();
    }
}