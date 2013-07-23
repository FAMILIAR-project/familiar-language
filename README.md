FAMILIAR language
=================

This is the repository for hosting the source code of FAMILIAR. 

The repo is currently "private" for two reasons:
 * we still have to fix the License (GPL, LGPL or EPL) and the GPL licence of FeatureIDE is a kind of problem at the moment 
 * we can easily have experimental, research-oriented and non visible branches (but I guess we can manage this with a public repo)

## How to?


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

### Testing the installation

To check your installation, execute the JUnit tests in '''/src/fr/unice/polytech/modalis/familiar/test''' and '''/src/fr/unice/polytech/modalis/familiar/test/featureide'''
with the the VM argument '''-Xmx1024M'''.

Note that there are some errors. You should have around (23rd of july): 
 * (test) 3 errors, 19 failures, 20 ignores (out of 436 tests)
 * (test/featureide) 10 errors, 6 failures, 59 ignored (out of 419 tests)

### Exporting an Executable Jar

If you want to export FAMILIAR as a standalone application (i.e., as an executable JAR): 
 * execute FML (package fr.unice.polytech.modalis.familiar.standalone;) which contains a main method ; 
 * right click on FAMILIAR project, select "Export..." and then "Runnable JAR file..."
 * please select "Package required..." and in Launch Configuration "FAMILIAR - FML" (you can also select "FAMILIAR - FamiliarEditor" for the version with the comprehensive environment)
  
### Hello World (API)

As a developer of FAMILIAR or API user, you certainly want to know how to load feature models and reuse facilities offered by FAMILIAR. 
Here is an "Hello World" in the form of a JUnit test that demonstrates the API in action



	package fr.unice.polytech.modalis.familiar.test;
	import static org.junit.Assert.assertEquals;
	import java.util.Collection;
	import org.junit.Test;
	import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
	import fr.unice.polytech.modalis.familiar.variable.SetVariable;
	import gsd.graph.ImplicationGraph;
	import gsd.graph.SimpleEdge;
	/**
	* @author macher1
	*
	*/
	public class HelloWorldTest extends FMLTest {
	  
	  @Test
	  public void test1() throws Exception {
	  	
	    FeatureModelVariable fm1 = FM("fm1", "A : B [C] ; ");
	    		
	    SetVariable cores1 = (SetVariable) _shell.parse("cores fm1");
	    System.err.println("cores1=" + cores1.names());
	    assertEquals(2, cores1.size());
	    ImplicationGraph<String> big1 = fm1.computeImplicationGraph() ;
	    Collection<SimpleEdge> e1 = big1.edges() ;
	    for (SimpleEdge e : e1) {
	      	System.err.println("" + big1.getEdgeSource(e) + " => " + big1.getEdgeTarget(e));
	    }
	  	    		
	  }
	
	}


The test passes and it will print the core features and the implicaiton graph of the feature model:

	cores1=[A, B]
	C => A
	B => A
	C => B
	A => B


