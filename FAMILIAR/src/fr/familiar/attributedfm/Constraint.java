/*
	This file is part of FaMaTS.

    FaMaTS is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    FaMaTS is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with FaMaTS.  If not, see <http://www.gnu.org/licenses/>.

 */
package fr.familiar.attributedfm;

import java.util.List;

import fr.familiar.attributedfm.util.Node;
import fr.familiar.attributedfm.util.Tree;

/**
 * It represents a complex constraint, it is used at Attributed questions. 
 * FaMa Support complex constraints between features and attributes.
 */
public abstract class Constraint {

	public abstract Tree<String> getAST();
	private String name;
	
	@Override
	public String toString() {
		Tree<String> tree = getAST();
		Node<String> root = tree.getRootElement();
		String res="";
		String tmp = inorderToString(root);
		res += tmp;		
		return res;
	}

	private String inorderToString(Node<String> root) {
		String res = "";
		int n = root.getNumberOfChildren();
		if (n > 0) {
			int size = n - 1;
			List<Node<String>> children = root.getChildren();
			for (int i = 0; i < size; i++) {
				Node<String> c = children.get(i);
				String aux = inorderToString(c);
				res += aux + " ";
			}
			res += root.getData() + " ";

			res += inorderToString(children.get(size));

		} else {
			res = root.getData();
		}
		return res.replace(" Attribute ", ".");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
