# Ad-hoc, external dependencies 

FeatureIDE jars + SAT4J core

We are a bit disconnected from recent FeatureIDE versions, so we need to save the JARs (legacy).

A way to include these jars is through a Maven plugin (but I failed with existing solutions)
 
I chose a script to include these JARS into a _local_ repo. 
 * Pros: best practices
 * Cons: hard to share the process   
See mavenize.sh  
 
Mostly to build the standalone FAMILIAR; also useful for WebFML

