#
# Packages the TVL Parser Jar and its
# dependencies into a nice .tar.gz.
# The dependencies have to be located
# at ../TVLLibs for this to work.
#

TIMESTAMP=$(date "+%Y%m%d%n")
FILENAME=TVLParser-$TIMESTAMP.tar.gz
FOLDER=TVLParser-$TIMESTAMP
LIBS=$FOLDER/libs

mkdir $FOLDER
mkdir $LIBS
cp TVLParser.jar $FOLDER/TVLParser.jar
cp ../TVLLibs/JFlex/JFlex.jar $LIBS/JFlex.jar
cp ../TVLLibs/java_cup/java-cup-11a-runtime.jar $LIBS/java-cup-11a-runtime.jar
cp ../TVLLibs/sat4j-core-v20090520/org.sat4j.core.jar $LIBS/org.sat4j.core.jar 

tar -czf $FILENAME $FOLDER
rm -rf $FOLDER