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
/*
 * Created on 15-Mar-2005
 */
package fr.familiar.attributedfm;

import fr.familiar.attributedfm.domain.KeyWords;
import fr.familiar.attributedfm.util.Node;
import fr.familiar.attributedfm.util.Tree;

/**
 * @author trinidad, Manuel Nieto Ucl
 */
public class RequiresDependency extends Dependency {
	
	public RequiresDependency(String name){
		super(name);
	}
	
	public RequiresDependency(Feature origin, Feature destination){		
		super(origin, destination);
	}
	
	public RequiresDependency(String name, Feature origin, Feature destination){
		super(name, origin, destination);
	}
	
	public String toString() {
		String res = origin.getName() + " requires " + destination.getName();
		return res;
	}

	@Override
	public Tree<String> getAST() {
		Node<String> r = new Node<String>(KeyWords.REQUIRES);
		Node<String> o = new Node<String>(this.origin.getName());
		Node<String> d = new Node<String>(this.destination.getName());
		r.addChild(o);
		r.addChild(d);
		Tree<String> res = new Tree<String>(r);
		return res;
	}
}
