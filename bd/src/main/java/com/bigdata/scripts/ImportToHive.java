package com.bigdata.scripts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ImportToHive {

    public static void main(String[] args) {
        String hiveUrl = "jdbc:hive2://hive-server:10000/datalake";
        String user = "hive";
        String password = ""; 

        try (Connection conn = DriverManager.getConnection(hiveUrl, user, password);
             Statement stmt = conn.createStatement()) {

            // Activer la permission de chargement local (si activé sur HiveServer2)
            stmt.execute("SET hive.strict.checks.large.query=false");
            stmt.execute("SET hive.exec.dynamic.partition.mode=nonstrict");

            System.out.println("Début de l'import vers Hive...");

            // Charger les données vers les tables Hive
            stmt.execute("LOAD DATA INPATH '/user/data/CO2.csv' INTO TABLE co2");
            System.out.println("CO2 importé dans Hive.");

            stmt.execute("LOAD DATA INPATH '/user/data/Catalogue.csv' INTO TABLE catalogue");
            System.out.println("Catalogue importé dans Hive.");

            System.out.println("Tous les imports Hive sont terminés !");

        } catch (Exception e) {
            System.err.println("Une erreur s'est produite lors de l'import dans Hive :");
            e.printStackTrace();
        }
    }
}
