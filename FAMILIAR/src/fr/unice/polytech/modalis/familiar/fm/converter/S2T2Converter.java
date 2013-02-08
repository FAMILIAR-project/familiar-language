package fr.unice.polytech.modalis.familiar.fm.converter;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.xtext.example.mydsl.FmleroStandaloneSetup;
import org.xtext.example.mydsl.fmprimitives.Feature;
import org.xtext.example.mydsl.fmprimitives.FeatureModel;

import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.interpreter.VariableNotExistingException;
import fr.unice.polytech.modalis.familiar.parser.VariableAmbigousConflictException;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;

public class S2T2Converter {
	
	
	
	 public S2T2Converter() {
	 this (true) ;
	 }
	
	 public S2T2Converter(boolean standalone) {
	 if (standalone)
	 initStandalone();
	
	
	 }
	
	 private void initStandalone() {
	 new FmleroStandaloneSetup().createInjectorAndDoEMFRegistration();
	
	 }
	
	
	 public EObject getEObjectFromFile(File file) {
	
	 ResourceSet rs = new ResourceSetImpl();
	
	 String uri = file.getAbsolutePath() ;
	 Resource resource = rs.getResource(URI.createURI("file:" + uri), true);
	
	 if (!resource.getErrors().isEmpty()) {
	 StringBuilder sb = new StringBuilder() ;
	 for (Diagnostic error : resource.getErrors()) {
	 sb.append( "line(" + error.getLine() + ") " + error.getLocation() + ": "
	 + error.getMessage() );
	
	 }
	 throw new IllegalArgumentException(sb.toString());
	 }
	 System.out.println ("resource=" + resource + " contents=" +
	 resource.getContents());
	 EObject eobject = resource.getContents().get(0);
	 return eobject ;
	
	 }
	
	
	 public String parseFile (File file) throws FDModelErrorException,
	 FDUnsupportedModelException {
	
	 EObject eo = getEObjectFromFile(file);
	
	 if (! (eo instanceof FeatureModel))
	 throw new FDModelErrorException("Feature diagram not found!");
	
	
	 FeatureModel fd = (FeatureModel) eo ;
	 Feature root = null;//= fd.getRoot() ;
	
	 StringBuilder sb = new StringBuilder();
	 sb.append("FM (");
	
	 sb.append(treatFeature(root)) ;
	
	 sb.append(")");
	 return sb.toString() ;
	 }
	
	
	
	
	
	 private String treatFeature(Feature ft) throws
	 FDUnsupportedModelException {
	 StringBuilder sb = new StringBuilder();
	
	 /*
	 EList<Feature> children = ft.getChildren() ;
	
	 if (ft.getChildren().size() >= 1) { // the feature has child-features
	 sb.append(ft.getName() + ": ");
	 Operator op = ft.getOperator() ;
	
	 // TODO: can be conveniently translated but at the moment, no!
	 if (op instanceof Card)
	 throw new
	 FDUnsupportedModelException("Cardinality currently not supported");
	 if (op instanceof Opt)
	 throw new
	 FDUnsupportedModelException("Opt (prefer And-group + 'optional' attribute) currently not supported");
	
	
	 // And-groups (mandatory/optional) (note that And-group is the by-default
	 group)
	 if (op instanceof And || op == null) {
	 for (Feature child : children) {
	 String ftName = child.getName() ;
	 if (isOptional(child))
	 ftName = "[" + ftName + "]";
	 sb.append("" + ftName + " ");
	 }
	 }
	
	 // Xor/Or-groups
	 else if (op instanceof Xor || op instanceof Or) {
	
	 if (children.size() == 1) // only one feature in the Xor/Or group =>
	 optional
	 sb.append("[" + children.get(0).getName() + "]");
	 // normal case: Xor/Or-groups with at least two subfeatures
	 else {
	 String XORcontent = "(";
	 int i = 0 ;
	 for (Feature child : children) {
	 String ftName = child.getName() ;
	 XORcontent += ftName ;
	 if (i != children.size() - 1)
	 XORcontent += "|" ;
	 i++;
	 }
	 XORcontent += ")" ;
	
	 if (op instanceof Or)
	 XORcontent += "+";
	
	 sb.append("" + XORcontent + "");
	 }
	
	 }
	
	 sb.append(" ; \n");
	
	 for (Feature child : children) {
	 sb.append (treatFeature(child) + "");
	 }
	 }
	 */
	
	 return sb.toString() ;
	
	 }
	
	
	
	 private boolean isOptional(Feature ft) {
	 /*
	 String optional = ft.getOptional() ;
	 if (optional == null)
	 return false ;
	 return ft.getOptional().equals("true") ;
	 */
	 return false ;
	 }
	
	 public String toStringXMI(EObject eobject) throws IOException {
	
	 new FmleroStandaloneSetup().createInjectorAndDoEMFRegistration();
	 URI domainModelURI = URI.createURI("dummy:/foo.xmi");
	 if (domainModelURI.fileExtension() == null)
	 domainModelURI = domainModelURI.appendFileExtension("xmi") ;
	
	 XMIResource xmiRes = (XMIResource) new
	 XMIResourceFactoryImpl().createResource(domainModelURI);
	
	 xmiRes.getContents().add(eobject) ;
	
	
	 OutputStream outputStream = new ByteArrayOutputStream() ;
	
	 xmiRes.save(outputStream, null);
	
	
	 return outputStream.toString() ;
	
	 }
	
	 /**
	 * @param fmVariable
	 * @return an instance of Lero/S2T2 Feature Model (EObject)
	 */
	 public FeatureModel fmlToS2T2FM(FeatureModelVariable fmVariable) {
	
	 FMLtoS2T2 converter = new FMLtoS2T2(fmVariable);
	 return converter.process();
	
	 }
	
	 /**
	 * @param fmv
	 * @return string, XMI representation of S2T2 feature model
	 */
	 public String fmlToS2T2XMI(FeatureModelVariable fmv) {
	 FeatureModel eo = fmlToS2T2FM(fmv);
	 String xmiRepresentation;
	 try {
	 xmiRepresentation = toStringXMI(eo);
	 } catch (IOException e) {
	 FMLShell.getInstance().printError("Unable to convert feature model to S2T2 "
	 + e.getMessage());
	 return "" ;
	 }
	 return xmiRepresentation;
	
	 }
	
	
	
	
	
	 /**
	 * @param file S2T2 feature model file (.fmprimitives)
	 * @return FML feature model
	 * @throws FDUnsupportedModelException
	 * @throws FDModelErrorException
	 * @throws IOException
	 */
	 public FeatureModelVariable S2T2ToFMLFM(File file) {
	 S2T2toFML converter = new S2T2toFML();
	 String strFM = converter.parseFile(file);
	 String idFM = "tmp" + new Random().nextInt(100);
	 String fmlInstruction = "" + idFM + " = " + strFM + "\n" ;
	 FMLShell.getInstance().printDebugMessage(fmlInstruction);
	 FMLShell.getInstance().parse(fmlInstruction);
	 FeatureModelVariable fmv = null;
	 try {
	 fmv = (FeatureModelVariable)
	 FMLShell.getInstance().getCurrentEnv().getVariable(idFM);
	 } catch (VariableNotExistingException e) {
	 FMLShell.getInstance().printError("Unable to convert back the string representation of S2T2toFML "
	 + e);
	 } catch (VariableAmbigousConflictException e) {
	 FMLShell.getInstance().printError("(amb) Unable to convert back the string representation of S2T2toFML "
	 + e);
	 }
	 return fmv ;
	
	 }
	
	
	
	
}
//
//
//

