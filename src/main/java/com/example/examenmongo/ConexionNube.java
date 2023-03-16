package com.example.examenmongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
public class ConexionNube {

    ConnectionString connectionString = new ConnectionString("mongodb+srv://jorge:jorge1234@cluster0.r6slhzf.mongodb.net/?retryWrites=true&w=majority");
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();
    MongoClient mongoClient = MongoClients.create(settings);
    MongoDatabase database = mongoClient.getDatabase("dsm501");

    public boolean insertar(Cliente cliente){
        MongoCollection<Document> collection=database.getCollection("clientes");
        Document document=new Document("idCliente", cliente.getIdCliente())
                .append("nombre", cliente.getNombre())
                .append("primerApellido", cliente.getPrimerApellido())
                .append("segundoApellido", cliente.getSegundoApellido())
                .append("correo", cliente.getCorreo())
                .append("domicilio",cliente.getDomicilio());
        collection.insertOne(document);
        return true;
    }

    public boolean actualizar(Cliente clienteAnterior, Cliente clienteNuevo){
        MongoCollection<Document> collection = database.getCollection("clientes");
        Bson filter = Filters.eq("idCliente", clienteAnterior.getIdCliente());
        Document document = new Document("idCliente", clienteNuevo.getIdCliente())
                .append("nombre", clienteNuevo.getNombre())
                .append("primerApellido", clienteNuevo.getPrimerApellido())
                .append("segundoApellido", clienteNuevo.getSegundoApellido())
                .append("correo", clienteNuevo.getCorreo())
                .append("domicilio",clienteNuevo.getDomicilio());
        Bson update = new Document("$set", document);
        UpdateResult result = collection.updateOne(filter, update);
        System.out.println(result.getModifiedCount() + " documento actualizado");
        return true;
    }

    public boolean mostrar() {
        try {
            MongoCollection<Document> collection = database.getCollection("clientes");
            FindIterable<Document> documents = collection.find();
            for (Document document : documents) {
                System.out.println(document.toJson());
            }
            return true;
        } catch (Exception e){
            System.out.println("error");
            return false;
        }
    }

    public boolean eliminar(int idCliente){
        MongoCollection<Document> collection = database.getCollection("clientes");
        Bson filter = Filters.eq("idCliente", idCliente);
        DeleteResult result = collection.deleteOne(filter);
        System.out.println(result.getDeletedCount() + " documento eliminado");
        return true;
    }

}
