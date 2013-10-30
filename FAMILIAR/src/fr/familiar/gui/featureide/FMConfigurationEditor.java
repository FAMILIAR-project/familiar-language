/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for manIpulation and Automatic Reasoning) project (https://nyx.unice.fr/projects/familiar/).
 *
 * Copyright (C) 2010 University of Nice Sophia Antipolis, UMR CNRS 6070, I3S Laboratory
 *
 * FAMILIAR is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  (See file COPYING)  If not, see
 * <http://www.gnu.org/licenses/>.
 */
package fr.familiar.gui.featureide;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

import de.ovgu.featureide.fm.core.FMCorePlugin;
import de.ovgu.featureide.fm.core.configuration.Configuration;
import de.ovgu.featureide.fm.core.configuration.ConfigurationReader;
import de.ovgu.featureide.fm.ui.editors.configuration.ConfigurationEditor;

public class FMConfigurationEditor extends ConfigurationEditor {

	public static final String ID = "FAMILIAR.FMConfigurationEditor";

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		System.out.println("starting init ..........................");
		setSite(site);
		setInput(input);
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
		// file = (IFile) input.getAdapter(IFile.class);
		try {
			file = (IFile) input.getAdapter(IFile.class);
		} catch (SecurityException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} catch (IllegalArgumentException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		System.out.println("FMConfigurationEditor.init()");
		// String varName = file.getName().substring(0, (file.getName().length()
		// - 9));
		// Variable varList = null;
		// try {
		// varList = FMShell.getInstance().getCurrentEnv().getVariable(varName);
		// } catch (VariableNotExistingException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// } catch (VariableAmbigousConflictException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		//
		// if(varList != null){
		// FeatureModelVariable fmv = ((ConfigurationVariable)varList).getFmw();
		// FeatureIDEConverter fic = new FeatureIDEConverter(fmv);
		// featureModel = fic.transform();
		// configuration = new Configuration(featureModel, true);
		// }
		System.out.println("getting the registred var");
		try {
			System.out.println(getFileField().getName());
		} catch (SecurityException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IllegalArgumentException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (NoSuchFieldException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IllegalAccessException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		// configuration =
		// ConfigurationRegister.getConfiguration(file.getName());
		try {
			configuration = ConfigurationRegister.getConf(getFileField())
					.getConfiguration();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchFieldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		featureModel = configuration.getFeatureModel(); // ConfigurationRegister.getFeatureModel(file.getName());
		// configuration = new Configuration(featureModel, true);

		// FIXME: isPageModified field is not visible
		
		// IFeatureProject featureProject = CorePlugin.getFeatureProject(file);
		// featureModel = featureProject.getFeatureModel();
		// configuration = new Configuration(featureModel, true);
//		try {
//			// dirty = !new
//			// ConfigurationReader(configuration).readFromFile(getFileField());
//			
//			isPageModified = !new ConfigurationReader(configuration)
//					.readFromFile(getFileField()); // setIsPageModifiedField();
//			if (!isPageModified) {
//				Configuration c = new Configuration(featureModel);
//				new ConfigurationReader(c).readFromFile(getFileField());
//				// dirty = !c.valid();
//				isPageModified = !c.valid(); // setIsPageModifiedField(!c.valid());
//			}
//		} catch (Exception e) {
//			FMCorePlugin.getDefault().logError(e);
//		}
//		try {
//			getSite().getPage().addPartListener(iPartListener);
//		} catch (SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		
		try {
			setPartName(getFileField().getName());
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		featureModel.addListener(this);
		firePropertyChange(IEditorPart.PROP_DIRTY);
	}

	protected void setConfiguration() throws SecurityException,
			IllegalArgumentException, NoSuchFieldException,
			NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		// IFeatureProject featureProject = CorePlugin.getFeatureProject(file);
		// featureModel = featureProject.getFeatureModel();

		System.out
				.println("*******************************************************");
		featureModel = ConfigurationRegister.getFeatureModel(getFileField()
				.getName());
		// configuration = new Configuration(featureModel, true);

		String text = new MyConfigurationWriter(configuration)
				.writeIntoString();
		configuration = new Configuration(featureModel, true);
		try {
			new ConfigurationReader(configuration).readFromString(text);
		} catch (Exception e) {
			FMCorePlugin.getDefault().logError(e);
		}
		// dirty = true;
		//isPageModified = true; // setIsPageModifiedField(true);
		firePropertyChange(IEditorPart.PROP_DIRTY);
	}

	public void refresh() {
		// Mathieu: I have removed this code
		/*
		 * Class c = advancedConfigurationPage.getClass(); Field treeViewerField
		 * = c.getDeclaredField("viewer"); treeViewerField.setAccessible(true);
		 * TreeViewer viewer = (TreeViewer)
		 * treeViewerField.get(advancedConfigurationPage); viewer.refresh();
		 */
	}

	/**
	 * Methods that allows to access to private variables and methods of the
	 * super class
	 */

	public IFile getFileField() throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		return file;
		/*
		 * Class c = this.getClass(); Field fileField =
		 * c.getDeclaredField("file"); fileField.setAccessible(true); return
		 * (IFile)fileField.get(this);
		 */
	}

	/*
	 * public void setFileField(IFile f) throws SecurityException,
	 * NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
	 * Class c = this.getClass(); Field fileField = c.getDeclaredField("file");
	 * fileField.setAccessible(true); fileField.set(this, f); }
	 */

	/*
	 * public boolean getIsPageModifiedField() throws SecurityException,
	 * NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
	 * Class c = this.getClass(); Field dirtyField =
	 * c.getDeclaredField("isPageModified"); dirtyField.setAccessible(true);
	 * return (Boolean)dirtyField.get(this);
	 * 
	 * }
	 */

	/*
	 * public void setIsPageModifiedField(boolean b) throws SecurityException,
	 * NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
	 * Class c = this.getClass(); Field dirtyField =
	 * c.getDeclaredField("isPageModified"); dirtyField.setAccessible(true);
	 * dirtyField.set(this , b);
	 * 
	 * }
	 */

	/*
	 * public IPartListener getIPartListenerField() throws SecurityException,
	 * NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
	 * Class c = this.getClass(); Field iPartListenerField =
	 * c.getDeclaredField("iPartListener");
	 * iPartListenerField.setAccessible(true); return
	 * (IPartListener)iPartListenerField.get(this); }
	 */
}
