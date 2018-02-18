#!/bin/bash

OUT=FAMILIAR-Standalone-1.0.10-jar-with-dependencies.jar
JAR=familiar.jar

if [ -f $JAR ]
then
  echo "File $JAR already exists"
  exit
fi

cd ../familiar.root
mvn -DskipTests install

cd ../familiar.standalone
mvn package

cd ../docker
cp ../familiar.standalone/target/$OUT ./$JAR
