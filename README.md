FAMILIAR language
=================

FAMILIAR (for FeAture Model scrIpt Language for manIpulation and Automatic Reasoning) is a language for importing, exporting, composing, decomposing, editing, configuring, computing "diffs", refactoring, reverse engineering, testing, and reasoning about (multiple) feature models. All these operations can be combined to realize complex variability management tasks.

FAMILIAR is used in different teaching contexts, for conducting research, or for collaborating with industry. 

You can use it:
 * online http://familiar.variability.io/ide/familiar
 * with an executable JAR
 * with the source code 

# Getting started 

Once you have cloned the repo, you can build FAMILIAR using Apache Maven:  
 * in ``familiar.root``, launch ```mvn install``` (```mvn install -DskipTests``` is even preferable since tests may not pass): the command builds all jars of each project in the respective "target" folders. Note that the jars are not executable
 * for building an executable jar, in ``familiar.standalone``, launch ```mvn package```: the command aggregates all previous jars into one executable jar (in the folder "target", ends up with "-jar-with-dependencies.jar")
 * for executing the tests, in ``familiar.test``, launch ```mvn integration-test``` 
 
We provide an illustrative Maven project (see FML-gettingstarted) to use FAMILIAR

# Credits 

FAMILIAR was originally created at I3S laboratory by Mathieu Acher, Philippe Collet and Philippe Lahire and is now jointly and openly managed by the DiverSE team (IRISA / Inria / University of Rennes 1) and the MODALIS team (I3S laboratory, University of Nice Sophia Antipolis). 
