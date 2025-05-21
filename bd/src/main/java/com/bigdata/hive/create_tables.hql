CREATE DATABASE IF NOT EXISTS datalake;

USE datalake;

/* Table externe du fichier CO2.csv */
CREATE EXTERNAL TABLE IF NOT EXISTS co2 (
  id INT,
  modele STRING,
  bonus_malus STRING
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
STORED AS TEXTFILE
LOCATION 'hdfs://hadoop-namenode:8020/user/hive/warehouse/co2'




/* Table externe du fichier Catalogue.csv*/
CREATE EXTERNAL TABLE IF NOT EXISTS catalogue (
  marque STRING,
  nom STRING,
  puissance INT,
  longueur STRING,
  nbPlaces INT,
  nbPortes INT,
  couleur STRING,
  occasion BOOLEAN,
  prix DOUBLE
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
STORED AS TEXTFILE
LOCATION 'hdfs://hadoop-namenode:8020/user/hive/warehouse/catalogue'



