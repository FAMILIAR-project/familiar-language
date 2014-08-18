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
package fr.familiar.variable.featureide;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.xtext.example.mydsl.fML.AutoConfMode;
import org.xtext.example.mydsl.fML.OpSelection;

import de.ovgu.featureide.fm.core.Feature;
import de.ovgu.featureide.fm.core.configuration.Configuration;
import de.ovgu.featureide.fm.core.configuration.Selection;
import de.ovgu.featureide.fm.core.configuration.SelectionNotPossibleException;
import de.ovgu.featureide.fm.ui.editors.configuration.ConfigurationEditor;
import fr.familiar.fm.converter.FeatureModelUtil;
import fr.familiar.fm.featureide.FMLtoFeatureIDE;
import fr.familiar.fm.featureide.MinMaxConfiguration;
import fr.familiar.gui.featureide.ConfigurationRegister;
import fr.familiar.gui.featureide.FMConfigurationEditor;
import fr.familiar.interpreter.FMLShell;
import fr.familiar.interpreter.NSFactory;
import fr.familiar.parser.NameSpace;
import fr.familiar.utils.URIUtils;
import fr.familiar.variable.ConfigurationVariable;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureVariable;
import fr.familiar.variable.RType;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;
import fr.familiar.variable.VariableIdentifier;

public class ConfigurationVariableFeatureIDEImpl extends ConfigurationVariable {
	
	
	private static Logger _LOGGER = Logger.getLogger(ConfigurationVariableFeatureIDEImpl.class);


	/**
	 * Internal representation of configuration We simply reuse FeatureIDE
	 * facilities
	 */
	private de.ovgu.featureide.fm.core.configuration.Configuration _configuration;

	/**
	 * The feature model variable associated to the configuration
	 */
	private FeatureModelVariable _fmv;

	public ConfigurationVariableFeatureIDEImpl(FeatureModelVariable fmv, String name) {
		this(fmv, name, NSFactory.mkEmpty());
	}

	public ConfigurationVariableFeatureIDEImpl(FeatureModelVariable fmv, String name, NameSpace ns) {
		this (mkConfiguration(fmv), fmv, name, ns);

	}

	private ConfigurationVariableFeatureIDEImpl(Configuration configuration, FeatureModelVariable fmv, String name, NameSpace ns) {
		this._configuration = configuration ; 
		this.setFmw(fmv);
		this.name = name;
		this.ns = ns;
		this.vid = new VariableIdentifier(name, ns);
	}

	private static Configuration mkConfiguration(FeatureModelVariable fmv) {
		// create a new configuration with the new FM (in FeatureIDE)
		Configuration c = new Configuration(new FMLtoFeatureIDE(fmv).convert(), true);
		return c ; 
	}

	@Override
	public String getSpecificValue() {
		Set<String> sel = getSelected();
		Set<String> desel = getDeselected();
		return "selected: " + sel.toString() + " \t deselected: "
				+ desel.toString();
		// return features.toString();
	}

	@Override
	public RType getRType() {
		return RType.CONFIGURATION;
	}

	@Override
	public Variable copy() {
		return new ConfigurationVariableFeatureIDEImpl(_configuration, _fmv, name, ns); // TODO
	}

	@Override
	public void setValue(Variable vari) {
		if (vari instanceof ConfigurationVariableFeatureIDEImpl) {
			ConfigurationVariableFeatureIDEImpl cw = (ConfigurationVariableFeatureIDEImpl) vari;
			setConfiguration(cw.getConfiguration());
			return;
		}
		FMLShell.getInstance().setError("Setting value type is not correct");

	}

	/**
	 * @return the configuration
	 */
	public Configuration getConfiguration() {
		return _configuration;
	}

	/**
	 * @param configuration
	 *            the configuration to set
	 */
	public void setConfiguration(Configuration configuration) {
		this._configuration = configuration;
	}

	@Override
	public boolean applySelection(String strFT, OpSelection op) {
		boolean validSelection = (_configuration.getFeatureModel().getFeature(strFT) != null);
		if (validSelection)
			try {
			_configuration.setManual(strFT, _toSelection(op));
			return true ; 
			}
			catch (SelectionNotPossibleException e) {
				return false ; 
			}
		return false ; 
	}



