package com.bigdata.scripts;

import java.sql.*;
import java.io.*;

public class ImportPostgres {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://postgres-metastore:5432/metastore";
        String user = "hive";
        String password = "hive";

        importCleanCo2(jdbcUrl, user, password, "data/CO2_clean.csv");
        importCleanCatalogue(jdbcUrl, user, password, "data/Catalogue_clean.csv");
        importCleanClients(jdbcUrl, user, password, "data/Client_clean.csv");

        System.out.println("Tous les imports vers PostgreSQL sont terminés !");
    }

    public static void importCleanCo2(String jdbcUrl, String user, String password, String filePath) {
        System.out.println("Import CO2...");
        try (Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
             BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length < 2) continue;

                PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO clean_co2(modele, bonus_malus) VALUES (?, ?)");
                stmt.setString(1, values[0]);
                String cleanBonus = values[1].replace("€", "").replace("\u00A0", "").replaceAll("[^\\d-]", "").trim();
                stmt.setFloat(2, Float.parseFloat(cleanBonus));
                stmt.executeUpdate();
            }

            System.out.println("CO2 importé !");
        } catch (Exception e) {
            System.err.println("Erreur lors de l'import CO2 :");
            e.printStackTrace();
        }
    }

    public static void importCleanCatalogue(String jdbcUrl, String user, String password, String filePath) {
        System.out.println("Import Catalogue...");
        try (Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
             BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length < 9) continue;

                PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO clean_catalogue(marque, nom, puissance, longueur, nbplaces, nbportes, couleur, occasion, prix) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                stmt.setString(1, values[0]);
                stmt.setString(2, values[1]);
                stmt.setInt(3, Integer.parseInt(values[2]));
                stmt.setString(4, values[3]);
                stmt.setInt(5, Integer.parseInt(values[4]));
                stmt.setInt(6, Integer.parseInt(values[5]));
                stmt.setString(7, values[6]);
                stmt.setBoolean(8, Boolean.parseBoolean(values[7]));
                stmt.setFloat(9, Float.parseFloat(values[8]));
                stmt.executeUpdate();
            }

            System.out.println("Catalogue importé !");
        } catch (Exception e) {
            System.err.println("Erreur lors de l'import Catalogue :");
            e.printStackTrace();
        }
    }

    public static void importCleanClients(String jdbcUrl, String user, String password, String filePath) {
        System.out.println("🔄 Import Clients...");
        try (Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
             BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length < 3) continue;

                PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO clean_clients(name, age, city) VALUES (?, ?, ?)");
                stmt.setString(1, values[0]);
                stmt.setInt(2, Integer.parseInt(values[1]));
                stmt.setString(3, values[2]);
                stmt.executeUpdate();
            }

            System.out.println("Clients importés !");
        } catch (Exception e) {
            System.err.println("Erreur lors de l'import Clients :");
            e.printStackTrace();
        }
    }
}
