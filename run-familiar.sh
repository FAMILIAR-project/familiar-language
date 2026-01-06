#!/bin/bash
# Run FAMILIAR with Java 11+ compatible JVM flags
DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
java --add-opens java.base/java.lang.reflect=ALL-UNNAMED \
     -jar "$DIR/familiar.standalone/target/FAMILIAR-Standalone-1.0.10-jar-with-dependencies.jar" "$@"