	private Selection _toSelection(OpSelection op) {
		if (op == OpSelection.SELECT)
			return Selection.SELECTED ; 
		else if (op == OpSelection.DESELECT)
			return Selection.UNSELECTED ; 
		else  
			return Selection.UNDEFINED ; 
	}

	public Set<String> getSelected()  {
		Set<String> res = new HashSet<String>();
		invokeUpdateManualUndefinedValues(_configuration);
		// _configuration.updateManualUndefinedValues();
		for (Feature ft : _configuration.getSelectedFeatures()) {
			String ftName = ft.getName() ;
			if (!FeatureModelUtil.isSynthetic(ftName))
				res.add(ftName);
		}
		return res;
	}

	@Override
	public Set<String> getDeselected() {
		Set<String> res = new HashSet<String>();
		invokeUpdateManualUndefinedValues(_configuration);
		// _configuration.updateManualUndefinedValues();
		for (Feature ft : _configuration.getUnSelectedFeatures()) {
			String ftName = ft.getName() ;
			if (!FeatureModelUtil.isSynthetic(ftName))
				res.add(ftName);
		}
		return res;
	}

	@Override
	public Set<String> getUnselected() {
		Set<String> res = new HashSet<String>();
		Set<String> sel = getSelected();
		Set<String> desel = getDeselected();
		Collection<Feature> ftures = _configuration.getFeatureModel()
				.getFeatures();
		for (Feature ft : ftures) {
			String s = ft.getName();
			boolean inSel = false;
			for (String strSel : sel) {
				if (s.equals(strSel))
					inSel = true;
			}

			for (String strDeSel : desel) {
				if (s.equals(strDeSel))
					inSel = true;
			}

			if (!inSel) // neither in sel or desel
				res.add(s);
		}
		return res;
	}

	public void setFmw(FeatureModelVariable fmw) {
		this._fmv = fmw;
	}

	public FeatureModelVariable getFmv() {
		return _fmv;
	}

	
	@Override
	public void autoselect(AutoConfMode mode) {
		
		// FIXME

		// at the end, the configuration is valid and complete
		_LOGGER.debug(mode.name());

		MinMaxConfiguration minc = new MinMaxConfiguration(_configuration, mode);
		// minc.updateManualUndefinedValues();
		invokeUpdateManualUndefinedValues(minc);
		_LOGGER.debug(
				"### deselected:" + minc.getUnSelectedFeatures());
		for (Feature ft : minc.getUnSelectedFeatures()) {
			_configuration.setManual(ft.getName(), Selection.UNSELECTED);
		}
		_LOGGER.debug(
				"### selected:" + minc.getSelectedFeatures());
		for (Feature ft : minc.getSelectedFeatures()) {
			_configuration.setManual(ft.getName(), Selection.SELECTED);
		}

		if (mode == AutoConfMode.MIN)
			for (String strFT : getUnselected()) { // undefined in FeatureIDE
				_configuration.setManual(strFT, Selection.UNSELECTED);
			}

	}

