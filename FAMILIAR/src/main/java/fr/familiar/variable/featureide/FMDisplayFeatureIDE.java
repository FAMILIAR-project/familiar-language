/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project (2010-2017)
 * http://familiar-project.github.com/
 *
 * FAMILIAR is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */

package fr.familiar.variable.featureide;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.progress.UIJob;

import de.ovgu.featureide.fm.ui.editors.FeatureModelEditor;
import fr.familiar.gui.featureide.FM_Familiar_FeatureIDE;
import fr.familiar.interpreter.FMLShell;
import fr.familiar.variable.FeatureModelVariable;
/**
 * @author macher1
 *
 */
public class FMDisplayFeatureIDE {
	
	protected Logger _LOGGER = Logger.getLogger(FMDisplayFeatureIDE.class);

	private FeatureModelVariable _fmv;

	public FMDisplayFeatureIDE(FeatureModelVariable fmv) {
		_fmv = fmv ; 
	}

	public void perform() {
		assert (FMLShell.getInstance().isEclipseBased());

		if (!FM_Familiar_FeatureIDE.isExists(_fmv)) {
			FM_Familiar_FeatureIDE.register(_fmv);
		}
		// de.ovgu.featureide.fm.core.io.guidsl.FeatureModelWriter writerFmIDE =
		// new FeatureModelWriter(FM_Familiar_FeatureIDE.updateFM(this));

		if (!FMLShell.getInstance().getPreference().isGraphicalDisplay())
			return;

		try {

			final IFile lfile = FM_Familiar_FeatureIDE.get(_fmv);
			// now we have serialized, we want to open

			
			_LOGGER.debug(
					"file (FeatureIDE)=" + lfile);

			
			
			
			UIJob analyzingJob = new UIJob(
					"Opening feature model with FeatureIDE") {
				@Override
				public IStatus runInUIThread(IProgressMonitor monitor) {
					
					IFileEditorInput editorInput = new FileEditorInput(lfile);
					IWorkbenchWindow window = PlatformUI.getWorkbench()
							.getActiveWorkbenchWindow();
					
					if (window == null) 
						return new Status(IStatus.ERROR, FeatureModelEditor.ID, "Unable to get active workbench window") ; 
					
					IWorkbenchPage page = window.getActivePage();
					if (page == null)
						return new Status(IStatus.ERROR, FeatureModelEditor.ID, "Unable to get active page") ; 
					
					
					_LOGGER.debug(
							"Opening the editor (FeatureIDE): active page="
									+ page);
					try {
						IDE.openEditor(page, editorInput, FeatureModelEditor.ID);						
					} catch (PartInitException e) {
						e.printStackTrace() ; 
						_LOGGER.debug(
								"#STRANGE: An exception has been catched during the FeatureIDE editor opening: e="
										+ e);
						
					}
					return Status.OK_STATUS;
				}
			};
			analyzingJob.setPriority(Job.DECORATE);
			analyzingJob.schedule();

			
		} catch (Exception e) {
			e.printStackTrace();
			_LOGGER.warn("#STRANGE: An exception has been catched during the FeatureIDE editor opening (2): e="
									+ e);
		}

	}

}
