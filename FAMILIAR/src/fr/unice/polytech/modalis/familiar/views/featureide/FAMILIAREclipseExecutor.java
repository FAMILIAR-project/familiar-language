package fr.unice.polytech.modalis.familiar.views.featureide;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;

public class FAMILIAREclipseExecutor implements IObjectActionDelegate {

	private Shell shell;
	
	/**
	 * File selected by the user
	 */
	private ISelection _selection;
	
	/**
	 * Constructor for Action1.
	 */
	public FAMILIAREclipseExecutor() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run (IAction action) {
	
		
		if ((_selection instanceof IStructuredSelection)) {


            IStructuredSelection structured = (IStructuredSelection) _selection;
            final Object object = structured.getFirstElement();

            // if the selection is a valid project file, get its name.
            if (object instanceof IFile) {
                
            	final IFile fmlFile = (IFile) object;
               

                // Call Selection
                new Job("FAMILIAR execution") {
    				public IStatus run(IProgressMonitor pm) {
    					
    					 FMLShell.executeEclipseScript(fmlFile, false); // not step by step

    					return Status.OK_STATUS;
    				}
    			}.schedule(); 
				
            } else {
                throw new IllegalArgumentException(
                        "Object is not Instance of IFile");
            }
        } else {
            throw new IllegalArgumentException(
                    "Selection is not an IStructuredSelection");
        }
		
		
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		_selection = selection;
	}

}
