#! /bin/bash
# compile main file and put out files in bin, then run it
javac src/**/*.java -d ./bin && java -cp ./bin Main