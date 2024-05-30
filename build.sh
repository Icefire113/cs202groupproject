#! /bin/bash
# build
mvn package

# run
echo ""
java -jar ./bin/cs202groupproject-*.jar