#!/bin/sh

mvn install:install-file -Dfile=de.ovgu.featureide.core_2.7.5.201512021815.jar -DgroupId=io.variability -DartifactId=de.ovgu.featureide.core -Dversion=2.7.5 -Dpackaging=jar
mvn install:install-file -Dfile=de.ovgu.featureide.fm.core_2.7.5.201512021815.jar -DgroupId=io.variability -DartifactId=de.ovgu.featureide.fm.core -Dversion=2.7.5 -Dpackaging=jar
mvn install:install-file -Dfile=de.ovgu.featureide.fm.ui_2.7.5.201512021815.jar -DgroupId=io.variability -DartifactId=de.ovgu.featureide.fm.ui -Dversion=2.7.5 -Dpackaging=jar
mvn install:install-file -Dfile=de.ovgu.featureide.ui_2.7.5.201512021815.jar -DgroupId=io.variability -DartifactId=de.ovgu.featureide.ui -Dversion=2.7.5 -Dpackaging=jar

