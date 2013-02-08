FAMILIAR language
=================

This is the repository for hosting the source code of FAMILIAR. 

The repo is currently "private" for two reasons:
 * we still have to fix the License (GPL, LGPL or EPL) and the GPL licence of FeatureIDE is a kind of problem at the moment 
 * we can easily have experimental, research-oriented and non visible branches (but I guess we can manage this with a public repo)

How to?
========

You need Eclipse: 
 * with Xtext 2.3.1 (Eclipse 4.2 with the last version of Xtext is currently used for instance)
 * with a Git plugin (e.g., EGit is now provided in most of the Eclipse distribs)
 * with an SVN plugin  (e.g., Subeclipse to checkout the code source of the project see [http://subclipse.tigris.org/servlets/ProjectProcess?pageID=p4wYuA]) for FeatureIDE (see below)

You have to checkout all the projects:

 * FAMILIAR
 * FML3rdPartiesMisc
 * FML3rdPartiesForSynthesis
 * org.xtext.example.fml
 * org.xtext.example.fml.sdk
 * org.xtext.example.fml.tests
 * org.xtext.example.fml.ui
 * SPLAR-plugin
 * TVLPackagedAsPlugin
 * org.xtext.example.fmlero
 * org.xtext.example.fmlero.sdk
 * org.xtext.example.fmlero.tests
 * org.xtext.example.fmlero.ui
 * S2T2toFML

FeatureIDE is not hosted here. 
Install FeatureIDE using directly the SVN of FeatureIDE project (we are non intrusive now in the code of FeatureIDE)
For installing FeatureIDE checkout the following projects, using the SVN server: https://faracvs.cs.uni-magdeburg.de/svn/tthuem-FeatureIDE/ (login is "anonymous" and there is no password)
 * de.ovgu.featureide.core
 * de.ovgu.featureide.fm.core
 * de.ovgu.featureide.fm.ui
 * de.ovgu.featureide.ui

Note that we are currently using revision #2074
Important final step: 
=> expose all packages of de.ovgu.featureide.ui and de.ovgu.featureide.fm.ui (in the two projects there are META-INF/MANIFEST.MF)
