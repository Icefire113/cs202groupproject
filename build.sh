#! /bin/bash
# build
echo "Building project..."
mvn package
clear

# run
echo "Starting program..."
mvn exec:java
clear


echo "Progam completed..."
