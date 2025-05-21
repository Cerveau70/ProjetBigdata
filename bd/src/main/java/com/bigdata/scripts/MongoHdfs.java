package com.bigdata.scripts;

import com.mongodb.client.*;
import org.bson.Document;

import java.io.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

public class MongoHdfs {

    public static void main(String[] args) {
        // Connexion à MongoDB
        try (MongoClient mongoClient = MongoClients.create("mongodb://mongodb:27017")) {
            MongoDatabase db = mongoClient.getDatabase("clients");
            MongoCollection<Document> collection = db.getCollection("clients");

            // Configuration Hadoop HDFS
            Configuration conf = new Configuration();
            conf.set("fs.defaultFS", "hdfs://hadoop-namenode:9000");

            // Accès au système de fichiers HDFS
            FileSystem fs = FileSystem.get(conf);
            Path path = new Path("/user/data/Client_0.csv");

            // Création du fichier dans HDFS
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fs.create(path, true)))) {

                for (Document doc : collection.find()) {
                    String name = doc.getString("name") != null ? doc.getString("name") : "unknown";
                    Integer age = doc.getInteger("age", -1);
                    String city = doc.getString("city") != null ? doc.getString("city") : "unknown";

                    writer.write(name + "," + age + "," + city + "\n");
                }

                System.out.println(" Données Mongo exportées vers HDFS avec succès !");
            } catch (IOException ioEx) {
                System.err.println("Erreur lors de l'écriture dans HDFS : " + ioEx.getMessage());
                ioEx.printStackTrace();
            }

        } catch (Exception e) {
            System.err.println("Erreur globale : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
