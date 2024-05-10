#! /bin/bash
# print java versions to a file
(javac --version && java --version) > javaVer.txt
# clean previous build 
rm -rf ./bin/*
# compile main file and put out files in bin, then run it
javac  src/*.java src/*/*.java -d ./bin
java -cp ./bin Main