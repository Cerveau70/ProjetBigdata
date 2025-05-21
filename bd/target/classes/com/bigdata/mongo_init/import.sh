#!/bin/bash
mongoimport --db clientDB --collection clients --type csv --headerline --file /docker-entrypoint-initdb.d/Client_0.csv
