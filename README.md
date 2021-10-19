FAMILIAR language
=================

FAMILIAR (for FeAture Model scrIpt Language for manIpulation and Automatic Reasoning) is a language for importing, exporting, composing, decomposing, editing, configuring, computing "diffs", refactoring, reverse engineering, testing, and reasoning about (multiple) feature models. All these operations can be combined to realize complex variability management tasks.

FAMILIAR is used in different teaching contexts, for conducting research, or for collaborating with industry. 

You can use it:
 * online http://familiar.variability.io/ide/familiar
 * with an executable JAR (https://github.com/FAMILIAR-project/familiar-language/releases/) 
 * with Docker thanks to [Sébastien Mosser](https://github.com/mosser) (docker pull familiarlang/familiar:1.2) 
 * with the source code 

# Getting started 

Once you have cloned the repo, you can build FAMILIAR using Apache Maven:  
 * in ``familiar.root``, launch ```mvn install``` (```mvn install -DskipTests``` is even preferable since tests may not pass): the command builds all jars of each project in the respective "target" folders. Note that the jars are not executable
 * for building an executable jar, in ``familiar.standalone``, launch ```mvn package```: the command aggregates all previous jars into one executable jar (in the folder "target", ends up with "-jar-with-dependencies.jar")
 * for executing the tests, in ``familiar.test``, launch ```mvn integration-test``` 
 
We provide an illustrative Maven project (see FML-gettingstarted) to programmatically use FAMILIAR with a Java fluent API

# Architecture 

 * FAMILIAR: interpreter of FAMILIAR programs, Java fluent API
 * org.xtext.example.fml*: Xtext project with the grammar of FAMILIAR 
 * familiar.test: some unit tests over FAMILIAR functionnalities (interesting to look at for learning how the API does work) 
 * familiar.root and familiar.standalone: mainly here for Maven 
 * FML-gettingstarted: ready-to-user project for programmatically using FAMILIAR with a Java fluent API
 * FML3rdPartiesMisc and FML3rdPartiesForSynthesis: a packaging of various libraries (at time Maven was not mainstream) 
 * SPLAR-plugin: a packaging of SPLAR (https://splot-research.org) 
 * PacogenPlugin: for generating T-wise configurations with Pacogen (deprecated from both sides)
 * TVLPackagedAsMock: some interoperability with TVL models (deprecated from both sides)
 * org.xtext.example.fmlero* and S2T2toFML: for interoperating with S2T2, a configurator developed at Lero (deprecated from both sides) 

FAMILIAR can internally reuse some FeatureIDE facilities (but we do not follow the evolution of FeatureIDE) 

# Docker 

## Using FAMILIAR with Docker 

```
docker pull familiarlang/familiar:1.2
vmacher:familiar-language macher1$ docker run -v $PWD:/familiar/host -it familiarlang/familiar:1.2
FAMILIAR (for FeAture Model scrIpt Language for manIpulation and Automatic Reasoning)  version 1.2 (beta)
http://familiar-project.github.com/
fml> fm1 = FM (D : O C K E [R] ; )
fm1: (FEATURE_MODEL) D: C [R] O K E ;
```

## Building Docker 

See the "docker" folder: prepare.sh is here to build the JAR, release.sh is here to build the Docker using Dockerfile (you have to change some settings since by default it pushes the Docker image) 

# Citation

Please use this bibtex entry to cite this work: 
```
@article{FAMILIAR, author = {Mathieu Acher and Philippe Collet and Philippe Lahire and Robert B. France}, 
title = {FAMILIAR: A domain-specific language for large scale management of feature models}, 
journal = {Science of Computer Programming (SCP)}, 
volume = {78}, 
number = {6}, 
year = {2013}, 
pages = {657-681}, 
ee = {http://dx.doi.org/10.1016/j.scico.2012.12.004}}
``` 

preprint is available here: https://hal.inria.fr/hal-00767175
see also tutorials: https://familiar-project.github.io/ 


# Credits 

FAMILIAR was originally created at I3S laboratory / Colorado State University by Mathieu Acher, Philippe Collet, Philippe Lahire, and Robert B. France. It is now jointly and openly managed by the DiverSE team (IRISA / Inria / University of Rennes 1) and the MODALIS team (I3S laboratory, University of Nice Sophia Antipolis). 
