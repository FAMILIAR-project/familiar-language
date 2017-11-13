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

package fr.familiar.experimental;

import gsd.synthesis.FeatureNode;

import java.util.Set;

/**
 * TODO: I simply don't understand this class and why we implement it
 * FIXME: should be removed
 * @author macher1
 *
 */
@Deprecated
public class FeatureNodeUtil {

	public static boolean hasChildren(FeatureNode<String> feature) {
		// TODO Auto-generated method stub
		return false;
	}

	public static FeatureNode<String> getParent(FeatureNode<String> feature) {
		// TODO Auto-generated method stub
		return null;
	}

	public static boolean isMandatorySet(FeatureNode<String> feature) {
		// TODO Auto-generated method stub
		return false;
	}

	public static Set<FeatureNode<String>> getChildren(
			FeatureNode<String> feature) {
		// TODO Auto-generated method stub
		return null;
	}

	public static boolean isAnd(FeatureNode<String> feature) {
		// TODO Auto-generated method stub
		return false;
	}

	public static int getChildrenCount(FeatureNode<String> parent) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static boolean isConcrete(FeatureNode<String> child) {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean isAbstract(FeatureNode<String> parent) {
		// TODO Auto-generated method stub
		return false;
	}

}
