/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project.
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.familiar.operations;

import fr.familiar.experimental.KSynthesisConfiguration;

/**
 * @author macher1
 *
 */
public class DefaultKSynthesisConfiguration extends KSynthesisConfiguration {
	
	private static final boolean _DEFAULT_ADDING_CROSS_TREE_CONSTRAINTS = true ; 
	private static final boolean _DEFAULT_OR_GROUPS_SUPPORT = true ; 

	@Override
	public boolean isAddingCrossTreeConstraints() {
		return _DEFAULT_ADDING_CROSS_TREE_CONSTRAINTS ; 
	}

	@Override
	public boolean hasOrGroupSupport() {
		return _DEFAULT_OR_GROUPS_SUPPORT ; 
	}

}
