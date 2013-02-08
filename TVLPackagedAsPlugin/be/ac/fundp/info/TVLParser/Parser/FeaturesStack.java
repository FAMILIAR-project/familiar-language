package be.ac.fundp.info.TVLParser.Parser;

import java.util.Vector;

import be.ac.fundp.info.TVLParser.Parser.FeaturesStack;

public class FeaturesStack implements Cloneable {
	
	Vector<String> stack;
	
	public FeaturesStack() {
		stack = new Vector<String>();
	}
	
	public boolean isEmpty() {
		if (stack.size() == 0)
			return true;
		else
			return false;
	}
	
	public void push(String featureID) {
		stack.add(featureID);
	}
	
	public String pop() {
		String featureID = stack.lastElement();
		stack.remove(stack.size()-1);
		return featureID;
	}
	
	public String toPath() {
		if (!(this.isEmpty())) {
			String path = stack.get(0);
			int i = 1;
			while (i <= stack.size()-1) {
				path = path.concat("."+stack.get(i));
				i++;
			}
			return path;
		}
		else {
			return null;
		}
	}
	
	public String toArrowPath() {
		if (!(this.isEmpty())) {
			String path = stack.get(0);
			int i = 1;
			while (i <= stack.size()-1) {
				path = path.concat(" -> "+stack.get(i));
				i++;
			}
			return path;
		}
		else {
			return null;
		}
	}
	
	public FeaturesStack clone() {
		FeaturesStack clonedFeaturesStack = new FeaturesStack();
		int i = 0;
		while (i <= this.stack.size()-1) {
			clonedFeaturesStack.stack.add(this.stack.get(i));
			i++;
		}
		return clonedFeaturesStack;
	}
	
	public String getLast() {
		return this.stack.get(this.stack.size()-1);
	}

}