	/**
	 * display graphically the content of the configuration variable, i.e., the
	 * configuration in FeatureIDE editor
	 */
	@Override
	public void gdisplay() {

		// save as a FeatureIDE file
		de.ovgu.featureide.fm.core.configuration.ConfigurationWriter writerFmIDE = new de.ovgu.featureide.fm.core.configuration.ConfigurationWriter(
				_configuration);

		ConfigurationRegister.register(getIdentifier() + ".equation", this);

		try {

			if (!ConfigurationRegister.isRegistred(this))
				;
			ConfigurationRegister.register(this);
			// IFile lfile = getSerializationFile();
			// System.out.println("Ifile (conf): " + lfile);
			// System.out.println("Ifile name (conf): " + lfile.getName());
			IFile lfile = ConfigurationRegister.getFile(this);
			writerFmIDE.saveToFile(lfile);

			// now we have serialized, we want to open

			IEditorInput ii = new FileEditorInput(lfile);

			_LOGGER.debug("IEditor: (conf) " + ii);
			try {

				IWorkbenchPage page = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage();
				_LOGGER.debug(
						"getting active page (conf) " + page);
				_LOGGER.debug(
						"opening the editor (conf) ");
				// IEditorPart iep = page.openEditor(ii,
				// ConfigurationEditor.ID);
				IEditorPart iep = page.openEditor(ii, FMConfigurationEditor.ID);
				_LOGGER.debug(
						"ConfigurationVariable.gdisplay() " + iep);
				if (iep instanceof FMConfigurationEditor)// ++++
					((FMConfigurationEditor) iep).refresh();// ++++
				// IEditorPart iep = page.findEditor(ii);// TODO a voire ...
				if (iep instanceof ConfigurationEditor) {
					_LOGGER.debug(
							"configuration display");
					((ConfigurationEditor) iep).dispose();

				}
				// page.openEditor(iei, "org.eclipse.ui.DefaultTextEditor");
			} catch (PartInitException e) {
				e.printStackTrace();
				_LOGGER.debug(
						"the exception is catched");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean changeFeatureModel(FeatureModelVariable fmv) {

		// first of all
		setFmw(fmv);

		
		// encapsulate for reasoning about
		ConfigurationVariableFeatureIDEImpl cv = new ConfigurationVariableFeatureIDEImpl(fmv, null);

		for (String selected : getSelected())
			if (!cv.applySelection(selected, OpSelection.SELECT)) {
				_LOGGER.debug(
						"Unable to apply the selection (FM has changed!) with feature: "
								+ selected);
				// e.g., the feature does not exist anymore
			}

		for (String deselected : getDeselected())
			if (!cv.applySelection(deselected, OpSelection.DESELECT)) {
				_LOGGER.debug(
						"Unable to apply the deselection (FM has changed!) with feature: "
								+ deselected);
				// e.g., the feature does not exist anymore
			}

		setConfiguration(cv.getConfiguration());

		return true;
	}

	private IFile getSerializationFile() {
		String directory = FMLShell.getInstance().getTemporaryPath();
		String name = getIdentifier();
		String filename = directory + name + ".equation";
		URI uri = URI.create(filename);
		IFile lfile = URIUtils.getIFileFromURI(uri);
		return lfile;
	}

	
	
	@Override
	public boolean isComplete() {
		SetVariable sw = getCurrentFeatures(OpSelection.UNSELECT);
		boolean bsw = sw.getVars().isEmpty();
		if (!bsw) {
			for (Variable v : sw.getVars()) {
				_LOGGER.debug(v.getValue());
			}
		}
		return bsw;
	}

	
	
	/**
	 * @param cw
	 *            the configuration variable to consider
	 * @param ops
	 *            the kinds of features we want to get : selected, deselected,
	 *            unselected
	 * @return the set of features that are either selected, deselected or
	 *         unselected
	 */
	public SetVariable getCurrentFeatures(OpSelection ops) {

		Set<Variable> vars = new HashSet<Variable>();
		Set<String> ftures = null;

		if (ops == OpSelection.SELECT)
			ftures = getSelected();
		else if (ops == OpSelection.DESELECT)
			ftures = getDeselected();
		else if (ops == OpSelection.UNSELECT)
			ftures = getUnselected();
		else
			FMLShell.getInstance().printTODO();

		for (String s : ftures) {
			FeatureVariable ft = new FeatureVariable(null, s, getFmv()); // anonymous
																			// variable
			vars.add(ft);
		}

		return new SetVariable(vars, null);

	}


	

	private void invokeUpdateManualUndefinedValues(Configuration configuration) {
		try {
			Class c = configuration.getClass();
			Method update = c.getDeclaredMethod("updateManualUndefinedValues");
			update.setAccessible(true);
			update.invoke(configuration);
		}
		catch (Exception e) {
			FMLShell.getInstance().printError("Unable to perform the configuration: " + e.getLocalizedMessage());
		}
		
	}

	@Override
	public boolean isValid() {
		return _configuration.isValid() ; 
	}

	@Override
	public Set<String> getConflicts() {
		FMLShell.getInstance().printTODO("Not supported by this implementation");
		return new HashSet<String>() ;
	}

}
