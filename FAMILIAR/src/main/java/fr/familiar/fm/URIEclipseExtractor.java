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
package fr.familiar.fm;

import java.net.URI;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;

public class URIEclipseExtractor {

	public static URI getWorkspacePath() {

		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		URI rootUri = root.getLocationURI();
		return rootUri;
	}

	public static URI getProjectPath(IFile iFile) {
		// IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		// IFolder folder = root.getFolder(iFile.getParent().getFullPath());
		IProject pr = iFile.getProject();
		URI uri = pr.getLocationURI();
		return uri; // iFile.getParent().getLocationURI();
		// return folder.getRawLocationURI(); //getLocationURI() ;
	}

}
